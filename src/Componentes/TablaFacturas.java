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
 * Clase TablaFacturas Comercial. Clase que hereda de JPanel. 
 * Clase para que sirve para mostrar todas las facturas
 * realizadas a los clientes en un JTable.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */
public class TablaFacturas extends JPanel{

    MVC_GestionC_Modelo gesModelo = new MVC_GestionC_Modelo();
    private JPanel panel_1 = new JPanel();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    JScrollPane scrollPane = new JScrollPane(tabla);

    public TablaFacturas() {

        modelo.addColumn("Nº Factura"); //Añadimos las columnas a la tabla 
        modelo.addColumn("Fecha");
        modelo.addColumn("Cod_rep");
        modelo.addColumn("Importe");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dni");

        panel_1.setLayout(new BorderLayout());
        panel_1.add(scrollPane, BorderLayout.CENTER);
        
        //Llamamos al método que rellena la tabla con los datos 
        rellenarTabla();
    }
    /**
     * Método que devuelve el panel dónde esta la TablaFacturas
     * @return devuelve el panel dónde esta la tablaFacturas
     */
    public JPanel getPanel1() {
        return panel_1;
    }
        
    /**
     * Método que rellena la tabla con un ResultSet a través de una consulta
     * a la BD. Obtiene todas las filas y las añade.
     */    
    public void rellenarTabla() {

        try {
            ResultSet rs = gesModelo.listaFacturas(); 
            while (rs.next()) {
                //Creamos un Objeto
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("num_factura"); 
                fila[1] = rs.getDate("fecha_fact");
                fila[2] = rs.getInt("cod_rep");
                fila[3] = rs.getFloat("importe");
                fila[4] = rs.getString("nombre");
                fila[5] = rs.getString("dni");
                modelo.addRow(fila);
            }
            tabla.updateUI();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
     //Método que vacia la tabla si encuentra algún dato.
    public void vaciarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    //Método que actualiza la tabla
    public void actualizarTabla() {
        vaciarTabla();
        rellenarTabla();
    }
    
   
}
