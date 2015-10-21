/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.exporter;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author OGPL
 */
public class CategoriaServicio {

    private Conexion nuevacon;

    public Connection getConexion() throws SQLException {
        nuevacon = new Conexion();
        return nuevacon.getConn();
    }

    public void CerrandoConexion() {
        nuevacon.cerrarConexion();
    }
}
