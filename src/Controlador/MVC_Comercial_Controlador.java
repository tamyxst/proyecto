/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MVC_Comercial_Modelo;
import Vista.MVC_Comercial_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author milla_000
 */
public class MVC_Comercial_Controlador {
    MVC_Comercial_Vista comercialVista;
    MVC_Comercial_Modelo comercialModelo;
    
    MVC_Comercial_Controlador(MVC_Comercial_Vista comercialVista,MVC_Comercial_Modelo comercialModelo){
        this.comercialVista=comercialVista;
        this.comercialModelo=comercialModelo;
        this.comercialVista.seleccionarBotonComercial(new SeleccionarBoton());
    }
    
    public class SeleccionarBoton implements ActionListener{
      String opcion;
        @Override
        public void actionPerformed(ActionEvent e) {
            opcion = e.getActionCommand();
            comercialModelo.opcionElegida(opcion);
        }
        
    }
}
