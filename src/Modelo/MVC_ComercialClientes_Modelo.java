/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Proyecto.CreaUI;
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
public class MVC_ComercialClientes_Modelo {
    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);
    
    public MVC_ComercialClientes_Modelo(){
        
    }
    public ResultSet listaClientes() throws SQLException{
        ResultSet rs = null;
        c.abrirConexion();
        try {
            String consulta = "SELECT * FROM clientes;";
            Statement st = c.getConexion().createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        } 
        return rs;
    }
    
    public void anadirNuevoCliente(String nombre, String apellidos,String dni,String cod_postal, String telefono){
        CallableStatement cs;
        try{
            c.abrirConexion();
            cs=c.getConexion().prepareCall("{call insertarCliente_en(?,?,?,?,?)}");
            cs.setString(1, nombre);
            cs.setString(2, apellidos);
            cs.setString(3, dni);
            cs.setString(4, cod_postal);
            cs.setString(5, telefono);
            cs.execute();
                    
        }catch(SQLException ex){
            Logger.getLogger(MVC_GestionFacturas_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean buscarClientesPorDni(String dni){
        boolean validar=false;
        c.abrirConexion();
        ResultSet rs;
        try{
            PreparedStatement comprobarDni = c.getConexion().prepareStatement("SELECT * FROM clientes WHERE dni=?");
            comprobarDni.setString(1, dni);
            rs=comprobarDni.executeQuery();
            
            if(rs.first()){
                validar= true;
            }else{
                validar= false;
            }
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionFacturas_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
    public void abrirBuscarClientes(){
        CreaUI.abrirMenuBuscarClientes();
    }
}
