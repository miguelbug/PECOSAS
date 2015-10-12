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
public class PecosaProductos implements Serializable {

    private Integer idpecoprod;
    private Integer idpecosa;
    private Integer idproducto;
    
    public PecosaProductos() {
    }

    public Integer getIdpecoprod() {
        return idpecoprod;
    }

    public void setIdpecoprod(Integer idpecoprod) {
        this.idpecoprod = idpecoprod;
    }

    public Integer getIdpecosa() {
        return idpecosa;
    }

    public void setIdpecosa(Integer idpecosa) {
        this.idpecosa = idpecosa;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }
    
}
