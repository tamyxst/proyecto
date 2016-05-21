/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import Controlador.MVC_Login_Controlador;
import Modelo.MVC_Gestion_Modelo;
import Vista.MVC_Comercial_Vista;
import Vista.MVC_Login_Vista;

/**
 *
 * @author milla_000
 */
public class CreaUI {
    public static void abrirMenuLogin(){
        MVC_Login_Vista v = new MVC_Login_Vista();
        MVC_Gestion_Modelo m = new MVC_Gestion_Modelo();
        MVC_Login_Controlador c = new MVC_Login_Controlador(v, m);
    }
    public static void abrirMenuComercial(){
        MVC_Comercial_Vista vComercial=new MVC_Comercial_Vista();
    }
    
    public static void abrirMenuTecnico(){
        
    }
    
    public static void abrirMenuFacturas(){
        
    }
    
    public static void abrirMenuArticulos(){
        
    }
            
}
