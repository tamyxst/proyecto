/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo_Comercial.MVC_Clientes_Modelo;
import Modelo_Comercial.MVC_GestionC_Modelo;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author milla_000
 */
public class TablaClientes {
    MVC_Clientes_Modelo gesModeloClientes = new MVC_Clientes_Modelo();
    private JPanel panel_2 = new JPanel();
    DefaultTableModel modeloCli = new DefaultTableModel();
    JTable tablaCli = new JTable(modeloCli);
    JScrollPane scrollPaneCli = new JScrollPane(tablaCli);
    static String cod_cliente;
    public TablaClientes(){
        modeloCli.addColumn("Cod Cliente"); //Añadimos las columnas a la tabla (tantas como queramos)
        modeloCli.addColumn("Nombre");
        modeloCli.addColumn("Apellidos");
        modeloCli.addColumn("Dni");
        modeloCli.addColumn("Cod Postal");
        modeloCli.addColumn("Telefono");
        this.cod_cliente=cod_cliente;
        tablaCli.addMouseListener(new MouseAdapter() 
   		{
                      @Override
      		public void mouseClicked(MouseEvent e) 
      		{
         		int row = tablaCli.rowAtPoint(e.getPoint());
                                
                        
                                cod_cliente=tablaCli.getValueAt(row, 0).toString();
				/* row devolvera -1 si se ha clicado fuera de la fila pero dentro de la tabla, si no devolvera el indice de la fila en la que se ha clicado. */
				MVC_ComercialPrincipal_Vista.nombreCliJ.setText(tablaCli.getValueAt(row, 1).toString());
   				MVC_ComercialPrincipal_Vista.apellidosCliJ.setText(tablaCli.getValueAt(row, 2).toString());
                                MVC_ComercialPrincipal_Vista.dniCliJ.setText(tablaCli.getValueAt(row, 3).toString());
   				MVC_ComercialPrincipal_Vista.cod_postalCliJ.setText(tablaCli.getValueAt(row, 4).toString());
                                MVC_ComercialPrincipal_Vista.telefonoCliJ.setText(tablaCli.getValueAt(row, 5).toString());
                                MVC_ComercialPrincipal_Vista.anadirCli.setEnabled(false);
      		}
   		});
        
        panel_2.setLayout(new BorderLayout());
        panel_2.add(scrollPaneCli, BorderLayout.CENTER);
        
        rellenarTablaClientes();
    }
    public JPanel getPanel2() {
        return panel_2;
    }
    public static int getCodCliente(){
        int CodCliente= Integer.parseInt(cod_cliente);
        return CodCliente;
    }
    
    public void rellenarTablaClientes() {

        try {
            ResultSet rs = gesModeloClientes.listaClientes(); //con es la conexión que hemos creado antes con el patrón singleton               
            //listaEquipos() es la consulta a la base de datos, que retorna un ResultSet
            while (rs.next()) {
                Object[] fila = new Object[6];//Creamos un Objeto con tantos parámetros como datos retorne cada fila 
                // de la consulta
                fila[0] = rs.getInt("cod_cliente"); //Lo que hay entre comillas son los campos de la base de datos
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellidos");
                fila[3] = rs.getString("dni");
                fila[4] = rs.getString("cod_postal");
                fila[5] = rs.getString("telefono");
                modeloCli.addRow(fila); // Añade una fila al final del modelo de la tabla
            }

            tablaCli.updateUI();//Actualiza la tabla

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void vaciarTablaClientes() {
        while (modeloCli.getRowCount() > 0) {
            modeloCli.removeRow(0);
        }
    }

    public void actualizarTablaClientes() {
        vaciarTablaClientes();
        rellenarTablaClientes();
    }
    
    public void limpiarTabla(){
        MVC_ComercialPrincipal_Vista.nombreCliJ.setText("");
    	MVC_ComercialPrincipal_Vista.apellidosCliJ.setText("");
    	MVC_ComercialPrincipal_Vista.dniCliJ.setText("");
    	MVC_ComercialPrincipal_Vista.cod_postalCliJ.setText("");
    	MVC_ComercialPrincipal_Vista.telefonoCliJ.setText("");
    }
    

    

    
}
