/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import Modelo.Gestion;
import Vista.UsuarioLogin_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author milla_000
 */
public class UsuarioLogin_Controlador {
    private UsuarioLogin_Vista interfaceLogin;
    private Gestion gesUsuarios;
    
    public UsuarioLogin_Controlador(UsuarioLogin_Vista interfaceLogin, Gestion gesUsuarios){
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
            
            if(!gesUsuarios.comprobarLogin(nombreUsuario, pass)){
                interfaceLogin.mostrarErrores("El usuario o la contraseña no son válidos");
            }else{
                gesUsuarios.comprobarTipo(nombreUsuario, pass);
                
            }
            
        }
        
    }
}
