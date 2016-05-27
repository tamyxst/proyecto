/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_Empleado;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

/**
 *
 * @author milla_000
 */
public class MVC_NuevoUser_Vista extends JFrame{
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
    
    public MVC_NuevoUser_Vista(){
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
        tecnico.setActionCommand("tecnico");
        buttonGroup.add(comercial);
        comercial.setActionCommand("comercial");
        tecnico.setSelected(true); //por defecto
        pArribaNuevoUsuario.add(tecnico);
        pArribaNuevoUsuario.add(comercial);
        pAbajoNuevoUsuario.add(anadirNuevo);
        
        
        fNuevoUsuario.add(pArribaNuevoUsuario, BorderLayout.NORTH);
        fNuevoUsuario.add(pAbajoNuevoUsuario, BorderLayout.SOUTH);
        fNuevoUsuario.setVisible(true);
    }
    
    public String getNombreNuevoUsuario(){
        return jNombreUsuario.getText();
    }
    public String getPassNuevoUsuario(){
        return jPassUsuario.getText();
    }
    public String getTipoNuevoUsuario(){
        String choice = buttonGroup.getSelection().getActionCommand();
        return choice;
    }
    public void addAnadirUsuario(ActionListener escucharBoton){
        anadirNuevo.addActionListener(escucharBoton);
    } 
    
    public void mostrarErrores(String mensajeError){
        JOptionPane.showMessageDialog(this, mensajeError);    
    }
    public void cerrarVentana(){
        fNuevoUsuario.dispose();
    }
        
}
