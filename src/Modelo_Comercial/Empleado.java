package Modelo_Comercial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milla_000
 */
public class Empleado {
    private String nombre;
    private String tipo;
    private boolean conectado;
    
    public Empleado(String nombre, String tipo, boolean conectado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.conectado=conectado;
    }
    
    public boolean estaConectado(){
        return isConectado(); 
    }
    
    public String toString(){
        return "Nombre: " + getNombre() + " Tipo: " + getTipo() + " Online: " + isConectado();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return the conectado
     */
    public boolean isConectado() {
        return conectado;
    }
    

}
