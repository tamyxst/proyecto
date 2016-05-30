/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_Comercial;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase MVC_BuscaC_Vista. Clase que hereda de JFrame. 
 * Corresponde a la Interfaz visual de Busqueda Clientes por dni.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class MVC_BuscaC_Vista extends JFrame {
    JFrame fBClientes=new JFrame();
    JPanel pBClientes=new JPanel();
    JButton buscar=new JButton("Buscar");
    JLabel dni=new JLabel("Dni");
    JTextField Jdni=new JTextField(15);
    
    public MVC_BuscaC_Vista(){
        fBClientes.setSize(200,150);
        fBClientes.setTitle("Busqueda de Clientes");
        fBClientes.setLocationRelativeTo(null);
        pBClientes.add(dni);
        pBClientes.add(Jdni);
        pBClientes.add(buscar);
        fBClientes.add(pBClientes);
        fBClientes.setVisible(true);
    }
    /**
     * Método que devuelve el dni
     * @return Devuelve el dni
     */
    public String getBuscar(){
        return Jdni.getText();
    }
    
    /**
     * Método que escucha los botones
     * @param escucharBoton de tipo ActionListener
     */
    public void buscarPorDniClientes(ActionListener escucharBoton){
        buscar.addActionListener(escucharBoton);
    }
    
    /**
     * Método que lanza un mensaje de error.
     * @param mensajeError Contiene el mensaje de error
     */
    public void mostrarMensajeErrorBuscar(String mensajeError){
        JOptionPane.showMessageDialog(this, mensajeError);
    }
    /**
     * Método que cierra la ventana
     */
    public void cerrarVentanaBuscadorClientes(){
        fBClientes.dispose();
    }
}

