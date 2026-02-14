package com.EstebanChun.Ejemplo.Service;

import com.EstebanChun.Ejemplo.Entity.Venta;
import com.EstebanChun.Ejemplo.Respository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImplements implements VentaService{
    private final VentaRepository ventaRepository;

    public VentaServiceImplements(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> getAllVenta() { return ventaRepository.findAll(); }

    @Override
    public Venta getVentaById(Integer id) { return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta saveVenta(Venta venta) throws RuntimeException {
        try {
            if (venta == null
                ||venta.getFechaVenta() == null
                ||venta.getCantidad() == null || venta.getCantidad() <=  0
                ||venta.getTotal() == null || venta.getTotal() <= 0
                ||venta.getIdEmpleado() == null || venta.getIdEmpleado() <= 0
                ||venta.getIdRepuesto() == null || venta.getIdRepuesto() <=  0){

                throw new RuntimeException("Los campos deben estar llenos y ser mayores a 0");
            }

            if (ventaRepository.existsByFechaVentaAndCantidadAndTotalAndIdEmpleadoAndIdRepuesto(
                    venta.getFechaVenta(),
                    venta.getCantidad(),
                    venta.getTotal(),
                    venta.getIdEmpleado(),
                    venta.getIdRepuesto())){
                throw new IllegalStateException("Ya existe una venta hecha con estos datos");
            }
            return ventaRepository.save(venta);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Venta updateVenta(Integer id, Venta venta) {
        Venta existingVenta = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Esta venta no existe"));

        existingVenta.setFechaVenta(venta.getFechaVenta());
        existingVenta.setCantidad(venta.getCantidad());
        existingVenta.setTotal(venta.getTotal());
        existingVenta.setIdEmpleado(venta.getIdEmpleado());
        existingVenta.setIdRepuesto(venta.getIdRepuesto());

        return ventaRepository.save(existingVenta);
    }

    @Override
    public void deleteVenta(Integer id) {
        if (!ventaRepository.existsById(id)){
            throw new RuntimeException("Esta venta no existe");
        }
        ventaRepository.deleteById(id);
    }
}
