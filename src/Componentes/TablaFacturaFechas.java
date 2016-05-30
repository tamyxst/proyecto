/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo_Comercial.MVC_GestionC_Modelo;
import java.awt.BorderLayout;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase TablaFacturaFechas Comercial. Clase que hereda de JPanel. 
 * Clase para que sirve para cuando el comercial quiera visualizar
 * las facturas realizadas entre fecha de inicio xxxx-xx-xx y 
 * xxxx-xx-xx en un JTable.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class TablaFacturaFechas extends JPanel{
    MVC_GestionC_Modelo gesModelo = new MVC_GestionC_Modelo();
    private JPanel panel_3 = new JPanel();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    JScrollPane scrollPane = new JScrollPane(tabla);
    java.sql.Date Date1; //Fecha inicio
    java.sql.Date Date2; //Fecha ultima
    
    public TablaFacturaFechas(){
        modelo.addColumn("Nº Factura"); //Añadimos las columnas a la tabla 
        modelo.addColumn("Fecha");
        modelo.addColumn("Cod_rep");
        modelo.addColumn("Importe");

        panel_3.setLayout(new BorderLayout());
        panel_3.add(scrollPane, BorderLayout.CENTER);
        
        //Llamamos al método que rellena la tabla con los datos
        rellenarTablaEntreFechas(Date1, Date2);
    }
    /**
     * Método que devuelve el panel dónde esta la TablaFacturaFechas
     * @return devuelve el panel dónde esta la tablaFacturaFechas
     */
    public JPanel getPanel1() {
        return panel_3;
    }
    /**
     * Método que rellena la tabla con un ResultSet a través de una consulta
     * a la BD. Obtiene todas las filas y las añade.
     * @param fecha1 corresponde a la fecha de inicio
     * @param fecha2 corresponde a la fecha del final
     */
    public void rellenarTablaEntreFechas(Date fecha1, Date fecha2) {
        ResultSet res=null;
        try {
             res = gesModelo.listaFacturasEntreFechas(fecha1, fecha2);            
            //es la consulta a la base de datos, que retorna un ResultSet
            while (res.next()) {
               //Creamos un Objeto
                Object[] fila = new Object[4];
                fila[0] = res.getString("num_factura"); //campos de la base de datos
                fila[1] = res.getDate("fecha_fact");
                fila[2] = res.getInt("cod_rep");
                fila[3] = res.getFloat("importe");
                modelo.addRow(fila); // Añadimos la fila
            }

            tabla.updateUI();//Actualizamos

        } catch (SQLException e) {
            
            e.printStackTrace();
        }

    }
    //Método que vacia la tabla Factura fechas si encuentra algún dato.
    public void vaciarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    //Método que actualiza la tabla factura fechas
    public void actualizarTabla() {
        vaciarTabla();
        rellenarTablaEntreFechas(Date1,Date2);
    }
    
    
}
