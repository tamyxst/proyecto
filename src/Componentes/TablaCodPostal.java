/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo_Comercial.MVC_GestionC_Modelo;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase TablaCodPostal Comercial. Clase que hereda de JPanel. 
 * El comercial cuando introduzca el código postal, se mostrará
 * una tabla con las "facturas" realizadas a los "Clientes" que
 * vivan en ese código postal. Se mostrará en un JTable.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class TablaCodPostal extends JPanel{
    MVC_GestionC_Modelo gesModelo = new MVC_GestionC_Modelo();
    private JPanel panel_5 = new JPanel();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    JScrollPane scrollPane = new JScrollPane(tabla);
    String cod_postal; //Recogemos el cod_postal para hacer la consulta
    
    public TablaCodPostal(){
        modelo.addColumn("Nº Factura"); //Añadimos las columnas a la tabla 
        modelo.addColumn("Fecha");
        modelo.addColumn("Cod_rep");
        modelo.addColumn("Importe");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dni");

        panel_5.setLayout(new BorderLayout());
        panel_5.add(scrollPane, BorderLayout.CENTER);
        
        //Llamamos al método que rellena la tabla con los datos
        rellenarTablaPorCodPostal(cod_postal);
    }
    /**
     * Método que devuelve el panel dónde esta la TablaCodPostal
     * @return devuelve el panel dónde esta la tablaCodPostal
     */
    public JPanel getPanel1() {
        return panel_5;
    }
    
    /**
     * Método que rellena la tabla con un ResultSet a través de una consulta a la BD.
     * Obtiene todas las filas de la tabla y las añade.
     * @param cod_postal Corresponde al código postal del cliente a buscar.
     */
    public void rellenarTablaPorCodPostal(String cod_postal) {

        try {
             //es la consulta a la base de datos, que retorna un ResultSet
            ResultSet rs = gesModelo.listaPorCodPostal(cod_postal); 
            while (rs.next()) {
                Object[] fila = new Object[6];//Creamos un Objeto con tantos parámetros como datos retorne cada fila 
                // de la consulta
                fila[0] = rs.getInt("num_factura"); //Lo que hay entre comillas son los campos de la base de datos
                fila[1] = rs.getDate("fecha_fact");
                fila[2] = rs.getInt("cod_rep");
                fila[3] = rs.getFloat("importe");
                fila[4] = rs.getString("nombre");
                fila[5] = rs.getString("dni");
                modelo.addRow(fila); // Añadimos fila
            }

            tabla.updateUI();//Actualizamos

        } catch (SQLException e) {
           
            e.printStackTrace();
        }

    }
    
   //Método que vacia la tabla clientes si encuentra algún dato.
    public void vaciarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    //Método que actualiza la tabla clientes    
    public void actualizarTabla() {
        vaciarTabla();
        rellenarTablaPorCodPostal(cod_postal);
    }
    
}
