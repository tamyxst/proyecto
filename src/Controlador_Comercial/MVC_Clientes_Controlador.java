/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import static Controlador_Comercial.MVC_Facturas_Controlador.comercialFactVista;
import Modelo_Comercial.MVC_Clientes_Modelo;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
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

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                nombre = comerPrincVist.getNombre();
                apellidos = comerPrincVist.getApellidos();
                dni = comerPrincVist.getDni();
                cod_postal = comerPrincVist.getCodPostal();
                telefono = comerPrincVist.getTelefono();

                Pattern pat = Pattern.compile("^(([A-Za-z]\\d{8})|(\\d{8}[A-Za-z]))$");
                Matcher mat = pat.matcher(dni);

                if (nombre.equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo dni esta vacío");
                } else if (apellidos.equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo apellidos esta vacío");
                } else if (dni.equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo dni no puede estar vacío");
                } else if (cod_postal.equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo Cod Postal esta vacío");
                } else if (telefono.equals("")) {
                    comercialFactVista.mostrarErroresFacturas("El campo telefono esta vacío");
                } else if (comerClienMod.buscarClientesPorDni(dni)) {
                    comercialFactVista.mostrarErroresFacturas("Ya existe un cliente con ese dni");
                } else if (mat.matches()) {
                    opcion = e.getActionCommand();
                    if (opcion.equals("Añadir")) {
                        //Añadimos cliente a la BD
                        comerClienMod.anadirNuevoCliente(nombre, apellidos, dni, cod_postal, telefono);
                        comercialFactVista.actualizarTablaClientes();
                    } else if (opcion.equals("Modificar")) {
                        
                    } else {

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
