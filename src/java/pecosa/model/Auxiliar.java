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
public class Auxiliar implements Serializable{

    private Integer getIdprod;
    private Integer getIdpecosa;
    
    public Auxiliar() {
    }

    public Integer getGetIdprod() {
        return getIdprod;
    }

    public void setGetIdprod(Integer getIdprod) {
        this.getIdprod = getIdprod;
    }

    public Integer getGetIdpecosa() {
        return getIdpecosa;
    }

    public void setGetIdpecosa(Integer getIdpecosa) {
        this.getIdpecosa = getIdpecosa;
    }
    
}
