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
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TabChangeEvent;
import pecosa.dao.ConfirmadosDao;
import pecosa.dao.DistribuidosDao;
import pecosa.dao.ListasGeneralesDao;
import pecosa.dao.VistaDao;
import pecosa.daoImpl.ConfirmadosDaoImpl;
import pecosa.daoImpl.DistribuidosDaoImpl;
import pecosa.daoImpl.ListasGeneralesDaoImpl;
import pecosa.daoImpl.VistaDaoImpl;
import pecosa.model.Dependencia;
import pecosa.model.GuardarDistribucion;
import pecosa.model.GuardarProducto;
import pecosa.model.ProductoVista;
import pecosa.model.ProductosConfirmados;
import pecosa.model.ProductosDistribuidos;
import pecosa.model.Usuario;
import pecosa.model.VerificarDistribucion;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class DistribucionBean implements Serializable {

    private Usuario usu;
    private final FacesContext faceContext;
    private List<ProductoVista> lista;
    private List<ProductoVista> productosSelec;
    private List<ProductoVista> filtroProducto;
    private VistaDao vistaD;

    private List<ProductosConfirmados> listaConfirmados;
    private ConfirmadosDao confirm;
    private ListasGeneralesDao lg;
    private ProductosConfirmados confirmSeleccionados;
    private ProductosConfirmados confirmSeleccionados2;
    private List<ProductosConfirmados> confirmFiltro;
    private List<String> listaDepe;
    private String destino;
    private String cantidad;
    private List<Integer> cantidades;

    private List<ProductosDistribuidos> listaDistrib;
    private DistribuidosDao distribuidosD;
    private List<ProductosDistribuidos> filtro;
    private ProductosDistribuidos seleccionado;

    public DistribucionBean() {
        vistaD = new VistaDaoImpl();
        lista = new ArrayList<ProductoVista>();
        listaDepe = new ArrayList<String>();
        cantidades = new ArrayList<Integer>();
        lg = new ListasGeneralesDaoImpl();
        listaConfirmados = new ArrayList<ProductosConfirmados>();
        confirm = new ConfirmadosDaoImpl();
        listaDistrib = new ArrayList<ProductosDistribuidos>();
        distribuidosD = new DistribuidosDaoImpl();
        faceContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) faceContext.getExternalContext().getSession(true);
        usu = (Usuario) session.getAttribute("sesionUsuario");
        llenarVista();
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage message = null;
        try {
            confirm.modificarSBN(((ProductosConfirmados) event.getObject()).getSbn(), ((ProductosConfirmados) event.getObject()).getIdNumero());
            llenarConfirmados();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HA ACTUALIZADO EL PRODUCTO " + ((ProductosConfirmados) event.getObject()).getBien());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE PUEDE ACTUALIZAR");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage message = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "NO SE HA MODIFICADO NADA");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void onRowEdit2(RowEditEvent event) {
        FacesMessage message = null;
        try {
            distribuidosD.confirmarSBN(((ProductosDistribuidos) event.getObject()).getSbn(), ((ProductosDistribuidos) event.getObject()).getIdNumero());
            llenarDistribuidos();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HA ACTUALIZADO EL PRODUCTO: "+((ProductosDistribuidos) event.getObject()).getBien());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA PODIDO ACTUALIZAR");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    public void onRowCancel2(RowEditEvent event) {
        FacesMessage message = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "NO SE HA HECHO NINGUNA ACCIÓN");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    //////////////////DESDE LA VISTA
    public void llenarVista() {
        try {
            lista = vistaD.getProductosVista();
        } catch (Exception e) {
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
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sfd.parse(fech);
    }

    /////////////////
    /////////////////PARA CONFIRMADOS
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
            Integer iddepe = vistaD.getIdDependencia(destino);
            System.out.println("ID DEPENDENCIA: " + iddepe);
            VerificarDistribucion verif = confirm.verificarProducto(iddepe, confirmSeleccionados.getIdNumero());
            if (verif != null) {
                System.out.println("ENTRA A ACTUALIZAR");
                verif.setCantidad(verif.getCantidad() + Integer.parseInt(cantidad));
                confirm.actualizarDistribucion2(verif);
                confirm.actualizarDistribucion(confirmSeleccionados.getIdDistrib(), Integer.parseInt(confirmSeleccionados.getCantidad()) - Integer.parseInt(cantidad));
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HA ACTUALIZADO Y ENVIADO A " + destino);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                System.out.println("ENTRA A GUARDAR");
                GuardarDistribucion gd = new GuardarDistribucion();
                gd.setCantidad(Integer.parseInt(cantidad));
                gd.setCodigo(iddepe);
                gd.setFecha(date);
                gd.setId_numero(confirmSeleccionados.getIdNumero());
                gd.setId_usuario(usu.getIdUsuario());
                confirm.guardarDistribucion(gd);
                confirm.actualizarDistribucion(confirmSeleccionados.getIdDistrib(), Integer.parseInt(confirmSeleccionados.getCantidad()) - Integer.parseInt(cantidad));
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HA DISTRIBUIDO A " + destino);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
            destino = " ";
            cantidad = " ";
            llenarConfirmados();
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROBLEMAS AL DISTRIBUIR");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    /////////////////
    ////////////////PARA DISTRIBUIDOS
    public void llenarDistribuidos() {
        try {
            listaDistrib = distribuidosD.getListaDistribuidos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ////////////////
    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("SIN CONFIRMAR")) {
            //llenarVista();
        } else {
            if (event.getTab().getTitle().equals("CONFIRMADOS")) {
                llenarConfirmados();
            } else {
                if (event.getTab().getTitle().equals("DISTRIBUIDOS")) {
                    llenarDistribuidos();
                }
            }
        }
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

    public List<ProductoVista> getFiltroProducto() {
        return filtroProducto;
    }

    public void setFiltroProducto(List<ProductoVista> filtroProducto) {
        this.filtroProducto = filtroProducto;
    }

    public VistaDao getVistaD() {
        return vistaD;
    }

    public void setVistaD(VistaDao vistaD) {
        this.vistaD = vistaD;
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

    public ListasGeneralesDao getLg() {
        return lg;
    }

    public void setLg(ListasGeneralesDao lg) {
        this.lg = lg;
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

    public ProductosConfirmados getConfirmSeleccionados2() {
        return confirmSeleccionados2;
    }

    public void setConfirmSeleccionados2(ProductosConfirmados confirmSeleccionados2) {
        this.confirmSeleccionados2 = confirmSeleccionados2;
    }

}