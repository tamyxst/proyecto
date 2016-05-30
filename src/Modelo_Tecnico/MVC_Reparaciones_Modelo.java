/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Tecnico;

import Proyecto.Conexion;
import Modelo_Comercial.MVC_GestionC_Modelo;
import Proyecto.Reparacion;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase MVC_GestionT_Modelo
 * Clase Modelo, dónde se realizan las consultas a la BD.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_Reparaciones_Modelo {

    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);

    public MVC_Reparaciones_Modelo() {

    }
    /**
     * Método que carga el JComboBox con una consulta a la BD.
     * Carga los usuarios empleados que son técnicos.
     * @return Devuelve los datos del los usuarios que son técnicos.
     */
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
    /**
     * Método que guarda a través de un procedimiento almacenado las reparaciones.
     * @param r Objeto de tipo reparación.
     */
    public void grabarReparacion(Reparacion r) {
        CallableStatement cs;
        try {
            c.abrirConexion();
            cs = c.getConexion().prepareCall("{call insertarReparacion_en(?,?,?,?,?,?,?)}");
            cs.setString(1, r.getProblema());
            cs.setString(2, r.getSolucion());
            cs.setDate(3, r.getF_recogida());
            cs.setDate(4, r.getF_entrega());
            cs.setInt(5, r.getCod_cliente());
            cs.setInt(6, r.getId());
            cs.setBoolean(7, r.isFacturado());
            cs.execute();

        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método que inserta a través de un procedimiento almacenado en la BD,
     * las reparaciones sin fecha de entrega.
     * @param r Objeto de la clase reparación.
     */
    public void grabarReparacionSinFechaEntrega(Reparacion r) {
        CallableStatement cs;
        Date f_entrega =null;
        try {
            c.abrirConexion();
            cs = c.getConexion().prepareCall("{call insertarReparacionSinFecha_en(?,?,?,?,?,?,?)}");
            cs.setString(1, r.getProblema());
            cs.setString(2, r.getSolucion());
            cs.setDate(3, r.getF_recogida());
            cs.setDate(4, f_entrega);
            cs.setInt(5, r.getCod_cliente());
            cs.setInt(6, r.getId());
            cs.setBoolean(7, r.isFacturado());
            cs.execute();

        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método que modifica la tabla reparaciones en la BD.
     * @param r Objeto de la clase reparación.
     */
    public void modificarReparacion(Reparacion r) {
        c.abrirConexion();
        ResultSet rs;
        try {
            PreparedStatement modificarRep = c.getConexion().prepareStatement("UPDATE reparaciones SET problema=?,solucion=?,f_entrega=? WHERE cod_rep=?");
            modificarRep.setString(1, r.getProblema());
            modificarRep.setString(2, r.getSolucion());
            modificarRep.setDate(3, r.getF_entrega());
            modificarRep.setInt(4, r.getCod_rep());
            modificarRep.executeUpdate();

            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método que da de baja las reparaciones en la BD, a través del cod_rep. 
     * @param r Objeto de la clase reparación.
     */
    public void bajaReparacion(Reparacion r) {
        c.abrirConexion();
        ResultSet rs;
        try {
            PreparedStatement bajaRep = c.getConexion().prepareStatement("DELETE from reparaciones where cod_rep=?");
            bajaRep.setInt(1, r.getCod_rep());
            bajaRep.executeUpdate();
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método que busca clientes por código de cliente y devuelve
     * true si lo encuentra en la BD.
     * @param cod_cliente Corresponde al cod_cliente del cliente.
     * @return devuelve true si encuentra al cliente en la BD. Devuelve
     * false si no lo encuentra.
     */
    public boolean buscarClientesCodCliente(int cod_cliente) {
        boolean validar=false;
        c.abrirConexion();
        ResultSet rs;
        try {
            PreparedStatement comprobarCodCliente = c.getConexion().prepareStatement("SELECT * FROM clientes WHERE cod_cliente=?");
            comprobarCodCliente.setInt(1, cod_cliente);
            rs = comprobarCodCliente.executeQuery();

            validar = rs.first();
            c.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
}
