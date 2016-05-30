/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Tecnico;

import Proyecto.Cliente;
import Modelo_Tecnico.MVC_ClientesR_Modelo;
import Vista_Tecnico.MVC_TecnicoPrincipal_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase MVC_ClientesR_Controlador
 * Clase Clientes que válida los datos ingresados por el usuario Técnico.
 * El usuario técnico sólo puede añadir.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */
public class MVC_ClientesR_Controlador {

    MVC_TecnicoPrincipal_Vista tecnPrinVis;
    MVC_ClientesR_Modelo cliRepMode;

    public MVC_ClientesR_Controlador(MVC_TecnicoPrincipal_Vista tecnPrinVis, MVC_ClientesR_Modelo cliRepMode) {
        this.tecnPrinVis = tecnPrinVis;
        this.cliRepMode = cliRepMode;
        this.tecnPrinVis.addEventosClientesR(new AddEventosClientesReparaciones());
    }

    public class AddEventosClientesReparaciones implements ActionListener {

        String opcion;
        String nombre;
        String apellidos;
        String dni;
        String cod_postal;
        String telefono;

        @Override
        public void actionPerformed(ActionEvent e) {
            //Recogemos los datos
            nombre = tecnPrinVis.getNombreR();
            apellidos = tecnPrinVis.getApellidosR();
            dni = tecnPrinVis.getDniR();
            cod_postal = tecnPrinVis.getCodPostalR();
            telefono = tecnPrinVis.getTelefonoR();
            
            //Creamos un objeto de tipo Cliente
            Cliente cliente = new Cliente(nombre, apellidos, dni, cod_postal, telefono);
            
            //Expresiones regulares
            Pattern patDni = Pattern.compile("^(([A-Za-z]\\d{8})|(\\d{8}[A-Za-z]))$");
            Matcher matDni = patDni.matcher(dni);

            Pattern patCodPostal = Pattern.compile("([0-9]{5})");
            Matcher matCodPostal = patCodPostal.matcher(cod_postal);

            Pattern patTfno = Pattern.compile("([0-9]{9})");
            Matcher matTfno = patTfno.matcher(telefono);

            if (cliente.getNombre().equals("")) {
                tecnPrinVis.mostrarErroresPanelTecnico("El campo nombre esta vacío");
            } else if (cliente.getApellidos().equals("")) {
                tecnPrinVis.mostrarErroresPanelTecnico("El campo apellidos esta vacío");
            } else if (cliente.getDni().equals("")) {
                tecnPrinVis.mostrarErroresPanelTecnico("El campo dni no puede estar vacío");
            } else if (cliRepMode.buscarClientesPorDni(dni)) {
                tecnPrinVis.mostrarErroresPanelTecnico("Ya existe un cliente con ese dni");
            } else if (!matTfno.matches()) {
                tecnPrinVis.mostrarErroresPanelTecnico("El campo telefono no es correcto");
            } else if (!matCodPostal.matches()) {
                tecnPrinVis.mostrarErroresPanelTecnico("El campo código postal no es correcto");
            } else if (matDni.matches()) {
                opcion = e.getActionCommand();
                if (opcion.equals("Nuevo")) {
                    cliRepMode.anadirNuevoCliente(cliente);
                    tecnPrinVis.actualizarTablaClientesR();
                }
            }else{
                tecnPrinVis.mostrarErroresPanelTecnico("El campo dni no es correcto");
            }
        }
    }
}
    
