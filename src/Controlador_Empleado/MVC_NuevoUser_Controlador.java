/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Empleado;

import Modelo_Empleado.MVC_GestionE_Modelo;
import Vista_Empleado.MVC_NuevoUser_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase MVC_NuevoUser_Controlador
 * Clase Nuevo Usuario Controlador que válida si los datos ingresados al 
 * dar de alta al nuevo usuario son válidos antes de ingresarlos en la BD.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_NuevoUser_Controlador {

    private MVC_NuevoUser_Vista nuevoUsuario_vista;
    private MVC_GestionE_Modelo gestionUser;

    public MVC_NuevoUser_Controlador(MVC_NuevoUser_Vista nuevoUsuario_vista, MVC_GestionE_Modelo gestionUser) {
        this.nuevoUsuario_vista = nuevoUsuario_vista;
        this.gestionUser = gestionUser;
        this.nuevoUsuario_vista.addAnadirUsuario(new AnadirUsuarioListener());
    }

    public class AnadirUsuarioListener implements ActionListener {
        
        String nombreN;
        String passN;
        String tipoN;

        @Override
        public void actionPerformed(ActionEvent e) {
            //Recogemos los datos
            nombreN = nuevoUsuario_vista.getNombreNuevoUsuario();
            passN = nuevoUsuario_vista.getPassNuevoUsuario();
            tipoN = nuevoUsuario_vista.getTipoNuevoUsuario();
            
            //Imprimimos los datos
            System.out.println(nombreN);
            System.out.println(passN);
            System.out.println(tipoN);
            
            //Expresión regular para la pass
            Pattern pat = Pattern.compile("[(^[A-Z]{1,2}[a-z]{2}[a-z0-9]{0,}[0-9]{2,}[$]$)]{8,}");
            Matcher mat = pat.matcher(passN);
            
            
            if (nombreN.equals("")) {
                nuevoUsuario_vista.mostrarErrores("El campo nombre esta vacío");
            } else if (passN.equals("")) {
                nuevoUsuario_vista.mostrarErrores("El campo contraseña esta vacío");
            }else if (mat.matches()) {
                gestionUser.altaNuevoUsuario(nombreN,passN,tipoN);
                nuevoUsuario_vista.cerrarVentana();
            } else {
                 nuevoUsuario_vista.mostrarErrores("La contraseña no es válida.");
            }
        }

    }

}
