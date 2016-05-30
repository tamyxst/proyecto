/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Comercial;


import Proyecto.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Clase MVC_BuscaC_Modelo
 * Clase Modelo, dónde se realizan las consultas a la BD.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_BuscaC_Modelo {
    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";
    
    Conexion c = new Conexion(servidor, bd, usuario, password);
    public MVC_BuscaC_Modelo(){
        
    }
    /**
     * Consulta a la BD. Busca el cliente con el dni correspondiente.
     * @param dni del cliente
     * @return Devuelve los datos del cliente que corresponda al dni buscado
     */
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
