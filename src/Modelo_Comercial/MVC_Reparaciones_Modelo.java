/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Comercial;

import java.sql.CallableStatement;
import java.sql.Date;
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
public class MVC_Reparaciones_Modelo {

    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);

    public MVC_Reparaciones_Modelo() {

    }

    public ResultSet cargarCombo() {
        ResultSet rs = null;
        String tecnico = "tecnico";
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

    public void grabarReparacion(Reparacion r) {
        CallableStatement cs;
        try {
            c.abrirConexion();
            cs = c.getConexion().prepareCall("{call insertarReparacion_en(?,?,?,?,?,?,?)}");
            cs.setString(1, r.getProblema());
            cs.setString(2, r.getSolucion());
            cs.setDate(3, r.getF_recogida());
            cs.setDate(5, r.getF_entrega());
            cs.setInt(6, r.getId());
            cs.setBoolean(7, r.isFacturado());
            cs.execute();

        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarReparacion(Reparacion r) {
        c.abrirConexion();
        ResultSet rs;
        try {
            PreparedStatement modificarRep = c.getConexion().prepareStatement("UPDATE reparaciones SET problema=?,solucion=?,f_entrega=? WHERE cod_rep=?");
            modificarRep.setString(1, r.getProblema());
            modificarRep.setString(2, r.getSolucion());
            modificarRep.setDate(3, r.getF_entrega());
            modificarRep.setInt(4, r.getCod_cliente());
            modificarRep.executeUpdate();

            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void bajaReparacion(Reparacion r){  
        c.abrirConexion();
        ResultSet rs;
        try{
            PreparedStatement bajaRep = c.getConexion().prepareStatement("DELETE from reparaciones where cod_rep=?");
            bajaRep.setInt(1, r.getCod_cliente());
            bajaRep.executeUpdate();

            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

