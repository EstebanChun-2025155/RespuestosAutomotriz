package com.EstebanChun.Ejemplo.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Venta")
public class Venta {
    @Id
    @GeneratedValue

    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "fecha_venta")
    private Date fechaVenta;

    @Column(name = "cantidad")
    private Integer Cantidad;

    @Column(name = "total")
    private Double Total;

    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column (name = "id_repuesto")
    private Integer idRepuesto;

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer cantidad) {
        Cantidad = cantidad;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(Integer idRepuesto) {
        this.idRepuesto = idRepuesto;
    }
}
