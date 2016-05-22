/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Proyecto.CreaUI;

/**
 *
 * @author milla_000
 */
public class MVC_Comercial_Modelo {
    public void opcionElegida(String opcion){
        if(opcion.equals("Facturas")){
            CreaUI.abrirMenuFacturas();
        }else{
            // Futuras...
        }
    }
}
