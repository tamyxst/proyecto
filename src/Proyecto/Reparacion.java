/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.sql.Date;
/**
 * Clase Reparación. Clase que representa cada reparación.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class Reparacion {
    private int cod_rep;
    private String problema;
    private String solucion;
    private java.sql.Date f_recogida;
    private java.sql.Date f_entrega;
    private int cod_cliente; // Corresponde al nº de cliente en la tabla clientes
    private int id; //Corresponde al nº de usuario en la tabla usuarios
    private boolean facturado; // si es 1 esta facturada la reparación
    
   
    public Reparacion(String problema, String solucion, Date f_recogida, Date f_entrega, int cod_cliente, int id, boolean facturado) {
        this.problema = problema;
        this.solucion = solucion;
        this.f_recogida = f_recogida;
        this.f_entrega = f_entrega;
        this.cod_cliente = cod_cliente;
        this.id = id;
        this.facturado = facturado;
    }
    public Reparacion(String problema, String solucion, Date f_recogida, int cod_cliente, int id, boolean facturado) {
        this.problema = problema;
        this.solucion = solucion;
        this.f_recogida = f_recogida;
        this.cod_cliente = cod_cliente;
        this.id = id;
        this.facturado = facturado;
    }
    
    public Reparacion(int cod_rep,String problema, String solucion,Date f_entrega) {
        this.cod_rep=cod_rep;
        this.problema = problema;
        this.solucion = solucion;
        this.f_entrega = f_entrega;
    }
    //Constructor FileChooser
    public Reparacion(String problema, Date f_recogida, Date f_entrega, int cod_cliente, int id) {
        this.problema = problema;
        this.f_recogida=f_recogida;
        this.f_entrega = f_entrega;
        this.cod_cliente=cod_cliente;
        this.id=id;
    }
    public Reparacion(int cod_rep) {
        this.cod_rep = cod_rep;
    }

    /**
     * @return Devuelve el cod_rep de la reparación
     */
    public int getCod_rep() {
        return cod_rep;
    }

    /**
     * @return Devuelve el problema de la reparación
     */
    public String getProblema() {
        return problema;
    }

    /**
     * @return Devuelve la solución de la reparación
     */
    public String getSolucion() {
        return solucion;
    }

    /**
     * @return Devuelve la fecha recogida de la reparación
     */
    public java.sql.Date getF_recogida() {
        return f_recogida;
    }

    /**
     * @return Devuelve la fecha entrega de la reparación
     */
    public java.sql.Date getF_entrega() {
        return f_entrega;
    }

    /**
     * @return Devuelve el código cliente de la reparación
     */
    public int getCod_cliente() {
        return cod_cliente;
    }

    /**
     * @return Devuelve el id de la reparación, que corresponde a 
     * el id de la tabla usuarios (empleados).
     */
    public int getId() {
        return id;
    }

    /**
     * @return Devuelve si esta facturada la reparación, true es SI y
     * false es no.
     */
    public boolean isFacturado() {
        return facturado;
    }

    /**
     * @param cod_rep set cod_rep de la reparación
     */
    public void setCod_rep(int cod_rep) {
        this.cod_rep = cod_rep;
    }

    /**
     * @param problema set problema de la reparación
     */
    public void setProblema(String problema) {
        this.problema = problema;
    }

    /**
     * @param f_recogida set fecha recogida de la reparación
     */
    public void setF_recogida(java.sql.Date f_recogida) {
        this.f_recogida = f_recogida;
    }

}
