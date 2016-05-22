/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Proyecto.CreaUI;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milla_000
 */
public class MVC_ComercialFacturas_Modelo {
    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);
    
    public MVC_ComercialFacturas_Modelo(){
        
    }
    
    public void grabarNuevaFactura(Date fecha_fact,String cod_rep,float importe, int cod_cliente){
        CallableStatement cs;
        try{
            c.abrirConexion();
            cs=c.getConexion().prepareCall("{call insertarFactura_en(?,?,?,?)}");
            cs.setDate(1, fecha_fact);
            cs.setString(2, cod_rep);
            cs.setFloat(3, importe);
            cs.setInt(4,cod_cliente);
            cs.execute();
                    
        }catch(SQLException ex){
            Logger.getLogger(MVC_Gestion_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean buscarClientesCodCliente(int cod_cliente){
        boolean validar=false;
        c.abrirConexion();
        ResultSet rs;
        try{
            PreparedStatement comprobarDni = c.getConexion().prepareStatement("SELECT * FROM clientes WHERE cod_cliente=?");
            comprobarDni.setInt(1, cod_cliente);
            rs=comprobarDni.executeQuery();
            
            if(rs.first()){
                validar= true;
            }else{
                validar= false;
            }
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_Gestion_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
    public void abrirBuscarClientes(){
        CreaUI.abrirMenuBuscarClientes();
    }
    
    public boolean buscarCodigoReparacion(String cod_rep){
        boolean validar=false;
        c.abrirConexion();
        ResultSet rs;
        try{
            PreparedStatement comprobarCodRep = c.getConexion().prepareStatement("SELECT * FROM reparaciones WHERE cod_rep=?");
            comprobarCodRep.setString(1, cod_rep);
            rs=comprobarCodRep.executeQuery();
            boolean facturado = false;
            while (rs.next()) {
                facturado = rs.getBoolean(8);
            }
            if(facturado==true){
                validar=true;
            }
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_Gestion_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
    
}
