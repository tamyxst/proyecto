/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import static Controlador_Comercial.MVC_Facturas_Controlador.comercialFactVista;
import Modelo_Comercial.Cliente;
import Modelo_Comercial.MVC_Clientes_Modelo;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import Tablas.TablaClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author milla_000
 */
public class MVC_Clientes_Controlador {

    MVC_ComercialPrincipal_Vista comerPrincVist;
    MVC_Clientes_Modelo comerClienMod;

    public MVC_Clientes_Controlador(MVC_ComercialPrincipal_Vista comerPrincVist, MVC_Clientes_Modelo comerClienMod) {
        this.comerPrincVist = comerPrincVist;
        this.comerClienMod = comerClienMod;
        this.comerPrincVist.addEventosClientes(new AddBotonesClientes());
    }

    public class AddBotonesClientes implements ActionListener {

        String opcion;
        String nombre;
        String apellidos;
        String dni;
        String cod_postal;
        String telefono;
        int cod_cliente;

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cod_cliente = TablaClientes.getCodCliente(); //Recibo el cod_cliente directamente de la tabla. 
                nombre = comerPrincVist.getNombre();
                apellidos = comerPrincVist.getApellidos();
                dni = comerPrincVist.getDni();
                cod_postal = comerPrincVist.getCodPostal();
                telefono = comerPrincVist.getTelefono();

                Cliente cliente = new Cliente(cod_cliente, nombre, apellidos, dni, cod_postal, telefono);

                Pattern pat = Pattern.compile("^(([A-Za-z]\\d{8})|(\\d{8}[A-Za-z]))$");
                Matcher mat = pat.matcher(dni);

                if (cliente.getNombre().equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo dni esta vacío");
                } else if (cliente.getApellidos().equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo apellidos esta vacío");
                } else if (cliente.getDni().equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo dni no puede estar vacío");
                } else if (cliente.getCod_postal().equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo Cod Postal esta vacío");
                } else if (cliente.getTelefono().equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo telefono esta vacío");
                } else if (mat.matches()) {
                    opcion = e.getActionCommand();
                    if (opcion.equals("Añadir")) {
                        if (comerClienMod.buscarClientesPorDni(dni)) {
                            comercialFactVista.mostrarErroresFacturas("Ya existe un cliente con ese dni");
                        } else {
                            //Añadimos cliente a la BD
                            comerClienMod.anadirNuevoCliente(cliente);
                            comercialFactVista.actualizarTablaClientes();
                        }
                    } else if (opcion.equals("Modificar")) {
                        //Cliente cliente=new Cliente(nombre, apellidos, dni, cod_postal, telefono);
                        comerClienMod.modificarCliente(cliente);
                        comercialFactVista.actualizarTablaClientes();
                    } else {
                        comerClienMod.bajaCliente(cliente);
                        comercialFactVista.actualizarTablaClientes();
                    }
                } else {
                    comercialFactVista.mostrarErroresFacturas("El dni no es válido");
                }
            } catch (NumberFormatException ex) {
                comercialFactVista.mostrarErroresFacturas("Has introducido números");
            }

        }
    }
}
