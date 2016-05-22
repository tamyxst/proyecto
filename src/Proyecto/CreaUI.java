/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import Controlador.MVC_BuscarClientes_Controlador;
import Controlador.MVC_ComercialClientes_Controlador;
import Modelo.MVC_BuscarClientes_Modelo;
import Controlador.MVC_ComercialFacturas_Controlador;
import Controlador.MVC_Login_Controlador;
import Controlador.MVC_NuevoUser_Controlador;
import Modelo.MVC_ComercialClientes_Modelo;
import Modelo.MVC_ComercialFacturas_Modelo;
import Modelo.MVC_GestionFacturas_Modelo;
import Vista.MVC_BuscarClientes_Vista;
import Vista.MVC_ComercialPrincipal_Vista;
import Vista.MVC_Comercial_Vista;
import Vista.MVC_Login_Vista;
import Vista.MVC_NuevoUser_Vista;

/**
 *
 * @author milla_000
 */
public class CreaUI {
    public static void abrirMenuLogin(){
        MVC_Login_Vista v = new MVC_Login_Vista();
        MVC_GestionFacturas_Modelo m = new MVC_GestionFacturas_Modelo();
        MVC_Login_Controlador c = new MVC_Login_Controlador(v, m);
    }
    public static void abrirMenuNuevoUsuario(){
        MVC_NuevoUser_Vista nuevoUserVista = new MVC_NuevoUser_Vista();
        MVC_GestionFacturas_Modelo nuevoUserGestion = new MVC_GestionFacturas_Modelo();
        MVC_NuevoUser_Controlador nuevoUserControl = new MVC_NuevoUser_Controlador(nuevoUserVista, nuevoUserGestion);
    }
    
    public static void abrirMenuComercial(){
        MVC_Comercial_Vista vComercial=new MVC_Comercial_Vista();
    }
    
    public static void abrirMenuTecnico(){
        
    }
    
    public static void abrirMenuFacturas(){
        MVC_ComercialPrincipal_Vista v=new MVC_ComercialPrincipal_Vista();
        MVC_ComercialFacturas_Modelo mFacturas=new MVC_ComercialFacturas_Modelo();
        MVC_ComercialClientes_Modelo mClientes=new MVC_ComercialClientes_Modelo();
        MVC_ComercialClientes_Controlador cClientes=new MVC_ComercialClientes_Controlador(v,mClientes);
        MVC_ComercialFacturas_Controlador cFacturas=new MVC_ComercialFacturas_Controlador(v,mFacturas,mClientes,cClientes);
    }
    
    public static void abrirMenuClientes(){
        MVC_ComercialPrincipal_Vista v=new MVC_ComercialPrincipal_Vista();
        MVC_ComercialClientes_Modelo mClientes=new MVC_ComercialClientes_Modelo();
        MVC_ComercialClientes_Controlador cClientes=new MVC_ComercialClientes_Controlador(v,mClientes);
    }
    
    public static void abrirMenuBuscarClientes(){
        MVC_BuscarClientes_Vista vbClientes=new MVC_BuscarClientes_Vista();
        MVC_BuscarClientes_Modelo mbClientes=new MVC_BuscarClientes_Modelo();
        MVC_BuscarClientes_Controlador cbClientes=new MVC_BuscarClientes_Controlador(vbClientes,mbClientes);
    }
    
    
            
}
