package Vista_Comercial;

import Modelo_Comercial.MVC_Reparaciones_Modelo;
import java.awt.BorderLayout;
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
public final class CargarTecnicos {
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    JComboBox combo = new JComboBox(modeloCombo);
    MVC_Reparaciones_Modelo gesModeloReparaciones=new MVC_Reparaciones_Modelo();
    private final JPanel panel1=new JPanel();
    
    public CargarTecnicos(){
        llenar();
        panel1.setLayout(new BorderLayout());
        panel1.add(combo, BorderLayout.CENTER);
        
    }
        public void llenar() {
            try {
                ResultSet rs = gesModeloReparaciones.cargarCombo();
                modeloCombo.addElement("Seleccione un Técnico");
                
                
                while (rs.next()) {
                    //Seleccionar nº de columna en la bd que corresponda al nombre del técnico.
                    modeloCombo.addElement(rs.getString(1));
                }
                
     
            } catch (SQLException e) {
                 e.printStackTrace();
            } 
        }
        
        public JPanel getPanel1(){
            return panel1;
        }
        
    
}
