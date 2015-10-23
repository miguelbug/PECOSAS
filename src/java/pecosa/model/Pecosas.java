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
public class Pecosas implements Serializable{

    private String codigoPecosa;
    private Integer idPecosa;
    
    public Pecosas() {
    }

    public String getCodigoPecosa() {
        return codigoPecosa;
    }

    public void setCodigoPecosa(String codigoPecosa) {
        this.codigoPecosa = codigoPecosa;
    }

    public Integer getIdPecosa() {
        return idPecosa;
    }

    public void setIdPecosa(Integer idPecosa) {
        this.idPecosa = idPecosa;
    }
    
}
