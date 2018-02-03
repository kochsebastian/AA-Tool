

package control;

import java.io.File;


import model.Model;
import view.View;
import xmlFramework.IOConnector;

/**
 * 
 * @author SebastianKoch
 *
 */
public class Control {
    
    View view;
    private static File openFile;
    private static Boolean alreadySaved = false; // bei speichern gibt noch einen bug
    
    /**
     * 
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }
    
    public static Boolean getalreadySaved() {
    		return alreadySaved;
    }
    
    /**
     * 
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
        }catch(Exception e) {}
    }
    
    /**
     * 
     * 
     */
    public void exportiere() {
        // schreibe Model aus View
		alreadySaved = true;
    		Model model = Model.getInstanz();
        model.schreibeModelAusView(view);
        
        // schreibe xml
        try {
        IOConnector.speichereXML(openFile);
        }catch(Exception e) {}
    }
    
    /**
     * 
     * @param datei
     */
    public void importiere(File datei) {
        // schreibe ein valides Model mit genug Platz in den Tabellen (jew. 1000 Zeilen)
        for (int i = 0; i < 1000; i++) {
            view.fuegeFunktionHinzu();
            view.fuegeDatumHinzu();       
            view.fuegeLeistungHinzu();
            view.fuegeQualitaetsanforderungHinzu();
            view.fuegeGlossarHinzu();
        }    
        alreadySaved = true;
    		openFile = datei;
        Model model = Model.getInstanz();
        model.schreibeModelAusView(view);
        
        // lese xml
        try {
        IOConnector.leseXML(datei);
    		}catch(Exception e) {}
        
        // lese Model in View ein
        view.getViewConnector().leseViewAusModel();
    }
    
    /**
     * 
     * @param datei
     */
    public void loeschen() {
        // schreibe ein valides Model mit genug Platz in den Tabellen (jew. 1000 Zeilen)
        try {
        	openFile.delete();
        	
        }catch(Exception e) {}
        alreadySaved = false;
        openFile = null;
    }
}
