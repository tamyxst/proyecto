/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

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
import java.text.DateFormat;
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
 *
 * @author milla_000
 */
public class FileChooser extends JPanel {

    static ArrayList<Reparacion> reparaciones = new ArrayList<>();
    MVC_GestionT_Modelo gesModelo = new MVC_GestionT_Modelo();
    static private final String saltoLinea = "\n";
    JButton btnImportar = new JButton("Importar");
    JButton btnExportar = new JButton("Exportar");
    JFileChooser fchooser;
    JPanel pBotones = new JPanel();

    public FileChooser() {
        fchooser = new JFileChooser();
        fchooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        btnImportar = crearBoton("Abrir fichero");
        btnExportar = crearBoton("Guardar fichero");

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
        if (e.getSource() == btnImportar) {

            int seleccion = fchooser.showOpenDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) {

                File file = fchooser.getSelectedFile();
                String path = file.getAbsolutePath();
                reparaciones = getArchivo(path);
                if (reparaciones.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se han cargado datos");
                } else {
                    gesModelo.anadirReparacionesDesdeFichero(reparaciones);
                    JOptionPane.showMessageDialog(this, "Datos cargados");
                }
                System.out.println("Abriendo: " + file.getName() + "." + saltoLinea);
            } else {
                System.out.println("Operación cancelada");
            }

        } else if (e.getSource() == btnExportar) {
            int respuesta = fchooser.showSaveDialog(FileChooser.this);
            if (respuesta == JFileChooser.APPROVE_OPTION) {
                File file = fchooser.getSelectedFile();
                //Aquí iría lo que haríamos con el archivo
                try {
                    ResultSet rs = gesModelo.cargarReparaciones();
                    while (rs.next()) {
                        String problema = rs.getString("problema");
                        Date f_recogida = rs.getDate("f_recogida");
                        Date f_entrega = rs.getDate("f_entrega");
                        int cod_cliente = rs.getInt("cod_cliente");
                        int id = rs.getInt("id");
                        Reparacion r = new Reparacion(problema, f_recogida, f_entrega, cod_cliente, id);
                        reparaciones.add(r);
                    }

                } catch (SQLException ex) {

                }

                try {
                    BufferedWriter bf = new BufferedWriter(new FileWriter(fchooser.getSelectedFile()));
                    String linea;
                    String titulo = "REPARACIONES PENDIENTES YEAR 2016";
                    String barra = "===============================================================================";
                    bf.write(titulo);
                    bf.newLine();
                    bf.write(barra);
                    bf.newLine();
                    for (Reparacion r : reparaciones) {
                        bf.write(String.format("%-60s%-20s%-20s%-20s%-20s%n" + barra + "%n", r.getProblema(), r.getF_recogida(), r.getF_entrega(), r.getCod_cliente(), r.getId()));

                        bf.flush();
                    }
                    bf.close();
                } catch (IOException ioe) {

                }
                System.out.println("Guardando: " + file.getName() + "." + saltoLinea);
            } else {
                System.out.println("Operación cancelada." + saltoLinea);
            }

        }

    }

    public ArrayList<Reparacion> getArchivo(String ruta) {
        FileReader fr = null;
        BufferedReader br = null;
        String linea;
        try {
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);

            String titulo = br.readLine();
            String barra = br.readLine();
            String barras = "===============================================================================";
            String[] reparacion = new String[5];
            try {
                while ((linea = br.readLine()) != null) {
                    if (!linea.equals(barras)) {
                        String problema = linea.substring(0, 60).trim();
                        String f_recogidaS = linea.substring(60, 80).trim();
                        String f_entregaS = linea.substring(80, 100).trim();
                        String cod_clienteS = linea.substring(100, 120).trim();
                        String idS = linea.substring(120, 140).trim();

                        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                        int cod_cliente = Integer.parseInt(cod_clienteS);
                        int id = Integer.parseInt(idS);

                        java.util.Date f_recogidaU = format.parse(f_recogidaS);
                        java.sql.Date f_recogida = convertirFecha(f_recogidaU);

                        java.util.Date f_entregaU = format.parse(f_entregaS);
                        java.sql.Date f_entrega = convertirFecha(f_entregaU);

                        Reparacion r = new Reparacion(problema, f_recogida, f_entrega, cod_cliente, id);
                        reparaciones.add(r);
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
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

    public java.sql.Date convertirFecha(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public JPanel getPanel() {
        return pBotones;
    }

}
