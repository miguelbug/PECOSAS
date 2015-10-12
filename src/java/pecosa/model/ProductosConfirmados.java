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
public class ProductosConfirmados implements Serializable{

    private Integer idNumero;
    private String destino;
    private String codigo;
    private String direccion;
    private String fecha;
    private String bien;
    private String unidad;
    private String marca;
    private String cantidad;
    private String precioUnit;
    private String serie;
    private String detalle;
    private Integer idDistrib;
    private String sbn;
    

    public ProductosConfirmados() {
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getBien() {
        return bien;
    }

    public void setBien(String bien) {
        this.bien = bien;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(String precioUnit) {
        this.precioUnit = precioUnit;
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

    public Integer getIdNumero() {
        return idNumero;
    }

    public void setIdNumero(Integer idNumero) {
        this.idNumero = idNumero;
    }

    public Integer getIdDistrib() {
        return idDistrib;
    }

    public void setIdDistrib(Integer idDistrib) {
        this.idDistrib = idDistrib;
    }

    public String getSbn() {
        return sbn;
    }

    public void setSbn(String sbn) {
        this.sbn = sbn;
    }

}
