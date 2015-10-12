/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pecosa.dao.DistribuidosDao;
import pecosa.daoImpl.DistribuidosDaoImpl;
import pecosa.model.ProductosDistribuidos;
import pecosa.model.Usuario;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class DistribuidosBean implements Serializable {

    private final FacesContext faceContext;
    private final Usuario usu;
    private List<ProductosDistribuidos> listaDistrib;
    private DistribuidosDao distribuidosD;
    private List<ProductosDistribuidos> filtro;
    private ProductosDistribuidos seleccionado;

    public DistribuidosBean(FacesContext faceContext, Usuario usu, List<ProductosDistribuidos> listaDistrib, DistribuidosDao distribuidosD) {
        this.faceContext = faceContext;
        this.usu = usu;
        this.listaDistrib = listaDistrib;
        this.distribuidosD = distribuidosD;
    }

    public DistribuidosBean() {
        listaDistrib = new ArrayList<ProductosDistribuidos>();
        distribuidosD = new DistribuidosDaoImpl();
        faceContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) faceContext.getExternalContext().getSession(true);
        usu = (Usuario) session.getAttribute("sesionUsuario");
        llenarDistribuidos();
    }

    public void llenarDistribuidos() {
        try {
            listaDistrib = distribuidosD.getListaDistribuidos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<ProductosDistribuidos> getListaDistrib() {
        return listaDistrib;
    }

    public void setListaDistrib(List<ProductosDistribuidos> listaDistrib) {
        this.listaDistrib = listaDistrib;
    }

    public DistribuidosDao getDistribuidosD() {
        return distribuidosD;
    }

    public void setDistribuidosD(DistribuidosDao distribuidosD) {
        this.distribuidosD = distribuidosD;
    }

    public List<ProductosDistribuidos> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<ProductosDistribuidos> filtro) {
        this.filtro = filtro;
    }

    public ProductosDistribuidos getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(ProductosDistribuidos seleccionado) {
        this.seleccionado = seleccionado;
    }

}
