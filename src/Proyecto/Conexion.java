/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Conexión. Clase que representa la conexión con la BD.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */
public class Conexion {
    private String server;
    private String bd;
    private String usuario;
    private String pass;
    private Connection conexion;
    ResultSet rs;
    
    //Constructor
    public Conexion(String server, String bd, String usuario, String pass){
        this.server = server;
        this.bd = bd;
        this.usuario = usuario;
        this.pass = pass;
    }
    /**
     * Método que abre conexión con la BD.
     * @return devuelve el estado de la conexión. True si esta conectado.
     */
    public boolean abrirConexion(){
        boolean estado =false;
        //System.out.println(getServer());
        try{
            conexion =DriverManager.getConnection(getServer() + getBd(), getUsuario(), getPass());
            estado=true;
        }catch(SQLException ex){
            System.out.println("Error en la conexion");
        }
        return estado;
    }
    /**
     * Método que cierra la conexión con la BD.
     */
    public void cerrarConexion(){
        try{
            getConexion().close();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * @return server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server set Corresponde al server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return Devuelve el nombre de la BD
     */
    public String getBd() {
        return bd;
    }

    /**
     * @param bd set Corresponde al nombre de la BD
     */
    public void setBd(String bd) {
        this.bd = bd;
    }

    /**
     * @return Devuelve el nombre del usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario set Corresponde al nombre del usuario de la BD.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass set Corresponde a la pass de acceso a la BD
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return Devuelve la conexión con la BD
     */
    public Connection getConexion() {
        return conexion;
    }


}
