/**
 * 
 */
package control;

import java.awt.event.ActionEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import aufwandsabschaetzung.AOptimierung;
import aufwandsabschaetzung.ViewFunctionPoint;
import basis.Resources;
import model.Model;
import view.View;
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
        else if (action.getActionCommand().equalsIgnoreCase(Resources.loescheProduktdatum)) {
            view.loescheDatum();

        }else if (action.getActionCommand().equalsIgnoreCase(Resources.loescheProduktfunktion)) {
            view.loescheFunktion();

        }else if (action.getActionCommand().equalsIgnoreCase(Resources.ladeDaten)) {
        	
            IOConnector.resetLFBuffer();
            IOConnector.resetLDBuffer();
            importiere(openFile);
            ViewFunctionPoint.addProdukt();
        } else if (action.getActionCommand().equalsIgnoreCase("Functionpoints berechnen")) {
        	ViewFunctionPoint.countfunktionen();
            ViewFunctionPoint.countdaten();
        	ViewFunctionPoint.calculateFunctionPoint();
        }
        else if(action.getActionCommand().equalsIgnoreCase("Lade optimierte Einflussfaktoren einer letzten Aufwandsabschaetzung")) {
        	int[] einflussfaktoren = AOptimierung.getOpimierungsDatei();
        	ViewFunctionPoint.setTableEinflussfaktoren(einflussfaktoren);
        }
        else if(action.getActionCommand().equalsIgnoreCase("Optimieren")) {
        	if(ViewFunctionPoint.getZielErgebnis() != 0) {
        		ViewFunctionPoint.optimieren(ViewFunctionPoint.getZielErgebnis(), ViewFunctionPoint.getE1(), ViewFunctionPoint.getEinflussfaktor());
        	}
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        processViewAction(arg0);
    }

}
