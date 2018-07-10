/**

 *
 */
package basis;

import javax.swing.Icon;

/**
 * @author SebastianKoch
 */
public class Resources {


    // enthaelt alle Zeichenketten, die zur Anzeige der GUI noetig sind zentral an einem Ort
    public static final String aaTool = "AA-Tool";

    public static final String vorBeendenSpeichern = "vor dem Beenden speichern?";

    public static final String exportieren = "XML-Exportieren";
    public static final String importieren = "XML-Importieren";
    public static final String erstellen = "Neues Projekt erstellen";

    public static final String deckblatt = "Deckblatt";
    public static final String kundenBeschreibung = "Kundenbeschreibung";
    public static final String zielbestimmung = "Zielbestimmung";
    public static final String produkteinsatz = "Produkteinsatz";
    public static final String produktumgebung = "Produktumgebung";
    public static final String produktfunktionen = "Produktfunktionen";
    public static final String produktdaten = "Produktdaten";
    public static final String qualitaetsanforderungen = "Qualitätsanforderungen";
    public static final String ergaenzungen = "Ergänzungen";
    public static final String glossar = "Glossar";
    public static final String aufwandsabschaetzung = "Aufwandsabschätzung";

    public static final String deckblattStandardInhalt = "Deckblatt\n======================\n\nTitel:\n\nDatum:\n\nAuftraggeber:\n\nErstellt von:";
    public static final String zielbestimmungStandardInhalt = "Zielbestimmung\n======================\n\nMusskriterien:\n\nWunschkriterien:\n\nAbgrenzungskriterien:";
    public static final String produkteinsatzStandardInhalt = "Produkteinsatz\n======================\n\nAnwendungsbereiche:\n\nZielgruppen:\n\nBetriebsbedingungen:";
    public static final String produktumgebungStandardInhalt = "Produktumgebung\n======================\n\nSoftware:\n\nHardware:\n\nOrgware:";
    public static final String kundenBeschreibungStandardInhalt = "Kundenbeschreibung\n======================\n\n Hier kann die Kundenbeschreibung stehen";
    public static final String ergaenzungenStandardInhalt = "Ergaenzungen\n======================\n\n Hier kann die Ergaenzungen stehen";
    public static final String aufwandsabschaetzungStandardInhalt = "Ich habe keine ahnung wie die Aufwandsabschätzung ablaufen soll also erstmal kurz dieser Platzhalter";

    public static final String fuegeProduktfunktionHinzu = "füge Produktfunktion hinzu";
    public static final String loescheProduktfunktion = "lösche Produktfunktion";

    public static final String fuegeProduktdatumHinzu = "füge Produktdatum hinzu";
    public static final String loescheProduktdatum = "lösche Produktdatum";


    public static final String fuegeQualitaetsanforderungHinzu = "füge Qualitätsanforderung hinzu";
    public static final String loescheQualitaetsanforderung = "lösche Qualitätsanforderung";


    public static final String fuegeGlossarHinzu = "füge Glossar hinzu";
    public static final String loescheGlossar = "lösche Glossar";


    public static final String bezeichnung = "Bezeichnung";
    public static final String wert = "Wert";

	public static final String laden = "Laden";

	public static  final String loeschen = "Löschen";
	public static final String speichern = "Speichern";
	public static final String schliessen = "Schließen";

	public static final String fuegeSimHinzu = "fuegeSimHinzu";
	
	public static final String readme = "AA-Tool-Readme (Bei erstverwendung bitte Lesen)\n======================\n "
			+ "Funktionserklärung:\n  Neues Projekt anlegen: öffnet ein neues Fenster, "
			+ "mit einer neuen Anforderungsanalyse\n "
			+ " Laden: Lässt Sie ein vorhandenes Projekt laden (Filetyp ist \"name\".xml\n"
			+ "  XML-Importieren: Lässt Sie ein vom AA-Tool erstelltes XML-Dokument importieren\n"
			+ "  XML-Exportieren: Lässt Sie ein XML-Dokument erstellen, dass alle Inhalte der Anforderungsanalyse enthält\n"
			+ "  Schliessen: Schließt das aktuelle Projekt\n"
			+ "  Speichern: Speichert Ihr aktuelles Projekt, wurde bereits eine Datei erstellt, oder geladen wird die aktuelle "
			+ "Datei überschrieben\n"
			+ "  Löschen: Löscht Ihre aktuelle Anforderungsanalyse (befindet sich eine verknüpfte Datei auf ihrem Spiecher "
			+ "wird diese ebenfalls gelöscht\n\n"
			+ "Gehen Sie bitte nicht davon aus dass das Programm automatisch speichert \n"
			+ "Nur in Ausnahmefällen wird gefragt ob Sie Sicher sind mit dem susgewählten Punkt fortgefahren werden sollen \n"
			+ "Bitte bestätigen sie alle Tabelleneintraege mit Enter/Return sonst kann es zu Speicherproblemen kommen";


}
