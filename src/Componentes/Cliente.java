/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

/**
 *
 * @author milla_000
 */
public class Cliente {
    private int cod_cliente;
    private String nombre;
    private String apellidos;
    private String dni;
    private String cod_postal;
    private String telefono;

    public Cliente(int cod_cliente, String nombre, String apellidos, String dni, String cod_postal, String telefono) {
        this.cod_cliente = cod_cliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.cod_postal = cod_postal;
        this.telefono = telefono;
    }
    
    public Cliente(String nombre, String apellidos, String dni, String cod_postal, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.cod_postal = cod_postal;
        this.telefono = telefono;
    }

    /**
     * @return the cod_cliente
     */
    public int getCod_cliente() {
        return cod_cliente;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @return the cod_postal
     */
    public String getCod_postal() {
        return cod_postal;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }
    
           
}
