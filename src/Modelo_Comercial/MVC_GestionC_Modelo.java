/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Comercial;

import Proyecto.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase MVC_GestionC_Modelo
 * Clase Modelo, dónde se realizan las consultas a la BD.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_GestionC_Modelo {

    String servidor = "jdbc:mysql://localhost/";
    String bd = "tienda";
    String usuario = "user";
    String password = "1234";

    Conexion c = new Conexion(servidor, bd, usuario, password);

    
    /**
     * Consulta a la BD de todas las facturas.
     * @return Devuelve todos los datos de la consulta.
     * @throws SQLException.
     */
    public ResultSet listaFacturas() throws SQLException {
        ResultSet rs = null;
        c.abrirConexion();
        try {
            String consulta = "SELECT f.num_factura,f.fecha_fact,f.cod_rep,f.importe,e.nombre,e.dni "
                    + "FROM facturas f JOIN clientes e ON(f.cod_cliente=e.cod_cliente)"
                    + "GROUP BY f.num_factura,f.fecha_fact,f.cod_rep,f.importe,e.nombre,e.dni ";
            Statement st = c.getConexion().createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        } 
        return rs;
    }
    /**
     * Método que consulta las facturas realizadas entre fechas.
     * @param fechaPrimera Corresponde a la fecha de inicio
     * @param fechaUltima Corresponde a la fecha final
     * @return Devuelve un resultSet con el listado de facturas
     */
    public ResultSet listaFacturasEntreFechas(Date fechaPrimera, Date fechaUltima) {
        c.abrirConexion();
        ResultSet rs = null;
        try {
            PreparedStatement consulta = c.getConexion().prepareStatement("select * from facturas where fecha_fact>=? AND fecha_fact<=? ORDER BY fecha_fact");
            consulta.setDate(1, fechaPrimera);
            consulta.setDate(2, fechaUltima);
            rs = consulta.executeQuery();

            //No se debe cerrar la conexion para mostrar tablas
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    /**
     * Consulta a la BD de todas las reparaciones no facturadas.
     * @return Devuelve un ResultSet con el listado de reparaciones
     */
    public ResultSet listaReparaciones() {
        c.abrirConexion();
        ResultSet rs = null;
        try {
            String consulta = "SELECT * FROM reparaciones WHERE facturado=false";
            Statement st = c.getConexion().createStatement();
            rs = st.executeQuery(consulta);
            //No se debe cerrar la conexion para mostrar tablas
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        } 
        return rs;
    }
    /**
     * Metodo que localiza las facturas que tiene un cliente, por código postal.
     * @param cod_postal Corresponde al cod_postal del cliente que tiene facturas
     * @return Devuelve un ResulSet 
     */
    public ResultSet listaPorCodPostal(String cod_postal) {
        c.abrirConexion();
        ResultSet rs = null;
        try {
            PreparedStatement consulta = c.getConexion().prepareStatement("select * from facturas f JOIN clientes c where f.cod_cliente=c.cod_cliente AND c.cod_postal=?");
            consulta.setString(1, cod_postal);
            rs = consulta.executeQuery();
            //No se debe cerrar la conexion para mostrar tablas
        } catch (SQLException ex) {
            Logger.getLogger(MVC_GestionC_Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
