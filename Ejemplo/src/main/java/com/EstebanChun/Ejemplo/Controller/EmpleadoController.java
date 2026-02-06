package com.EstebanChun.Ejemplo.Controller;

import com.EstebanChun.Ejemplo.Model.Empleado;
import com.EstebanChun.Ejemplo.Service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/empleados")

public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @PostMapping
    public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Empleado empleado) {
        try {
            Empleado createdEmpleado = empleadoService.saveEmpleado(empleado);
            return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmpleado(@PathVariable Integer id) {
        try {
            if (empleadoService.getEmpleadoById(id) == null) {
                return ResponseEntity.status(404).body("El empleado no existe");
            }

            empleadoService.deleteEmpleado(id);
            return ResponseEntity.status(204).build();

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el Empleado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {

        try {
            Empleado actualizado = empleadoService.updateEmpleado(id, empleado);
            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
