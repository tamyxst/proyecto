/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Tecnico;

import Proyecto.Conexion;
import Proyecto.Reparacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Clase MVC_GestionT_Modelo
 * Clase Modelo, dónde se realizan las consultas a la BD.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_GestionT_Modelo {

    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);

    public MVC_GestionT_Modelo() {

    }
    /**
     * Método que realiza una consulta a la BD de las reparaciones.
     * @return Devuelve un ResultSet de todos los datos.
     */
    public ResultSet listaReparaciones() {
        c.abrirConexion();
        ResultSet rs = null;
        try {
            String consulta = "SELECT cod_rep,problema,solucion,f_recogida,f_entrega,cod_cliente,id,facturado FROM reparaciones";
            Statement st = c.getConexion().createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        return rs;
    }
    /**
     * Método que carga las reparaciones
     * @return Devuelve un ResultSet con los datos.
     */
    public ResultSet cargarReparaciones() {
        c.abrirConexion();
        ResultSet rs = null;
        try {
            String consulta = "SELECT problema, f_recogida, f_entrega, cod_cliente, id FROM reparaciones";
            Statement st = c.getConexion().createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        return rs;
    }
    /**
     * Método que añade reparaciones desde el fichero
     * @param r 
     */
    public void anadirReparacionesDesdeFichero(ArrayList<Reparacion> r) {
        String solucion="";
        c.abrirConexion();
        try {
            PreparedStatement inRep = c.getConexion().prepareStatement("INSERT INTO reparaciones VALUES (NULL,?,?,?,?,?,?,?)");
            for (int i = 0; i < r.size(); i++) {
                inRep.setString(1, r.get(i).getProblema());
                inRep.setString(2, solucion);
                inRep.setDate(3, r.get(i).getF_recogida());
                inRep.setDate(4, r.get(i).getF_entrega());
                inRep.setInt(5, r.get(i).getCod_cliente());
                inRep.setInt(6, r.get(i).getId());
                inRep.setBoolean(7, false);
                inRep.addBatch();
            }
            inRep.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    
}
