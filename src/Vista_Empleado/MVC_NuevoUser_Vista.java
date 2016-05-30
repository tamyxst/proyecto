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
 * Clase MVC_NuevoUser_Vista. 
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
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
    /**
     * 
     * @return Devuelve el nombre del usuario 
     */
    public String getNombreNuevoUsuario(){
        return jNombreUsuario.getText();
    }
    /**
     * 
     * @return Devuelve la pass del usuario
     */
    public String getPassNuevoUsuario(){
        return jPassUsuario.getText();
    }
    /**
     * 
     * @return Devuelve el tipo de usuario
     */
    public String getTipoNuevoUsuario(){
        String choice = buttonGroup.getSelection().getActionCommand();
        return choice;
    }
    /**
     * Método que escucha botones
     * @param escucharBoton de tipo ActionListener
     */
    public void addAnadirUsuario(ActionListener escucharBoton){
        anadirNuevo.addActionListener(escucharBoton);
    } 
    /**
     * Método que lanza un mensaje de error
     * @param mensajeError Contiene el mensaje de error
     */
    public void mostrarErrores(String mensajeError){
        JOptionPane.showMessageDialog(this, mensajeError);    
    }
    /**
     * Método que cierra la ventana
     */
    public void cerrarVentana(){
        fNuevoUsuario.dispose();
    }
        
}
