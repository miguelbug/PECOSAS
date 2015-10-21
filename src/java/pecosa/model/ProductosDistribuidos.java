/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecosa.model;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class ProductosDistribuidos implements Serializable{

    private Integer idNumero;
    private String direccion;
    private String fecha;
    private String codigo;
    private String bien;
    private String destino;
    private String cantidad;
    private String fecha_dist;
    private String unidad;
    private String marca;
    private String serie;
    private String detalle;
    private String sbn;
    private String usuario;
    private Double precio;
    private String asignado;
    
    public ProductosDistribuidos() {
    }

    public Integer getIdNumero() {
        return idNumero;
    }

    public void setIdNumero(Integer idNumero) {
        this.idNumero = idNumero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getBien() {
        return bien;
    }

    public void setBien(String bien) {
        this.bien = bien;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_dist() {
        return fecha_dist;
    }

    public void setFecha_dist(String fecha_dist) {
        this.fecha_dist = fecha_dist;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getSbn() {
        return sbn;
    }

    public void setSbn(String sbn) {
        this.sbn = sbn;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getAsignado() {
        return asignado;
    }

    public void setAsignado(String asignado) {
        this.asignado = asignado;
    }
    
}
