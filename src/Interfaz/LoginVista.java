/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author milla_000
 */
public class LoginVista extends JFrame {
    JFrame f=new JFrame();
    JPanel pArribaLogin=new JPanel();
    JPanel pAbajoLogin=new JPanel();
    JButton validar=new JButton("Validar");
    JButton anadirNuevoUsuario=new JButton("Nuevo Usuario");
    JLabel lusuario=new JLabel("Usuario");
    JLabel lpass=new JLabel("Contraseña");
    JTextField jusuario=new JTextField(20);
    JTextField jpass=new JTextField(20);
    
    LoginVista(){
        f.setSize(250,130);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        
        pArribaLogin.setLayout(new GridLayout(2,2));
        pArribaLogin.add(lusuario);
        pArribaLogin.add(jusuario);
        pArribaLogin.add(lpass);
        pArribaLogin.add(jpass);
        pAbajoLogin.setLayout(new GridLayout(1,2));
        pAbajoLogin.add(validar);
        pAbajoLogin.add(anadirNuevoUsuario);
        f.add(pArribaLogin, BorderLayout.NORTH);
        f.add(pAbajoLogin, BorderLayout.SOUTH);
        
        f.setVisible(true);
        
        
    }
}
