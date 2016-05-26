/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_Comercial;

import Modelo_Comercial.Empleado;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Alumno
 */
public class MVC_TecnicoPrincipal_Vista {

    JFrame fRep1 = new JFrame();
    CargarTecnicos comboTec=new CargarTecnicos();
    //Página 1
    JPanel panel1 = new JPanel();
    JPanel pListadoR = new JPanel();
    JPanel pDatosR = new JPanel();
    JPanel pBotones = new JPanel();
    JButton anadirR = new JButton();
    JButton modificarR = new JButton();
    JButton bajaR = new JButton();
    JLabel cod_repRL = new JLabel("Cód Rep:");
    JTextField cod_repRJ = new JTextField();
    JLabel problemaRL = new JLabel("Problema:");
    JTextField problemaRJ = new JTextField();
    JLabel solucionRL = new JLabel("Solución:");
    JTextField solucionRJ = new JTextField();
    JLabel fecha_recogidaRL = new JLabel("Fecha recogida:");
    JDateChooser fecha_recogidaR = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    JLabel fecha_entregaRL = new JLabel("Fecha entrega:");
    JDateChooser fecha_entregaR = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    JLabel cod_clienteRL = new JLabel("Cód Cliente:");
    JTextField cod_clienteRJ = new JTextField();

    JLabel tecnicoRL = new JLabel("Técnico:"); //Combo box a la tabla usuarios... se correspondan al nombre
    
    //Página 2
    JPanel panel2 = new JPanel();

    public MVC_TecnicoPrincipal_Vista() {
        fRep1.setSize(700, 700);
        fRep1.setLocationRelativeTo(null);

        JLabel label = new JLabel("Bienvenido/a");
        label.setHorizontalTextPosition(JLabel.TRAILING);
        label.setIcon(UIManager.getIcon("OptionPane.informationIcon"));

        createPage1Reparaciones();
        //createPage2Reparaciones();
        //createPage3Repraciones();

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.addTab(null, new JPanel());
        tabbedPane.setTabComponentAt(0, label);

        tabbedPane.addTab("Reparaciones", panel1);
        tabbedPane.addTab("Clientes", panel2);
        //tabbedPane.addTab("Buscar", panel3);

        tabbedPane.setIconAt(1, new ImageIcon("reparaciones.gif"));
        tabbedPane.setIconAt(2, new ImageIcon("clientes.gif"));
        //tabbedPane.setIconAt(3, new ImageIcon("buscar.gif"));

        fRep1.add(tabbedPane);
        fRep1.setVisible(true);
    }

    public void createPage1Reparaciones() {
        JLabel label = new JLabel("Nueva Reparación");
        label.setHorizontalTextPosition(JLabel.TRAILING);

        pDatosR.setLayout(null);
        pDatosR.setLayout(new GridLayout(7, 2));

        cod_repRJ.setBounds(10, 35, 150, 20);
        problemaRJ.setBounds(10, 35, 150, 20);
        solucionRJ.setBounds(10, 35, 150, 20);
        cod_clienteRJ.setBounds(10, 35, 150, 20);

        pDatosR.add(cod_repRL);
        pDatosR.add(cod_repRJ);

        pDatosR.add(problemaRL);
        pDatosR.add(problemaRJ);

        pDatosR.add(solucionRL);
        pDatosR.add(solucionRJ);

        pDatosR.add(fecha_entregaRL);
        pDatosR.add(fecha_entregaR);

        pDatosR.add(fecha_entregaRL);
        pDatosR.add(fecha_entregaR);

        pDatosR.add(cod_clienteRL);
        pDatosR.add(cod_clienteRJ);

        pDatosR.add(tecnicoRL);
        //Añadir combobox
        pDatosR.add(comboTec.getPanel1(), BorderLayout.CENTER);
        panel1.add(pDatosR);
    }

}
