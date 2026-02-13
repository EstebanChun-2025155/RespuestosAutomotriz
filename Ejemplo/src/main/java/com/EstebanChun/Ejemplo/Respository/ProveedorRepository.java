package com.EstebanChun.Ejemplo.Respository;

import com.EstebanChun.Ejemplo.Entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    boolean existsByNombreProveedorAndTelefonoAndDireccionAndEmailProveedor(
            String nombreProveedor,
            Integer telefono,
            String direccion,
            String emailProveedor
    );
}
