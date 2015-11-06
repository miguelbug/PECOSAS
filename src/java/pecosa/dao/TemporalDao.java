/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecosa.dao;

import pecosa.model.Temporal;

/**
 *
 * @author OGPL
 */
public interface TemporalDao {
    public void guardarTemporal(Temporal t);
    public void actualizarTemporal();
    public Integer getLote();
}
