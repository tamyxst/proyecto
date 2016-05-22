/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Proyecto.CreaUI;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author milla_000
 */
public class MVC_Comercial_Vista extends JFrame{

    JFrame fComercial = new JFrame("Menu Comercial");
    JPanel pComercial = new JPanel();
    JButton facturas = new JButton("Facturas");
    JButton articulos = new JButton("Articulos");

    public MVC_Comercial_Vista() {
        fComercial.setTitle("Menu Comercial");
        fComercial.setSize(200, 100);
        fComercial.setLocationRelativeTo(null);
        
        
        facturas.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                CreaUI.abrirMenuFacturas();
            }
            
        });
        
        pComercial.setLayout(new GridLayout (2,1));
        pComercial.add(facturas);
        pComercial.add(articulos);
        fComercial.add(pComercial);
        fComercial.setVisible(true);
    }

    public void seleccionarBotonComercial(ActionListener escucharBotonComercial){
        facturas.addActionListener(escucharBotonComercial);
        articulos.addActionListener(escucharBotonComercial);
    } 
}
