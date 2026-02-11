package com.EstebanChun.Ejemplo.Controller;

import com.EstebanChun.Ejemplo.Entity.Repuesto;
import com.EstebanChun.Ejemplo.Service.RepuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuesto")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) { this.repuestoService = repuestoService; }

    @GetMapping
    public List<Repuesto> getAllRepuesto(){return repuestoService.getAllRepuesto(); }

    @PostMapping
    public ResponseEntity<Object> createRepuesto(@Valid @RequestBody Repuesto repuesto){
        try {
            Repuesto createRepuesto = repuestoService.saveRepuesto(repuesto);
            return new ResponseEntity<>(createRepuesto, HttpStatus.CREATED);
        }catch (RuntimeException e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteRepuesto(@PathVariable Integer id){
        try {
            if(repuestoService.getRepuestoById(id) == null){
                return ResponseEntity.status(404).body("El Repuesto no existe");
            }

            repuestoService.deleteRepuesto(id);
            return ResponseEntity.status(202).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el repuesto");
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateRepuesto(@PathVariable Integer id, @RequestBody Repuesto repuesto){
        try {
            Repuesto actualizado = repuestoService.updateRepuesto(id, repuesto);
            return  ResponseEntity.ok(actualizado);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
