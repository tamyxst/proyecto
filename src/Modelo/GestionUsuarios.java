/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milla_000
 */
public class GestionUsuarios {
    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String pass = "1234";
    
    public GestionUsuarios(){
        
    }
    Conexion c=new Conexion(servidor, bd, usuario, pass);
    
    public boolean comprobarLogin(String nombre, String password){
        boolean validar=false;
        c.abrirConexion();
        ResultSet rs;
        try{
            PreparedStatement comprobar= c.getConexion().prepareStatement("SELECT nombre,pass FROM usuarios WHERE nombre=? AND pass =?");
            comprobar.setString(1, nombre);
            comprobar.setString(2, password);
            rs=comprobar.executeQuery();
            
            if( rs.first() ){
                System.out.println("true");// si es valido el primer reg. hay una fila, tons el usuario y su pw existen
                validar= true;
                //usuario validado correctamente
            }else
                System.out.println("false");
                validar= false;
        
        } catch (SQLException ex) {
            Logger.getLogger(GestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    return validar;
    }
}




