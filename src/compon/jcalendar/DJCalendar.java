/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compon.jcalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Clase DJCalendar.
 * 
 * @author Tamara Gascón Moreno
 * @version Tienda Reparaciones 1.0 Mayo 2016
 */
public class DJCalendar {
    /**
     * Método que añade al calendario fechas especiales.
     * @return Devuelve fechas especiales
     */
    public static List fechasEspeciales() {
        List fechas = new ArrayList();

        Calendar calendar = new GregorianCalendar(2016, Calendar.AUGUST, 15);
        fechas.add(calendar);
        calendar = new GregorianCalendar(2016, Calendar.DECEMBER, 25);
        fechas.add(calendar);
        calendar = new GregorianCalendar(2016, Calendar.JANUARY, 1);
        fechas.add(calendar);
        calendar = new GregorianCalendar(2016, Calendar.MAY, 1);
        fechas.add(calendar);
        calendar = new GregorianCalendar(2016, Calendar.NOVEMBER, 1);
        fechas.add(calendar);
        
        return fechas;
    }

    public static List tipFechas() {
        List tips = new ArrayList();
        tips.add("Diciembre 25");
        tips.add("Enero 5");
        tips.add("Agosto 15");
        tips.add("Enero 1");
        tips.add("Mayo 1");
        tips.add("Noviembre 1");

        return tips;
    }


}
