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
 * Clase TablaPendientesFacturar Comercial. Clase que hereda de JPanel. 
 * Clase que sirve para mostrar todas las reparaciones pendientes
 * de facturar en un JTable.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class TablaPendientesFacturar extends JPanel{
    MVC_GestionC_Modelo gesModelo = new MVC_GestionC_Modelo();
    private final JPanel panel_4 = new JPanel();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    JScrollPane scrollPane = new JScrollPane(tabla);
    
    public TablaPendientesFacturar(){
        modelo.addColumn("Cod_rep"); 
        modelo.addColumn("Problema");
        modelo.addColumn("Solución");
        modelo.addColumn("Fecha recogida");
        modelo.addColumn("Fecha Entrega");
        modelo.addColumn("Cod_cliente");
        modelo.addColumn("id");
        modelo.addColumn("Facturado");

        panel_4.setLayout(new BorderLayout());
        panel_4.add(scrollPane, BorderLayout.CENTER);
        //Llamamos al método que rellena la tabla con los datos 
        rellenarTablaReparaciones();
    }
     /**
     * Método que devuelve el panel dónde esta la TablaPendientesFacturar
     * @return devuelve el panel dónde esta la TablaPendientesFacturar
     */
    public JPanel getPanel1() {
        return panel_4;
    }
    /**
     * Método que rellena la tabla con un ResultSet a través de una consulta
     * a la BD. Obtiene todas las filas y las añade.
     */ 
    public void rellenarTablaReparaciones() {

        try {
            ResultSet rs = gesModelo.listaReparaciones(); 
            while (rs.next()) {
                Object[] fila = new Object[9];
                fila[0] = rs.getInt("cod_rep"); 
                fila[1] = rs.getString("problema");
                fila[2] = rs.getString("solucion");
                fila[3] = rs.getDate("f_recogida");
                fila[4] = rs.getDate("f_entrega");
                fila[5] = rs.getInt("cod_cliente");
                fila[6] = rs.getInt("id");
                fila[8] = rs.getBoolean("facturado");
                modelo.addRow(fila); 
            }
            tabla.updateUI();//Actualizamos

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
        rellenarTablaReparaciones();
    }
    
   
}
