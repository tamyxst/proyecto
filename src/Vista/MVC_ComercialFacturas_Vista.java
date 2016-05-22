/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author milla_000
 */
public class MVC_ComercialFacturas_Vista extends JFrame{
    JFrame fFact = new JFrame();
    JPanel pDatosFacturaA1 = new JPanel();
    JPanel panelIzda = new JPanel();
    JPanel panelDcho = new JPanel();
    JPanel pFacturasA2 = new JPanel();
    JPanel pClientesA3 = new JPanel();
    JPanel pListadoFact=new JPanel();
    JLabel nombreCF, dniCF, apellidosCF, direccionCF, codPostalCF, telefonoCF, fechaCF;
    JTextField nombreJF, dniJF, apellidosJF, direccionJF, codPostalJF, telefonoJF, fechaJF;
public MVC_ComercialFacturas_Vista(){
    fFact.setLocationRelativeTo(null);
    fFact.setSize(900, 500);
    
    fFact.add(panelIzda, BorderLayout.WEST);
    fFact.add(panelDcho, BorderLayout.EAST);
    

    //t.setVisible(true);
    fFact.getContentPane().add( new Tabla(),BorderLayout.CENTER );
    fFact.setVisible(true);
}
}
