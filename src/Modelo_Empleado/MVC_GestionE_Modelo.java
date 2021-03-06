/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Empleado;

import Proyecto.Conexion;
import Modelo_Comercial.MVC_GestionC_Modelo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase MVC_GestionE_Modelo
 * Clase Modelo, dónde se realizan las consultas a la BD.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_GestionE_Modelo {
    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);
    /**
     * Método que consulta los datos en la BD.
     * @param nombre Corresponde a la pass del empleado
     * @param password Corresponde a la pass del empleado
     * @return Devuelve true si son correctos, y false si no lo son.
     */
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
    /**
     * Método que comprueba el tipo de empleado, a través de una consulta
     * a la BD con su nombre y contraseña.
     * @param nombre Corresponde al nombre del empleado
     * @param pass Corresponde al nombre del empleado
     * @return Devuelve el tipo, si es técnico o comercial
     */
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
    /**
     * Método que inserta datos a la BD, a través de un procedimiento almacenado.
     * @param nombre del empleado
     * @param password del empleado
     * @param tipo del empleado
     */
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
    /**
     * Método que hace una consulta a la BD y devuelve el id del técnico
     * @param nombre Corresponde al nombre del empleado
     * @return id del empleado
     */
    public int pedirIdTecnico(String nombre){
        int id=0;
        c.abrirConexion();
        ResultSet rs;

        try {
            PreparedStatement comprobarT = c.getConexion().prepareStatement("SELECT * FROM usuarios WHERE nombre=?");
            comprobarT.setString(1, nombre);
            rs = comprobarT.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
