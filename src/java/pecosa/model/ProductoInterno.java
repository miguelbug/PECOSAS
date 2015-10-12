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
public class ProductoInterno implements Serializable{

    private Integer id_numero;
    
    public ProductoInterno() {
    }

    public Integer getId_numero() {
        return id_numero;
    }

    public void setId_numero(Integer id_numero) {
        this.id_numero = id_numero;
    }
    
}
