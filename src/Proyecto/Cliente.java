/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

/**
 * Clase Cliente que representa cada cliente de la aplicación.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */
public class Cliente {
    private int cod_cliente;
    private String nombre;
    private String apellidos;
    private String dni;
    private String cod_postal;
    private String telefono;
    
    /**
     * Método constructor para modificar. Nos sirve para recoger los datos del cliente en la clase 
     * y controlador, validarlos, para posteriormente modificarlos en la base de datos.
     * @param cod_cliente identifica el identificador único de cada cliente.
     * @param nombre del cliente
     * @param apellidos del cliente
     * @param dni único del cliente
     * @param cod_postal del cliente
     * @param telefono del cliente
     */
    public Cliente(int cod_cliente, String nombre, String apellidos, String dni, String cod_postal, String telefono) {
        this.cod_cliente = cod_cliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.cod_postal = cod_postal;
        this.telefono = telefono;
    }
    /**
     * Método constructor para almacenar. Nos sirve para recoger los datos del cliente en la clase 
     * y controlador, validarlos, para posteriormente almacenarlos en la base de datos.
     * @param nombre del cliente
     * @param apellidos del cliente
     * @param dni único del cliente
     * @param cod_postal del cliente
     * @param telefono del cliente
     */
    public Cliente(String nombre, String apellidos, String dni, String cod_postal, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.cod_postal = cod_postal;
        this.telefono = telefono;
    }

    /**
     * Obtiene el códgigo del cliente
     * @return the cod_cliente
     */
    public int getCod_cliente() {
        return cod_cliente;
    }

    /**
     * Obtiene el nombre del cliente
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos del cliente
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el dni del cliente
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Obtiene el código postal del cliente
     * @return the cod_postal
     */
    public String getCod_postal() {
        return cod_postal;
    }

    /**
     * Obtiene el teléfono del cliente
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }
    
           
}
