/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Tecnico;

import Componentes.TablaReparaciones;
import Modelo_Empleado.MVC_GestionE_Modelo;
import Modelo_Tecnico.MVC_Reparaciones_Modelo;
import Proyecto.Reparacion;
import Modelo_Tecnico.MVC_ClientesR_Modelo;
import Vista_Tecnico.MVC_TecnicoPrincipal_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Clase MVC_Reparaciones_Controlador
 * Clase Reparaciones. Se válidan los datos de las reparaciones.
 * Las reparaciones las inserta el usuario técnico. El usuario técnico
 * puede añadir, modificar, eliminar y reset.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_Reparaciones_Controlador {

    static MVC_TecnicoPrincipal_Vista tecnVista;
    MVC_Reparaciones_Modelo repModelo;
    MVC_ClientesR_Modelo cliMod;
    MVC_ClientesR_Controlador cliContr;
    MVC_GestionE_Modelo gesEmpMod;

    public MVC_Reparaciones_Controlador(MVC_TecnicoPrincipal_Vista tecnVista, MVC_Reparaciones_Modelo repModelo, MVC_ClientesR_Modelo cliMod, MVC_ClientesR_Controlador cliContr, MVC_GestionE_Modelo gesEmpMod) {
        this.tecnVista = tecnVista;
        this.repModelo = repModelo;
        this.cliMod = cliMod;
        this.cliContr = cliContr;
        this.gesEmpMod = gesEmpMod;
        MVC_Reparaciones_Controlador.tecnVista.addEventosBotonesPTecnico(new AddBotonesEventosReparaciones());
    }

    public class AddBotonesEventosReparaciones implements ActionListener {

        String problema;
        String solucion;
        Date fecha1;
        Date fecha2;
        int codCliente;
        String idString;
        String opcion;
        int cod_rep;

        @Override
        public void actionPerformed(ActionEvent e) {

            //Hacemos la consulta a la bd para que nos devuelva la #id del técnico
            //En el string va el nombre
            problema = tecnVista.getProblemaReparacion();
            solucion = tecnVista.getSolucionReparacion();
            fecha2 = tecnVista.getFechaEntregaReparacion();
            
            //Recogemos la opcion del usuario
            opcion = e.getActionCommand();
            if (opcion.equals("Añadir")) {
                fecha1 = tecnVista.getFechaRecogidaReparacion();
                idString = tecnVista.getNombreTecnico();
                codCliente = tecnVista.getCodClienteReparacion();

                if (codCliente == 0) {
                    tecnVista.mostrarErroresPanelTecnico("El cod cliente esta vacío");
                } else if (!repModelo.buscarClientesCodCliente(codCliente)) {
                    tecnVista.mostrarErroresPanelTecnico("El cliente no existe, debes crearlo antes");
                } else if (problema.equals("") || problema.length() > 55) {
                    tecnVista.mostrarErroresPanelTecnico("El campo problema no es válido");
                } else if (solucion.equals("") || solucion.length() > 55) {
                    tecnVista.mostrarErroresPanelTecnico("El campo solución no es válido");
                } else if (fecha1 == null) {
                    tecnVista.mostrarErroresPanelTecnico("El campo fecha esta vacío");
                } else if (fecha2 == null) {
                    tecnVista.mostrarErroresPanelTecnico("El campo fecha esta vacío");
                } else {

                    int id = gesEmpMod.pedirIdTecnico(idString);
                    java.sql.Date fechaRec = convertirFecha(fecha1);
                    java.sql.Date fechaEnt = convertirFecha(fecha2);

                    Reparacion repAnadir = new Reparacion(problema, solucion, fechaRec, fechaEnt, codCliente, id, false);
                    repModelo.grabarReparacion(repAnadir);
                    tecnVista.actualizarTablaReparaciones();
                }
            } else if (opcion.equals("Modificar")) {
                //Recibimos los campos
                cod_rep = TablaReparaciones.getCodRep();

                if (problema.equals("") || problema.length() > 55) {
                    tecnVista.mostrarErroresPanelTecnico("El campo problema no es válido");
                } else if (solucion.equals("") || solucion.length() > 55) {
                    tecnVista.mostrarErroresPanelTecnico("El campo solución no es válido");
                } else if (fecha2 == null) {
                    tecnVista.mostrarErroresPanelTecnico("El campo fecha esta vacío");
                }
                java.sql.Date fechaEnt = convertirFecha(fecha2);
                Reparacion rep1 = new Reparacion(cod_rep, problema, solucion, fechaEnt);

                repModelo.modificarReparacion(rep1);
                tecnVista.actualizarTablaReparaciones();

            } else if (opcion.equals("Eliminar")){
                //Recibimos los campos
                cod_rep = TablaReparaciones.getCodRep();
                Reparacion repBaja = new Reparacion(cod_rep);
                repModelo.bajaReparacion(repBaja);
                tecnVista.actualizarTablaReparaciones();
            } else{
                tecnVista.limpiarCampos();
            }
        }
    }
    /**
     * Método que convierte fecha
     * @param date Pasamos la fecha de tipo java util
     * @return devolvemos la fecha convertida a java sql Date
     */
    public java.sql.Date convertirFecha(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

}
