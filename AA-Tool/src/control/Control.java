

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
    
    /**
     * 
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }
    
    /**
     * 
     * @param datei
     */
    public void exportiere(File datei) {
        // schreibe Model aus View
        Model model = Model.getInstanz();
        model.schreibeModelAusView(view);
        
        // schreibe xml
        IOConnector.speichereXML(datei);
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
        Model model = Model.getInstanz();
        model.schreibeModelAusView(view);
        
        // lese xml
        IOConnector.leseXML(datei);
        
        // lese Model in View ein
        view.getViewConnector().leseViewAusModel();
    }
}
