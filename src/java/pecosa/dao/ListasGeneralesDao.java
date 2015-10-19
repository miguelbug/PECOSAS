/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecosa.dao;

import java.util.List;
import pecosa.model.Dependencia;
import pecosa.model.Persona;

/**
 *
 * @author OGPL
 */
public interface ListasGeneralesDao {
    public List<Dependencia> getListaDependencias();
    public List<String> getNombrePersonas(Integer codigo);
}
