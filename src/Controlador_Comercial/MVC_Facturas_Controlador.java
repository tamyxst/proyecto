/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import Modelo_Comercial.MVC_Clientes_Modelo;
import Modelo_Comercial.MVC_Facturas_Modelo;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Clase MVC_Facturas_Controlador
 * Clase Controlador Comercial que válida los datos de las facturas.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_Facturas_Controlador {

    static MVC_ComercialPrincipal_Vista comercialFactVista;
    MVC_Facturas_Modelo comercialGesModelo;
    MVC_Clientes_Modelo comercialCliModelo;
    MVC_Clientes_Controlador comercialCliConst;
    MVC_Buscador_Controlador buscaFac;
    static int cod_cliente; //Corresponde al cod_cliente que comprobará si existe

    public MVC_Facturas_Controlador(MVC_ComercialPrincipal_Vista comercialFactVista, MVC_Facturas_Modelo comercialGesModelo, MVC_Clientes_Modelo comercialCliModelo, MVC_Clientes_Controlador comercialCliConst, MVC_Buscador_Controlador buscaFac) {
        this.comercialFactVista = comercialFactVista;
        this.comercialGesModelo = comercialGesModelo;
        this.comercialCliModelo = comercialCliModelo;
        this.comercialCliConst = comercialCliConst;
        this.comercialFactVista.addEventosBotonesPComercial(new SeleccionaBotonesFacturas());
        this.buscaFac = buscaFac;
        this.cod_cliente = cod_cliente;
    }
    public class SeleccionaBotonesFacturas implements ActionListener {

        String codCliente;
        Date fecha;
        int codRep;
        float importe;
        String opcion;
        String nombreBuscado;

        @Override
        public void actionPerformed(ActionEvent e) {

            opcion = e.getActionCommand();
            if (opcion.equals("Añadir")) {
                try {
                    codCliente = comercialFactVista.getCodigoCliente();
                    fecha = comercialFactVista.getFecha();
                    codRep = comercialFactVista.getCodigoRepF();
                    importe = comercialFactVista.getImporteF();

                    System.out.println("Fecha " + fecha);
                    int cod_cliente = Integer.parseInt(codCliente);

                    if (cod_cliente==0) {
                        comercialFactVista.mostrarErroresPanelComercial("El campo dni esta vacío");
                        //Buscamos si el cod_cliente existe sino saltará error porque se debe crear antes
                    } else if (!comercialGesModelo.buscarClientesCodCliente(cod_cliente)) {
                        comercialFactVista.mostrarErroresPanelComercial("El cliente no existe, debes crearlo antes");
                        //Buscamos el cod_rep existe sino saltará error porque se debe crear antes
                    } else if (comercialGesModelo.buscarCodigoReparacion(codRep)) {
                        comercialFactVista.mostrarErroresPanelComercial("Código reparación no válido. Ya esta facturado o debes crearlo antes.");
                    } else if (codRep == (0)) {
                        comercialFactVista.mostrarErroresPanelComercial("El campo código Reparación no puede ser 0");
                    } else if (importe == 0) {
                        comercialFactVista.mostrarErroresPanelComercial("El campo importe esta vacío");
                    } else {

                        //Cambiamos el formato de fecha a java.sql.Date
                        java.sql.Date fechaI = convertirFecha(fecha);

                        comercialGesModelo.grabarNuevaFactura(fechaI, codRep, importe, cod_cliente);
                        //Marcamos la reparacion como facturada
                        comercialGesModelo.marcarReparacionFacturada(codRep);
                        comercialFactVista.actualizarTablaFacturas();
                    }
                } catch (NumberFormatException ex) {
                    comercialFactVista.mostrarErroresPanelComercial("Has introducido carácteres no válidos");
                }
            } else {
                //Se abrirá el menu busqueda clientes.
                comercialGesModelo.abrirBuscarClientes();
                //Se abre el menu se introduce el dni...

            }

        }

        public java.sql.Date convertirFecha(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    }

}
