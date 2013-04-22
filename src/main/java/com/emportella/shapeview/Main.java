/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.emportella.shapeview;

import com.emportella.shapeview.control.ShapeViewControl;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Eduardo Portella
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {

        setLookAndFeel();
        int i = args.length;
        if (i == 0) {
            EventQueue.invokeLater(new Runnable() {

                public void run() {
                    try {
                        ShapeViewControl svc = new ShapeViewControl();
                        svc.fileChooser();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ShapeView - Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        } else {
            EventQueue.invokeLater(new Runnable() {

                public void run() {
                    try {
                        ShapeViewControl svc = new ShapeViewControl();
                        svc.setFile(args[0]);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

    }

    private static void setLookAndFeel() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
