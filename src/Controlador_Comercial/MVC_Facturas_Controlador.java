/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import Modelo_Comercial.MVC_Clientes_Modelo;
import Modelo_Comercial.MVC_Facturas_Modelo;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import Vista_Comercial.TablaFacturas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


/**
 *
 * @author milla_000
 */
public class MVC_Facturas_Controlador {

    static MVC_ComercialPrincipal_Vista comercialFactVista;
    MVC_Facturas_Modelo comercialGesModelo;
    MVC_Clientes_Modelo comercialCliModelo;
    MVC_Clientes_Controlador comercialCliConst;
    MVC_Buscador_Controlador buscaFac;
    TablaFacturas t = new TablaFacturas();
    static int cod_cliente;

    public MVC_Facturas_Controlador(MVC_ComercialPrincipal_Vista comercialFactVista, MVC_Facturas_Modelo comercialGesModelo,MVC_Clientes_Modelo comercialCliModelo, MVC_Clientes_Controlador comercialCliConst,MVC_Buscador_Controlador buscaFac ) {
        this.comercialFactVista = comercialFactVista;
        this.comercialGesModelo = comercialGesModelo;
        this.comercialCliModelo=comercialCliModelo;
        this.comercialCliConst=comercialCliConst;
        this.comercialFactVista.addEventosBotones(new SeleccionaBotonesFacturas());
        this.buscaFac=buscaFac;
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
        int codRep;
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

                    
                    System.out.println("Fecha " + fecha);
                    int cod_cliente = Integer.parseInt(codCliente);

                    if (codCliente.equals("")) {
                        comercialFactVista.mostrarErroresFacturas("El campo dni esta vacío");
                    } else if (!comercialGesModelo.buscarClientesCodCliente(cod_cliente)) {
                        comercialFactVista.mostrarErroresFacturas("El cliente no existe, debes crearlo antes");
                    } else if (comercialGesModelo.buscarCodigoReparacion(codRep)) {
                        comercialFactVista.mostrarErroresFacturas("Código reparación no válido. Ya esta facturado o debes crearlo antes.");
                    } else if (codRep==(0)) {
                        comercialFactVista.mostrarErroresFacturas("El campo código Reparación no puede ser 0");
                    } else if (importe == 0) {
                        comercialFactVista.mostrarErroresFacturas("El campo importe esta vacío");
                    } else {

                        //Cambiamos el formato de fecha a java.sql.Date
                        java.util.Date fecha = new java.util.Date();
                        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());

                        comercialGesModelo.grabarNuevaFactura(sqlDate, codRep, importe, cod_cliente);
                        //Marcamos la reparacion como facturada
                        comercialGesModelo.marcarReparacionFacturada(codRep);
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
