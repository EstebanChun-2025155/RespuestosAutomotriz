package com.EstebanChun.Ejemplo.Controller;

import com.EstebanChun.Ejemplo.Entity.Venta;
import com.EstebanChun.Ejemplo.Service.RepuestoService;
import com.EstebanChun.Ejemplo.Service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
public class VentaController {
    private final VentaService ventaService;

    public VentaController(VentaService ventaService, RepuestoService repuestoService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> getAllVenta(){ return ventaService.getAllVenta(); }


    @PostMapping()
    public ResponseEntity<Object> createVenta(@Valid @RequestBody Venta venta) {
        try {
            Venta createVenta = ventaService.saveVenta(venta);
            return  new ResponseEntity<>(createVenta, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteVenta(@PathVariable Integer id){
        try {
            if(ventaService.getVentaById(id) == null) {
                return ResponseEntity.status(404).body("Esta venta no existe");
            }
            ventaService.deleteVenta(id);
            return ResponseEntity.status(202).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar la venta");
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateVenta(@PathVariable Integer id, @RequestBody Venta venta){
        try {
            Venta actualizado = ventaService.updateVenta(id, venta);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
