/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecosa.exporter;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author OGPL
 */
public class Conexion {
    Connection conn;
    
    public Conexion() throws SQLException{        
        obtenerConexion();  
    }
    public void obtenerConexion() {
        try{
            InitialContext ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/hr");
            conn = ds.getConnection();
        }
        catch(Exception e){
            System.out.println("PROBLEMAS EN CONEXION: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void cerrarConexion(){
        try{
            conn.close();
        }catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
