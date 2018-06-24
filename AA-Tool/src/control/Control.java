

package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import basis.Resources;
import model.Model;
import view.View;
import view.ViewAufwandsabschaetzung;
import xmlFramework.IOConnector;

/**
 * 
 * @author SebastianKoch
 *
 */
public class Control implements ActionListener{
    
    View view;
    
    private static File openFile = null;
    private static Boolean alreadySaved = false; // bei speichern gibt noch einen bug

    
    
    /**
     * 
     * @param view
     */
    public void setView(View view) {
        this.view = view;
        view.displayView(this);
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
        }catch(Exception e) {
        	alreadySaved = false;
        }
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
    
    
    public void processViewAction(ActionEvent action)
    {
        System.out.println("ACTION: " + action.getActionCommand().toString());
        if (action.getActionCommand().equalsIgnoreCase(Resources.erstellen)){
        	view.closeWindow();
        	view.displayView2(this);
        }
        if (action.getActionCommand().equalsIgnoreCase(Resources.exportieren)){
        	
        	File datei = view.showSpeichernUnterDialog();   
       		exportiere(datei);  
        	
        }
        if (action.getActionCommand().equalsIgnoreCase(Resources.importieren) || 
        		action.getActionCommand().equalsIgnoreCase("Laden")){
        	File datei = view.showOeffnenDialog();
        	view.displayView2(this);
        	importiere(datei);  
        }
        
        if (action.getActionCommand().equalsIgnoreCase("Schließen")){
        	view.closeWindow();
        	view.displayView(this);
        }
        if (action.getActionCommand().equalsIgnoreCase("Speichern")){
        	if(!Control.getalreadySaved()) {
    			File datei = view.showSpeichernUnterDialog();    
    			exportiere(datei);  
    		}else {
    			exportiere(openFile);   
    		}
        }
        if (action.getActionCommand().equalsIgnoreCase("Löschen")){
        	if(view.showLoeschenDialog()) {
        		try {
        			loeschen();   
        		}catch(Exception e) {}
        		view.closeWindow();
            	view.displayView(this);
        	}
        }
        
        
        if (action.getActionCommand().equalsIgnoreCase("Weiter Komplexität")){
        	if(!Control.getalreadySaved()) {
    			File datei = view.showSpeichernUnterDialog();    
    			exportiere(datei);  
    		}else {
    			exportiere(openFile);   
    		}
        	IOConnector.resetLFBuffer();
        	IOConnector.resetLDBuffer();
			importiere(openFile);
			ViewAufwandsabschaetzung.addProdukt();	
        }
        
        //////////

        if (action.getActionCommand().equalsIgnoreCase(Resources.fuegeProduktdatumHinzu)){
            view.fuegeFunktionHinzu();
            
        }
        
        if (action.getActionCommand().equalsIgnoreCase(Resources.loescheProduktfunktion)){
        	view.loescheFunktion();
        	
        }
        
        if (action.getActionCommand().equalsIgnoreCase(Resources.fuegeProduktdatumHinzu)){
        	view.fuegeDatumHinzu();
       
        }
        
        if (action.getActionCommand().equalsIgnoreCase(Resources.loescheProduktdatum)){
        	view.loescheDatum();
           
        }
        
        if (action.getActionCommand().equalsIgnoreCase(Resources.fuegeProduktleistungHinzu)){
        	view.fuegeLeistungHinzu();
            
        }
        
        if (action.getActionCommand().equalsIgnoreCase(Resources.loescheProduktleistung)){
        	view.loescheLeistung();
          
        }
        
        if (action.getActionCommand().equalsIgnoreCase(Resources.fuegeQualitaetsanforderungHinzu)){
        	view.fuegeQualitaetsanforderungHinzu();
           
        }
        
        if (action.getActionCommand().equalsIgnoreCase(Resources.loescheQualitaetsanforderung)){
        	view.loescheQualitaetsanforderung();
           
        }
        
        if (action.getActionCommand().equalsIgnoreCase(Resources.fuegeGlossarHinzu)){
        	view.fuegeGlossarHinzu();
           
        }
        
        if (action.getActionCommand().equalsIgnoreCase(Resources.loescheGlossar)){
        	view.loescheGlossar();
          
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        processViewAction(arg0);
    }
}
