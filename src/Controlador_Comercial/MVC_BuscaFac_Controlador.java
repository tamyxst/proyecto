/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import Modelo_Comercial.MVC_BuscaFac_Modelo;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author milla_000
 */
public class MVC_BuscaFac_Controlador {
        private MVC_ComercialPrincipal_Vista buscaFactVista;
    private MVC_BuscaFac_Modelo buscaFactModelo;

    public MVC_BuscaFac_Controlador(MVC_ComercialPrincipal_Vista buscaFactVista, MVC_BuscaFac_Modelo buscaFactModelo) {
        this.buscaFactVista = buscaFactVista;
        this.buscaFactModelo = buscaFactModelo;
    }
    public class BuscarPorDniClientes implements ActionListener {
        
        Date fechaPrimera;
        Date fechaUltima;
        String codPostal;
        String poblacion;

        @Override
        public void actionPerformed(ActionEvent e) {
        
            fechaPrimera= buscaFactVista.getFechaPrimera();
            fechaUltima=buscaFactVista.getFechaUltima();
            codPostal=buscaFactVista.getCodPostalListadoFras();
            poblacion=buscaFactVista.getPoblacionListadoFras();
            
            //
            Pattern pat = Pattern.compile("[0-9]{5}");
            Matcher mat = pat.matcher(codPostal);
            
            java.util.Date fechaPrimera = new java.util.Date();
            java.sql.Date sqlDatePrimera = new java.sql.Date(fechaPrimera.getTime());
            
            java.util.Date fechaUltima = new java.util.Date();
            java.sql.Date sqlDateUltima = new java.sql.Date(fechaUltima.getTime());
            
            if (mat.matches()) {
                //Ejecutar codigo
                
                
                
                
            } else {
                 buscaFactVista.mostrarErroresFacturas("El código postal no es válido.");
            }
        }

    }
}
