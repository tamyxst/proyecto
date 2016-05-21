/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import Modelo.MVC_Gestion_Modelo;
import Vista.MVC_Login_Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author milla_000
 */
public class MVC_Login_Controlador {
    private MVC_Login_Vista interfaceLogin;
    private MVC_Gestion_Modelo gesUsuarios;
    
    public MVC_Login_Controlador(MVC_Login_Vista interfaceLogin, MVC_Gestion_Modelo gesUsuarios){
        this.interfaceLogin=interfaceLogin;
        this.gesUsuarios=gesUsuarios;
        this.interfaceLogin.addValidarUsuario(new Validar());
       
    }
    
    public class Validar implements ActionListener{
        String nombreUsuario;
        String pass;
        boolean conectado;
 
        
        @Override
        public void actionPerformed(ActionEvent e) {
            nombreUsuario = interfaceLogin.getNombre();
            pass = interfaceLogin.getPass();
            
            if(!gesUsuarios.comprobarLogin(nombreUsuario, pass)){
                interfaceLogin.mostrarErrores("El usuario o la contraseña no son válidos");
                conectado=false;
            }else{
                String tipo= gesUsuarios.comprobarTipo(nombreUsuario, pass);
                Empleado emp=new Empleado(nombreUsuario,tipo,true);
                if(emp.getTipo().equals("comercial")){
                    interfaceLogin.abrirMenuComercial();
                }
                conectado=true;
                
            }
            /*try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MVC_Login_Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            interfaceLogin.cerrarVentana();
            
        }
        
        /*public void doButtonAction(){
           SwingWorker<Void,Void> swingworker =new SwingWorker<Void,Void>(){

               @Override
               protected Void doInBackground() throws Exception {
                   gesUsuarios.altaNuevoUsuario(pass, pass, pass);
                   gesUsuarios.comprobarLogin(nombreUsuario, pass);
                   return null;
               }
               @Override
               protected void done() {
                   interfaceLogin.cerrarVentana();
               }
               
           };
            swingworker.execute();
        }*/
       
       public boolean empleadoConectado(){
            return conectado;
       }
       
       /*public Empleado empleado(){
           String tipo= gesUsuarios.comprobarTipo(nombreUsuario, pass);
           Empleado emp=new Empleado(nombreUsuario,tipo,true);
           return emp;
       }*/
       
        
    }
    
    
}
