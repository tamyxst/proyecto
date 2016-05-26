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
public class TablaPendientesFacturar {
    MVC_GestionC_Modelo gesModelo = new MVC_GestionC_Modelo();
    private JPanel panel_4 = new JPanel();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    JScrollPane scrollPane = new JScrollPane(tabla);
    
    public TablaPendientesFacturar(){
        modelo.addColumn("Cod_rep"); //Añadimos las columnas a la tabla (tantas como queramos)
        modelo.addColumn("Problema");
        modelo.addColumn("Solución");
        modelo.addColumn("Fecha recogida");
        modelo.addColumn("Fecha Entrega");
        modelo.addColumn("Cod_cliente");
        modelo.addColumn("id");
        modelo.addColumn("Facturado");

        panel_4.setLayout(new BorderLayout());
        panel_4.add(scrollPane, BorderLayout.CENTER);

        rellenarTablaReparaciones();
    }
    public JPanel getPanel1() {
        return panel_4;
    }
    public void rellenarTablaReparaciones() {

        try {
            ResultSet rs = gesModelo.listaReparaciones(); //con es la conexión que hemos creado antes con el patrón singleton               
            //listaEquipos() es la consulta a la base de datos, que retorna un ResultSet
            while (rs.next()) {
                Object[] fila = new Object[9];//Creamos un Objeto con tantos parámetros como datos retorne cada fila 
                // de la consulta
                fila[0] = rs.getInt("cod_rep"); //Lo que hay entre comillas son los campos de la base de datos
                fila[1] = rs.getString("problema");
                fila[2] = rs.getString("solucion");
                fila[3] = rs.getDate("f_recogida");
                fila[4] = rs.getDate("f_entrega");
                fila[5] = rs.getInt("cod_cliente");
                fila[6] = rs.getInt("id");
                fila[8] = rs.getBoolean("facturado");
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
        rellenarTablaReparaciones();
    }
    
   
}
