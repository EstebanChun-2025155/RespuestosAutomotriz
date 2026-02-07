package com.EstebanChun.Ejemplo.Service;

import com.EstebanChun.Ejemplo.Model.Empleado;
import com.EstebanChun.Ejemplo.Respository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImplements implements EmpleadoService  {
    private  final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImplements(EmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) throws RuntimeException {
        try{
            if (empleado == null
                    || empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().isBlank()
                    || empleado.getApellidoEmpelado() == null || empleado.getApellidoEmpelado().isBlank()
                    || empleado.getPuestoEmpleado() == null || empleado.getPuestoEmpleado().isBlank()
                    || empleado.getEmailEmpleado() == null || empleado.getEmailEmpleado().isBlank()) {
                throw new IllegalArgumentException("Datos del empleado obligatorios");
            }


            if (empleadoRepository.existsByNombreEmpleadoAndApellidoEmpeladoAndPuestoEmpleadoAndEmailEmpleado(
                    empleado.getNombreEmpleado(),
                    empleado.getApellidoEmpelado(),
                    empleado.getPuestoEmpleado(),
                    empleado.getEmailEmpleado())) {

                throw new RuntimeException("Ya existe un empleado con estos datos");
            }

            if (empleadoRepository.existsByNombreEmpleadoAndApellidoEmpeladoAndPuestoEmpleadoAndEmailEmpleado(
                    empleado.getNombreEmpleado(),
                    empleado.getApellidoEmpelado(),
                    empleado.getPuestoEmpleado(),
                    empleado.getEmailEmpleado())){

                throw  new RuntimeException("Ya existe un empleado con estos datos");
            }
            return empleadoRepository.save(empleado);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado){
        Empleado existingEmpleado = empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("El empleado no existe"));

        existingEmpleado.setNombreEmpleado(empleado.getNombreEmpleado());
        existingEmpleado.setApellidoEmpelado(empleado.getApellidoEmpelado());
        existingEmpleado.setPuestoEmpleado(empleado.getPuestoEmpleado());
        existingEmpleado.setEmailEmpleado(empleado.getEmailEmpleado());

        return empleadoRepository.save(existingEmpleado);

    }

    @Override
    public void deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
