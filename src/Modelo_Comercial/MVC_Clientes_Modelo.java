/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Comercial;

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
public class MVC_Clientes_Modelo {
    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);
    
    public MVC_Clientes_Modelo(){
        
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
            Logger.getLogger(MVC_GestionFac_Modelo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MVC_GestionFac_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
    public void abrirBuscarClientes(){
        CreaUI.abrirMenuBuscarClientes();
    }
    
    public void modificarCliente(Cliente cliente){  
        c.abrirConexion();
        ResultSet rs;
        try{
            PreparedStatement modificarCli = c.getConexion().prepareStatement("UPDATE clientes SET nombre=?,apellidos=?,dni=?,cod_postal=?, telefono=? WHERE cod_cliente=?");
            modificarCli.setString(1, cliente.getNombre());
            modificarCli.setString(2, cliente.getApellidos());
            modificarCli.setString(3, cliente.getDni());
            modificarCli.setString(4, cliente.getCod_postal());
            modificarCli.setString(5, cliente.getTelefono());
            modificarCli.setInt(6, cliente.getCod_cliente());
            modificarCli.executeUpdate();

            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionFac_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void bajaCliente(Cliente cliente){  
        c.abrirConexion();
        ResultSet rs;
        try{
            PreparedStatement bajaCli = c.getConexion().prepareStatement("DELETE from clientes where cod_CLIENTE=?");
            bajaCli.setInt(1, cliente.getCod_cliente());
            bajaCli.executeUpdate();

            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionFac_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
