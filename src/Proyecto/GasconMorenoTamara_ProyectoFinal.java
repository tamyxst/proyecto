/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import Controlador.UsuarioLogin_Controlador;
import Modelo.Gestion;
import Vista.UsuarioLogin_Vista;

/**
 *
 * @author Alumno
 */
public class GasconMorenoTamara_ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UsuarioLogin_Vista v=new UsuarioLogin_Vista();
        Gestion m=new Gestion();
        UsuarioLogin_Controlador c=new UsuarioLogin_Controlador(v,m);
        
                
    }
    
}
