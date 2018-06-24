package view;



/*
 * TableSelectionDemo.java requires no other files.
 */

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.awt.GridLayout;
import java.awt.Dimension;

import basis.Resources;
import xmlFramework.IOConnector;
import xmlFramework.XMLParser;
import control.Control;

public class ViewAufwandsabschaetzung extends JPanel { 
    private JTable tableProduktfunktionen;
    private JTable tableProduktdaten;
    private JTable tableKomplexitaetLF;
    private JTable tableKomplexitaetLD;
    private JTable tableEinflussfaktoren;
    private static MyTableModel dtmLF;
    private static MyTableModel dtmLD;
    private DefaultTableModel dtmKomplexitaetLF;
    private DefaultTableModel dtmKomplexitaetLD;
    private DefaultTableModel dtmEinflussfaktoren;
    private JTextPane background;
    private JButton weiter;
    private JButton zurueck;
    
    View view;
    
    public ViewAufwandsabschaetzung(ActionListener actionListener) {
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        background = new JTextPane();
        add(background);

        //Tabelle um Produktfunktionen zu kategorisieren
        tableProduktfunktionen = new JTable();
        dtmLF = new MyTableModel(new String[]{"LF", "EI", "EO", "EQ"}, 0);
        String produktnummer;
        /*for(int i = 0; i < XMLParser.sBuffer.size() - 1; i++){
        	
        	//test = view.getProduktfunktionenJTable().getTableHeader().getToolTipText();
        	produktnummer = XMLParser.sBuffer.get(i);
        	dtmLF.addRow(new Object[]{produktnummer, new Boolean(false), new Boolean(false), new Boolean(false)});
        }*/
        tableProduktfunktionen.setModel(dtmLF);

        //variable Fenstergröße
        int anzahlLF = 60;
        int dimensionLF = 160;
        if (anzahlLF < 50) dimensionLF = 16 * anzahlLF;
        tableProduktfunktionen.setPreferredScrollableViewportSize(new Dimension(500, dimensionLF));
        tableProduktfunktionen.setFillsViewportHeight(true);
        add(new JScrollPane(tableProduktfunktionen));
       
        
      //Tabelle für Komplexität 
        tableKomplexitaetLF = new JTable();
        dtmKomplexitaetLF = new MyTableModel(new String[]{"LF", "FTR", "DET"}, 0);
        for(int i = 0; i < 5; i++) {
        	dtmKomplexitaetLF.addRow(new Object[]{"//LF010//", 0, 0});
        }
        tableKomplexitaetLF.setModel(dtmKomplexitaetLF);
        tableKomplexitaetLF.setPreferredScrollableViewportSize(new Dimension(500, 80));
        tableKomplexitaetLF.setFillsViewportHeight(true);      
        add(new JScrollPane(tableKomplexitaetLF));
        
        
        
        //Tabelle um Produktdaten zu kategorisieren
        tableProduktdaten = new JTable();
        dtmLD = new MyTableModel(new String[]{"LD", "ELF", "ILF"}, 0);
        for(int i = 0; i < 5; i++) {
        	//test = view.getProduktfunktionenJTable().getTableHeader().getToolTipText();
        	produktnummer = "";
        	dtmLD.addRow(new Object[]{produktnummer, new Boolean(false), new Boolean(false)});
        }
        tableProduktdaten.setModel(dtmLD);
        
        //variable Fenstergröße
        int anzahlLD = 5;
        int dimensionLD = 0;
        if (anzahlLD >= anzahlLF) dimensionLD = dimensionLF;
        else dimensionLD = 16 * anzahlLD;
        
        tableProduktdaten.setPreferredScrollableViewportSize(new Dimension(500, 80));
        tableProduktdaten.setFillsViewportHeight(true);      
        add(new JScrollPane(tableProduktdaten));
        
        
        //LD Tabelle für Komplexität
        tableKomplexitaetLD = new JTable();
        dtmKomplexitaetLD = new MyTableModel(new String[]{"LD", "RET", "DET"}, 0);
        for(int i = 0; i < 5; i++) {
        	dtmKomplexitaetLD.addRow(new Object[]{"//LF010//", 0, 0});
        }
        tableKomplexitaetLD.setModel(dtmKomplexitaetLD);
        tableKomplexitaetLD.setPreferredScrollableViewportSize(new Dimension(500, 80));
        tableKomplexitaetLD.setFillsViewportHeight(true);      
        add(new JScrollPane(tableKomplexitaetLD));
        
        
        //Einflussfaktoren Tabelle
        tableEinflussfaktoren = new JTable();
        dtmEinflussfaktoren = new MyTableModel(new String[]{"", ""}, 0);
        
        //füge Einflussfaktoren hinzu
        dtmEinflussfaktoren.addRow(new Object[]{"Verflechtung mit anderen Anwendungssystemen (0-5)", 0});		//alles in Ressourcen 
        dtmEinflussfaktoren.addRow(new Object[]{"Dezentrale Daten, dezentrale Vertiefungen (0-5)", 0});
        dtmEinflussfaktoren.addRow(new Object[]{"Transaktionsrate (0-5)", 0});
        dtmEinflussfaktoren.addRow(new Object[]{"Verarbeitungslogistik", ""});
        dtmEinflussfaktoren.addRow(new Object[]{"  a) Rechenoperationen (0-10)", 0});
        dtmEinflussfaktoren.addRow(new Object[]{"  b) Kontrollverfahren (0-5)", 0});
        dtmEinflussfaktoren.addRow(new Object[]{"  c) Ausnahmeregelung (0-10)", 0});
        dtmEinflussfaktoren.addRow(new Object[]{"  d) Logik (0-5)", 0});
        dtmEinflussfaktoren.addRow(new Object[]{"Wiederverwendbarkeit (0-5)", 0});
        dtmEinflussfaktoren.addRow(new Object[]{"Datenbestandskonvertierung (0-5)", 0});
        dtmEinflussfaktoren.addRow(new Object[]{"Anpassbarkeit (0-5)", 0});
        
        tableEinflussfaktoren.setModel(dtmEinflussfaktoren);
        tableEinflussfaktoren.setPreferredScrollableViewportSize(new Dimension(500, 96));
        tableEinflussfaktoren.setFillsViewportHeight(true);

        add(new JScrollPane(tableEinflussfaktoren));
        
        
        weiter = new JButton("Weiter Komplexität");			//noch in Ressourcen
        add(weiter);
        weiter.addActionListener(actionListener);
        zurueck = new JButton("Zurueck Auswahl");
        add(zurueck);
        zurueck.setVisible(false);
        seitenwechsel(true);
        //Tabelle entfernen
        /*
         * tableEinflussfaktoren.setVisible(false);
         * tableEinflussfaktoren.setFillsViewportHeight(false);
         * tableEinflussfaktoren.setPreferredScrollableViewportSize(null);
         * tableEinflussfaktoren.setTableHeader(null);
         */
        
    }
    

    private void seitenwechsel(Boolean seite) {
    	//ändert Button
    	weiter.setVisible(seite);
    	zurueck.setVisible(!seite);
    	
        tableEinflussfaktoren.setVisible(false);
        tableEinflussfaktoren.setFillsViewportHeight(false);
        tableEinflussfaktoren.setPreferredScrollableViewportSize(null);
        tableEinflussfaktoren.setTableHeader(null);
    }
   
    private void vorherigeSeite() {
    	
    }

  
 /////////////////////////////
    //vllt sinnvoller eine Klasse zu machen, mit unterschiedlichen Parametern und Schleife. leichter anpassbar!
    ////////////////////////
    //würde Factory Method verwenden!!
    ///////////////////////////
    //Zeilen müssen bei Deklaration alle hinzugefügt werden!!
    
    /*
     * Klasse für Produkfunktionstabelle zum differenzieren
     */
    class MyTableModel extends DefaultTableModel {   
    	
 
        public MyTableModel(Object[] strings, int i) {
			super(strings, i);
		}

        /*
         * dafür da, dass Checkboxen statt Text (True/False) da steht
         */
    	@Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Block the first column to edit
         */
        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }
        
      
	
    }
    
    public static void addProdukt(){
    		for(int i = 0; i < IOConnector.getsLFBuffer().size() - 1; i++){        	
        	//test = view.getProduktfunktionenJTable().getTableHeader().getToolTipText();
        	
        	dtmLF.addRow(new Object[]{IOConnector.getsLFBuffer().get(i), new Boolean(false), new Boolean(false), new Boolean(false)});
    		}
    		
    		for(int i = 0; i < IOConnector.getsLDBuffer().size() - 1; i++){
    			dtmLD.addRow(new Object[]{IOConnector.getsLDBuffer().get(i), new Boolean(false), new Boolean(false), new Boolean(false)});
    		}
    }


	


	
    
    
    
    
}  
 
    
 
   

