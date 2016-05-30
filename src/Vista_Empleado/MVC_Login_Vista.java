/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_Empleado;

import Controlador_Empleado.MVC_NuevoUser_Controlador;
import Proyecto.CreaUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Clase MVC_Login_Vista. 
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_Login_Vista extends JFrame {
    MVC_NuevoUser_Controlador control;
    JFrame f = new JFrame();
    JPanel pArribaLogin = new JPanel();
    JPanel pAbajoLogin = new JPanel();
    JButton validar = new JButton("Validar");
    JButton anadirNuevoUsuario = new JButton("Nuevo Usuario");
    JLabel lusuario = new JLabel("Usuario");
    JLabel lpass = new JLabel("Contraseña");
    JTextField jUsuario = new JTextField(20);
    JTextField jpass = new JTextField(20);

    public MVC_Login_Vista() {
        f.setSize(250, 130);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        pArribaLogin.setLayout(new GridLayout(2, 2));
        pArribaLogin.add(lusuario);
        pArribaLogin.add(jUsuario);
        pArribaLogin.add(lpass);

        pArribaLogin.add(jpass);
        pAbajoLogin.setLayout(new GridLayout(1, 2));

        anadirNuevoUsuario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                CreaUI.abrirMenuNuevoUsuario();
            }

        });
        pAbajoLogin.add(validar);
        pAbajoLogin.add(anadirNuevoUsuario);
        f.add(pArribaLogin, BorderLayout.NORTH);
        f.add(pAbajoLogin, BorderLayout.SOUTH);
        f.setVisible(true);

    }
    /**
     * 
     * @return Devuelve el nombre del empleado 
     */
    public String getNombre() {
        return jUsuario.getText();
    }
    /**
     * 
     * @return Devuelve el pass del empleado
     */
    public String getPass() {
        return jpass.getText();
    }
    /**
     * Método que escucha los botones
     * @param escucharBoton de tipo ActionListener
     */
    public void addValidarUsuario(ActionListener escucharBoton) {
        validar.addActionListener(escucharBoton);
    }
    /**
     * Método que muestra el mensaje de error
     * @param mensajeError 
     */
    public void mostrarErrores(String mensajeError) {
        JOptionPane.showMessageDialog(this, mensajeError);
    }
    /**
     * Método que cierra la ventana
     */
    public void cerrarVentana(){
        f.dispose();
    }

}
