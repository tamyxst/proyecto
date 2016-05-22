/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MVC_ComercialFacturas_Modelo;
import Vista.MVC_ComercialFacturas_Vista;
import Vista.Tabla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author milla_000
 */
public class MVC_ComercialFacturas_Controlador {

    private MVC_ComercialFacturas_Vista comercialFactVista;
    private MVC_ComercialFacturas_Modelo comercialGesModelo;
    private Tabla t=new Tabla();

    public MVC_ComercialFacturas_Controlador(MVC_ComercialFacturas_Vista comercialFactVista, MVC_ComercialFacturas_Modelo comercialGesModelo) {
        this.comercialFactVista = comercialFactVista;
        this.comercialGesModelo = comercialGesModelo;
        this.comercialFactVista.addEventosBotones(new SeleccionaBotonesFacturas());
    }

    public class SeleccionaBotonesFacturas implements ActionListener {

        String nombre;
        String dni;
        Date fecha;
        String codRep;
        float importe;
        String opcion;

        @Override
        public void actionPerformed(ActionEvent e) {

            opcion = e.getActionCommand();

            if (opcion.equals("Añadir")) {

                try {
                    nombre = comercialFactVista.getNombreF();
                    dni = comercialFactVista.getDniF();
                    fecha = comercialFactVista.getFecha();
                    codRep = comercialFactVista.getCodigoRepF();
                    importe = comercialFactVista.getImporteF();

                    Pattern pat = Pattern.compile("^(([A-Za-z]\\d{8})|(\\d{8}[A-Za-z]))$");
                    Matcher mat = pat.matcher(dni);

                    System.out.println("Fecha " + fecha);

                    if (nombre.equals("")) {
                        comercialFactVista.mostrarErroresFacturas("El campo nombre esta vacío");
                    } else if (dni.equals("")) {
                        comercialFactVista.mostrarErroresFacturas("El campo dni esta vacío");
                    } else if (codRep.equals("")) {
                        comercialFactVista.mostrarErroresFacturas("El campo código Reparación esta vacío");
                    } else if (importe == 0) {
                        comercialFactVista.mostrarErroresFacturas("El campo importe esta vacío");
                    } else if (mat.matches()) {
                        
                        //Cambiamos el formato de fecha a java.sql.Date
                        java.util.Date fecha = new java.util.Date();
                        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime()); 

                        comercialGesModelo.grabarNuevaFactura(nombre,dni,sqlDate,codRep,importe);
                        comercialFactVista.actualizarTablaFacturas();
                    }else{
                       comercialFactVista.mostrarErroresFacturas("El campo dni no es válido");
                    }
                } catch (NumberFormatException ex) {
                    comercialFactVista.mostrarErroresFacturas("Has introducido números");
                }
            }else{
                //
            }

        }

    }

}
