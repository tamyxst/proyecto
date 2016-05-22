/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import Modelo.Empleado;

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

        CreaUI.abrirMenuFacturas();
        //CreaUI.abrirMenuLogin();
        //Antes de ejecutar esto tiene que meter los datos
        //Calcular entre dos fechasJCalendar comprobar entre dos fechas
    }
}
