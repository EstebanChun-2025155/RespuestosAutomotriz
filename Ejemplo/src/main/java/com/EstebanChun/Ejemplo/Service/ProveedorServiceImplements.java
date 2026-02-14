package com.EstebanChun.Ejemplo.Service;

import com.EstebanChun.Ejemplo.Entity.Proveedor;
import com.EstebanChun.Ejemplo.Respository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImplements implements ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImplements(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores() {return proveedorRepository.findAll();}

    @Override
    public Proveedor getProveedorById(Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException {
        try{
            if(proveedor == null
                || proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().isBlank()
                || proveedor.getDireccion() == null || proveedor.getDireccion().isBlank()
                || proveedor.getEmailProveedor() == null || proveedor.getEmailProveedor().isBlank()){
                throw new IllegalArgumentException("Los espacios deben estar llenos ");
            }

            if (!(proveedor.getEmailProveedor().contains("@gmail.com") ||
                    proveedor.getEmailProveedor().contains("@hotmail.com") ||
                    proveedor.getEmailProveedor().contains("@outlook.com") ||
                    proveedor.getEmailProveedor().contains("@yahoo.com"))){
                throw new IllegalStateException ("El email solo es valido bajo los campos de @gmail.com, @hotmail.com, @outlook.com, @yahoo.com");
            }
            if (proveedorRepository.existsByNombreProveedorAndTelefonoProveedorAndDireccionAndEmailProveedor(
                    proveedor.getNombreProveedor(),
                    proveedor.getTelefonoProveedor(),
                    proveedor.getDireccion(),
                    proveedor.getEmailProveedor())){
                throw new RuntimeException("Ya existe un proveedor con esos datos");
            }
            return proveedorRepository.save(proveedor);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedor) {
        Proveedor existingProveedor = proveedorRepository.findById(id).orElseThrow(() -> new RuntimeException("El proveedor no existe"));

        if (proveedor == null
            || proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().isBlank()
            || proveedor.getEmailProveedor() == null || proveedor.getEmailProveedor().isBlank()
            ||proveedor.getDireccion() == null || proveedor.getDireccion().isBlank()){
            throw new IllegalArgumentException("Los campos deben de estar llenos");
        }

        if (!(proveedor.getEmailProveedor().contains("@gmail.com")
                ||proveedor.getEmailProveedor().contains("@hotmail.com")
                || proveedor.getEmailProveedor().contains("@outlook.com")
                || proveedor.getEmailProveedor().contains("@yahoo.com"))){
            throw new IllegalArgumentException("El email solo es valido bajo los campos de @gmail.com, @hotmail.com, @outlook.com, @yahoo.com");
        }

        if (proveedorRepository.existsByNombreProveedorAndTelefonoProveedorAndDireccionAndEmailProveedor(
                proveedor.getNombreProveedor(),
                proveedor.getTelefonoProveedor(),
                proveedor.getDireccion(),
                proveedor.getEmailProveedor())){
            throw new RuntimeException("Ya existe un proveedor con esos datos");
        }

        existingProveedor.setNombreProveedor(proveedor.getNombreProveedor());
        existingProveedor.setEmailProveedor(proveedor.getEmailProveedor());
        existingProveedor.setDireccion(proveedor.getDireccion());
        existingProveedor.setTelefonoProveedor(proveedor.getTelefonoProveedor());

        return proveedorRepository.save(existingProveedor);
    }

    @Override
    public void deleteProveedor(Integer id) { proveedorRepository.deleteById(id);}
}
