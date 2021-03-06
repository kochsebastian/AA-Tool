package aufwandsabschaetzung;

import xmlFramework.IOConnector;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import basis.Resources;
import container.EI;
import container.ELF;
import container.EO;
import container.EQ;
import container.ILF;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Berechnungsfunktionen um Functionpoints zu bestimmen
 * @author ChrisBoger, SebastianKoch, AnneBlomeier
 *
 */

@SuppressWarnings("serial")
public class FunctionpointAnalyse extends JPanel implements IAufwandsabschaetzung{
	private static JTable tableProduktfunktionen;
	private static JTable tableProduktdaten;
	private static JTable tableKomplexitaetLF;
    private static JTable tableKomplexitaetLD;
    private static JTable tableEinflussfaktoren;
    private static JTable tableUebersicht;
    private static JTable tableUebersichtFunctionpoints;
    private static MyTableModel dtmLF;
    private static MyTableModel dtmLD;
    private static MyTableModel dtmUebersicht;
    private static MyTableModel dtmUebersichtFunctionpoints;
    private static DefaultTableModel dtmKomplexitaetLF;
    private static DefaultTableModel dtmKomplexitaetLD;
    private static DefaultTableModel dtmEinflussfaktoren;
    private static JButton ladeDaten;
    private static JButton weiter;
    private static JButton optimieren;
    private static JButton ladeSelbstoptimierung;
    private static JButton speicherSelbstoptimierung;
    private static JButton resetSelbstoptimierung;
    private static JTextArea zielFunctionPoints;
    private static JTextArea zielEinflussfaktor;
    private static ArrayList<EI> ei = new ArrayList<EI>();
    private static ArrayList<EQ> eq = new ArrayList<EQ>();
    private static ArrayList<ILF> ilf = new ArrayList<ILF>();
    private static ArrayList<ELF> elf = new ArrayList<ELF>();
    
    
    public FunctionpointAnalyse(ActionListener actionListener) {
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        
        //Textfeld, der Laden der Produktfunktionen/-daten erklärt
        JTextArea erklaerungLaden = new JTextArea(Resources.erklaerungLaden);
        erklaerungLaden.setEditable(false);
        add(erklaerungLaden);
        ladeDaten = new JButton(Resources.ladeDaten);
        ladeDaten.addActionListener(actionListener);
        add(ladeDaten);

        //Tabelle um Produktfunktionen zu kategorisieren
        tableProduktfunktionen = new JTable();
        dtmLF = new MyTableModel(Resources.zuordnungProduktfunktionen, 0);
        tableProduktfunktionen.setModel(dtmLF);
        
        //variable Fenstergröße
        int anzahlLF = 60;
        int dimensionLF = 160;
        if (anzahlLF < 50) dimensionLF = 16 * anzahlLF;
        tableProduktfunktionen.setPreferredScrollableViewportSize(new Dimension(600, dimensionLF));
        tableProduktfunktionen.setFillsViewportHeight(true);
        add(new JScrollPane(tableProduktfunktionen));
        
        
        //Tabelle für Komplexität 
        tableKomplexitaetLF = new JTable();
        dtmKomplexitaetLF = new MyTableModel(Resources.komplexitaetLF, 0);
       
        tableKomplexitaetLF.setModel(dtmKomplexitaetLF);
        tableKomplexitaetLF.setPreferredScrollableViewportSize(new Dimension(600, 80));
        tableKomplexitaetLF.setFillsViewportHeight(true);      
        add(new JScrollPane(tableKomplexitaetLF));
        
        
        
        //Tabelle um Produktdaten zu kategorisieren
        tableProduktdaten = new JTable();
        dtmLD = new MyTableModel(Resources.zuordnungProduktdaten, 0);
        
        tableProduktdaten.setModel(dtmLD);
        
        //variable Fenstergröße
        int anzahlLD = 5;
        int dimensionLD = 0;
        if (anzahlLD >= anzahlLF) dimensionLD = dimensionLF;
        else dimensionLD = 16 * anzahlLD;
        
        tableProduktdaten.setPreferredScrollableViewportSize(new Dimension(600, 80));
        tableProduktdaten.setFillsViewportHeight(true);      
        add(new JScrollPane(tableProduktdaten));
        
        
        //LD Tabelle für Komplexität
        tableKomplexitaetLD = new JTable();
        dtmKomplexitaetLD = new MyTableModel(Resources.komplexitaetLD, 0);
        
        tableKomplexitaetLD.setModel(dtmKomplexitaetLD);
        tableKomplexitaetLD.setPreferredScrollableViewportSize(new Dimension(600, 80));
        tableKomplexitaetLD.setFillsViewportHeight(true);      
        add(new JScrollPane(tableKomplexitaetLD));
        
        //Einflussfaktoren Tabelle
        tableEinflussfaktoren = new JTable();
        dtmEinflussfaktoren = new MyTableModel(Resources.einflussfaktoren, 0);
        
        //füge Einflussfaktoren hinzu
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussfaktorenVerflechtung, 0});		
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussfaktorenDezentral, 0});
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussfaktorenTransaktionsrate, 0});
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussfaktorenVerarbeitungslogistik, ""});
        
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussRechenoperationen, 0});
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussKontrollverfahren, 0});
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussAusnahmeregelung, 0});
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussLogik, 0});
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussWiederverwendbarkeit, 0});
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussDatenbestand, 0});
        dtmEinflussfaktoren.addRow(new Object[]{Resources.einflussAnpassbarkeit, 0});
        
       
        tableEinflussfaktoren.setModel(dtmEinflussfaktoren);
        tableEinflussfaktoren.setPreferredScrollableViewportSize(new Dimension(600, 128));
        tableEinflussfaktoren.setFillsViewportHeight(true);  
        add(new JScrollPane(tableEinflussfaktoren));
        
        
        //Button zum Berechnen der Funktionpoints
        weiter = new JButton(Resources.berechneFunctionpoints);			        
        weiter.addActionListener(actionListener);
        add(weiter);
        
        
        
        //Übersichtstabelle
        tableUebersicht = new JTable();
        dtmUebersicht = new MyTableModel(Resources.uebersicht, 0);
        
        for(int i = 0; i < 5; i++) {
        	dtmUebersicht.addRow(new String[]{"", "", Resources.einfach, "", ""});
        	dtmUebersicht.addRow(new String[]{"", "", Resources.mittel, "", ""});
        	dtmUebersicht.addRow(new String[]{"", "", Resources.komplex, "", ""});        	
        }
        dtmUebersicht.addRow(Resources.summe);
        dtmUebersicht.setValueAt(Resources.eingabe, 0, 0);
        dtmUebersicht.setValueAt(Resources.ausgabe, 3, 0);
        dtmUebersicht.setValueAt(Resources.abfrage, 6, 0);
        dtmUebersicht.setValueAt(Resources.datenbestaende, 9, 0);
        dtmUebersicht.setValueAt(Resources.referenzdaten, 12, 0);
        
        
        tableUebersicht.setModel(dtmUebersicht);
        tableUebersicht.setPreferredScrollableViewportSize(new Dimension(600, 256));
        tableUebersicht.setFillsViewportHeight(true);        
        add(new JScrollPane(tableUebersicht));
        
        
        //Functionpoints Tabelle
        tableUebersichtFunctionpoints = new JTable();
        dtmUebersichtFunctionpoints = new MyTableModel(new String[]{"", "", ""}, 0);
        
        tableUebersichtFunctionpoints.setModel(dtmUebersichtFunctionpoints);      
        tableUebersichtFunctionpoints.setPreferredScrollableViewportSize(new Dimension(600, 48));
        tableUebersichtFunctionpoints.getColumnModel().getColumn(0).setPreferredWidth(200);
        tableUebersichtFunctionpoints.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableUebersichtFunctionpoints.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        dtmUebersichtFunctionpoints.addRow(Resources.summeEinflussfaktoren);
        dtmUebersichtFunctionpoints.addRow(Resources.faktorEinflussbewertung);
        dtmUebersichtFunctionpoints.addRow(Resources.bewertungFunctionpoints);
        add(new JScrollPane(tableUebersichtFunctionpoints));
        
        
        //Textfeld, zum Erklären der Selbstoptimierung
        JTextArea erklaerungOptimierung = new JTextArea(Resources.erklaerungOptimierung);
        erklaerungOptimierung.setEditable(false);
        add(erklaerungOptimierung);
        
        //Optimierungspanel
        JPanel optimierungsPanel = new JPanel();
        zielEinflussfaktor = new JTextArea(Resources.zielEinflussfaktor);
        optimierungsPanel.add(zielEinflussfaktor);
        zielFunctionPoints = new JTextArea(Resources.zielFunctionpoints);
        optimierungsPanel.add(zielFunctionPoints);
        add(optimierungsPanel);
        //Selbstoptimierte Nachkalkulation
        optimieren = new JButton(Resources.optimieren);
        optimieren.addActionListener(actionListener);
        add(optimieren);
        
        //Textfeld, für Speichern/Laden/Zurücksetzen der Nachkalkulation
        JTextArea erklaerungOptimierungProjekt = new JTextArea(Resources.erklaerungOptimierungProjekt);
        erklaerungOptimierungProjekt.setEditable(false);
        add(erklaerungOptimierungProjekt);
        
        //Selbstoptimierte Nachkalkulation
        //laden
        ladeSelbstoptimierung = new JButton(Resources.ladeSelbstoptimierung);
        ladeSelbstoptimierung.addActionListener(actionListener);
        add(ladeSelbstoptimierung);
        
        //speichern
        speicherSelbstoptimierung = new JButton(Resources.speicherOptimierung);
        speicherSelbstoptimierung.addActionListener(actionListener);
        add(speicherSelbstoptimierung);
        
        //zurücksetzen
        resetSelbstoptimierung = new JButton(Resources.resetSelbstoptimierung);
        resetSelbstoptimierung.addActionListener(actionListener);
        add(resetSelbstoptimierung);
        
    }
    
    
    static ArrayList<EO> eo = new ArrayList<EO>();
    
    
    
    
    
    /**
     * Fuegt Produktdaten, -funktionen in die entsprechenden Tabellen hinzu
     */
    public static void addProdukt(){

    	int laengeLF = IOConnector.getsLFBuffer().size();
    	int laengeLD = IOConnector.getsLDBuffer().size();
    	
    	while (dtmLF.getRowCount() > 0) {
    		dtmLF.removeRow(0);
    	}
    	while (dtmLD.getRowCount() > 0){
    		dtmLD.removeRow(0);
    	}
    	while (dtmKomplexitaetLF.getRowCount() > 0){
    		dtmKomplexitaetLF.removeRow(0);
    	}
    	while (dtmKomplexitaetLD.getRowCount() > 0){
    		dtmKomplexitaetLD.removeRow(0);
    	}
    	
    	for(int i = 0; i < laengeLF; i++){
    		dtmLF.addRow(new Object[]{"//LF" + IOConnector.getsLFBuffer().get(i) + "//", new Boolean(false), new Boolean(false), new Boolean(false)});
    	}
    		
    	for(int i = 0; i < laengeLF; i++) {
    		dtmKomplexitaetLF.addRow(new Object[]{"//LF" + IOConnector.getsLFBuffer().get(i) + "//", 0, 0});
        }
    		
    	
    	for(int i = 0; i < laengeLD; i++){
    		dtmLD.addRow(new Object[]{"//LD" + IOConnector.getsLDBuffer().get(i) + "//", new Boolean(false), new Boolean(false), new Boolean(false)});
    	}
    	
    	for(int i = 0; i < laengeLD; i++) {
        	dtmKomplexitaetLD.addRow(new Object[]{"//LD" + IOConnector.getsLDBuffer().get(i) + "//", 0, 0});
        }
    	
    }
    
    /**
     * Fuegt die Werte der Functionpoint-Berechnung in die Übersichtstabelle ein
     */
    public static void addFunctionpoints() {
    	int laengeBuffer = IOConnector.getsFunctionpointsBuffer().size();
    	
    	while (dtmUebersicht.getRowCount() > 0) {
    		dtmUebersicht.removeRow(0);
    	}
    	
    	for(int i = 0; i < laengeBuffer - 1; i++) {
    		dtmUebersicht.addRow(new Object[]{"", "", "", IOConnector.getsFunctionpointsBuffer().get(i)});
    	}
    }
    
    
    /**
     * Zählt die Anzahl an Eingaben/Ausgaben/Abfragen
     */
    public static void countfunktionen() {
    	for(int i = 0; i < dtmLF.getRowCount(); i++) {
    		
    		for(int j = 1; j < 4; j++) {
    			if((boolean) dtmLF.getValueAt(i, j)){
    				int ftr = getLFftr(i);
    				int ret = getLFdet(i);
    				switch(j) {
    					case 2: eo.add(new EO(ftr,ret)); break;
    					case 3: eq.add(new EQ(ftr,ret)); break;
    					case 1: ei.add(new EI(ftr,ret)); break;
    					default: continue;
    				}
    			}
    		}
    	}
    }
    
    
    /**
     * zählt die Anzahl an Produktdaten (ELF/ILF)
     */
    public static void countdaten() {
    	for(int i = 0; i < dtmLD.getRowCount(); i++) {
    		
    		for(int j = 1; j < 3; j++) {
    			if((boolean) dtmLD.getValueAt(i, j)){
    				int ret = getLDret(i);
    				int det = getLDdet(i);
    				switch(j) {
    					case 1: ilf.add(new ILF(ret,det)); break;
    					case 2: elf.add(new ELF(ret,det)); break;
    					default: continue;
    				}
    			}
    		}
    	}
    }
    
    /**
     * getter für FTR
     * @param zeile, in Tabelle
     * @return FTR-Wert
     */
    public static int getLFftr(int zeile) {
    	return (int) tableKomplexitaetLF.getValueAt(zeile, 1);	
    }
    
    /**
     * getter für DET der Produktfunktionen
     * @param zeile, in Tabelle
     * @return DET-Wert
     */
    public static int getLFdet(int zeile) {
    	return (int) tableKomplexitaetLF.getValueAt(zeile, 2);	
    }
    
    /**
     * getter für RET
     * @param zeile, in Tabelle
     * @return RET-Wert
     */
    public static int getLDret(int zeile) {
    	return (int) tableKomplexitaetLD.getValueAt(zeile, 1);	
    }
    
    /**
     * getter für DET der Produktdaten
     * @param zeile, in Tabelle
     * @return DET-Wert
     */
    public static int getLDdet(int zeile) {
    	return (int) tableKomplexitaetLD.getValueAt(zeile, 2);	
    }
    
    /**
     * Zählt die Werte der einzelnen Einflussfaktoren zusammen
     * @return Summe Einflussfaktoren
     */
    public static int getSumEinflussfak() {
    	int sum=0;
    	for(int i = 0; i < tableEinflussfaktoren.getRowCount(); i++) {
    		if(i == 3)
    			continue;
    		sum += (int) tableEinflussfaktoren.getValueAt(i, 1);
    	}
    	return sum;
    }
    
    /**
     * Berechnung der FunctionPoints aus Einflussfaktoren, Produktfunktionen und Produktdaten
     */
    public static void calculateFunctionPoint() {
    	tableUebersichtFunctionpoints.setValueAt(getSumEinflussfak(), 0, 2); 
    	anzahlEintragen();
    	double E3 = Math.round(((double)getSumEinflussfak()/(double)100 + (double)0.7) *100d)/100d;
    	double E1 = (int) tableUebersicht.getValueAt(15, 4);
    	tableUebersichtFunctionpoints.setValueAt(E3,1,2); 
    	double ergebnis = Math.round(E3* E1*100d)/100d;
    	tableUebersichtFunctionpoints.setValueAt(ergebnis,2,2); 
    	
    	
    }
    
    /**
     * Eintragen der Anzhal gleichgewichteter Werte der Produktfunktionen und Produktdaten in die Uebersichtstabelle
     */
    public static void anzahlEintragen() {
    	
	    tableUebersicht.setValueAt(EI.getCountlow(), 0, 1);
	    tableUebersicht.setValueAt(EI.getCountaverage(), 1, 1);
	    tableUebersicht.setValueAt(EI.getCounthigh(), 2, 1);
	    tableUebersicht.setValueAt(EO.getCountlow(), 3, 1);
	    tableUebersicht.setValueAt(EO.getCountaverage(), 4, 1);
	    tableUebersicht.setValueAt(EO.getCounthigh(), 5, 1);
	    tableUebersicht.setValueAt(EQ.getCountlow(), 6, 1);
	    tableUebersicht.setValueAt(EQ.getCountaverage(), 7, 1);
	    tableUebersicht.setValueAt(EQ.getCounthigh(), 8, 1);
	    tableUebersicht.setValueAt(ILF.getCounthigh(), 9, 1);
	    tableUebersicht.setValueAt(ILF.getCountlow(), 10, 1);
	    tableUebersicht.setValueAt(ILF.getCountaverage(), 11, 1);
	    tableUebersicht.setValueAt(ELF.getCounthigh(), 12, 1);
	    tableUebersicht.setValueAt(ELF.getCountlow(), 13, 1);
	    tableUebersicht.setValueAt(ELF.getCountaverage(), 14, 1);
	    
	    tableUebersicht.setValueAt(EI.getGewichtunglow(),0,3);
	    tableUebersicht.setValueAt(EI.getGewichtungAverage(),1,3);
	    tableUebersicht.setValueAt(EI.getGewichtungHigh(),2,3);
	    tableUebersicht.setValueAt(EO.getGewichtunglow(), 3, 3);
	    tableUebersicht.setValueAt(EO.getGewichtungAverage(), 4, 3);
	    tableUebersicht.setValueAt(EO.getGewichtungHigh(), 5, 3);
	    tableUebersicht.setValueAt(EQ.getGewichtunglow(), 6, 3);
	    tableUebersicht.setValueAt(EQ.getGewichtungAverage(), 7, 3);
	    tableUebersicht.setValueAt(EQ.getGewichtungHigh(), 8, 3);
	    tableUebersicht.setValueAt(ILF.getGewichtunglow(), 9, 3);
	    tableUebersicht.setValueAt(ILF.getGewichtungAverage(), 10, 3);
	    tableUebersicht.setValueAt(ILF.getGewichtungHigh(), 11, 3);
	    tableUebersicht.setValueAt(ELF.getGewichtunglow(), 12, 3);
	    tableUebersicht.setValueAt(ELF.getGewichtungAverage(), 13, 3);
	    tableUebersicht.setValueAt(ELF.getGewichtungHigh(), 14, 3);

	    tableUebersicht.setValueAt(EI.getCountlow()*EI.getGewichtunglow(),0,4);
	    tableUebersicht.setValueAt(EI.getCountaverage()*EI.getGewichtungAverage(),1,4);
	    tableUebersicht.setValueAt(EI.getCounthigh()*EI.getGewichtungHigh(),2,4);
	    tableUebersicht.setValueAt(EO.getCountlow()*EO.getGewichtunglow(), 3, 4);
	    tableUebersicht.setValueAt(EO.getCountaverage()*EO.getGewichtungAverage(), 4, 4);
	    tableUebersicht.setValueAt(EO.getCounthigh()*EO.getGewichtungHigh(), 5, 4);
	    tableUebersicht.setValueAt(EQ.getCountlow()*EQ.getGewichtunglow(), 6, 4);
	    tableUebersicht.setValueAt(EQ.getCountaverage()*EQ.getGewichtungAverage(), 7, 4);
	    tableUebersicht.setValueAt(EQ.getCounthigh()*EQ.getGewichtungHigh(), 8, 4);
	    tableUebersicht.setValueAt(ILF.getCountlow()*ILF.getGewichtunglow(), 9, 4);
	    tableUebersicht.setValueAt(ILF.getCountaverage()*ILF.getGewichtungAverage(), 10, 4);
	    tableUebersicht.setValueAt(ILF.getCounthigh()*ILF.getGewichtungHigh(), 11, 4);
	    tableUebersicht.setValueAt(ELF.getCountlow()*ELF.getGewichtunglow(), 12, 4);
	    tableUebersicht.setValueAt(ELF.getCountaverage()*ELF.getGewichtungAverage(), 13, 4);
	    tableUebersicht.setValueAt(ELF.getCounthigh()*ELF.getGewichtungHigh(), 14, 4);
	    int summe = 0;
	    for(int i = 0; i<=14;i++) {
	    	summe += (int) tableUebersicht.getValueAt(i, 4);
	    }
	    
	    tableUebersicht.setValueAt(summe, 15, 4);
	    
	    EI.resetCount();
	    EO.resetCount();
	    EQ.resetCount();
	    ILF.resetCount();
	    ELF.resetCount();
	    
    }
    


	/**
	 * @return the tableEinflussfaktoren
	 */
	public static JTable getTableEinflussfaktoren() {
		return tableEinflussfaktoren;
	}

	/**
	 * @param tableEinflussfaktoren the tableEinflussfaktoren to set
	 */
	public static void setTableEinflussfaktoren(JTable tableEinflussfaktoren) {
		FunctionpointAnalyse.tableEinflussfaktoren = tableEinflussfaktoren;
	}


	/**
	 * @param faktor
	 */
	public static void setTableEinflussfaktoren(int[] faktor) {
		
		for(int i = 0; i< tableEinflussfaktoren.getRowCount();i++) {
			if(i == 3) {
				continue;
			}
			FunctionpointAnalyse.tableEinflussfaktoren.setValueAt(faktor[i], i, 1);
		}
			
	}
	
	/**
	 * getter für Ergebnis der Functionpoints-Berechnung
	 */
	public static double getZielErgebnis() {
		double zielErgebnis;
		String tmp = zielFunctionPoints.getText();
		try {
			zielErgebnis = Double.parseDouble(tmp);
		}catch(NumberFormatException nfe) {
			zielErgebnis = 0;
		}
		return zielErgebnis;
	}
	
	/**
	 * getter für Einflussfaktor der in Textfeld zur Optimierung eingegeben wurde
	 * @return
	 */
	public static String getEinflussfaktor() {
		
		return zielEinflussfaktor.getText();
		
	}
    
	/**
	 * Optimierungsmethode, die das Ergebnis an die gewünschten Functionpoints anpasst
	 * @param zielErgebnis, welches als berechnete Functionpoints wünschenswert ist
	 * @param E1, Teilergebnis der Functionpoint-Berechnung
	 * @param einflussfaktor, welcher angepasst werden soll um zielErgebnis zu erreichen
	 */
	public static void optimieren(double zielErgebnis, int E1, String einflussfaktor) {
		double newEinflusssumme = ((zielErgebnis / E1) -0.7 )* 100 ;
		double oldEinflusssumme = getSumEinflussfak();
		double diff = newEinflusssumme - oldEinflusssumme;
		int diff_int = (int) Math.round(diff);
		for(int i = 0; i< tableEinflussfaktoren.getRowCount();i++) {
			if(i == 3)
				continue;
			if(Resources.alleEinflussfaktoren[i].equals(einflussfaktor)){
				tableEinflussfaktoren.setValueAt((int)tableEinflussfaktoren.getValueAt(i,1) + diff_int,i, 1);
				if((int)tableEinflussfaktoren.getValueAt(i, 1)<0) {
					tableEinflussfaktoren.setValueAt(0, i, 1);
				}
			}
		}
		countfunktionen();
		countdaten();
		calculateFunctionPoint();
	}
	
	/**
	 * getter für Teilergebnis aus Functionpoints-Berechnung
	 * @return E1
	 */
	public static int getE1() {
		return (int) tableUebersicht.getValueAt( 15, 4);
	}

	/**
	 * @return the tableProduktfunktionen
	 */
	public static JTable getTableProduktfunktionen() {
		return tableProduktfunktionen;
	}

	/**
	 * @param tableProduktfunktionen the tableProduktfunktionen to set
	 */
	public static void setTableProduktfunktionen(JTable tableProduktfunktionen) {
		FunctionpointAnalyse.tableProduktfunktionen = tableProduktfunktionen;
	}

	/**
	 * @return the tableProduktdaten
	 */
	public static JTable getTableProduktdaten() {
		return tableProduktdaten;
	}

	/**
	 * @param tableProduktdaten the tableProduktdaten to set
	 */
	public static void setTableProduktdaten(JTable tableProduktdaten) {
		FunctionpointAnalyse.tableProduktdaten = tableProduktdaten;
	}

	/**
	 * @return the tableKomplexitaetLF
	 */
	public static JTable getTableKomplexitaetLF() {
		return tableKomplexitaetLF;
	}

	/**
	 * @param tableKomplexitaetLF the tableKomplexitaetLF to set
	 */
	public static void setTableKomplexitaetLF(JTable tableKomplexitaetLF) {
		FunctionpointAnalyse.tableKomplexitaetLF = tableKomplexitaetLF;
	}

	/**
	 * @return the tableKomplexitaetLD
	 */
	public static JTable getTableKomplexitaetLD() {
		return tableKomplexitaetLD;
	}

	/**
	 * @param tableKomplexitaetLD the tableKomplexitaetLD to set
	 */
	public static void setTableKomplexitaetLD(JTable tableKomplexitaetLD) {
		FunctionpointAnalyse.tableKomplexitaetLD = tableKomplexitaetLD;
	}

	/**
	 * @return the tableUebersicht
	 */
	public static JTable getTableUebersicht() {
		return tableUebersicht;
	}

	/**
	 * @param tableUebersicht the tableUebersicht to set
	 */
	public static void setTableUebersicht(JTable tableUebersicht) {
		FunctionpointAnalyse.tableUebersicht = tableUebersicht;
	}

	/**
	 * @return the tableUebersichtFunctionpoints
	 */
	public static JTable getTableUebersichtFunctionpoints() {
		return tableUebersichtFunctionpoints;
	}

	/**
	 * @param tableUebersichtFunctionpoints the tableUebersichtFunctionpoints to set
	 */
	public static void setTableUebersichtFunctionpoints(JTable tableUebersichtFunctionpoints) {
		FunctionpointAnalyse.tableUebersichtFunctionpoints = tableUebersichtFunctionpoints;
	}
	/**
	 * @return the zielFunctionPoints
	 */
	public static JTextArea getZielFunctionPoints() {
		return zielFunctionPoints;
	}

	/**
	 * @param zielFunctionPoints the zielFunctionPoints to set
	 */
	public static void setZielFunctionPoints(String s) {
		JTextArea zielFunctionPoints = new JTextArea(s);
		FunctionpointAnalyse.zielFunctionPoints = zielFunctionPoints;
	}

	/**
	 * @return the zielEinflussfaktor
	 */
	public static JTextArea getZielEinflussfaktor() {
		return zielEinflussfaktor;
	}

	/**
	 * @param zielEinflussfaktor the zielEinflussfaktor to set
	 */
	public static void setZielEinflussfaktor(String s) {
		JTextArea zielEinflussfaktor = new JTextArea(s);
		FunctionpointAnalyse.zielEinflussfaktor = zielEinflussfaktor;
	}
    
}  
 
    
 
   

