/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.Date;
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
    
    public void grabarNuevaFactura(String nombre,String dni,Date fecha_fact,String cod_rep,float importe){
        CallableStatement cs;
        try{
            c.abrirConexion();
            cs=c.getConexion().prepareCall("{call insertarFactura_en(?,?,?,?,?)}");
            cs.setString(1, nombre);
            cs.setString(2, dni);
            cs.setDate(3, fecha_fact);
            cs.setString(4, cod_rep);
            cs.setFloat(5, importe);
            cs.execute();
                    
        }catch(SQLException ex){
            Logger.getLogger(MVC_Gestion_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
