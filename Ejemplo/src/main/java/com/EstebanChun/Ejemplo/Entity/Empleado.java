package com.EstebanChun.Ejemplo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Empleados")

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "apellido_empleado")
    private String apellidoEmpelado;

    @Column(name = "puesto_empleado")
    private String puestoEmpleado;

    @Column(name = "email_empleado")
    private String emailEmpleado;


    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public void setEmailEmpleado(String emailEmpleado) {
        this.emailEmpleado = emailEmpleado;
    }

    public String getPuestoEmpleado() {
        return puestoEmpleado;
    }

    public void setPuestoEmpleado(String puestoEmpleado) {
        this.puestoEmpleado = puestoEmpleado;
    }

    public String getApellidoEmpelado() {
        return apellidoEmpelado;
    }

    public void setApellidoEmpelado(String apellidoEmpelado) {
        this.apellidoEmpelado = apellidoEmpelado;
    }


}
