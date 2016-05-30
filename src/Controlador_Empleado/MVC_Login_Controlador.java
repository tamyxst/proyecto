/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Empleado;

import Proyecto.Empleado;
import Modelo_Empleado.MVC_GestionE_Modelo;
import Proyecto.CreaUI;
import Vista_Empleado.MVC_Login_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase MVC_Login_Controlador
 * Clase Login Controlador que válida los datos ingresados al loguearse.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_Login_Controlador {

    private MVC_Login_Vista interfaceLogin;
    private MVC_GestionE_Modelo gesUsuarios;

    public MVC_Login_Controlador(MVC_Login_Vista interfaceLogin, MVC_GestionE_Modelo gesUsuarios) {
        this.interfaceLogin = interfaceLogin;
        this.gesUsuarios = gesUsuarios;
        this.interfaceLogin.addValidarUsuario(new Validar());
    }

    public class Validar implements ActionListener {

        String nombreUsuario;
        String pass;
        boolean conectado;

        @Override
        public void actionPerformed(ActionEvent e) {
            nombreUsuario = interfaceLogin.getNombre();
            pass = interfaceLogin.getPass();
            
            //Comprobamos si los datos ingresados son correctos.
            if (!gesUsuarios.comprobarLogin(nombreUsuario, pass)) {
                interfaceLogin.mostrarErrores("El usuario o la contraseña no son válidos");
                conectado = false;
            } else {
                //Comprobamos el tipo de usuario que es, técnico o comercial
                String tipo = gesUsuarios.comprobarTipo(nombreUsuario, pass);
                
                //Creamos un objeto de tipo empleado
                Empleado emp = new Empleado(nombreUsuario, tipo, true);
                
                if (emp.getTipo().equals("comercial")) {
                    CreaUI.abrirMenuComercial();
                } else {
                    CreaUI.abrirMenuTecnico();
                }
                conectado = true;
            }
            //Cerramos ventana de login
            interfaceLogin.cerrarVentana();
        }
        /**
         * Método que comprueba si el empleado esta conectado
         * @return devuelve true si el empleado esta conectado.
         * Devuelve false si el empleado no esta conectado.
         */
        public boolean empleadoConectado() {
            return conectado;
        }

    }

}
