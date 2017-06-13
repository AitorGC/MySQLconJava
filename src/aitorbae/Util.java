/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aitorbae;

/**
 *
 * @author alumno
 */

import java.awt.Component;
import java.awt.Color;
import javax.swing.JOptionPane;

public final class Util {
    
    public static void informar(Component c, String mensaje, String titulo){
        JOptionPane.showMessageDialog(c, mensaje, titulo, 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void advertir(Component c, String mensaje, String titulo){
        JOptionPane.showMessageDialog(c, mensaje, titulo, 
                JOptionPane.WARNING_MESSAGE);
    }

    public static String capturar(Component c, String mensaje, String titulo){
        String datos = JOptionPane.showInputDialog(c, mensaje, titulo, 
                JOptionPane.QUESTION_MESSAGE);
        if(datos == null){
            datos = "";
        }
        return datos;
    }
    
    public static boolean confirmar(Component c, String mensaje, String titulo){
        int respuesta = JOptionPane.showConfirmDialog(c, mensaje, titulo, 
                JOptionPane.YES_NO_OPTION);
        if(respuesta == JOptionPane.YES_OPTION){
            return true;
        }else{
            return false;
        }    
    }
    
    /**public static Color COLOR_ENFOQUE = Color.YELLOW;
    public static Color COLOR_SINENFOQUE = Color.WHITE;*/
}
