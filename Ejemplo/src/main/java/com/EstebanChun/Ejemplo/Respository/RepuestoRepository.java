package com.EstebanChun.Ejemplo.Respository;

import com.EstebanChun.Ejemplo.Entity.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {
    boolean existsByNombreRepuestoAndCategoriaRepuestoAndPrecioCompraAndPrecioVentaAndIdProveedor(
            String nombreRepuesto,
            String categoriaRepuesto,
            Double precioCompra,
            Double precioventa,
            Integer idProveedor
    );
}
