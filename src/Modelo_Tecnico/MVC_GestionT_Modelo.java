/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Tecnico;

import Componentes.Conexion;
import Componentes.Reparacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author milla_000
 */
public class MVC_GestionT_Modelo {

    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);

    public MVC_GestionT_Modelo() {

    }

    public ResultSet listaReparaciones() {
        c.abrirConexion();
        ResultSet rs = null;
        try {
            String consulta = "SELECT * FROM reparaciones";
            Statement st = c.getConexion().createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        return rs;
    }

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
