package Proyecto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase Empleado. Clase que representa cada empleado de la aplicación.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class Empleado {
    private String nombre;
    private String tipo; //Puede ser tecnico o comercial
    private String pass;
    private boolean conectado;
    
    //Constructor que utilizamos en MVC_Login_Controlador
    public Empleado(String nombre, String tipo, String pass, boolean conectado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.pass=pass;
        this.conectado=conectado;
    }
   
    public Empleado(String nombre, String tipo,boolean conectado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.conectado=conectado;
    }
    
    /**
     * @return Devuelve si esta conectado el empleado
     */
    public boolean estaConectado(){
        return isConectado(); 
    }
    /**
     * Método que imprime los datos del empleado
     * @return Devuelve los datos del empleado
     */
    public String toString(){
        return "Nombre: " + getNombre() + " Tipo: " + getTipo() + " Online: " + isConectado();
    }

    /**
     * @return Devuelve el nombre del empleado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return Devuelve el tipo del empleado "tecnico" o "comercial"
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return Devuelve si esta conectado "true" o "false"
     */
    public boolean isConectado() {
        return conectado;
    }
    

}
