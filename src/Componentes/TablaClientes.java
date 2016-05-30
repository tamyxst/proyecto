/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo_Comercial.MVC_Clientes_Modelo;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
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
 * Clase TablaClientes Comercial y Técnico. Clase que hereda de JPanel. 
 * Se muestra un listado de todos los clientes, recogidos de la BD en un JTable.
 * El comercial cuando pulsa cualquier celda de la tabla, automáticamente
 * los datos se situarán en sus campos correspondientes para que puedan 
 * ser modificados. El botón añadir será desactivado.

 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */
public class TablaClientes extends JPanel{
    MVC_Clientes_Modelo gesModeloClientes = new MVC_Clientes_Modelo();
    private final JPanel panel_2 = new JPanel();
    DefaultTableModel modeloCli = new DefaultTableModel();
    JTable tablaCli = new JTable(modeloCli);
    JScrollPane scrollPaneCli = new JScrollPane(tablaCli);
    static String cod_cliente; //Recogemos el cod_cliente para modificar.
    
    public TablaClientes(){
        modeloCli.addColumn("Cod Cliente"); //Añadimos las columnas a la tabla
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
				
                                /**
                                 * row nos devolverá -1 si se hace click fuera, si no devolvera el indice de la fila en la que hace click.
                                 * Cuando seleccionamos una celda los datos seleccionados apareceran en los campos, para modificar.
                                 */
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
        
        //Llamamos al método que rellena la tabla con los datos
        rellenarTablaClientes();
    }
    /**
     * Método que devuelve el panel dónde esta la tabla clientes
     * @return devuelve el panel dónde esta la tabla clientes
     */
    public JPanel getPanel2() {
        return panel_2;
    }
    /**
     * Devuelve el cod_cliente seleccionado para modificarlo
     * @return devuelve el cod_cliente para cuando hagamos la modificación localizarlo en la BD.
     */
    public static int getCodCliente(){
        int CodCliente= Integer.parseInt(cod_cliente);
        return CodCliente;
    }
    /**
     * Método que rellena la tabla con un ResultSet a través de una consulta a la BD.
     * Obtiene todas las filas de la tabla clientes y las añade.
     */
    public void rellenarTablaClientes() {

        try {
            //es la consulta a la base de datos, que retorna un ResultSet
            ResultSet rs = gesModeloClientes.listaClientes(); 
            while (rs.next()) {
                Object[] fila = new Object[6];//Creamos un Objeto con tantos parámetros como datos retorne cada fila 
                // de la consulta
                fila[0] = rs.getInt("cod_cliente"); //Lo que hay entre comillas son los campos de la base de datos
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellidos");
                fila[3] = rs.getString("dni");
                fila[4] = rs.getString("cod_postal");
                fila[5] = rs.getString("telefono");
                modeloCli.addRow(fila); // Añadimos fila
            }

            tablaCli.updateUI();//Actualizamos

        } catch (SQLException e) {
            
            e.printStackTrace();
        }

    }
    //Método que vacia la tabla clientes si encuentra algún dato.
    public void vaciarTablaClientes() {
        while (modeloCli.getRowCount() > 0) {
            modeloCli.removeRow(0);
        }
    }
    //Método que actualiza la tabla clientes
    public void actualizarTablaClientes() {
        vaciarTablaClientes();
        rellenarTablaClientes();
    }
    //Método que limpia los campos
    public void limpiarCamposClientes(){
        MVC_ComercialPrincipal_Vista.nombreCliJ.setText("");
    	MVC_ComercialPrincipal_Vista.apellidosCliJ.setText("");
    	MVC_ComercialPrincipal_Vista.dniCliJ.setText("");
    	MVC_ComercialPrincipal_Vista.cod_postalCliJ.setText("");
    	MVC_ComercialPrincipal_Vista.telefonoCliJ.setText("");
    }
    

    

    
}
