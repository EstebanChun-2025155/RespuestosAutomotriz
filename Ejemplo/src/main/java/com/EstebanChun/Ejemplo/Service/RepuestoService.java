package com.EstebanChun.Ejemplo.Service;

import com.EstebanChun.Ejemplo.Entity.Repuesto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepuestoService {
    List<Repuesto> getAllRepuesto();
    Repuesto getRepuesto(Integer id);
    Repuesto saveRepuesto(Repuesto repuesto) throws RuntimeException;
    Repuesto updateRepuesto(Integer id, Repuesto repuesto);
    void deleteRepuesto(Integer id );
}

