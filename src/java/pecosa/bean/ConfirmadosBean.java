/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import pecosa.dao.ConfirmadosDao;
import pecosa.dao.ListasGeneralesDao;
import pecosa.dao.VistaDao;
import pecosa.daoImpl.ConfirmadosDaoImpl;
import pecosa.daoImpl.ListasGeneralesDaoImpl;
import pecosa.daoImpl.VistaDaoImpl;
import pecosa.model.Dependencia;
import pecosa.model.GuardarDistribucion;
import pecosa.model.ProductosConfirmados;
import pecosa.model.Usuario;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class ConfirmadosBean implements Serializable {

    private List<ProductosConfirmados> listaConfirmados;
    private final FacesContext faceContext;
    private final Usuario usu;
    private ConfirmadosDao confirm;
    private ListasGeneralesDao lg;
    private ProductosConfirmados confirmSeleccionados;
    private List<ProductosConfirmados> confirmFiltro;
    private List<String> listaDepe;
    private String destino;
    private String cantidad;
    private List<Integer> cantidades;
    private VistaDao vistaD;

    public ConfirmadosBean() {
        vistaD = new VistaDaoImpl();
        listaDepe = new ArrayList<String>();
        cantidades = new ArrayList<Integer>();
        lg = new ListasGeneralesDaoImpl();
        listaConfirmados = new ArrayList<ProductosConfirmados>();
        confirm = new ConfirmadosDaoImpl();
        faceContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) faceContext.getExternalContext().getSession(true);
        usu = (Usuario) session.getAttribute("sesionUsuario");
        llenarConfirmados();
    }

    public void llenarConfirmados() {
        listaConfirmados.clear();
        try {
            listaConfirmados = confirm.getProductosConfirmados();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void llenar() {
        llenarDependencias();
        llenarCantidades();
    }

    public void llenarDependencias() {
        listaDepe.clear();
        try {
            List<Dependencia> lista = lg.getListaDependencias();
            for (int i = 0; i < lista.size(); i++) {
                listaDepe.add(lista.get(i).getNombre());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void llenarCantidades() {
        cantidades.clear();
        try {
            int i = 1;
            while (i <= Integer.parseInt(confirmSeleccionados.getCantidad())) {
                cantidades.add(i);
                i++;
            }
        } catch (Exception e) {

        }
    }

    public void distribuir() {
        Date date = new Date();
        FacesMessage message = null;
        try {
            System.out.println("ID NUmero: " + confirmSeleccionados.getIdNumero());
            GuardarDistribucion gd = new GuardarDistribucion();
            gd.setCantidad(Integer.parseInt(cantidad));
            gd.setCodigo(vistaD.getIdDependencia(destino));
            gd.setFecha(date);
            gd.setId_numero(confirmSeleccionados.getIdNumero());
            gd.setId_usuario(usu.getIdUsuario());
            //guardar
            confirm.guardarDistribucion(gd);
            //actualizar
            confirm.actualizarDistribucion(confirmSeleccionados.getIdDistrib(), Integer.parseInt(confirmSeleccionados.getCantidad()) - Integer.parseInt(cantidad));
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HA DISTRIBUIDO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            destino = " ";
            cantidad = " ";
            llenarConfirmados();
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROBLEMAS AL DISTRIBUIR");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public List<ProductosConfirmados> getListaConfirmados() {
        return listaConfirmados;
    }

    public void setListaConfirmados(List<ProductosConfirmados> listaConfirmados) {
        this.listaConfirmados = listaConfirmados;
    }

    public ConfirmadosDao getConfirm() {
        return confirm;
    }

    public void setConfirm(ConfirmadosDao confirm) {
        this.confirm = confirm;
    }

    public ProductosConfirmados getConfirmSeleccionados() {
        return confirmSeleccionados;
    }

    public void setConfirmSeleccionados(ProductosConfirmados confirmSeleccionados) {
        this.confirmSeleccionados = confirmSeleccionados;
    }

    public List<ProductosConfirmados> getConfirmFiltro() {
        return confirmFiltro;
    }

    public void setConfirmFiltro(List<ProductosConfirmados> confirmFiltro) {
        this.confirmFiltro = confirmFiltro;
    }

    public ListasGeneralesDao getLg() {
        return lg;
    }

    public void setLg(ListasGeneralesDao lg) {
        this.lg = lg;
    }

    public List<String> getListaDepe() {
        return listaDepe;
    }

    public void setListaDepe(List<String> listaDepe) {
        this.listaDepe = listaDepe;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(List<Integer> cantidades) {
        this.cantidades = cantidades;
    }

    public VistaDao getVistaD() {
        return vistaD;
    }

    public void setVistaD(VistaDao vistaD) {
        this.vistaD = vistaD;
    }

}
