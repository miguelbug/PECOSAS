/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import pecosa.dao.ConfirmadosDao;
import pecosa.dao.TemporalDao;
import pecosa.daoImpl.ConfirmadosDaoImpl;
import pecosa.daoImpl.TemporalDaoImpl;
import pecosa.exporter.CategoriaServicio;
import pecosa.exporter.ReporteController;
import pecosa.model.Usuario;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class RealizarReporteBean implements Serializable {

    private static final long serialVersionUID = 8797816477254175229L;
    private FacesContext context;
    private ServletContext serveltcontext;
    private int opcionFormato;
    private ReporteController repor;
    private CategoriaServicio categoriaServicio;
    private HashMap<String, Object> parametros;
    private Usuario usu;
    private ConfirmadosDao confirm;
    private TemporalDao td;

    public RealizarReporteBean() {
        confirm = new ConfirmadosDaoImpl();
        td = new TemporalDaoImpl();
    }

    public void inicializar(String reporte) throws SQLException {
        
        context = FacesContext.getCurrentInstance();
        serveltcontext = (ServletContext) context.getExternalContext().getContext();
        repor = new ReporteController();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        usu = (Usuario) session.getAttribute("sesionUsuario");
        parametros = new HashMap<String, Object>();
        parametros.clear();
        repor = ReporteController.getInstance(reporte);
        categoriaServicio = new CategoriaServicio();
        repor.setConexion(categoriaServicio.getConexion());
        repor.setTipoFormato(opcionFormato);
    }

    public void imprimirDistribucion() throws SQLException {
        inicializar("Distribuidos");
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombre());
        parametros.put("logo", getLogo());
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
        confirm.actualizarFlac();
        
    }
    
    public void imprimirConfirmados() throws SQLException {
        inicializar("Confirmados");
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombre());
        parametros.put("logo", getLogo());
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
        confirm.actualizarFlac();
        td.actualizarTemporal();
    }
    
    public String getLogo() {
        String logo = "";
        logo = serveltcontext.getRealPath("image/" + "escudo_reporte" + ".jpg");
        return logo;
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public ServletContext getServeltcontext() {
        return serveltcontext;
    }

    public void setServeltcontext(ServletContext serveltcontext) {
        this.serveltcontext = serveltcontext;
    }

    public int getOpcionFormato() {
        return opcionFormato;
    }

    public void setOpcionFormato(int opcionFormato) {
        this.opcionFormato = opcionFormato;
    }

    public ReporteController getRepor() {
        return repor;
    }

    public void setRepor(ReporteController repor) {
        this.repor = repor;
    }

    public CategoriaServicio getCategoriaServicio() {
        return categoriaServicio;
    }

    public void setCategoriaServicio(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    public HashMap<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(HashMap<String, Object> parametros) {
        this.parametros = parametros;
    }

}
