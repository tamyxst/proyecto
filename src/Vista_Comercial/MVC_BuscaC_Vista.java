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
 *
 * @author milla_000
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
    
    public String getBuscar(){
        return Jdni.getText();
    }
    
    public void buscarPorDniClientes(ActionListener escucharBoton){
        buscar.addActionListener(escucharBoton);
    }
    
    public void mostrarMensajeErrorBuscar(String mensajeError){
        JOptionPane.showMessageDialog(this, mensajeError);
    }
    
    public void cerrarVentanaBuscadorClientes(){
        fBClientes.dispose();
    }
}

