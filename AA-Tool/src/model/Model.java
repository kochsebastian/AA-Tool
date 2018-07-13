package model;


import basis.Resources;
import view.View;

import java.util.ArrayList;

/**
 * Model
 * @author SebastianKoch
 */
public class Model extends AModel {
    // Erstellung aller Inhalte

    private static Model model;

    private Model() {
        // Datenstruktur Knoten fuer Tab erstellen
        deckblatt = new Tab(Resources.deckblatt);
        kundenbeschreibung = new Tab(Resources.kundenBeschreibung);
        zielbestimmung = new Tab(Resources.zielbestimmung);
        produkteinsatz = new Tab(Resources.produkteinsatz);
        produktfunktionen = new Tab(Resources.produktfunktionen);
        produktdaten = new Tab(Resources.produktdaten);
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

        // Inhalte der Ergaenzungen erstellen
        ATab ergaenzungenInhalt = new Inhalt(Resources.ergaenzungen);
        ergaenzungen.addNext(ergaenzungenInhalt);

        // Inhalte der Glossar erstellen
        ATab glossarInhalt = new Inhalt(Resources.glossar);
        glossar.addNext(glossarInhalt);

        // Inhalte der Schaetzung einhaengen
        ATab aufwandsschaetzungInhalt = new Inhalt(Resources.aufwandsabschaetzung);
        aufwandsschaetzung.addNext(aufwandsschaetzungInhalt);

        // Tabs verketten
        deckblatt.addNext(kundenbeschreibung);
        kundenbeschreibung.addNext(zielbestimmung);
        zielbestimmung.addNext(produkteinsatz);
        produkteinsatz.addNext(produktfunktionen);
        produktfunktionen.addNext(produktdaten);
        produktdaten.addNext(ergaenzungen);
        ergaenzungen.addNext(glossar);
        //  glossar.addNext(aufwandsschaetzung);
    }

    /**
     * 
     * @return 
     */
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
        this.deckblatt = deckblatt;
    }

    /**
     *  die gesamte GUI auslesen und alle Daten daraus in das Model schreiben
     * @param view
     */
    public void schreibeModelAusView(View view) {
        ATab aktuellerTab = getRoot();

        while (aktuellerTab.getNext() != null) {
            ArrayList<ATab> next = aktuellerTab.getNext();

            for (int j = 0; j < next.size(); j++) {
                if (next.get(j) instanceof Inhalt) {
                    Inhalt tmpNext = (Inhalt) next.get(j);
                    String tmp[][] = view.getViewConnector().getInhalt(tmpNext.getName());

                    tmpNext.setInhalt(tmp);
                }
            }

            for (int j = 0; j < next.size(); j++) {
                if (next.get(j) instanceof Tab || next.get(j) instanceof Inhalt) {
                    aktuellerTab = next.get(j);
                }
            }
        }
    }


}
