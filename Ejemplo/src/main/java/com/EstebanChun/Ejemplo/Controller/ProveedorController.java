package com.EstebanChun.Ejemplo.Controller;

import com.EstebanChun.Ejemplo.Entity.Proveedor;
import com.EstebanChun.Ejemplo.Service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<Proveedor> getAllProveedor(){ return proveedorService.getAllProveedores();}

    @PostMapping
    public ResponseEntity<Object> createProveedor(@Valid @RequestBody Proveedor proveedor){
        try {
            Proveedor createProveedor = proveedorService.saveProveedor(proveedor);
            return new ResponseEntity<>(createProveedor, HttpStatus.CREATED);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProveedor(@PathVariable Integer id){
        try {
            if (proveedorService.getProveedorById(id) == null ){
                return ResponseEntity.status(404).body("El Proveedor no existe");
            }

            proveedorService.deleteProveedor(id);
            return ResponseEntity.status(204).build();

        }catch (Exception e){
            return  ResponseEntity.badRequest().body("Error al eliminar el Proveedor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor){
        try {
            Proveedor actualizado = proveedorService.updateProveedor(id, proveedor);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
