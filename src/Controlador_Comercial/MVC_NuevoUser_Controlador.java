/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import Modelo_Comercial.MVC_GestionFac_Modelo;
import Vista_Comercial.MVC_NuevoUser_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author milla_000
 */
public class MVC_NuevoUser_Controlador {

    private MVC_NuevoUser_Vista nuevoUsuario_vista;
    private MVC_GestionFac_Modelo gestionUser;

    public MVC_NuevoUser_Controlador(MVC_NuevoUser_Vista nuevoUsuario_vista, MVC_GestionFac_Modelo gestionUser) {
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
            nombreN = nuevoUsuario_vista.getNombreNuevoUsuario();
            passN = nuevoUsuario_vista.getPassNuevoUsuario();
            tipoN = nuevoUsuario_vista.getTipoNuevoUsuario();

            System.out.println(nombreN);
            System.out.println(passN);
            System.out.println(tipoN);
            
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
