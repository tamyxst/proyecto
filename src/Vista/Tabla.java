/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.MVC_Gestion_Modelo;
import java.awt.BorderLayout;


import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author milla_000
 */
public class Tabla extends JPanel{
    MVC_Gestion_Modelo gesModelo=new MVC_Gestion_Modelo();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    private JScrollPane panelScroll;
    public Tabla() { 
        //JScrollPane panelScroll = new JScrollPane(tabla);
        panelScroll = new JScrollPane( tabla );
        add( panelScroll, BorderLayout.CENTER );
        //getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false; //Con esto conseguimos que la tabla no se pueda editar
            }
        };
        tabla.setVisible(true);
        tabla.setSize(500,300);

        tabla = new JTable(modelo); //Metemos el modelo dentro de la tabla

        modelo.addColumn("Nº Factura"); //Añadimos las columnas a la tabla (tantas como queramos)
        modelo.addColumn("Nombre");
        modelo.addColumn("Dni");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cod_rep");
        modelo.addColumn("Importe");

        rellenarTabla(); //Llamamos al método que rellena la tabla con los datos de la base de datos

        //SIN ESTO NO SALEN LOS DATOS
        panelScroll.setViewportView(tabla);
        //scrollPane.setViewportView(tabla);//Esto añade la tabla al portView del scrollPane, si estaba puesto anteriormente
        //hay que borrarlo del otro sitio, sino puede dar error de NullPointerException
    }
    public void rellenarTabla(){
 
        try {
            ResultSet rs = gesModelo.listaFacturas(); //con es la conexión que hemos creado antes con el patrón singleton               
                                               //listaEquipos() es la consulta a la base de datos, que retorna un ResultSet
            while(rs.next()){
                Object[] fila = new Object[6];//Creamos un Objeto con tantos parámetros como datos retorne cada fila 
                                              // de la consulta
                fila[0] = rs.getString("num_factura"); //Lo que hay entre comillas son los campos de la base de datos
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("dni");
                fila[3] = rs.getDate("fecha_fact");
                fila[4] = rs.getString("cod_rep");
                fila[5] = rs.getFloat("importe");
                modelo.addRow(fila); // Añade una fila al final del modelo de la tabla
            }
 
            tabla.updateUI();//Actualiza la tabla
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
    void vaciarTabla(){
        while (modelo.getRowCount() > 0) modelo.removeRow(0);
    }

}
