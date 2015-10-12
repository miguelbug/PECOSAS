/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import pecosa.dao.VistaDao;
import pecosa.daoImpl.VistaDaoImpl;
import pecosa.model.GuardarDistribucion;
import pecosa.model.ProductoVista;
import pecosa.model.Usuario;
import pecosa.model.GuardarProducto;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class VistaBean implements Serializable {

    private Usuario usu;
    private final FacesContext faceContext;
    private List<ProductoVista> lista;
    private List<ProductoVista> productosSelec;
    private List<ProductoVista> filtroProducto;
    private VistaDao vistaD;

    public VistaBean(FacesContext faceContext, List<ProductoVista> lista, VistaDao vistaD) {
        this.faceContext = faceContext;
        this.lista = lista;
        this.vistaD = vistaD;
    }

    public VistaBean() {
        vistaD = new VistaDaoImpl();
        lista = new ArrayList<ProductoVista>();
        faceContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) faceContext.getExternalContext().getSession(true);
        usu = (Usuario) session.getAttribute("sesionUsuario");

    }

    public void llenarVista() {
        try{
            lista = vistaD.getProductosVista();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void confirmar() throws ParseException {
        FacesMessage message = null;
        try {
            for (int i = 0; i < productosSelec.size(); i++) {
                GuardarProducto gp = new GuardarProducto();
                gp.setDireccion(productosSelec.get(i).getDireccion());
                gp.setFecha_crea(transfFecha(productosSelec.get(i).getFecha()));
                gp.setBien(productosSelec.get(i).getBien());
                gp.setUnidad(productosSelec.get(i).getUnidad());
                gp.setMarca(productosSelec.get(i).getMarca());
                gp.setPrecio(Double.parseDouble(productosSelec.get(i).getPrecioUnit()));
                gp.setSerie(productosSelec.get(i).getSerie());
                gp.setDetalle(productosSelec.get(i).getDetalle());
                gp.setCodigo(Integer.parseInt(productosSelec.get(i).getCodigo()));
                vistaD.confirmarProductos_1(gp);

                GuardarDistribucion gd = new GuardarDistribucion();
                gd.setId_numero(vistaD.getIdProductoInterno(gp.getFecha_crea(), gp.getBien(), gp.getCodigo()));
                gd.setCantidad(Integer.parseInt(productosSelec.get(i).getCantidad()));
                gd.setFecha(transfFecha(productosSelec.get(i).getFecha()));
                gd.setCodigo(vistaD.getIdDependencia(productosSelec.get(i).getDestino()));
                gd.setId_usuario(usu.getIdUsuario());
                vistaD.confirmarProductos_2(gd);
            }
            lista.clear();
            lista = vistaD.getProductosVista();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HA CONFIRMADO EL PRODUCTO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROBLEMAS AL CONFIRMAR");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public Date transfFecha(String fech) throws ParseException {
        SimpleDateFormat sfd = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
        return sfd.parse(fech);
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public List<ProductoVista> getLista() {
        return lista;
    }

    public void setLista(List<ProductoVista> lista) {
        this.lista = lista;
    }

    public List<ProductoVista> getProductosSelec() {
        return productosSelec;
    }

    public void setProductosSelec(List<ProductoVista> productosSelec) {
        this.productosSelec = productosSelec;
    }

    public VistaDao getVistaD() {
        return vistaD;
    }

    public void setVistaD(VistaDao vistaD) {
        this.vistaD = vistaD;
    }

    public List<ProductoVista> getFiltroProducto() {
        return filtroProducto;
    }

    public void setFiltroProducto(List<ProductoVista> filtroProducto) {
        this.filtroProducto = filtroProducto;
    }

}
