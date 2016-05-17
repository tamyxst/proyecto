/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.GestionUsuarios;
import Vista.Login_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author milla_000
 */
public class GestionUsuario_Controlador {
    private Login_Vista interfaceLogin;
    private GestionUsuarios gesUsuarios;
    
    public GestionUsuario_Controlador(Login_Vista interfaceLogin, GestionUsuarios gesUsuarios){
        this.interfaceLogin=interfaceLogin;
        this.gesUsuarios=gesUsuarios;
        this.interfaceLogin.addValidarUsuario(new ValidarListener());
    }
    public class ValidarListener implements ActionListener{
        String nombreUsuario;
        String pass;
       
        @Override
        public void actionPerformed(ActionEvent e) {
            nombreUsuario = interfaceLogin.getNombre();
            pass = interfaceLogin.getPass();
            
            System.out.println(nombreUsuario);
            System.out.println(pass);
            gesUsuarios.comprobarLogin(nombreUsuario, pass);
            
        }
        
    }
}
