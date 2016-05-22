/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author milla_000
 */
public class MVC_BuscarClientes_Modelo {
    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);
    public MVC_BuscarClientes_Modelo(){
        
    }
    public ResultSet buscarClientesPorDni(String dni){
        c.abrirConexion();
        ResultSet rs=null;
        try{ 
            PreparedStatement buscarC = c.getConexion().prepareStatement("SELECT * FROM CLIENTES WHERE dni=?");
            buscarC.setString(1, dni);
            rs=buscarC.executeQuery();
        }catch(SQLException ex){
             Logger.getLogger(ex.getMessage());
        }
        return rs;
    }
}
