/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import static Controlador_Comercial.MVC_Facturas_Controlador.comercialFactVista;
import Componentes.Cliente;
import Modelo_Comercial.MVC_Clientes_Modelo;
import Componentes.TablaClientes;
import Vista_Comercial.MVC_ComercialPrincipal_Vista;
import Vista_Tecnico.MVC_TecnicoPrincipal_Vista;
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
            try{
                cod_cliente = TablaClientes.getCodCliente(); //Recibo el cod_cliente directamente de la tabla. 
            }catch(NumberFormatException ex){}    
                
                nombre = comerPrincVist.getNombre();
                apellidos = comerPrincVist.getApellidos();
                dni = comerPrincVist.getDni();
                cod_postal = comerPrincVist.getCodPostal();
                telefono = comerPrincVist.getTelefono();

                Cliente cliente = new Cliente(cod_cliente, nombre, apellidos, dni, cod_postal, telefono);

                Pattern patDni = Pattern.compile("^(([A-Za-z]\\d{8})|(\\d{8}[A-Za-z]))$");
                Matcher matDni = patDni.matcher(dni);
                
                Pattern patCodPostal = Pattern.compile("([0-9]{5})");
                Matcher matCodPostal = patCodPostal.matcher(cod_postal);
                
                Pattern patTfno = Pattern.compile("([0-9]{9})");
                Matcher matTfno = patTfno.matcher(telefono);

                if (cliente.getNombre().equals("")) {
                    comercialFactVista.mostrarErroresPanelComercial("El campo nombre esta vacío");
                } else if (cliente.getApellidos().equals("")) {
                    comercialFactVista.mostrarErroresPanelComercial("El campo apellidos esta vacío");
                } else if (cliente.getDni().equals("")) {
                    comercialFactVista.mostrarErroresPanelComercial("El campo dni no puede estar vacío");
                } else if (!matTfno.matches()) {
                    comercialFactVista.mostrarErroresPanelComercial("El campo telefono no es correcto");
                } else if(!matCodPostal.matches()){
                    comercialFactVista.mostrarErroresPanelComercial("El campo código postal no es correcto");
                }else if (matDni.matches()) {
                    opcion = e.getActionCommand();
                    if (opcion.equals("Añadir")) {
                        if (comerClienMod.buscarClientesPorDni(dni)) {
                            comercialFactVista.mostrarErroresPanelComercial("Ya existe un cliente con ese dni");
                        } else {
                            //Añadimos cliente a la BD
                            comerClienMod.anadirNuevoCliente(cliente);
                            comercialFactVista.actualizarTablaClientes();
                        }
                    } else if (opcion.equals("Modificar")) {
                        comerClienMod.modificarCliente(cliente);
                        comercialFactVista.actualizarTablaClientes();
                    } else {
                        comerClienMod.bajaCliente(cliente);
                        comercialFactVista.actualizarTablaClientes();
                    }
                } else {
                    comercialFactVista.mostrarErroresPanelComercial("El dni no es válido");
                }
            } 

        
    }
}
