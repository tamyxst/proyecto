/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MVC_ComercialFacturas_Modelo;
import Vista.MVC_ComercialFacturas_Vista;
import Vista.TablaFacturas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author milla_000
 */
public class MVC_ComercialFacturas_Controlador {

    static MVC_ComercialFacturas_Vista comercialFactVista;
    MVC_ComercialFacturas_Modelo comercialGesModelo;
    TablaFacturas t = new TablaFacturas();
    static int cod_cliente;

    public MVC_ComercialFacturas_Controlador(MVC_ComercialFacturas_Vista comercialFactVista, MVC_ComercialFacturas_Modelo comercialGesModelo) {
        this.comercialFactVista = comercialFactVista;
        this.comercialGesModelo = comercialGesModelo;
        this.comercialFactVista.addEventosBotones(new SeleccionaBotonesFacturas());
        this.cod_cliente=cod_cliente;
    }

    public static void setText(int codcliente) {
         cod_cliente = codcliente;
         System.out.println("aqui PROBANDO:" + cod_cliente);
         comercialFactVista.setCodCliente(cod_cliente);
    }

    public class SeleccionaBotonesFacturas implements ActionListener {

        String codCliente;
        Date fecha;
        String codRep;
        float importe;
        String opcion;

        String nombreBuscado;
        String dniBuscado;

        @Override
        public void actionPerformed(ActionEvent e) {

            opcion = e.getActionCommand();

            if (opcion.equals("Añadir")) {

                try {
                    codCliente = comercialFactVista.getCodigoCliente();
                    fecha = comercialFactVista.getFecha();
                    codRep = comercialFactVista.getCodigoRepF();
                    importe = comercialFactVista.getImporteF();

                    /*Pattern pat = Pattern.compile("^(([A-Za-z]\\d{8})|(\\d{8}[A-Za-z]))$");
                     Matcher mat = pat.matcher(codCliente);*/
                    System.out.println("Fecha " + fecha);
                    int cod_cliente = Integer.parseInt(codCliente);

                    if (codCliente.equals("")) {
                        comercialFactVista.mostrarErroresFacturas("El campo dni esta vacío");
                    } else if (!comercialGesModelo.buscarClientesCodCliente(cod_cliente)) {
                        comercialFactVista.mostrarErroresFacturas("El cliente no existe, debes crearlo antes");
                    } else if (comercialGesModelo.buscarCodigoReparacion(codRep)) {
                        comercialFactVista.mostrarErroresFacturas("Código reparación no válido. Ya esta facturado o debes crearlo antes.");
                    } else if (codRep.equals("")) {
                        comercialFactVista.mostrarErroresFacturas("El campo código Reparación esta vacío");
                    } else if (importe == 0) {
                        comercialFactVista.mostrarErroresFacturas("El campo importe esta vacío");
                    } else {

                        //Cambiamos el formato de fecha a java.sql.Date
                        java.util.Date fecha = new java.util.Date();
                        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());

                        comercialGesModelo.grabarNuevaFactura(sqlDate, codRep, importe, cod_cliente);
                        comercialFactVista.actualizarTablaFacturas();
                    }
                } catch (NumberFormatException ex) {
                    comercialFactVista.mostrarErroresFacturas("Has introducido números");
                }
            } else {
                //Se abrirá el menu busqueda clientes.
                comercialGesModelo.abrirBuscarClientes();
                //Se abre el menu se introduce el dni...
                
               
            }

        }

    }

}
