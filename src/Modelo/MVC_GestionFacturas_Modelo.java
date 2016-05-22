/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
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
public class MVC_GestionFacturas_Modelo {

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
            Logger.getLogger(MVC_GestionFacturas_Modelo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MVC_GestionFacturas_Modelo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MVC_GestionFacturas_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet listaFacturas() throws SQLException {
        ResultSet rs = null;
        c.abrirConexion();
        try {
            String consulta = "SELECT f.num_factura,f.fecha_fact,f.cod_rep,f.importe,e.nombre,e.dni "
                    + "FROM facturas f JOIN clientes e ON(f.cod_cliente=e.cod_cliente)JOIN reparaciones r "
                    + "ON(e.cod_cliente=r.cod_cliente)"
                    + "GROUP BY f.num_factura,f.fecha_fact,f.cod_rep,f.importe,e.nombre,e.dni ";
            Statement st = c.getConexion().createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        } 
        return rs;
    }
}
