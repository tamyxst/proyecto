/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_Comercial;

import static Controlador_Comercial.MVC_Facturas_Controlador.comercialFactVista;
import Modelo_Comercial.MVC_Facturas_Modelo;
import Modelo_Comercial.MVC_Reparaciones_Modelo;
import Modelo_Comercial.Reparacion;
import Vista_Comercial.MVC_TecnicoPrincipal_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 *
 * @author milla_000
 */
public class MVC_Reparaciones_Controlador {

    MVC_TecnicoPrincipal_Vista tecnVista;
    MVC_Reparaciones_Modelo repModelo;
    MVC_Facturas_Modelo comercialGesModelo;

    public MVC_Reparaciones_Controlador(MVC_TecnicoPrincipal_Vista tecnVista, MVC_Reparaciones_Modelo repModelo, MVC_Facturas_Modelo comercialGesModelo) {
        this.repModelo = repModelo;
        this.tecnVista = tecnVista;
        this.comercialGesModelo=comercialGesModelo;
        this.tecnVista.addEventosBotonesPTecnico(new AddBotonesEventos());

    }

    public class AddBotonesEventos implements ActionListener {
            String problema;
            String solucion;
            Date fecha1;
            Date fecha2;
            int codCliente;
            String idString;
            String opcion;
            
           
            
        @Override
        public void actionPerformed(ActionEvent e) {

            problema=tecnVista.getProblemaReparacion();
            solucion=tecnVista.getSolucionReparacion();
            fecha1=tecnVista.getFechaRecogidaReparacion();
            fecha2=tecnVista.getFechaEntregaReparacion();
            idString=tecnVista.getNombreTecnico();
            codCliente=tecnVista.getCodClienteReparacion();
            
            int id= Integer.parseInt(idString);
            
            java.sql.Date fechaRec = convertirFecha(fecha1);
            java.sql.Date fechaEnt = convertirFecha(fecha2);
            
            Reparacion rep=new Reparacion(problema,solucion,fechaRec,fechaEnt, codCliente,id,false);
            
            if (codCliente==0) {
                        comercialFactVista.mostrarErroresPanelComercial("El campo dni esta vacío");
                    } else if (!comercialGesModelo.buscarClientesCodCliente(codCliente)) {
                        comercialFactVista.mostrarErroresPanelComercial("El cliente no existe, debes crearlo antes");
                    }
    
            opcion=e.getActionCommand();
            if(opcion.equals("Añadir")){ 
                repModelo.grabarReparacion(rep);
            }else if(opcion.equals("Modificar")){
                repModelo.modificarReparacion(rep);
            }else{
                repModelo.bajaReparacion(rep);
            }
        }

    }

    public java.sql.Date convertirFecha(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

}
