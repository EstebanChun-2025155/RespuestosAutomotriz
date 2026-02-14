package com.EstebanChun.Ejemplo.Respository;

import com.EstebanChun.Ejemplo.Entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    boolean existsByFechaVentaAndCantidadAndTotalAndIdEmpleadoAndIdRepuesto(
        Date fechaVenta,
        Integer cantidad,
        Double total,
        Integer idEmpleado,
        Integer idRepuesto
    );
}
