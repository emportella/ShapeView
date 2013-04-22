
package com.emportella.shapeview.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
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
public class ShapeViewControl {

    private int fileCount;
    private File[] files;
    private File file;
    private FileDataStore store;
    private SimpleFeatureSource featureSource;
    private MapContent map;
    private JMapFrame jmf;
    private JButton lJB, rJB;

    public ShapeViewControl() {
    }

    public void fileChooser() throws IOException {
        file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            System.exit(0);
        }
        files = file.getParentFile().listFiles(new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return (name.endsWith(".shp"));
            }
        });
        setAddLayer();
         mapTitle();
        initMap();
        displayMap();
    }

    private void setAddLayer() throws IOException {
        store = null;
        store = FileDataStoreFinder.getDataStore(file);
        featureSource = store.getFeatureSource();
        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        map = new MapContent();
        map.addLayer(layer);
        
    }

    private void mapTitle() {
        map.setTitle(file.getName());
        
    }

    private void initMap() {
        jmf = new JMapFrame();
        jmf.setSize(800, 600);
        //jmf.enableLayerTable(true);
        jmf.enableStatusBar(true);
        jmf.enableToolBar(true);
        enableButtons();
    }

    private void displayMap() {

        jmf.setMapContent(map);
        jmf.setTitle("ShapeView - " + file.getName());
        jmf.setVisible(true);
        jmf.initComponents();

    }

    private void enableButtons() {

        lJB = new BasicArrowButton(SwingConstants.WEST);
        rJB = new BasicArrowButton(SwingConstants.EAST);
        lJB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    navigateFolder();
                } catch (IOException ex) {
                    Logger.getLogger(ShapeViewControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        jmf.getToolBar().add(lJB);
        jmf.getToolBar().add(rJB);
    }

    public void setFile(String string) throws IOException {
        file = new File(string);
        files = file.getParentFile().listFiles(new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return (name.endsWith(".shp"));
            }
        });
        setAddLayer();
        mapTitle();
        displayMap();
    }

    private void nextFile(File nextFile) throws IOException {
        this.file = nextFile;
        map.dispose();
        setAddLayer();
        mapTitle();
        displayMap();
    }

    private void navigateFolder() throws IOException {
        if (fileCount == 0) {
            fileCount = 1;
            if (fileCount < files.length) {
                nextFile(files[fileCount]);
                fileCount++;

            }
        } else {
            if (fileCount < files.length) {
                nextFile(files[fileCount]);
                fileCount++;
            }
        }

    
}
}