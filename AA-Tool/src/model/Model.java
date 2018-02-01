

package model;

import java.util.ArrayList;
import basis.Resources;
import view.View;

/**
 * 
 * @author SebastianKoch
 *
 */
public class Model extends AModel {
    // Erstellung aller Inhalte
    
    private static Model model;
    
    private Model () {
        // Datenstruktur Knoten fuer Tab erstellen
        deckblatt = new Tab(Resources.deckblatt);
        kundenbeschreibung = new Tab(Resources.kundenBeschreibung);
        zielbestimmung = new Tab(Resources.zielbestimmung);
        produkteinsatz = new Tab(Resources.produkteinsatz);
        produktfunktionen = new Tab(Resources.produktfunktionen);
        produktdaten = new Tab(Resources.produktdaten);
        produktleistungen = new Tab(Resources.produktleistungen);
        qualitaetsanforderungen = new Tab(Resources.qualitaetsanforderungen);
        ergaenzungen = new Tab(Resources.ergaenzungen);
        glossar = new Tab(Resources.glossar);
        aufwandsschaetzung = new Tab(Resources.aufwandsabschaetzung);
       
        // Inhalte des Deckblatts erstellen
        ATab deckblattInhalt = new Inhalt(Resources.deckblatt);
        deckblatt.addNext(deckblattInhalt);
     
        // Inhalte der Kundenbeschreibung erstellen
        ATab kundenbeschreibungInhalt = new Inhalt(Resources.kundenBeschreibung);
        kundenbeschreibung.addNext(kundenbeschreibungInhalt);
        
        // Inhalte der Zielbestimmung erstellen
        ATab zielbestimmungInhalt = new Inhalt(Resources.zielbestimmung);
        zielbestimmung.addNext(zielbestimmungInhalt);
        
        // Inhalte des Produkteinsatzes erstellen
        ATab produkteinsatzInhalt = new Inhalt(Resources.produkteinsatz);
        produkteinsatz.addNext(produkteinsatzInhalt);
        
        // Inhalte der Produktfunktionen erstellen
        ATab produktfunktionenInhalt = new Inhalt(Resources.produktfunktionen);
        produktfunktionen.addNext(produktfunktionenInhalt);
        
        // Inhalte der Produktdaten erstellen
        ATab produktdatenInhalt = new Inhalt(Resources.produktdaten);
        produktdaten.addNext(produktdatenInhalt);
        
        // Inhalte der Produktleistungen erstellen
        ATab produktleistungenInhalt = new Inhalt(Resources.produktleistungen);
        produktleistungen.addNext(produktleistungenInhalt);
        
        // Inhalte der Qualitaetsanforderungen erstellen
        ATab qualitaetsanforderungenInhalt = new Inhalt(Resources.qualitaetsanforderungen);
        qualitaetsanforderungen.addNext(qualitaetsanforderungenInhalt);
        
        // Inhalte der Ergaenzungen erstellen
        ATab ergaenzungenInhalt = new Inhalt(Resources.ergaenzungen);
        ergaenzungen.addNext(ergaenzungenInhalt);
        
        // Inhalte der Glossar erstellen
        ATab glossarInhalt = new Inhalt(Resources.glossar);
        glossar.addNext(glossarInhalt);
        
        // Inhalte der Schaetzung einhaengen
        ATab aufwandsschaetzungInhalt = new Inhalt(Resources.aufwandsabschaetzung);
        aufwandsschaetzung.addNext(aufwandsschaetzungInhalt);
        
        // Tab einhaengen
        deckblatt.addNext(kundenbeschreibung);
        kundenbeschreibung.addNext(zielbestimmung);
        zielbestimmung.addNext(produkteinsatz);
        produkteinsatz.addNext(produktfunktionen);
        produktfunktionen.addNext(produktdaten);
        produktdaten.addNext(produktleistungen);
        produktleistungen.addNext(qualitaetsanforderungen);
        qualitaetsanforderungen.addNext(ergaenzungen);
        ergaenzungen.addNext(glossar);
        glossar.addNext(aufwandsschaetzung);
    }
    

    public static Model getInstanz() {        
        if (model == null) {
            model = new Model();
        }        
        return model;
    }
    
   
    public ATab getRoot() {
        return deckblatt;
    }
    
    public void setRoot(ATab deckblatt) {
        this.deckblatt =  deckblatt;
    }
    
    // die gesamte GUI auslesen und alle Daten daraus in das Model schreiben
    public void schreibeModelAusView(View view) {
        ATab aktuellerTab = getRoot();
        
        while(aktuellerTab.getNext() != null) {
            ArrayList <ATab> next = aktuellerTab.getNext();
            
            for(int j = 0; j < next.size(); j++) {
                if(next.get(j) instanceof Inhalt) {
                    Inhalt tmpNext = (Inhalt) next.get(j);
                    String tmp[][] = view.getViewConnector().getInhalt(tmpNext.getName());
                    
                    tmpNext.setInhalt(tmp);
                }
            }
            
            for(int j = 0; j < next.size(); j++) {
                if(next.get(j) instanceof Tab || next.get(j) instanceof Inhalt) {
                    aktuellerTab = next.get(j);
                }
            }
        }
    }
}
