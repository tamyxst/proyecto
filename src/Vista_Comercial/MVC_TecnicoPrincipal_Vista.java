/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_Comercial;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author Alumno
 */
public class MVC_TecnicoPrincipal_Vista {
    JFrame fRep1=new JFrame();
    
    JPanel panel1=new JPanel();
    JPanel pListadoR=new JPanel();
    JPanel pDatosR=new JPanel();
    JPanel pBotones=new JPanel();
    JButton anadirR=new JButton();
    JButton modificarR=new JButton();
    JButton bajaR=new JButton();
    JLabel cod_repRL=new JLabel();
    JTextField cod_repRJ=new JTextField();
    JLabel problemaRL=new JLabel();
    JTextField problemaRJ=new JTextField();
    JLabel solucionRL=new JLabel();
    JTextField solucionRJ=new JTextField();
    JLabel fecha_recogidaRL=new JLabel();
    JDateChooser fecha_recogidaR = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    JLabel fecha_entregaRL=new JLabel();
    JDateChooser fecha_entregaR = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    JLabel cod_clienteRL=new JLabel();
    JTextField cod_clienteRJ=new JTextField();
    
    JLabel tecnicoRL=new JLabel(); //Combo box a la tabla usuarios... se correspondan al nombre
    JTextField tecnicoRJ=new JTextField();
}
