/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Proyecto.Reparacion;
import Modelo_Tecnico.MVC_GestionT_Modelo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase FileChooserT Comercial. Clase que hereda de JPanel. El técnico cuando
 * pulse el botón "Exportar", deberá escoger la ruta dónde guardar el
 * archivo, y se generará un archivo .txt con las reparaciones. Al pulsar el botón
 * "Importar" se podrá importar a la base de datos las reparaciones desde un
 * archivo .txt del mismo modelo .txt exportado.

 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */

public class FileChooserT extends JPanel {

    static ArrayList<Reparacion> reparaciones = new ArrayList<>();
    MVC_GestionT_Modelo gesModelo = new MVC_GestionT_Modelo();
    static private final String saltoLinea = "\n";
    JButton btnImportar = new JButton("Importar");
    JButton btnExportar = new JButton("Exportar");
    JFileChooser fchooser;
    JPanel pBotones = new JPanel();
    
    public FileChooserT() {
        fchooser = new JFileChooser();
        
        //Directorios y ficheros.
        fchooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        btnImportar = crearBoton("Cargar Reparaciones");
        btnExportar = crearBoton("Guardar Reparaciones");
        
        //Añadimos los botones
        pBotones.add(btnImportar);
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
        //Si el boton es Importar
        if (e.getSource() == btnImportar) {
            int seleccion = fchooser.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File file = fchooser.getSelectedFile();
                String path = file.getAbsolutePath();
                //Recogemos los datos del fichero .txt 
                reparaciones = getArchivo(path);
                //Si el array esta vacío salta mensaje
                if (reparaciones.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se han cargado datos");
                } else {
                    //Se añaden los datos a la BD, mostramos mensaje como que ha ido bien.
                    gesModelo.anadirReparacionesDesdeFichero(reparaciones);
                    JOptionPane.showMessageDialog(this, "Datos cargados");
                }
                System.out.println("Abriendo: " + file.getName() + "." + saltoLinea);
            } else {
                System.out.println("Operación cancelada");
            }
            //Si el boton es exportar
        } else if (e.getSource() == btnExportar) {
            int respuesta = fchooser.showSaveDialog(FileChooserT.this);
            if (respuesta == JFileChooser.APPROVE_OPTION) {
                File file = fchooser.getSelectedFile();
                String path = file.getAbsolutePath();
                //Se crea el archivo .txt si todo va bien
                crearArchivoTxt(path);
                System.out.println("Guardando: " + file.getName() + "." + saltoLinea);
            } else {
                System.out.println("Operación cancelada." + saltoLinea);
            }

        }

    }
    /**
     * Método que realiza una consulta a la BD, recoge los datos de las
     * reparaciones y las almacena en un fichero .txt con formato.
     * @param path corresponde a la ruta seleccionada por el técnico.
     */
    public void crearArchivoTxt(String path) {
        //Aquí iría lo que haríamos con el archivo
        try {
            //Recogemos datos de la consulta
            ResultSet rs = gesModelo.cargarReparaciones();
            while (rs.next()) {
                String problema = rs.getString("problema");
                Date f_recogida = rs.getDate("f_recogida");
                Date f_entrega = rs.getDate("f_entrega");
                int cod_cliente = rs.getInt("cod_cliente");
                int id = rs.getInt("id");
                Reparacion r = new Reparacion(problema, f_recogida, f_entrega, cod_cliente, id);
                //Las añadimos al arrayList
                reparaciones.add(r);
            }
        } catch (SQLException ex) {}
        try {
            //Creamos un fichero .txt
            BufferedWriter bf = new BufferedWriter(new FileWriter(path + ".txt"));
            String linea;
            String titulo = "REPARACIONES PENDIENTES YEAR 2016";
            String barra = "===============================================================================";
            //Escribimos el titulo
            bf.write(titulo);
            bf.newLine(); //Salto linea
            bf.write(barra);
            bf.newLine(); //Salto linea
            for (Reparacion r : reparaciones) {
                //Le damos formato y entre producto y producto colocamos barras.
                bf.write(String.format("%-60s%-20s%-20s%-20s%-20s%n" + barra + "%n", r.getProblema(), r.getF_recogida(), r.getF_entrega(), r.getCod_cliente(), r.getId()));
                bf.flush();
            }
            bf.close();
        } catch (IOException ioe) {}
    }
    /**
     * Método que recoge los datos del fichero .txt y los almacena en el
     * array de reparaciones
     * @param ruta
     * @return 
     */
    public ArrayList<Reparacion> getArchivo(String ruta) {
        FileReader fr = null;
        BufferedReader br = null;
        String linea;
        try {
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);
            //Leemos el titulo
            String titulo = br.readLine();
            String barra = br.readLine();
            String barras = "===============================================================================";
            String[] reparacion = new String[5];
            try {
                while ((linea = br.readLine()) != null) {
                    //Recogemos los datos 
                    if (!linea.equals(barras)) {
                        String problema = linea.substring(0, 60).trim();
                        String f_recogidaS = linea.substring(60, 80).trim();
                        String f_entregaS = linea.substring(80, 100).trim();
                        String cod_clienteS = linea.substring(100, 120).trim();
                        String idS = linea.substring(120, 140).trim();
                        
                        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                        int cod_cliente = Integer.parseInt(cod_clienteS);
                        int id = Integer.parseInt(idS);
                        
                        //Convertimos las fechas
                        java.util.Date f_recogidaU = format.parse(f_recogidaS);
                        java.sql.Date f_recogida = convertirFecha(f_recogidaU);

                        java.util.Date f_entregaU = format.parse(f_entregaS);
                        java.sql.Date f_entrega = convertirFecha(f_entregaU);

                        Reparacion r = new Reparacion(problema, f_recogida, f_entrega, cod_cliente, id);
                        reparaciones.add(r);
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(FileChooserT.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(reparaciones.get(1).getProblema());

        } catch (FileNotFoundException fne) {
            System.out.println("No se ha encontrado el archivo");
        } catch (IOException ioe) {
            System.out.println("Error");
            try {
                br.close();
            } catch (IOException ex) {
            }
        }
        return reparaciones;
    }
    /**
     * Método que convierte la fecha de java.util a java.sql
     * @param date java util fecha que le pasamos para convertir
     * @return date java sql fecha que devuelve 
     */
    public java.sql.Date convertirFecha(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
    /**
     * Método que devuelve el panel dónde se encuentra el JFileChooser
     * @return el panel dónde esta el JFileChooser
     */
    public JPanel getPanel() {
        return pBotones;
    }

}
