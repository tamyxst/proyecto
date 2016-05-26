/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import Modelo_Comercial.Empleado;
import Modelo_Comercial.MVC_GestionC_Modelo;
import Proyecto.CreaUI;
import Vista_Comercial.MVC_Login_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author milla_000
 */
public class MVC_Login_Controlador {

    private MVC_Login_Vista interfaceLogin;
    private MVC_GestionC_Modelo gesUsuarios;

    public MVC_Login_Controlador(MVC_Login_Vista interfaceLogin, MVC_GestionC_Modelo gesUsuarios) {
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

            if (!gesUsuarios.comprobarLogin(nombreUsuario, pass)) {
                interfaceLogin.mostrarErrores("El usuario o la contraseña no son válidos");
                conectado = false;
            } else {
                String tipo = gesUsuarios.comprobarTipo(nombreUsuario, pass);
                Empleado emp = new Empleado(nombreUsuario, tipo, true);
                if (emp.getTipo().equals("comercial")) {
                    CreaUI.abrirMenuComercial();
                } else {
                    CreaUI.abrirMenuTecnico();
                }

                conectado = true;
            }
            interfaceLogin.cerrarVentana();
        }

        /**
         * Event Dispatch Thread
         *
         * @return
         */
        /*public void actionPerformed(ActionEvent e) {
         Runnable miRunnable = new Runnable() {
         public void run() {
         try {
         nombreUsuario = interfaceLogin.getNombre();
         pass = interfaceLogin.getPass();

         if (!gesUsuarios.comprobarLogin(nombreUsuario, pass)) {
         interfaceLogin.mostrarErrores("El usuario o la contraseña no son válidos");
         conectado = false;
         } else {
         String tipo = gesUsuarios.comprobarTipo(nombreUsuario, pass);
         Empleado emp = new Empleado(nombreUsuario, tipo, true);
         if (emp.getTipo().equals("comercial")) {
         interfaceLogin.abrirMenuComercial();
         }
         conectado = true;

         }
         System.out.println("Me han pulsado");
         Thread.sleep(10000); //Tarea que consume diez segundos.
         System.out.println("Terminé");
         } catch (Exception e) {
         e.printStackTrace();
         }
         }
         };
         Thread hilo = new Thread(miRunnable);
         hilo.start();
         interfaceLogin.cerrarVentana ();
         }*/
        public boolean empleadoConectado() {
            return conectado;
        }

    }

}
