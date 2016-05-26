/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import Controlador_Comercial.MVC_BuscaC_Controlador;
import Controlador_Comercial.MVC_Buscador_Controlador;
import Controlador_Comercial.MVC_Clientes_Controlador;
import Modelo_Comercial.MVC_BuscaC_Modelo;
import Controlador_Comercial.MVC_Facturas_Controlador;
import Controlador_Comercial.MVC_Login_Controlador;
import Controlador_Comercial.MVC_NuevoUser_Controlador;
import Modelo_Comercial.MVC_Clientes_Modelo;
import Modelo_Comercial.MVC_Facturas_Modelo;
import Modelo_Comercial.MVC_GestionC_Modelo;
import Vista_Comercial.MVC_BuscaC_Vista;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import Vista_Comercial.MVC_Comercial_Vista;
import Vista_Comercial.MVC_Login_Vista;
import Vista_Comercial.MVC_NuevoUser_Vista;
import Vista_Comercial.MVC_TecnicoPrincipal_Vista;

/**
 *
 * @author milla_000
 */
public class CreaUI {
    public static void abrirMenuLogin(){
        MVC_Login_Vista v = new MVC_Login_Vista();
        MVC_GestionC_Modelo m = new MVC_GestionC_Modelo();
        MVC_Login_Controlador c = new MVC_Login_Controlador(v, m);
    }
    public static void abrirMenuNuevoUsuario(){
        MVC_NuevoUser_Vista nuevoUserVista = new MVC_NuevoUser_Vista();
        MVC_GestionC_Modelo nuevoUserGestion = new MVC_GestionC_Modelo();
        MVC_NuevoUser_Controlador nuevoUserControl = new MVC_NuevoUser_Controlador(nuevoUserVista, nuevoUserGestion);
    }
    
    public static void abrirMenuComercial(){
        MVC_Comercial_Vista vComercial=new MVC_Comercial_Vista();
    }
    
    public static void abrirMenuTecnico(){
        MVC_TecnicoPrincipal_Vista tecPrinVis=new MVC_TecnicoPrincipal_Vista();
        
    }
    
    public static void abrirMenuFacturas(){
        MVC_ComercialPrincipal_Vista v=new MVC_ComercialPrincipal_Vista();
        MVC_Facturas_Modelo mFacturas=new MVC_Facturas_Modelo();
        MVC_Clientes_Modelo mClientes=new MVC_Clientes_Modelo();
        MVC_Clientes_Controlador cClientes=new MVC_Clientes_Controlador(v,mClientes);
        MVC_Buscador_Controlador buscaFac=new MVC_Buscador_Controlador(v);
        MVC_Facturas_Controlador cFacturas=new MVC_Facturas_Controlador(v,mFacturas,mClientes,cClientes,buscaFac);
        
    }
    
    public static void abrirMenuClientes(){
        MVC_ComercialPrincipal_Vista comerPrinVis=new MVC_ComercialPrincipal_Vista();
        MVC_Clientes_Modelo mClientes=new MVC_Clientes_Modelo();
        MVC_Clientes_Controlador cClientes=new MVC_Clientes_Controlador(comerPrinVis,mClientes);
    }
    
    public static void abrirMenuBuscarClientes(){
        MVC_BuscaC_Vista vbClientes=new MVC_BuscaC_Vista();
        MVC_BuscaC_Modelo mbClientes=new MVC_BuscaC_Modelo();
        MVC_BuscaC_Controlador cbClientes=new MVC_BuscaC_Controlador(vbClientes,mbClientes);
    }
    
          
}
