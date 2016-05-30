package Componentes;

import Modelo_Tecnico.MVC_Reparaciones_Modelo;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Clase ComboTecnicos del paquete Componentes. Clase que hereda de JPanel.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */
public final class ComboTecnicos extends JPanel {

    JComboBox combo = new JComboBox();
    MVC_Reparaciones_Modelo gesModeloReparaciones = new MVC_Reparaciones_Modelo();
    private JPanel panel1 = new JPanel();
    String nombre; //Aquí guardaremos el dato seleccionado

    public ComboTecnicos() {
        this.nombre = nombre;
        llenar();
        panel1.setLayout(new BorderLayout());
        panel1.add(combo, BorderLayout.CENTER);

        
        combo.addItemListener(new ItemListener() {
            @Override
            //Recogemos los cambios de selección del combobox
            public void itemStateChanged(ItemEvent arg0) { 
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    //Coge el elemento seleccionado y lo almacena en la variable nombre
                    nombre = (String) combo.getSelectedItem(); 
                    
                }
            }
        });
    }
    /**
     * Método que a través de una consulta a la BD recibe el nombre de los usuarios
     * que son técnicos y los añade a un combobox. 
     */
    public void llenar() {
        try {
            ResultSet rs = gesModeloReparaciones.cargarCombo();
            String comboId;
            combo.addItem("Seleccione un Técnico");
            while (rs.next()) {
                //Seleccionar nº de columna en la bd que corresponda al nombre del técnico.
                comboId = rs.getString(1);
                combo.addItem(comboId);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Método que sirve para llamar el panel dónde hemos añadido el combobox
     * anteriormente.
     * @return Devuelve el panel dónde hemos añadido el combobox.
     */
    public JPanel getPanel1() {
        return panel1;
    }
    /**
     * Devuelve el dato seleccionado en el combobox
     * Lo llamaremos desde la vista.
     * @return Devuelve el nombre del técnico seleccionado en el combobox.
     */
    public String getNombreTecnico() {
        return nombre;
    }
    /**
     * Método que sirve para activar o desactivar el combobox.
     * @param dato Valor booleano. Si es true el combobox estará activo, si
     * es false estará desactivado.
     */
    public void getSetEnabled(boolean dato){
        combo.setEnabled(dato);
    }

}
