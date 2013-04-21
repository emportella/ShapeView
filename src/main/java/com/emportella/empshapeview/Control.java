
package com.emportella.empshapeview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 * 
 */
public class Control {

    /**
     * 
     */
    
    public static void main(String[] args) throws Exception {
        // display a data store file chooser dialog for shapefiles
        try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(UnsupportedLookAndFeelException ex){
                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
//        File argfile = new File(args[0]);
//        File folder = new File(argfile.getParent());
//        
//        
//        if (folder.isDirectory()) {
//           
//            
//        } else {
//        }
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();

        // Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("Quickstart");
        JButton jb = new JButton("tetste");
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TTTTTT");
            }
        });
        
        
        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);
        
        // Now display the map
        JMapFrame jmf = new JMapFrame();
        jmf.setSize(800, 600);
//        jmf.enableLayerTable(true);
        jmf.enableStatusBar(true);
        jmf.enableToolBar(true);
        jmf.getToolBar().add(jb);
        jmf.setMapContent(map);
        jmf.setVisible(true);
        jmf.initComponents();
    }

}