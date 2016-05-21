/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author milla_000
 */
public class MVC_Comercial_Vista extends JFrame{

    JFrame fComercial = new JFrame();
    JPanel pComercial = new JPanel();
    JButton facturas = new JButton("Facturas");
    JButton articulos = new JButton("Articulos");

    public MVC_Comercial_Vista() {
        fComercial.setLocationRelativeTo(null);
        fComercial.setSize(250, 150);
        fComercial.setResizable(false);
        
        pComercial.setLayout(new GridLayout (2,1));
        pComercial.add(facturas);
        pComercial.add(articulos);
        fComercial.add(pComercial);
        fComercial.setVisible(true);
    }

    public void seleccionarBotonComercial(ActionListener escucharBoton){
        facturas.addActionListener(escucharBoton);
        articulos.addActionListener(escucharBoton);
    } 
}
