/**
 * 
 */
package control;

import java.awt.event.ActionEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import basis.Resources;
import model.Model;
import view.View;
import view.ViewAufwandsabschaetzung;
import xmlFramework.IOConnector;

/**
 * @author SebastianKoch
 *
 */
public class SimControl implements IControl {

	private static File openFile = null;
    private static Boolean alreadySaved = false; // bei speichern gibt noch einen bug
    View view;

    public static Boolean getalreadySaved() {
        return alreadySaved;
    }

    /**
     * @param view
     */
    public void setView(View view) {
        this.view = view;
        view.displayEmpty(this);
        
    }

    /**
     * @param datei
     */
    public void exportiere(File datei) {
        // schreibe Model aus View
        openFile = datei;
        alreadySaved = true;
        Model model = Model.getInstanz();
        model.schreibeModelAusView(view);

        // schreibe xml
        try {
            IOConnector.speichereXML(datei);
        } catch (Exception e) {
            e.getCause();
            e.getMessage();
            alreadySaved = false;
        }
    }


    /**
     * @param datei
     */
    public void importiere(File datei) {
        // schreibe ein valides Model mit genug Platz in den Tabellen (jew. 1000 Zeilen)

        for (int i = 0; i < 1000; i++) {
            view.fuegeFunktionHinzu();
            view.fuegeDatumHinzu();
            view.fuegeGlossarHinzu();
        }
       /* for (int i = 0; i < 5; i++) {
            view.fuegeQualitaetsanforderungHinzu();
        }*/
        alreadySaved = true;
        openFile = datei;
        Model model = Model.getInstanz();
        model.schreibeModelAusView(view);

        // lese xml
        try {
            IOConnector.leseXML(datei);
        } catch (Exception e) {
        }

        // lese Model in View ein
        view.getViewConnector().leseViewAusModel();
    }

    


    public void processViewAction(ActionEvent action) {
        System.out.println("ACTION: " + action.getActionCommand().toString());
        if (action.getActionCommand().equalsIgnoreCase(Resources.erstellen)) {
            view.closeWindow();
            view.displayFull(this);
        } else if (action.getActionCommand().equalsIgnoreCase(Resources.exportieren)) {
        	
        		File dir = new File("test");
        		dir.mkdirs();
        		File tmp = new File(dir, "testxml.xml");
        		try {
					tmp.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
            exportiere(tmp);

        } else if (action.getActionCommand().equalsIgnoreCase(Resources.importieren)) {
            File datei = new File("test" + File.separator + "testxml.xml");
            view.displayFull(this);
            importiere(datei);
        } else if (action.getActionCommand().equalsIgnoreCase(Resources.schliessen)) {
            view.closeWindow();
            view.displayEmpty(this);
        } 
        else if(action.getActionCommand().equalsIgnoreCase(Resources.fuegeSimHinzu)) {
        	view.fuegeSimHinzu();
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        processViewAction(arg0);
    }

}
