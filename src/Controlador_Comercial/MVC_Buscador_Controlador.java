/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Clase MVC_Buscador_Controlador
 * Clase Controlador que válida los datos código postal, fechas, y que solicita
 * al modelo las consultas para mostrar los listados de facturas por código postal,
 * por fechas y listado de reparaciones no facturadas.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_Buscador_Controlador {

    private final MVC_ComercialPrincipal_Vista buscaFactVista;

    public MVC_Buscador_Controlador(MVC_ComercialPrincipal_Vista buscaFactVista) {
        this.buscaFactVista = buscaFactVista;
        this.buscaFactVista.addEventosBusquedaFacturas(new BusquedaFacturas());
    }

    public class BusquedaFacturas implements ActionListener {

        String opcion; //Opción que escoge el comercial
        Date fechaPrimera;
        Date fechaUltima;
        String codPostal;

        @Override
        public void actionPerformed(ActionEvent e) {

            fechaPrimera = buscaFactVista.getFechaPrimera();
            fechaUltima = buscaFactVista.getFechaUltima();
            codPostal = buscaFactVista.getCodPostalListadoFras();

            opcion = e.getActionCommand();
            try {
                if (opcion.equals("Mostrar por fechas")) {
                    //Vaciamos la tabla para que si se muestra otra vez no se duplique
                    buscaFactVista.vaciarTablaFechas();
                    buscaFactVista.mostrarTablaFechas(fechaPrimera, fechaUltima);
                } else if (opcion.equals("Mostrar Reparaciones")) {
                    //Vaciamos la tabla para que si se muestra otra vez no se duplique
                    buscaFactVista.vaciarTablaReparaciones();
                    buscaFactVista.mostrarTablaReparaciones();
                }
                if (opcion.equals("Mostrar")) {
                    //Vaciamos la tabla para que si se muestra otra vez no se duplique
                    buscaFactVista.vaciarTablaFacturas();
                    buscaFactVista.mostrarTablaFacturas(codPostal);
                }
            } catch (NullPointerException ex) {
                //Nos aseguramos de que se selecciona una fecha
                buscaFactVista.mostrarErroresPanelComercial("No has seleccionado ninguna fecha");
            }

        }
    }
}
