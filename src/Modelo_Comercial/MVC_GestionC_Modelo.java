/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Comercial;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milla_000
 */
public class MVC_GestionC_Modelo {

    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);

    public boolean comprobarLogin(String nombre, String password) {
        boolean validar = false;
        c.abrirConexion();
        ResultSet rs;
        try {
            PreparedStatement comprobar = c.getConexion().prepareStatement("SELECT nombre,pass FROM usuarios WHERE nombre=? AND pass =?");
            comprobar.setString(1, nombre);
            comprobar.setString(2, password);
            rs = comprobar.executeQuery();

            if (rs.first()) {
                System.out.println("true");
                validar = true;
            } else {
                System.out.println("false");
                validar = false;
            }
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }

    public String comprobarTipo(String nombre, String pass) {
        String tipo = "";
        c.abrirConexion();
        ResultSet rs;

        try {
            PreparedStatement comprobarT = c.getConexion().prepareStatement("SELECT * FROM usuarios WHERE nombre=? AND pass =?");
            comprobarT.setString(1, nombre);
            comprobarT.setString(2, pass);
            rs = comprobarT.executeQuery();

            while (rs.next()) {
                tipo = rs.getString(4);
            }
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipo;
    }

    public void altaNuevoUsuario(String nombre, String password, String tipo) {
        CallableStatement cs;
        try {
            c.abrirConexion();
            //Se lo indicamos con 2 interrogantes el nº de parametros.
            cs = c.getConexion().prepareCall("{call insertarUsuario_en(?,?,?)}");
            //Establecemos los valores de los parametros.
            cs.setString(1, nombre);
            cs.setString(2, password);
            cs.setString(3, tipo);
            //Ejecutar el procedimiento almacenado.
            cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet listaFacturas() throws SQLException {
        ResultSet rs = null;
        c.abrirConexion();
        try {
            String consulta = "SELECT f.num_factura,f.fecha_fact,f.cod_rep,f.importe,e.nombre,e.dni "
                    + "FROM facturas f JOIN clientes e ON(f.cod_cliente=e.cod_cliente)"
                    + "GROUP BY f.num_factura,f.fecha_fact,f.cod_rep,f.importe,e.nombre,e.dni ";
            Statement st = c.getConexion().createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        } 
        return rs;
    }
    public ResultSet listaFacturasEntreFechas(Date fechaPrimera, Date fechaUltima) {
        c.abrirConexion();
        ResultSet rs = null;
        try {
            PreparedStatement consulta = c.getConexion().prepareStatement("select * from facturas where fecha_fact>=? AND fecha_fact<=? ORDER BY fecha_fact");
            consulta.setDate(1, fechaPrimera);
            consulta.setDate(2, fechaUltima);
            rs = consulta.executeQuery();

            //No se debe cerrar la conexion para mostrar tablas
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public ResultSet listaReparaciones() {
        c.abrirConexion();
        ResultSet rs = null;
        try {
            String consulta = "SELECT * FROM reparaciones WHERE facturado=false";
            Statement st = c.getConexion().createStatement();
            rs = st.executeQuery(consulta);
            //No se debe cerrar la conexion para mostrar tablas
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        } 
        return rs;
    }
    
    public ResultSet listaPorCodPostal(String cod_postal) {
        c.abrirConexion();
        ResultSet rs = null;
        try {
            PreparedStatement consulta = c.getConexion().prepareStatement("select * from facturas f JOIN clientes c where f.cod_cliente=c.cod_cliente AND c.cod_postal=?");
            consulta.setString(1, cod_postal);
            rs = consulta.executeQuery();
            //No se debe cerrar la conexion para mostrar tablas
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
