/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.NuevoUsuario_Controlador;
import Modelo.Gestion;
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
 *
 * @author milla_000
 */
public class UsuarioLogin_Vista extends JFrame {

    JFrame f = new JFrame();
    JPanel pArribaLogin = new JPanel();
    JPanel pAbajoLogin = new JPanel();
    JButton validar = new JButton("Validar");
    JButton anadirNuevoUsuario = new JButton("Nuevo Usuario");
    JLabel lusuario = new JLabel("Usuario");
    JLabel lpass = new JLabel("Contraseña");
    JTextField jUsuario = new JTextField(20);
    JTextField jpass = new JTextField(20);

    public UsuarioLogin_Vista() {
        f.setSize(250, 130);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        pArribaLogin.setLayout(new GridLayout(2, 2));
        pArribaLogin.add(lusuario);
        pArribaLogin.add(jUsuario);
        pArribaLogin.add(lpass);
        
        pArribaLogin.add(jpass);
        pAbajoLogin.setLayout(new GridLayout(1, 2));

        anadirNuevoUsuario.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                NuevoUsuario_Vista nuevoUserVista=new NuevoUsuario_Vista();
                Gestion nuevoUserGestion=new Gestion();
                NuevoUsuario_Controlador nuevoUserControl=new NuevoUsuario_Controlador(nuevoUserVista,nuevoUserGestion);
                System.out.println(nuevoUserControl);
            }
            
        });
        pAbajoLogin.add(validar);
        pAbajoLogin.add(anadirNuevoUsuario);
        f.add(pArribaLogin, BorderLayout.NORTH);
        f.add(pAbajoLogin, BorderLayout.SOUTH);
        f.setVisible(true);
        
    }
    public String getNombre(){
        return jUsuario.getText();
    }
    public String getPass(){
        return jpass.getText();
    }
    public void addValidarUsuario(ActionListener escucharBoton){
        validar.addActionListener(escucharBoton);
    }
    public void mostrarErrores(String mensajeError){
        JOptionPane.showMessageDialog(this, mensajeError);    
    }
    
}
