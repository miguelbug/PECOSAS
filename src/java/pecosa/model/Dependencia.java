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
public class Dependencia implements Serializable {

    private Integer codigoDep;
    private String nombre;
    private String siglas;
    
    public Dependencia() {
    }

    public Integer getCodigoDep() {
        return codigoDep;
    }

    public void setCodigoDep(Integer codigoDep) {
        this.codigoDep = codigoDep;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }
    
}
