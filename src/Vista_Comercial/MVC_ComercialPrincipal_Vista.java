/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_Comercial;

import Tablas.TablaReparaciones;
import Tablas.TablaFacturas;
import Tablas.TablaCodPostal;
import Tablas.TablaFacturaFechas;
import Tablas.TablaClientes;
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
public final class MVC_ComercialPrincipal_Vista extends JDialog {

    JFrame fFact2 = new JFrame();
    TablaFacturaFechas tFacFechas=new TablaFacturaFechas();
    TablaReparaciones tRepa=new TablaReparaciones();
    TablaCodPostal tCodPos=new TablaCodPostal();
    
    //Página 1
    JPanel panel1 = new JPanel();
    JPanel pListado, pDatosF, pBotones;
    JButton anadir, buscarCliente;
    JLabel codClienteL, fechaL, codigoRepL, importeL;
    JTextField codClienteJ, fechaJ, codigoRepJ, importeJ;
    JDateChooser calendar = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    TablaFacturas t = new TablaFacturas();

    //Página 2
    JPanel panel2 = new JPanel();
    JPanel pDatosCli = new JPanel();
    JPanel pBotonesCli = new JPanel();
    JLabel nombreCliL = new JLabel("Nombre");
    JLabel apellidosCliL = new JLabel("Apellidos");
    JLabel dniCliL = new JLabel("Dni");
    JLabel cod_postalCliL = new JLabel("Cod Postal");
    JLabel telefonoCliL = new JLabel("Telefono");
    public static JTextField nombreCliJ = new JTextField(20);
    public static JTextField apellidosCliJ = new JTextField(20);
    public static JTextField dniCliJ = new JTextField(20);
    public static JTextField cod_postalCliJ = new JTextField(20);
    public static JTextField telefonoCliJ = new JTextField(20);
    TablaClientes tCli = new TablaClientes();
    JButton anadirCli = new JButton("Añadir");
    JButton modificarCli = new JButton("Modificar");
    JButton bajaCli = new JButton("Baja");
    JPanel pListadoCli = new JPanel();

    //Página 3
    JPanel panel3 = new JPanel();
    JPanel panelFechas = new JPanel();
    JPanel panelBusq = new JPanel();
    JButton mostrarFechas = new JButton("Mostrar por fechas");
    JButton mostrarCodPos = new JButton("Mostrar");
    JButton mostrarRepNoFac=new JButton("Mostrar Reparaciones");
    JLabel reparNoFacturadas=new JLabel("Reparaciones no facturadas");
    JLabel frasRealizadasLa = new JLabel("Facturas entre fechas");
    JLabel frasCodPostalLa = new JLabel("Facturas por Código postal");
    JTextField frasCodPos = new JTextField(10);
    JDateChooser primFras = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    JDateChooser ultiFras = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');

    
    public MVC_ComercialPrincipal_Vista() {

        fFact2.setSize(700, 700);
        fFact2.setLocationRelativeTo(null);

        JLabel label = new JLabel("Bienvenido/a");
        label.setHorizontalTextPosition(JLabel.TRAILING); 
        label.setIcon(UIManager.getIcon("OptionPane.informationIcon"));

        createPage1();
        createPage2();
        createPage3();

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.addTab(null, new JPanel());
        tabbedPane.setTabComponentAt(0, label);

        tabbedPane.addTab("Facturas", panel1);
        tabbedPane.addTab("Clientes", panel2);
        tabbedPane.addTab("Buscar", panel3);

        tabbedPane.setIconAt(1, new ImageIcon("facturas.gif"));
        tabbedPane.setIconAt(2, new ImageIcon("clientes.gif"));
        tabbedPane.setIconAt(3, new ImageIcon("buscar.gif"));

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

        codClienteL = new JLabel("Código Cliente");
        codClienteJ = new JTextField(20);
        codClienteJ.setBounds(10, 35, 150, 20);

        fechaL = new JLabel("Fecha");

        codigoRepL = new JLabel("Codigo Rep");
        codigoRepJ = new JTextField(20);
        codigoRepJ.setBounds(10, 35, 150, 20);

        importeL = new JLabel("Importe");
        importeJ = new JTextField(20);
        importeJ.setBounds(10, 35, 150, 20);

        pDatosF.add(fechaL);//3
        pDatosF.add(calendar);
        pDatosF.add(codigoRepL);
        pDatosF.add(codigoRepJ);//1
        pDatosF.add(importeL);
        pDatosF.add(importeJ);//2
        pDatosF.add(codClienteL);
        pDatosF.add(codClienteJ);//4
        panel1.add(pDatosF, BorderLayout.NORTH);

        //Panel Botones
        pBotones = new JPanel();
        anadir = new JButton("Añadir");
        buscarCliente = new JButton("Buscar Cliente");
        pBotones.add(anadir);
        pBotones.add(buscarCliente);
        panel1.add(pBotones, BorderLayout.CENTER);

        //Panel Listado facturas
        pListado = new JPanel();
        pListado.add(t.getPanel1(), BorderLayout.SOUTH);
        pListado.setVisible(true);
        panel1.add(pListado, BorderLayout.SOUTH);

    }

    public Date getFecha() {
        return calendar.getDate();
    }

    public String getCodigoCliente() {
        return codClienteJ.getText();
    }

    public int getCodigoRepF() {
        int codRepN = Integer.parseInt(codigoRepJ.getText());
        return codRepN;
    }

    public float getImporteF() {
        Float importF = Float.parseFloat(importeJ.getText());
        return importF;
    }

    public void setCodCliente(int codCliente) {
        String cadena = String.valueOf(codCliente);
        //System.out.println("Compruebo que funciona COD CLIENTE:" + cadena);
        this.codClienteJ.setText(cadena);
    }

    public void mostrarErroresFacturas(String mensajeError) {
        JOptionPane.showMessageDialog(this, mensajeError);
    }

    public void addEventosBotones(ActionListener escucharBoton) {
        anadir.addActionListener(escucharBoton);
        buscarCliente.addActionListener(escucharBoton);
    }

    public void actualizarTablaFacturas() {
        t.actualizarTabla();
    }

    //CLIENTES
    public void createPage2() {
        JLabel label = new JLabel("Nuevo Cliente");
        label.setHorizontalTextPosition(JLabel.TRAILING);

        //Panel Datos factura
        pDatosCli.setLayout(null);
        pDatosCli.setLayout(new GridLayout(6, 2));

        nombreCliJ.setBounds(10, 35, 150, 20);
        apellidosCliJ.setBounds(10, 35, 150, 20);
        dniCliJ.setBounds(10, 35, 150, 20);
        cod_postalCliJ.setBounds(10, 35, 150, 20);
        telefonoCliJ.setBounds(10, 35, 150, 20);

        pDatosCli.add(nombreCliL);
        pDatosCli.add(nombreCliJ);

        pDatosCli.add(apellidosCliL);
        pDatosCli.add(apellidosCliJ);
        pDatosCli.add(dniCliL);
        pDatosCli.add(dniCliJ);
        pDatosCli.add(cod_postalCliL);
        pDatosCli.add(cod_postalCliJ);
        pDatosCli.add(telefonoCliL);
        pDatosCli.add(telefonoCliJ);
        panel2.add(pDatosCli, BorderLayout.NORTH);

        //Panel Botones
        pBotonesCli.add(anadirCli);
        pBotonesCli.add(modificarCli);
        pBotonesCli.add(bajaCli);
        panel2.add(pBotonesCli, BorderLayout.CENTER);

        //Panel Listado facturas
        pListadoCli.add(tCli.getPanel2(), BorderLayout.SOUTH);
        pListadoCli.setVisible(true);
        panel2.add(pListadoCli, BorderLayout.SOUTH);

    }

    public String getNombre() {
        return nombreCliJ.getText();
    }

    public String getApellidos() {
        return apellidosCliJ.getText();
    }

    public String getDni() {
        return dniCliJ.getText();
    }

    public String getCodPostal() {
        return cod_postalCliJ.getText();
    }

    public String getTelefono() {
        return telefonoCliJ.getText();
    }

    public void addEventosClientes(ActionListener escucharBoton) {
        anadirCli.addActionListener(escucharBoton);
        modificarCli.addActionListener(escucharBoton);
        bajaCli.addActionListener(escucharBoton);
    }

    public void actualizarTablaClientes() {
        tCli.actualizarTablaClientes();
    }

    public void createPage3() {
        
        panel3.setLayout(new GridLayout(3,1));
        panelFechas.add(frasRealizadasLa);
        panelFechas.add(primFras);
        panelFechas.add(ultiFras);
        panelFechas.add(mostrarFechas);

        //panelBusq.setLayout(new GridLayout(1, 3));

        //mostrarCodPos.setPreferredSize(new Dimension(25, 25));

        panelBusq.add(frasCodPostalLa);
        panelBusq.add(frasCodPos);
        panelBusq.add(mostrarCodPos);
        
        JPanel pRep=new JPanel();
        
        pRep.add(reparNoFacturadas);
        pRep.add(mostrarRepNoFac);

        panel3.add(panelFechas, BorderLayout.NORTH);
        panel3.add(panelBusq, BorderLayout.NORTH);
        panel3.add(pRep, BorderLayout.NORTH);
    }

    public void addEventosBusquedaFacturas(ActionListener escucharBoton) {
        mostrarFechas.addActionListener(escucharBoton);
        mostrarCodPos.addActionListener(escucharBoton);
        mostrarRepNoFac.addActionListener(escucharBoton);
    }

    public Date getFechaPrimera() {
        return primFras.getDate();
    }

    public Date getFechaUltima() {
        return ultiFras.getDate();
    }

    public String getCodPostalListadoFras() {
        return frasCodPos.getText();
    }

    public void mostrarTablaFechas(Date fecha1, Date fecha2) {
        java.sql.Date fechaI= convertirFecha(fecha1);
        java.sql.Date fechaU= convertirFecha(fecha2);    
        System.out.println(fechaI +" "+fechaU);

        tFacFechas.rellenarTablaEntreFechas(fechaI, fechaU);
        JFrame fLisFrasFechas=new JFrame();
        fLisFrasFechas.add(tFacFechas.getPanel1(), BorderLayout.CENTER);
        fLisFrasFechas.setSize(500,300);
        fLisFrasFechas.setLocationRelativeTo(null);
        fLisFrasFechas.setResizable(false);
        fLisFrasFechas.setVisible(true);
    }

    public java.sql.Date convertirFecha(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
    
    public void mostrarTablaReparaciones(){
        JFrame fReparaciones=new JFrame();
        fReparaciones.add(tRepa.getPanel1(), BorderLayout.CENTER);
        fReparaciones.setSize(500,300);
        fReparaciones.setLocationRelativeTo(null);
        fReparaciones.setResizable(false);
        fReparaciones.setVisible(true);
    }
    
    public void mostrarTablaFacturas(String cod_postal){
        JFrame fFacCodPostal=new JFrame();
        
        tCodPos.rellenarTablaPorCodPostal(cod_postal);
        fFacCodPostal.add(tCodPos.getPanel1(), BorderLayout.CENTER);
        fFacCodPostal.setSize(500,300);
        fFacCodPostal.setLocationRelativeTo(null);
        fFacCodPostal.setResizable(false);
        fFacCodPostal.setVisible(true);
    }
}
