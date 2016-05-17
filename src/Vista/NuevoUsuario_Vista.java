/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Modelo.Empleado;
import Modelo.Conexion;

/**
 *
 * @author milla_000
 */
public class NuevoUsuario_Vista extends JFrame{
    JFrame fNuevoUsuario=new JFrame();
    JPanel pArribaNuevoUsuario=new JPanel();
    JPanel pAbajoNuevoUsuario=new JPanel();
    JButton anadirNuevo=new JButton("Añadir");
    JRadioButton tecnico=new JRadioButton("Técnico");
    JRadioButton comercial=new JRadioButton("Comercial");
    ButtonGroup buttonGroup;
    JLabel lNombreUsuario=new JLabel("Nombre");
    JTextField jNombreUsuario=new JTextField(20);
    JLabel lPassUsuario=new JLabel("Contraseña");
    JTextField jPassUsuario=new JTextField(20);
    
    NuevoUsuario_Vista(){
        fNuevoUsuario.setSize(200,200);
        fNuevoUsuario.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fNuevoUsuario.setLocationRelativeTo(null);
        
        pArribaNuevoUsuario.setLayout(new GridLayout(3, 2));
        pArribaNuevoUsuario.add(lNombreUsuario);
        pArribaNuevoUsuario.add(jNombreUsuario);
        pArribaNuevoUsuario.add(lPassUsuario);
        pArribaNuevoUsuario.add(jPassUsuario);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(tecnico);
        tecnico.setActionCommand("Técnico");
        buttonGroup.add(comercial);
        comercial.setActionCommand("Comercial");
        pArribaNuevoUsuario.add(tecnico);
        pArribaNuevoUsuario.add(comercial);
        pAbajoNuevoUsuario.add(anadirNuevo);
        
        
        anadirNuevo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //Empleado e=new Empleado();
                String choice = buttonGroup.getSelection().getActionCommand();
                Empleado emp=new Empleado(jNombreUsuario.getText(),jPassUsuario.getText(),choice);
                //Conexion.darAltaUsuario(jNombreUsuario.getText(),jPassUsuario.getText(),choice);
            }
            
        });
        fNuevoUsuario.add(pArribaNuevoUsuario, BorderLayout.NORTH);
        fNuevoUsuario.add(pAbajoNuevoUsuario, BorderLayout.SOUTH);
        fNuevoUsuario.setVisible(true);
    }
        
}
