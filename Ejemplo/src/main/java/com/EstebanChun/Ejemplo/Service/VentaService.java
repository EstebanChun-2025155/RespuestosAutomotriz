package com.EstebanChun.Ejemplo.Service;

import com.EstebanChun.Ejemplo.Entity.Venta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VentaService {
    List<Venta> getAllVenta();
    Venta getVentaById(Integer id);
    Venta saveVenta(Venta venta) throws RuntimeException;
    Venta updateVenta(Integer id, Venta venta);
    void deleteVenta(Integer id);
}
