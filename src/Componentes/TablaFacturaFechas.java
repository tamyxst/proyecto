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
 *
 * @author Alumno
 */
public class TablaFacturaFechas {
    MVC_GestionC_Modelo gesModelo = new MVC_GestionC_Modelo();
    private JPanel panel_3 = new JPanel();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    JScrollPane scrollPane = new JScrollPane(tabla);
    java.sql.Date Date1;
    java.sql.Date Date2;
    
    public TablaFacturaFechas(){
        modelo.addColumn("Nº Factura"); //Añadimos las columnas a la tabla (tantas como queramos)
        modelo.addColumn("Fecha");
        modelo.addColumn("Cod_rep");
        modelo.addColumn("Importe");

        panel_3.setLayout(new BorderLayout());
        panel_3.add(scrollPane, BorderLayout.CENTER);

        rellenarTablaEntreFechas(Date1, Date2);
    }
    
    public JPanel getPanel1() {
        return panel_3;
    }
    public void rellenarTablaEntreFechas(Date fecha1, Date fecha2) {
        ResultSet res=null;
        try {
             res = gesModelo.listaFacturasEntreFechas(fecha1, fecha2); //con es la conexión que hemos creado antes con el patrón singleton               
            //listaEquipos() es la consulta a la base de datos, que retorna un ResultSet
            while (res.next()) {
                Object[] fila = new Object[4];//Creamos un Objeto con tantos parámetros como datos retorne cada fila 
                // de la consulta
                fila[0] = res.getString("num_factura"); //Lo que hay entre comillas son los campos de la base de datos
                fila[1] = res.getDate("fecha_fact");
                fila[2] = res.getInt("cod_rep");
                fila[3] = res.getFloat("importe");
                modelo.addRow(fila); // Añade una fila al final del modelo de la tabla
            }

            tabla.updateUI();//Actualiza la tabla

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void vaciarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    public void actualizarTabla() {
        vaciarTabla();
        rellenarTablaEntreFechas(Date1,Date2);
    }
    
    
}
