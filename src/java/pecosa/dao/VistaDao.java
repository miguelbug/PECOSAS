/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecosa.dao;

import java.util.Date;
import java.util.List;
import pecosa.model.GuardarDistribucion;
import pecosa.model.GuardarProducto;
import pecosa.model.ProductoVista;

/**
 *
 * @author OGPL
 */
public interface VistaDao {
    public List<ProductoVista> getProductosVista();
    public void confirmarProductos_1(GuardarProducto gp);
    public void confirmarProductos_2(GuardarDistribucion gd);
    public Integer getIdDependencia(String nombre);
    public Integer getIdProductoInterno(Date fecha, String bien, Integer codigo);
}
