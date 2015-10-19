/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecosa.dao;

import java.util.List;
import pecosa.model.GuardarDistribucion;
import pecosa.model.GuardarProducto;
import pecosa.model.Pecosa;
import pecosa.model.PecosaProductos;
import pecosa.model.ProductosConfirmados;
import pecosa.model.VerificarDistribucion;

/**
 *
 * @author OGPL
 */
public interface ConfirmadosDao {
    public List<ProductosConfirmados> getProductosConfirmados();
    public void guardarDistribucion(GuardarDistribucion p);
    public void actualizarDistribucion(Integer idDis, Integer cantidad);
    public void actualizarDistribucion2(VerificarDistribucion verif);
    public VerificarDistribucion verificarProducto(Integer idDependencia, Integer idNumero);
    public void modificarSBN(String sbn, Integer idNumero);
    public Integer getIdProductos(GuardarProducto gp);
    public Integer getIdPecosa(Pecosa p);
    public void guardarPecosa(Pecosa p);
    public void guardarProdPecosa(PecosaProductos pp);
    public Integer getIdPersona(String nombre);
}
