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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author milla_000
 */
public class MVC_Buscador_Controlador {

    private final MVC_ComercialPrincipal_Vista buscaFactVista;

    public MVC_Buscador_Controlador(MVC_ComercialPrincipal_Vista buscaFactVista) {
        this.buscaFactVista = buscaFactVista;
        this.buscaFactVista.addEventosBusquedaFacturas(new BusquedaFacturas());
    }

    public class BusquedaFacturas implements ActionListener {

        String opcion;
        Date fechaPrimera;
        Date fechaUltima;
        String codPostal;

        @Override
        public void actionPerformed(ActionEvent e) {

            fechaPrimera = buscaFactVista.getFechaPrimera();
            fechaUltima = buscaFactVista.getFechaUltima();
            codPostal = buscaFactVista.getCodPostalListadoFras();

            Pattern pat = Pattern.compile("[0-9]{0,5}");
            Matcher mat = pat.matcher(codPostal);

            //System.out.println(sqlDateUlti);
            //System.out.println(sqlDatePrime);*/
            opcion = e.getActionCommand();
            try {
                if (opcion.equals("Mostrar por fechas")) {
                    buscaFactVista.mostrarTablaFechas(fechaPrimera, fechaUltima);
                } else if (opcion.equals("Mostrar Reparaciones")) {
                    buscaFactVista.mostrarTablaReparaciones();
                }
                if (opcion.equals("Mostrar")) {
                    buscaFactVista.mostrarTablaFacturas(codPostal);
                }

           //if (mat.matches()) {
                //Ejecutar codigo
                //buscaFactModelo.buscarFacturasEntreFechas(sqlDatePrimera,sqlDateUltima);
                //buscaFactModelo.buscarFacturasCodPostal(codPostal);
                //buscaFactModelo.buscarFacturasPoblacion(poblacion);*/
                //} else {
                //  buscaFactVista.mostrarErroresPanelComercial("El código postal no es válido.");
                //}
            } catch (NullPointerException ex) {
                buscaFactVista.mostrarErroresPanelComercial("No has seleccionado ninguna fecha");
            }

        }
    }
}
