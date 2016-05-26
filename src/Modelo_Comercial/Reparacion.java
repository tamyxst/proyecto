/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_Comercial;

import java.sql.Date;

/**
 *
 * @author milla_000
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
    
    public Reparacion(int cod_rep, String problema, String solucion, Date f_recogida, Date f_entrega, int cod_cliente, int id, boolean facturado) {
        this.cod_rep = cod_rep;
        this.problema = problema;
        this.solucion = solucion;
        this.f_recogida = f_recogida;
        this.f_entrega = f_entrega;
        this.cod_cliente = cod_cliente;
        this.id = id;
        this.facturado = facturado;
    }
    public Reparacion(String problema, String solucion, Date f_recogida, Date f_entrega, int cod_cliente, int id, boolean facturado) {
        this.problema = problema;
        this.solucion = solucion;
        this.f_recogida = f_recogida;
        this.f_entrega = f_entrega;
        this.cod_cliente = cod_cliente;
        this.id = id;
        this.facturado = facturado;
    }

    /**
     * @return the cod_rep
     */
    public int getCod_rep() {
        return cod_rep;
    }

    /**
     * @return the problema
     */
    public String getProblema() {
        return problema;
    }

    /**
     * @return the solucion
     */
    public String getSolucion() {
        return solucion;
    }

    /**
     * @return the f_recogida
     */
    public java.sql.Date getF_recogida() {
        return f_recogida;
    }

    /**
     * @return the f_entrega
     */
    public java.sql.Date getF_entrega() {
        return f_entrega;
    }

    /**
     * @return the cod_cliente
     */
    public int getCod_cliente() {
        return cod_cliente;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the facturado
     */
    public boolean isFacturado() {
        return facturado;
    }

}
