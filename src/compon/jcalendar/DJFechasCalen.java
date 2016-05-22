/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compon.jcalendar;

import java.awt.Color;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author milla_000
 */
public class DJFechasCalen {
    
Calendar calendar = Calendar.getInstance();

 public DJFechasCalen() {

 }

 public boolean isSpecial(Date date) {
  calendar.setTime(date);
  return calendar.get(Calendar.MONTH) == Calendar.DECEMBER
    && calendar.get(Calendar.DAY_OF_MONTH) == 24;
 }
 
 

 public Color getSpecialForegroundColor() {
  return Color.GREEN;
 }

 public Color getSpecialBackroundColor() {
  return Color.WHITE;
 }

 public String getSpecialTooltip() {
  return "Es Navidad";
 }

 public boolean isInvalid(Date date) {
  calendar.setTime(date);
  return calendar.get(Calendar.MONTH) == Calendar.JANUARY
    && calendar.get(Calendar.DAY_OF_MONTH) == 01;
 }

 public Color getInvalidForegroundColor() {
  return Color.WHITE;
 }

 public Color getInvalidBackroundColor() {
  return Color.BLACK;
 }

 public String getInvalidTooltip() {
  return "No es día Laborable";
 }
}
  