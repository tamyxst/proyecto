/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Comercial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author milla_000
 */
public class MVC_Reparaciones_Modelo {
     String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);

    public MVC_Reparaciones_Modelo(){
        
    }
    
    public ResultSet cargarCombo(){
        ResultSet rs = null;
        String tecnico="tecnico";
        c.abrirConexion();
        try {
            PreparedStatement cargarCombo = c.getConexion().prepareStatement("SELECT nombre FROM usuarios WHERE tipo=?");
            cargarCombo.setString(1, tecnico);
            rs = cargarCombo.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        } 
        return rs;
    }
}
