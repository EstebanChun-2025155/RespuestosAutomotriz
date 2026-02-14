package com.EstebanChun.Ejemplo.Service;

import com.EstebanChun.Ejemplo.Entity.Repuesto;
import com.EstebanChun.Ejemplo.Respository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepuestoServiceImplements implements RepuestoService {
    private final RepuestoRepository repuestoRepository;

    public RepuestoServiceImplements(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public List<Repuesto> getAllRepuesto() { return repuestoRepository.findAll(); }

    @Override
    public Repuesto getRepuestoById(Integer id) {
        return repuestoRepository.findById(id).orElse(null);
    }

    @Override
    public Repuesto saveRepuesto(Repuesto repuesto) throws RuntimeException {
        try {
            if (repuesto == null
                || repuesto.getNombreRepuesto() == null || repuesto.getNombreRepuesto().isBlank()
                || repuesto.getCategoriaRepuesto() == null || repuesto.getCategoriaRepuesto().isBlank()
                || repuesto.getPrecioCompra() == null || repuesto.getPrecioCompra() <= 0
                || repuesto.getPrecioVenta() == null || repuesto.getPrecioVenta() <= 0
                || repuesto.getIdProveedor() == null || repuesto.getIdProveedor() <= 0){

                throw new RuntimeException("Lo campos deben de estar llenos, asi como el precio de venta/compra y el id deben de ser mayores a 0");
            }

            if (repuestoRepository.existsByNombreRepuestoAndCategoriaRepuestoAndPrecioCompraAndPrecioVentaAndIdProveedor(
                    repuesto.getNombreRepuesto(),
                    repuesto.getCategoriaRepuesto(),
                    repuesto.getPrecioCompra(),
                    repuesto.getPrecioVenta(),
                    repuesto.getIdProveedor())){
                throw new IllegalStateException("Ya existe un repuesto con esos datos");
            }
            return repuestoRepository.save(repuesto);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Repuesto updateRepuesto(Integer id, Repuesto repuesto) {
        Repuesto existingRepuesto = repuestoRepository.findById(id).orElseThrow(() -> new RuntimeException("El Repuesto no existe"));

        if ( repuesto == null
                || repuesto.getNombreRepuesto() == null || repuesto.getNombreRepuesto().isBlank()
                || repuesto.getCategoriaRepuesto() == null || repuesto.getCategoriaRepuesto().isBlank()
                || repuesto.getPrecioCompra() == null || repuesto.getPrecioCompra() <= 0
                || repuesto.getPrecioVenta() == null || repuesto.getPrecioVenta() <= 0
                || repuesto.getIdProveedor() == null || repuesto.getIdProveedor() <= 0){

            throw new RuntimeException("Lo campos deben de estar llenos, asi como el precio de venta/compra y el id deben de ser mayores a 0");
        }

        if (repuestoRepository.existsByNombreRepuestoAndCategoriaRepuestoAndPrecioCompraAndPrecioVentaAndIdProveedor(
                repuesto.getNombreRepuesto(),
                repuesto.getCategoriaRepuesto(),
                repuesto.getPrecioCompra(),
                repuesto.getPrecioVenta(),
                repuesto.getIdProveedor())){
            throw new IllegalStateException("Ya existe un repuesto con esos datos");
        }

        existingRepuesto.setNombreRepuesto(repuesto.getNombreRepuesto());
        existingRepuesto.setCategoriaRepuesto(repuesto.getCategoriaRepuesto());
        existingRepuesto.setPrecioCompra(repuesto.getPrecioCompra());
        existingRepuesto.setPrecioVenta(repuesto.getPrecioVenta());

        return  repuestoRepository.save(existingRepuesto);
    }

    @Override
    public void deleteRepuesto(Integer id) {
        if (!repuestoRepository.existsById(id)){
        throw new RuntimeException("El repuesto no existe");
        }
    }
}
