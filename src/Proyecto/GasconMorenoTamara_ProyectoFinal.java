/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import Controlador.GestionUsuario_Controlador;
import Modelo.GestionUsuarios;
import Vista.Login_Vista;

/**
 *
 * @author Alumno
 */
public class GasconMorenoTamara_ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login_Vista v=new Login_Vista();
        GestionUsuarios m=new GestionUsuarios();
        GestionUsuario_Controlador c=new GestionUsuario_Controlador(v,m);
        
                
    }
    
}
