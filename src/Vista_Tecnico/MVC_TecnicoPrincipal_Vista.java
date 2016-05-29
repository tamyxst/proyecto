/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_Tecnico;

import Componentes.ComboTecnicos;
import Componentes.FileChooserT;
import Componentes.TablaClientes;
import Componentes.TablaReparaciones;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Alumno
 */
public class MVC_TecnicoPrincipal_Vista extends JFrame {

    JFrame fRep1 = new JFrame();
    public static ComboTecnicos comboTec=new ComboTecnicos();
    TablaReparaciones tReparacionesT=new TablaReparaciones();
    FileChooserT fc=new FileChooserT();
    
    //Página 1
    JPanel panel1 = new JPanel();
    JPanel pListadoR = new JPanel();
    JPanel pDatosR = new JPanel();
    JPanel pBotones = new JPanel();
    JButton anadirR = new JButton("Añadir");
    JButton modificarR = new JButton("Modificar");
    JButton bajaR = new JButton("Eliminar");
    JLabel problemaRL = new JLabel("Problema:");
    public static JTextField problemaRJ = new JTextField(20);
    JLabel solucionRL = new JLabel("Solución:");
    public static JTextField solucionRJ = new JTextField(20);
    JLabel fecha_recogidaRL = new JLabel("Fecha recogida:");
    public static JDateChooser fecha_recogidaR = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    JLabel fecha_entregaRL = new JLabel("Fecha entrega:");
    public static JDateChooser fecha_entregaR = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    JLabel cod_clienteRL = new JLabel("Cód Cliente:");
    public static JTextField cod_clienteRJ = new JTextField();
    JLabel tecnicoRL = new JLabel("Técnico:"); //Combo box a la tabla usuarios... se correspondan al nombre
    
    //Página 2
    JPanel panel2 = new JPanel();
    JPanel pDatosCliR = new JPanel();
    JPanel pBotonesCliR = new JPanel();
    JLabel nombreCliLR = new JLabel("Nombre");
    JLabel apellidosCliLR = new JLabel("Apellidos");
    JLabel dniCliLR = new JLabel("Dni");
    JLabel cod_postalCliLR = new JLabel("Cod Postal");
    JLabel telefonoCliLR = new JLabel("Telefono");
    public static JTextField nombreCliJR = new JTextField(20);
    public static JTextField apellidosCliJR = new JTextField(20);
    public static JTextField dniCliJR = new JTextField(20);
    public static JTextField cod_postalCliJR = new JTextField(20);
    public static JTextField telefonoCliJR = new JTextField(20);
    TablaClientes tCliR = new TablaClientes();
    JButton anadirCliR = new JButton("Nuevo");
    JPanel pListadoCliR = new JPanel();
    
    //Página 3
    JPanel panel3=new JPanel();

    public MVC_TecnicoPrincipal_Vista() {
        fRep1.setSize(700, 700);
        fRep1.setLocationRelativeTo(null);

        JLabel label = new JLabel("Bienvenido/a");
        label.setHorizontalTextPosition(JLabel.TRAILING);
        label.setIcon(UIManager.getIcon("OptionPane.informationIcon"));

        createPage1Reparaciones();
        createPage2Reparaciones();
        createPage3Reparaciones();

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.addTab(null, new JPanel());
        tabbedPane.setTabComponentAt(0, label);

        tabbedPane.addTab("Reparaciones", panel1);
        tabbedPane.addTab("Clientes", panel2);
        tabbedPane.addTab("Buscar", panel3);

        tabbedPane.setIconAt(1, new ImageIcon("reparaciones.gif"));
        tabbedPane.setIconAt(2, new ImageIcon("clientes.gif"));
        tabbedPane.setIconAt(3, new ImageIcon("calendar.gif"));

        fRep1.add(tabbedPane);
        fRep1.setVisible(true);
    }

    public void createPage1Reparaciones() {
        JLabel label = new JLabel("Nueva Reparación");
        label.setHorizontalTextPosition(JLabel.TRAILING);

        pDatosR.setLayout(null);
        pDatosR.setLayout(new GridLayout(6, 2));

        problemaRJ.setBounds(30, 35, 150, 20);
        solucionRJ.setBounds(30, 35, 150, 20);
        cod_clienteRJ.setBounds(30, 35, 150, 20);

        pDatosR.add(problemaRL);
        pDatosR.add(problemaRJ);

        pDatosR.add(solucionRL);
        pDatosR.add(solucionRJ);

        pDatosR.add(fecha_recogidaRL);
        pDatosR.add(fecha_recogidaR);

        pDatosR.add(fecha_entregaRL);
        pDatosR.add(fecha_entregaR);

        pDatosR.add(cod_clienteRL);
        pDatosR.add(cod_clienteRJ);

        pDatosR.add(tecnicoRL);
        //Añadir combobox
        pDatosR.add(comboTec.getPanel1(), BorderLayout.CENTER);
        
        pBotones.add(anadirR);
        pBotones.add(modificarR);
        pBotones.add(bajaR);
        
        panel1.add(pDatosR, BorderLayout.NORTH);
        panel1.add(pBotones, BorderLayout.CENTER);
        panel1.add(tReparacionesT.getPanel1(), BorderLayout.SOUTH);
        
    }
    
    public String getProblemaReparacion(){
        return problemaRJ.getText();
    }
    public String getSolucionReparacion(){
        return solucionRJ.getText();
    }    
    public Date getFechaRecogidaReparacion(){
        return fecha_recogidaR.getDate();
    }
    public Date getFechaEntregaReparacion(){
        return fecha_entregaR.getDate();
    }
    public int getCodClienteReparacion(){
        int cod_cliente= Integer.parseInt(cod_clienteRJ.getText());
        return cod_cliente;
    }    
    public void mostrarTablaReparacionesT(){
        JFrame fReparacionesT=new JFrame();
        fReparacionesT.add(tReparacionesT.getPanel1(), BorderLayout.CENTER);
        fReparacionesT.setSize(500,300);
        fReparacionesT.setLocationRelativeTo(null);
        fReparacionesT.setResizable(false);
        fReparacionesT.setVisible(true);
    }
    public void mostrarErroresPanelTecnico(String mensajeError){
        JOptionPane.showMessageDialog(this,mensajeError);
    }
    
    public void addEventosBotonesPTecnico(ActionListener escucharBoton){
        anadirR.addActionListener(escucharBoton);
        modificarR.addActionListener(escucharBoton);
        bajaR.addActionListener(escucharBoton);
    }
    public void actualizarTablaReparaciones(){
        tReparacionesT.actualizarTabla();
    }
    
    public String getNombreTecnico(){
        return comboTec.getNombreTecnico();
    }
    public void createPage2Reparaciones() {
        JLabel label = new JLabel("Nuevo Cliente");
        label.setHorizontalTextPosition(JLabel.TRAILING);

        //Panel Datos factura
        pDatosCliR.setLayout(null);
        pDatosCliR.setLayout(new GridLayout(6, 2));

        nombreCliJR.setBounds(10, 35, 150, 20);
        apellidosCliJR.setBounds(10, 35, 150, 20);
        dniCliJR.setBounds(10, 35, 150, 20);
        cod_postalCliJR.setBounds(10, 35, 150, 20);
        telefonoCliJR.setBounds(10, 35, 150, 20);

        pDatosCliR.add(nombreCliLR);
        pDatosCliR.add(nombreCliJR);

        pDatosCliR.add(apellidosCliLR);
        pDatosCliR.add(apellidosCliJR);
        pDatosCliR.add(dniCliLR);
        pDatosCliR.add(dniCliJR);
        pDatosCliR.add(cod_postalCliLR);
        pDatosCliR.add(cod_postalCliJR);
        pDatosCliR.add(telefonoCliLR);
        pDatosCliR.add(telefonoCliJR);
        panel2.add(pDatosCliR, BorderLayout.NORTH);

        //Panel Botones
        pBotonesCliR.add(anadirCliR);
        panel2.add(pBotonesCliR, BorderLayout.CENTER);

        //Panel Listado facturas
        pListadoCliR.add(tCliR.getPanel2(), BorderLayout.SOUTH);
        pListadoCliR.setVisible(true);
        panel2.add(pListadoCliR, BorderLayout.SOUTH);
    }
    
    public String getNombreR() {
        return nombreCliJR.getText();
    }

    public String getApellidosR() {
        return apellidosCliJR.getText();
    }

    public String getDniR() {
        return dniCliJR.getText();
    }

    public String getCodPostalR() {
        return cod_postalCliJR.getText();
    }

    public String getTelefonoR() {
        return telefonoCliJR.getText();
    }

    public void addEventosClientesR(ActionListener escucharBoton) {
        anadirCliR.addActionListener(escucharBoton);
    }

    public void actualizarTablaClientesR() {
        tCliR.actualizarTablaClientes();
    }
    
    public void createPage3Reparaciones() {
        JLabel label = new JLabel("Cargar reparaciones");
        JLabel cargaDatos=new JLabel("En Fichero .txt");
        label.setHorizontalTextPosition(JLabel.TRAILING);
        panel3.add(cargaDatos);
        panel3.add(fc.getPanel(), BorderLayout.CENTER);
        
    }
}
