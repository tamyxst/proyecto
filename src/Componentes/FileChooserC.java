/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo_Comercial.MVC_GestionC_Modelo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author milla_000
 */
public class FileChooserC extends JPanel {
    MVC_GestionC_Modelo gesModeloC=new MVC_GestionC_Modelo();
    static private final String saltoLinea = "\n";
    JButton btnExportar = new JButton("Exportar");
    JFileChooser fchooser;
    JPanel pBotones = new JPanel();

    public FileChooserC() {
        fchooser = new JFileChooser();
        fchooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        btnExportar = crearBoton("Guardar Reparaciones");
        pBotones.add(btnExportar);

        add(pBotones, BorderLayout.PAGE_START);
    }

    public JButton crearBoton(String textoBoton) {
        JButton boton = new JButton(textoBoton);
        boton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comportamientoBotones(e);
            }

        });
        return boton;
    }

    public void comportamientoBotones(ActionEvent e) {
        if (e.getSource() == btnExportar) {
            int respuesta = fchooser.showSaveDialog(FileChooserC.this);
            if (respuesta == JFileChooser.APPROVE_OPTION) {
                File file = fchooser.getSelectedFile();
                //Aquí iría lo que haríamos con el archivo
                String path = file.getAbsolutePath();
                getXML(path);
            JOptionPane.showMessageDialog(this, "Datos guardados");
                System.out.println("Guardando: " + file.getName() + "." + saltoLinea);
            } else {
                System.out.println("Operación cancelada." + saltoLinea);
            }

        }

    }
    public JPanel getPanel() {
        return pBotones;
    }
    public void getXML(String ruta)
    {
        Document doc = new Document();
        //etiqueta principal
        Element root = new Element("DataBase");
        doc.setRootElement(root);
        try {
            ResultSet rs= gesModeloC.listaReparaciones();
            //consulta
            ResultSetMetaData resultmetadata = rs.getMetaData();
            //obtiene la cantidad de columnas de la tabla
            int numCols = resultmetadata.getColumnCount();
            while (rs.next()) {
                List elmts = new ArrayList();
                for (int i=1; i <= numCols; i++) {
                    //obtiene nombre de columna
                    String colName = resultmetadata.getColumnName(i);                    
                    //Obtiene el contendio de la celda
                    String colVal = rs.getString(i);                    
                    //forma los elementos para el XML
                    Element elmt = new Element(colName);
                    elmt.setText(colVal);
                    elmts.add(elmt);
                }
                //para la etiqueta
                Element row = new Element("reparacion");
                //añade el registro en xml
                row.setContent(elmts);
                root.addContent(row);                
            }
            //cierra database
            rs.close();
            // Graba el archiv XML en disco
            XMLOutputter outputter = new XMLOutputter( Format.getPrettyFormat() );
            
            String Rruta= ruta + ".xml";
            try {
               outputter.output(doc, new FileOutputStream ( Rruta ));
               System.out.println("Arhivo XML creado en " + ruta);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
               System.err.println(e);
        }
    }

}


