/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import Controlador.MVC_Login_Controlador;
import Controlador.MVC_Login_Controlador.Validar;
import Modelo.Empleado;
import Modelo.MVC_Gestion_Modelo;
import Vista.MVC_Login_Vista;

/**
 *
 * @author Alumno
 */
public class GasconMorenoTamara_ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String nombre = "";
        String tipo = "";
        boolean conectado = false;
        boolean validar = false;

        Empleado emp = new Empleado(nombre, tipo, conectado);

        //Sino esta logueado ejecuta esto...
        /*MVC_Login_Vista v = new MVC_Login_Vista();
        MVC_Gestion_Modelo m = new MVC_Gestion_Modelo();
        MVC_Login_Controlador c = new MVC_Login_Controlador(v, m);
        Validar valida = c.new Validar();*/
        
        CreaUI.abrirMenuLogin();

        //Antes de ejecutar esto tiene que meter los datos
        //Calcular entre dos fechasJCalendar comprobar entre dos fechas
    }
}
