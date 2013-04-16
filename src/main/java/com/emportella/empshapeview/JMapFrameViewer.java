/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.emportella.empshapeview;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToolBar;
import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;

/**
 *
 * @author Eduardo Portella
 */
public class JMapFrameViewer extends JMapFrame {

    private JToolBar jTB;
    private JButton jB;
    
    public JMapFrameViewer() {
    }

    public JMapFrameViewer(MapContent content) {
        JMapFrame.showMap(content);
        ButtonGroup bg = new ButtonGroup();
        jB = new JButton("teste");
        jB.setAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botão");
            }
        });
        
        jB.setEnabled(true);
        
        jTB = new JToolBar("Teste");
        jTB.setVisible(true);
        
        jTB.add(jB);
        super.getContentPane().add(jTB);
    }

    @Override
    public void initComponents() {
    }

}
