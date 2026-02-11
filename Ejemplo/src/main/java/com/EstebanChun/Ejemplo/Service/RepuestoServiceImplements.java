package com.EstebanChun.Ejemplo.Service;

import com.EstebanChun.Ejemplo.Entity.Repuesto;
import com.EstebanChun.Ejemplo.Respository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepuestoServiceImplements implements RepuestoService {
    private final RepuestoRepository repuestoRepository;

    public RepuestoServiceImplements(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public List<Repuesto> getAllRepuesto() { return repuestoRepository.findAll(); }

    @Override
    public Repuesto getRepuestoById(Integer id) {
        return repuestoRepository.findById(id).orElse(null);
    }

    @Override
    public Repuesto saveRepuesto(Repuesto repuesto) throws RuntimeException {
        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto updateRepuesto(Integer id, Repuesto repuesto) {
        Repuesto existingRepuesto = repuestoRepository.findById(id).orElseThrow(() -> new RuntimeException("El Repuesto no existe"));
        return  repuestoRepository.save(existingRepuesto);
    }

    @Override
    public void deleteRepuesto(Integer id) {repuestoRepository.findById(id);}
}
