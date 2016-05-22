/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author milla_000
 */
public class MVC_ComercialFacturas_Vista extends JDialog {

    JFrame fFact2 = new JFrame();
    JPanel panel1 = new JPanel();
    JPanel pListado, pDatosF, pBotones;
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JButton anadir,buscarCliente;
    JLabel nombreCF, dniCF, fechaL, codigoRepL, importeL;
    JTextField nombreJF, dniJF, fechaJ, codigoRepJ, importeJ;
    JDateChooser calendar = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    Tabla t=new Tabla();
    
    public MVC_ComercialFacturas_Vista() {

        fFact2.setSize(700, 700);
        fFact2.setLocationRelativeTo(null);

        JLabel label = new JLabel("Bienvenido/a");
        label.setHorizontalTextPosition(JLabel.TRAILING); // Set the text position regarding its icon
        label.setIcon(UIManager.getIcon("OptionPane.informationIcon"));

        createPage1();
        //createPage2();
        //createPage3();

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.addTab(null, new JPanel());
        tabbedPane.setTabComponentAt(0, label);

        tabbedPane.addTab("Facturas", panel1);
        tabbedPane.addTab("Clientes", panel2);
        tabbedPane.addTab("Articulos", panel3);

        tabbedPane.setIconAt(1, new ImageIcon("facturas.gif"));
        tabbedPane.setIconAt(2, new ImageIcon("clientes.gif"));
        tabbedPane.setIconAt(3, new ImageIcon("reparaciones.gif"));

        fFact2.add(tabbedPane);
        fFact2.setVisible(true);
    }

    public void createPage1() {
        JLabel label = new JLabel("Nueva Factura");
        label.setHorizontalTextPosition(JLabel.TRAILING);
        
    //Panel Datos factura
        pDatosF = new JPanel();
        pDatosF.setLayout(null);
        pDatosF.setLayout(new GridLayout(6, 2));

        nombreCF = new JLabel("Nombre");
        nombreJF = new JTextField(20);
        nombreJF.setBounds(10, 35, 150, 20);

        dniCF = new JLabel("Dni");
        dniJF = new JTextField(20);
        dniJF.setBounds(10, 35, 150, 20);

        fechaL = new JLabel("Fecha");

        codigoRepL = new JLabel("Codigo Rep");
        codigoRepJ = new JTextField(20);
        codigoRepJ.setBounds(10, 35, 150, 20);

        importeL = new JLabel("Importe");
        importeJ = new JTextField(20);
        importeJ.setBounds(10, 35, 150, 20);

    
        pDatosF.add(nombreCF);
        pDatosF.add(nombreJF);
        pDatosF.add(fechaL);
        pDatosF.add(calendar);
        pDatosF.add(dniCF);
        pDatosF.add(dniJF);
        pDatosF.add(codigoRepL);
        pDatosF.add(codigoRepJ);
        pDatosF.add(importeL);
        pDatosF.add(importeJ);
        panel1.add(pDatosF, BorderLayout.NORTH);

    //Panel Botones
        pBotones = new JPanel();
        anadir = new JButton("Añadir");
        buscarCliente=new JButton("Buscar Cliente");
        pBotones.add(anadir);
        pBotones.add(buscarCliente);
        panel1.add(pBotones, BorderLayout.CENTER);
    
    //Panel Listado facturas
        pListado = new JPanel();
        
        pListado.add(t.getPanel1(), BorderLayout.SOUTH);
        pListado.setVisible(true);

        panel1.add(pListado, BorderLayout.SOUTH);

    }
    public String getNombreF(){
        return nombreJF.getText();
    }
    public Date getFecha(){
        return calendar.getDate();
    }
    public String getDniF(){
        return dniJF.getText();
    }
    public String getCodigoRepF(){
        return codigoRepJ.getText();
    }
    public float getImporteF(){
        Float importF = Float.parseFloat(importeJ.getText());
        return importF;
    }
    public void mostrarErroresFacturas(String mensajeError){
        JOptionPane.showMessageDialog(this, mensajeError);
    }
    public void addEventosBotones(ActionListener escucharBoton){
        anadir.addActionListener(escucharBoton);
        buscarCliente.addActionListener(escucharBoton);
    }
    public void actualizarTablaFacturas(){
        t.actualizarTabla();
    }
    
    

}
