/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import static Controlador_Comercial.MVC_Facturas_Controlador.comercialFactVista;
import Proyecto.Cliente;
import Modelo_Comercial.MVC_Clientes_Modelo;
import Componentes.TablaClientes;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase MVC_Clientes_Controlador
 * Clase Controlador Comercial que válida los datos de los clientes.
 * Escucha los botones añadir, modificar, baja y reset.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
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

        String opcion; //Corresponde a la opción escogida por el Comercial
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
            } catch (NumberFormatException ex) {
            }

            nombre = comerPrincVist.getNombre();
            apellidos = comerPrincVist.getApellidos();
            dni = comerPrincVist.getDni();
            cod_postal = comerPrincVist.getCodPostal();
            telefono = comerPrincVist.getTelefono();
            
            //Creamos objeto cliente
            Cliente cliente = new Cliente(cod_cliente, nombre, apellidos, dni, cod_postal, telefono);
            
            //Expresiones regulares
            Pattern patDni = Pattern.compile("^(([A-Za-z]\\d{8})|(\\d{8}[A-Za-z]))$");
            Matcher matDni = patDni.matcher(dni);

            Pattern patCodPostal = Pattern.compile("([0-9]{5})");
            Matcher matCodPostal = patCodPostal.matcher(cod_postal);

            Pattern patTfno = Pattern.compile("([0-9]{9})");
            Matcher matTfno = patTfno.matcher(telefono);
            
            //Recogemos la opción
            opcion = e.getActionCommand();

            switch (opcion) {
                case "Añadir":
                    //Comprobamos los campos
                    if (cliente.getNombre().equals("")) {
                        comercialFactVista.mostrarErroresPanelComercial("El campo nombre esta vacío");
                    } else if (cliente.getApellidos().equals("")) {
                        comercialFactVista.mostrarErroresPanelComercial("El campo apellidos esta vacío");
                    } else if (cliente.getDni().equals("")) {
                        comercialFactVista.mostrarErroresPanelComercial("El campo dni no puede estar vacío");
                    } else if (!matTfno.matches()) {
                        comercialFactVista.mostrarErroresPanelComercial("El campo telefono no es correcto");
                    } else if (!matCodPostal.matches()) {
                        comercialFactVista.mostrarErroresPanelComercial("El campo código postal no es correcto");
                    } else if (matDni.matches()) {
                        if (comerClienMod.buscarClientesPorDni(dni)) {
                            comercialFactVista.mostrarErroresPanelComercial("Ya existe un cliente con ese dni");
                        } else {
                            //Añadimos cliente a la BD
                            comerClienMod.anadirNuevoCliente(cliente);
                            comercialFactVista.actualizarTablaClientes();
                        }
                    } else {
                        comercialFactVista.mostrarErroresPanelComercial("El dni no es válido");
                    }   break;
                case "Modificar":
                    comerClienMod.modificarCliente(cliente);
                    comercialFactVista.actualizarTablaClientes();
                    break;
                case "Baja":
                    comerClienMod.bajaCliente(cliente);
                    comercialFactVista.actualizarTablaClientes();
                    break;
                default:
                    comercialFactVista.limpiarCamposClientes();
                    break;
            }
        }

    }
}
