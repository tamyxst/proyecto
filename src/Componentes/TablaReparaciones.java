/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo_Tecnico.MVC_GestionT_Modelo;
import Vista_Tecnico.MVC_TecnicoPrincipal_Vista;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase TablaReparaciones Técnico. Clase que hereda de JPanel. 
 * Clase para que sirve para mostrar todas las reparaciones en 
 * un JTable.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class TablaReparaciones extends JPanel{
    MVC_GestionT_Modelo gesModelo=new MVC_GestionT_Modelo();
    private JPanel panel_6 = new JPanel();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    JScrollPane scrollPane = new JScrollPane(tabla);
    static String cod_rep;
    public TablaReparaciones(){
        this.cod_rep=cod_rep;
        modelo.addColumn("Cod_rep"); 
        modelo.addColumn("Problema");
        modelo.addColumn("Solución");
        modelo.addColumn("Fecha recogida");
        modelo.addColumn("Fecha Entrega");
        modelo.addColumn("Cod_cliente");
        modelo.addColumn("id");
        modelo.addColumn("Facturado");

        tabla.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int row= tabla.rowAtPoint(e.getPoint());
                cod_rep=tabla.getValueAt(row, 0).toString();
                MVC_TecnicoPrincipal_Vista.modificarR.setEnabled(true);
                MVC_TecnicoPrincipal_Vista.bajaR.setEnabled(true);
                MVC_TecnicoPrincipal_Vista.problemaRJ.setText(tabla.getValueAt(row, 1).toString());
                MVC_TecnicoPrincipal_Vista.solucionRJ.setText(tabla.getValueAt(row, 2).toString());
                MVC_TecnicoPrincipal_Vista.fecha_recogidaR.setEnabled(false);
                MVC_TecnicoPrincipal_Vista.cod_clienteRJ.setEditable(false);
                MVC_TecnicoPrincipal_Vista.comboTec.getSetEnabled(false);
                MVC_TecnicoPrincipal_Vista.anadirR.setEnabled(false);
            }
        });
        

        panel_6.setLayout(new BorderLayout());
        panel_6.add(scrollPane, BorderLayout.CENTER);
        //Llamamos al método que rellena la tabla con los datos 
        rellenarTablaReparaciones();
    }
    /**
     * Método que devuelve el panel dónde esta la tabla
     * @return devuelve el panel dónde esta la tabla
     */
    public JPanel getPanel1() {
        return panel_6;
    }
    /**
     * Devuelve el cod_rep de la reparación seleccionada en la tabla.
     * @return Devuelve el cod_rep de la reparación seleccionada en la tabla 
     */
    public static int getCodRep(){
        int codRep= Integer.parseInt(cod_rep);
        return codRep;
    }
    /**
     * Método que rellena la tabla con un ResultSet a través de una consulta
     * a la BD. Obtiene todas las filas y las añade.
     */ 
    public void rellenarTablaReparaciones() {

        try {
            ResultSet rs = gesModelo.listaReparaciones(); 
            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("cod_rep"); 
                fila[1] = rs.getString("problema");
                fila[2] = rs.getString("solucion");
                fila[3] = rs.getDate("f_recogida");
                fila[4] = rs.getDate("f_entrega");
                fila[5] = rs.getInt("cod_cliente");
                fila[6] = rs.getInt("id");
                fila[7] = rs.getBoolean("facturado");
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
        rellenarTablaReparaciones();
    }
    
}
