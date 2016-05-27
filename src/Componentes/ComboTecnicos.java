package Componentes;

import Modelo_Tecnico.MVC_Reparaciones_Modelo;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author milla_000
 */
public final class ComboTecnicos {

    //DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    JComboBox combo = new JComboBox();
    MVC_Reparaciones_Modelo gesModeloReparaciones = new MVC_Reparaciones_Modelo();
    private JPanel panel1 = new JPanel();
    String nombre;

    public ComboTecnicos() {
        this.nombre = nombre;
        llenar();
        panel1.setLayout(new BorderLayout());
        panel1.add(combo, BorderLayout.CENTER);

        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) { // picks up changes to combobox selection
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    nombre = (String) combo.getSelectedItem(); // takes the selected item
                    
                }
            }
        });
    }

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

    public JPanel getPanel1() {
        return panel1;
    }

    public String getNombreTecnico() {
        return nombre;
    }
    
    public void getSetEnabled(){
        combo.setEnabled(false);
    }
    

}
