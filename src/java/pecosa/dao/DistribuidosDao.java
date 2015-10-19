/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecosa.dao;

import java.util.List;
import pecosa.model.ProductosDistribuidos;
import pecosa.model.VerificarDistribucion;

/**
 *
 * @author OGPL
 */
public interface DistribuidosDao {
    public List<ProductosDistribuidos> getListaDistribuidos();
    public void confirmarSBN(String sbn, Integer idNumero);
    public Integer getIdPersona(Integer codigo);
    public Integer getIdPersonaXnombre(Integer codigo, String nombre);
}
