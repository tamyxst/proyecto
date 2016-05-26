/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Comercial;

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
public class MVC_Facturas_Modelo {
    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);
    
    public MVC_Facturas_Modelo(){
        
    }
    
    public void grabarNuevaFactura(Date fecha_fact,int cod_rep,float importe, int cod_cliente){
        CallableStatement cs;
        try{
            c.abrirConexion();
            cs=c.getConexion().prepareCall("{call insertarFactura_en(?,?,?,?)}");
            cs.setDate(1, fecha_fact);
            cs.setInt(2, cod_rep);
            cs.setFloat(3, importe);
            cs.setInt(4,cod_cliente);
            cs.execute();
                    
        }catch(SQLException ex){
            Logger.getLogger(MVC_GestionFac_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void marcarReparacionFacturada(int cod_rep){
        boolean facturado=true;
        c.abrirConexion();
        ResultSet rs;
        try{
            PreparedStatement marcarRep = c.getConexion().prepareStatement("UPDATE reparaciones SET facturado=? WHERE cod_rep =?");
            marcarRep.setBoolean(1, facturado);
            marcarRep.setInt(2, cod_rep);
            marcarRep.executeUpdate();
            
            
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionFac_Modelo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MVC_GestionFac_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
    public void abrirBuscarClientes(){
        CreaUI.abrirMenuBuscarClientes();
    }
    
    public boolean buscarCodigoReparacion(int cod_rep){
        boolean validar=true;
        c.abrirConexion();
        ResultSet rs;
        try{
            PreparedStatement comprobarCodRep = c.getConexion().prepareStatement("SELECT * FROM reparaciones WHERE cod_rep=?");
            comprobarCodRep.setInt(1, cod_rep);
            rs=comprobarCodRep.executeQuery();
            boolean facturado=true;
            while (rs.next()) {
                facturado = rs.getBoolean(8);
            }
            if(facturado==false){
                validar=false;
            }
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionFac_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
    
}
