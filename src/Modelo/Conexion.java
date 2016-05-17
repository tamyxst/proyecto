/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;



import java.sql.*;

/**
 *
 * @author milla_000
 */
public class Conexion {
    private String server;
    private String bd;
    private String usuario;
    private String pass;
    private Connection conexion;
    ResultSet rs;
    
    public Conexion(String server, String bd, String usuario, String pass){
        this.server = server;
        this.bd = bd;
        this.usuario = usuario;
        this.pass = pass;
    }

    
    public boolean abrirConexion(){
        boolean estado =false;
        System.out.println(getServer());
        try{
            conexion =DriverManager.getConnection(getServer() +getBd(), getUsuario(), getPass());
            estado=true;
        }catch(SQLException ex){
            System.out.println("Error en la conexion");
        }
        return estado;
    }
    
    public void cerrarConexion(){
        try{
            getConexion().close();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return the bd
     */
    public String getBd() {
        return bd;
    }

    /**
     * @param bd the bd to set
     */
    public void setBd(String bd) {
        this.bd = bd;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
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
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the conexion
     */
    public Connection getConexion() {
        return conexion;
    }


}
