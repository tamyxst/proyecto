/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Tecnico;

import Componentes.Cliente;
import Componentes.Conexion;
import Modelo_Comercial.MVC_GestionC_Modelo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno
 */
public class MVC_ClientesR_Modelo {
    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);
    
    
    public MVC_ClientesR_Modelo(){
        
    }
    
    public void anadirNuevoCliente(Cliente cliente){
        CallableStatement cs;
        try{
            c.abrirConexion();
            cs=c.getConexion().prepareCall("{call insertarCliente_en(?,?,?,?,?)}");
            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getApellidos());
            cs.setString(3, cliente.getDni());
            cs.setString(4, cliente.getCod_postal());
            cs.setString(5, cliente.getTelefono());
            cs.execute();
                    
        }catch(SQLException ex){
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
}
