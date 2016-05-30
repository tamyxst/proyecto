/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import Modelo_Comercial.MVC_BuscaC_Modelo;
import Vista_Comercial.MVC_BuscaC_Vista;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Clase MVC_BuscaC_Controlador. 
 * Clase Controlador de Búsqueda clientes por dni.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_BuscaC_Controlador {

    private MVC_BuscaC_Vista buscarClientesV;
    private MVC_BuscaC_Modelo buscarClientesM;
   

    public MVC_BuscaC_Controlador(MVC_BuscaC_Vista buscarClientesV, MVC_BuscaC_Modelo buscarClientesM) {
        this.buscarClientesV = buscarClientesV;
        this.buscarClientesM = buscarClientesM;
        this.buscarClientesV.buscarPorDniClientes(new BuscarPorDniClientes());
    }

    public class BuscarPorDniClientes implements ActionListener {

        String dni;

        @Override
        public void actionPerformed(ActionEvent e) {
            dni = buscarClientesV.getBuscar();
            ResultSet rs;
            int cod_cliente = 0;
            try {
                rs = buscarClientesM.buscarClientesPorDni(dni);
                    while (rs.next()) {
                        cod_cliente = rs.getInt(1);
                    }
                    if(cod_cliente==0) {
                        buscarClientesV.mostrarMensajeErrorBuscar("No hay resultados.");
                    }else{
                        //System.out.println(cod_cliente); 
                        String cadena = String.valueOf(cod_cliente);
                        //Ponemos en el JTextField el código de cliente localizado por dni
                        MVC_ComercialPrincipal_Vista.codClienteJ.setText(cadena);
                        buscarClientesV.cerrarVentanaBuscadorClientes();
                    }
 
            } catch (SQLException ex) {
                Logger.getLogger(ex.getMessage());
            }

        }

    }
}
