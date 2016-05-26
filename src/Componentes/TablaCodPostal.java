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
 *
 * @author Alumno
 */
public class TablaCodPostal {
    MVC_GestionC_Modelo gesModelo = new MVC_GestionC_Modelo();
    private JPanel panel_5 = new JPanel();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    JScrollPane scrollPane = new JScrollPane(tabla);
    String cod_postal;
    public TablaCodPostal(){
        modelo.addColumn("Nº Factura"); //Añadimos las columnas a la tabla (tantas como queramos)
        modelo.addColumn("Fecha");
        modelo.addColumn("Cod_rep");
        modelo.addColumn("Importe");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dni");

        panel_5.setLayout(new BorderLayout());
        panel_5.add(scrollPane, BorderLayout.CENTER);

        rellenarTablaPorCodPostal(cod_postal);
    }
    public JPanel getPanel1() {
        return panel_5;
    }
        //Llamamos al método que rellena la tabla con los datos de la base de datos

        //SIN ESTO NO SALEN LOS DATOS
    //panelScroll.setViewportView(tabla);//Esto añade la tabla al portView del scrollPane, si estaba puesto anteriormente
    //hay que borrarlo del otro sitio, sino puede dar error de NullPointerException
    public void rellenarTablaPorCodPostal(String cod_postal) {

        try {
            ResultSet rs = gesModelo.listaPorCodPostal(cod_postal); //con es la conexión que hemos creado antes con el patrón singleton               
            //listaEquipos() es la consulta a la base de datos, que retorna un ResultSet
            while (rs.next()) {
                Object[] fila = new Object[6];//Creamos un Objeto con tantos parámetros como datos retorne cada fila 
                // de la consulta
                fila[0] = rs.getString("num_factura"); //Lo que hay entre comillas son los campos de la base de datos
                fila[1] = rs.getDate("fecha_fact");
                fila[2] = rs.getString("cod_rep");
                fila[3] = rs.getFloat("importe");
                fila[4] = rs.getString("nombre");
                fila[5] = rs.getString("dni");
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
        rellenarTablaPorCodPostal(cod_postal);
    }
    
}
