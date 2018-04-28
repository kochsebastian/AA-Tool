package view;




import java.awt.*;





import java.awt.event.*;
import java.io.File;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aufwandsabschaetzung.Aufwandsabschaetzung;
import basis.Resources;

import control.Control;

/**
 * 
 * @author SebastianKoch
 *
 */
public class View implements ActionListener {

    private static View view = null;
    private JFrame hauptfenster = null;
    
    private JButton exportieren;
    private JButton importieren;
    private JButton erstellen;
    private JButton speichern;
    private JButton laden;
    private JButton loeschen;
    private JButton drucken;
    private JButton pdf;
    private JButton schliessen;
  
    
    private JTextArea kundenbeschreibung;
    private JTextArea deckblattTextField;
    private JTextArea zielbestimmungTextField;
    private JTextArea produkteinsatzTextField;
    private JTextArea ergaenzungenTextField;
    private JTextArea aufwandTextField;
   
    private JTable produktfunktionenJTable;
    private JTable produktdatenJTable;
    private JTable produktleistungenJTable;
    private JTable qualitaetsanforderungenJTable;
    private JTable glossarJTable;
    private JTable aufwandJTable;
    
    
    private DefaultTableModel produktfunktionenJTableModel;
    private JButton fuegeFunktionenReiheHinzu;
    private JButton loescheFunktionenReihe;
    
    private DefaultTableModel produktdatenJTableModel;
    private JButton fuegeDatenReiheHinzu;
    private JButton loescheDatenReihe;
    
    private DefaultTableModel produktleistungenJTableModel;
    private JButton fuegeLeistungenReiheHinzu;
    private JButton loescheLeistungenReihe;
    
    private DefaultTableModel qualitaetsanforderungenJTableModel;
    private JButton fuegeQualitaetsanforderungenReiheHinzu;
    private JButton loescheQualitaetsanforderungenReihe;
    
    private DefaultTableModel glossarJTableModel;
    private JButton fuegeGlossarReiheHinzu;
    private JButton loescheGlossarReihe;
    
    private DefaultTableModel aufwandJTableModel;
    private JButton fuegeAufwandReiheHinzu;
    private JButton loescheAufwandReihe;
    
    private static Control control;
    
    private static ViewModelConnector viewConnector;
    
    public static Aufwandsabschaetzung a;


 
    
    private View() {
        
    }
    
    /**
     * 
     * @param _control
     * @return
     */
    public static View getInstanz(Control _control) {
     //   aufwandsabschaetzung = _aufwandsabschaetzung;
        control = _control;
        if (view == null) {
            view = new View();
            
            viewConnector = new ViewModelConnector(view);
        }             
        return view;
    }
    
    public void show() {
    		
    	// setze Nimbus look and feel fuer Anzeige (fuer besseres Design)
        try {            
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            
        }
        
        // Erzeugung eines neuen Framefensters
        hauptfenster = new JFrame();
        hauptfenster.setTitle(Resources.aaTool);
        hauptfenster.setSize(1000, 800);
        hauptfenster.setResizable(false);
        // setze das Fenster in die Bildschirmmitte
        try {
            hauptfenster.setLocationRelativeTo(null);
        }
        catch(Exception e) {
            
        }
        
    }
    
    
    public void showEmpty() {
    		show();
    		
        // Beim Betätigen des [x]-Buttons: Java-Programm beenden
        hauptfenster.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        hauptfenster.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               
                    System.exit(0);
                
            }
        });
        
        // Erzeugung der Buttons der Menueleiste
        erstellen = new JButton(Resources.erstellen);
        laden = new JButton("Laden");
        importieren = new JButton(Resources.importieren);
       
        
        
        // Buttons dem Listener zuordnen 
        importieren.addActionListener(this);
        erstellen.addActionListener(this);
        laden.addActionListener(this);
        
        
        // Menueleiste dem Frame hinzufuegen
        JPanel menueleiste = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menueleiste.setBorder(BorderFactory.createLineBorder(Color.black));
        menueleiste.setMaximumSize(new Dimension(1000, 100));
        menueleiste.add(erstellen);
        menueleiste.add(laden);
    
        
        menueleiste.add(importieren);  
       
        
        
        // Erzeugung der JPanels
        JPanel titelblattJPanel = new JPanel();
        
        
        // Inhalte des Deckblatts
        JTextArea titelblattTextField = new JTextArea(" ", 44, 76);
        titelblattJPanel.add(titelblattTextField);
        
        
        // Erzeugung eines JTabbedPane-Objektes
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
 
        // Registerkarten diesem hinzugefuegen
        tabs.addTab("                           ", titelblattJPanel);
        
        
        // JTabbedPane dem Frame hinzufuegen        
        JPanel hauptfensterJPanel= new JPanel();
        hauptfensterJPanel.setLayout(new BoxLayout(hauptfensterJPanel, BoxLayout.Y_AXIS));
        hauptfensterJPanel.add(menueleiste);
        hauptfensterJPanel.add(tabs);
        
        hauptfenster.getContentPane().add(hauptfensterJPanel);
        
        // JFrame anzeigen
        hauptfenster.setVisible(true);
    }
    
    
    
    
    /**
     * @wbp.parser.entryPoint
     */
    public void showFull() {
    		show();
        // Beim Betätigen des [x]-Buttons: Java-Programm beenden
        hauptfenster.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        hauptfenster.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // zeige Dialog zum Abfragen, ob vor dem Beenden noch exportiert werden soll
                int auswahl = JOptionPane.showConfirmDialog(hauptfenster, Resources.vorBeendenSpeichern, Resources.aaTool, JOptionPane.YES_NO_OPTION);
                
                if (auswahl == JOptionPane.YES_OPTION) {
                    System.out.println("jetzt erst noch exportieren...");
                    File datei = showSpeichernUnterDialog();
                    if(datei != null) {
                        control.exportiere(datei);
                        System.out.println("Call: control.exportiere(datei);");
                    }
                    System.exit(0);
                }            
                else {
                    System.exit(0);
                }
            }
        });
        
        // Erzeugung der Buttons der Menueleiste
        erstellen = new JButton(Resources.erstellen);
        laden = new JButton("Laden");
        speichern = new JButton("Speichern");
        loeschen = new JButton("Löschen");
        schliessen = new JButton("Schließen");
        drucken = new JButton("Drucken");
        exportieren = new JButton(Resources.exportieren);
        importieren = new JButton(Resources.importieren);
        pdf = new JButton("Pdf erstellen");
        
        
        // Buttons dem Listener zuordnen
        exportieren.addActionListener(this);
        importieren.addActionListener(this);
        erstellen.addActionListener(this);
        speichern.addActionListener(this);
        laden.addActionListener(this);
        loeschen.addActionListener(this);
        drucken.addActionListener(this);
        pdf.addActionListener(this);
        schliessen.addActionListener(this);
        
        // Menueleiste dem Frame hinzufuegen
        JPanel menueleiste = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menueleiste.setBorder(BorderFactory.createLineBorder(Color.black));
        menueleiste.setMaximumSize(new Dimension(1000, 100));
        menueleiste.add(erstellen);
        menueleiste.add(laden);
        menueleiste.add(speichern);
        menueleiste.add(loeschen);
        menueleiste.add(schliessen);
        menueleiste.add(drucken);
        menueleiste.add(exportieren);
        menueleiste.add(importieren);  
        menueleiste.add(pdf);
        
        
        // Erzeugung der JPanels
        JPanel deckblattJPanel = new JPanel();
        JPanel kundenbeschreibungPanel = new JPanel();
        JPanel zielbestimmungJPanel = new JPanel();
        JPanel produkteinsatzJPanel = new JPanel();
        JPanel ergaenzungenJPanel = new JPanel();
        JPanel produktfunktionenJPanel = new JPanel();
        JPanel produktdatenJPanel = new JPanel();
        JPanel produktleistungenJPanel = new JPanel();
        JPanel qualitaetsanforderungenJPanel = new JPanel();
        JPanel glossarJPanel = new JPanel();
        JPanel aufwandsabschaetzungJPanel = new JPanel();
        
        
        // Inhalte des Deckblatts
        deckblattTextField = new JTextArea(Resources.deckblattStandardInhalt, 44, 76);
        deckblattJPanel.add(deckblattTextField);
        
        // Inhalte der Kundenbeschreibung
        kundenbeschreibung = new JTextArea(Resources.kundenBeschreibungStandardInhalt, 44, 76);
        kundenbeschreibungPanel.add(kundenbeschreibung);
        
        // Inhalte der Zielbestimmung
        zielbestimmungTextField = new JTextArea(Resources.zielbestimmungStandardInhalt, 44, 76);
        zielbestimmungJPanel.add(zielbestimmungTextField);
        
        // Inhalte des Produkteinsatzes
        produkteinsatzTextField = new JTextArea(Resources.produkteinsatzStandardInhalt, 44, 76);
        produkteinsatzJPanel.add(produkteinsatzTextField);
        
        // Inhalte des Produkteinsatzes
        ergaenzungenTextField = new JTextArea(Resources.ergaenzungenStandardInhalt, 44, 76);
        ergaenzungenJPanel.add(ergaenzungenTextField);
      /*  
        // Inhalte des Produkteinsatzes
        aufwandTextField = new JTextArea(Resources.aufwandsabschaetzungStandardInhalt, 44, 76);
        aufwandJPanel.add(aufwandTextField);
        
       */
        
        // Inhalt der Prduktfunktionen erstellen
        produktfunktionenJTable = new JTable(0, 2);
        produktfunktionenJTableModel = (DefaultTableModel) produktfunktionenJTable.getModel();
        produktfunktionenJTable.getColumnModel().getColumn(0).setHeaderValue(Resources.bezeichnung);
        produktfunktionenJTable.getColumnModel().getColumn(1).setHeaderValue(Resources.wert);
        fuegeFunktionHinzu();
        
        // neue Buttons fuer die Tabelle
        fuegeFunktionenReiheHinzu = new JButton(Resources.fuegeProduktfunktionHinzu);
        loescheFunktionenReihe = new JButton(Resources.loescheProduktfunktion);
        
        // eigenes JPanel fuer die Buttons
        JPanel produktfunktionenButtons = new JPanel(new FlowLayout());
        produktfunktionenButtons.setMaximumSize(new Dimension(1000, 100));
        produktfunktionenButtons.add(fuegeFunktionenReiheHinzu);
        produktfunktionenButtons.add(loescheFunktionenReihe);
                
        // Buttons dem Listener zuordnen
        fuegeFunktionenReiheHinzu.addActionListener(this);
        loescheFunktionenReihe.addActionListener(this);
        
        produktfunktionenJPanel.setLayout(new BoxLayout(produktfunktionenJPanel, BoxLayout.Y_AXIS));
        produktfunktionenJPanel.add(produktfunktionenButtons);
        
        JScrollPane pane = new JScrollPane(produktfunktionenJTable);
        produktfunktionenJPanel.add(pane);
        
        
        
        // Inhalt der Prduktdaten erstellen
        produktdatenJTable = new JTable(0, 2);
        produktdatenJTableModel = (DefaultTableModel) produktdatenJTable.getModel();
        produktdatenJTable.getColumnModel().getColumn(0).setHeaderValue(Resources.bezeichnung);
        produktdatenJTable.getColumnModel().getColumn(1).setHeaderValue(Resources.wert);
        fuegeDatumHinzu();
        
        // neue Buttons fuer die Tabelle
        fuegeDatenReiheHinzu = new JButton(Resources.fuegeProduktdatumHinzu);
        loescheDatenReihe = new JButton(Resources.loescheProduktdatum);
        
        // eigenes JPanel fuer die Buttons
        JPanel produktdatenButtons = new JPanel(new FlowLayout());
        produktdatenButtons.setMaximumSize(new Dimension(1000, 100));
        produktdatenButtons.add(fuegeDatenReiheHinzu);
        produktdatenButtons.add(loescheDatenReihe);
        
        // Buttons dem Listener zuordnen
        fuegeDatenReiheHinzu.addActionListener(this);
        loescheDatenReihe.addActionListener(this);
        
        produktdatenJPanel.setLayout(new BoxLayout(produktdatenJPanel, BoxLayout.Y_AXIS));
        produktdatenJPanel.add(produktdatenButtons);
        
        JScrollPane datenPane = new JScrollPane(produktdatenJTable);
        produktdatenJPanel.add(datenPane);
        
        
        
        // Inhalt der Prduktleistungen erstellen
        produktleistungenJTable = new JTable(0, 2);
        produktleistungenJTableModel = (DefaultTableModel) produktleistungenJTable.getModel();
        produktleistungenJTable.getColumnModel().getColumn(0).setHeaderValue(Resources.bezeichnung);
        produktleistungenJTable.getColumnModel().getColumn(1).setHeaderValue(Resources.wert);
        fuegeLeistungHinzu();
        
        // neue Buttons fuer die Tabelle
        fuegeLeistungenReiheHinzu = new JButton(Resources.fuegeProduktleistungHinzu);
        loescheLeistungenReihe = new JButton(Resources.loescheProduktleistung);
        
        // eigenes JPanel fuer die Buttons
        JPanel produktleistungenButtons = new JPanel(new FlowLayout());
        produktleistungenButtons.setMaximumSize(new Dimension(1000, 100));
        produktleistungenButtons.add(fuegeLeistungenReiheHinzu);
        produktleistungenButtons.add(loescheLeistungenReihe);
        
        // Buttons dem Listener zuordnen
        fuegeLeistungenReiheHinzu.addActionListener(this);
        loescheLeistungenReihe.addActionListener(this);
        
        produktleistungenJPanel.setLayout(new BoxLayout(produktleistungenJPanel, BoxLayout.Y_AXIS));
        produktleistungenJPanel.add(produktleistungenButtons);
        
        JScrollPane leistungPane = new JScrollPane(produktleistungenJTable);
        produktleistungenJPanel.add(leistungPane);
        
        
        // Inhalt der Qualitaetsanforderungen erstellen
        qualitaetsanforderungenJTable = new JTable(0, 2);
        qualitaetsanforderungenJTableModel = (DefaultTableModel) qualitaetsanforderungenJTable.getModel();
        qualitaetsanforderungenJTable.getColumnModel().getColumn(0).setHeaderValue(Resources.bezeichnung);
        qualitaetsanforderungenJTable.getColumnModel().getColumn(1).setHeaderValue(Resources.wert);
        fuegeQualitaetsanforderungHinzu();
        
        // neue Buttons fuer die Tabelle
        fuegeQualitaetsanforderungenReiheHinzu = new JButton(Resources.fuegeQualitaetsanforderungHinzu);
        loescheQualitaetsanforderungenReihe = new JButton(Resources.loescheQualitaetsanforderung);
        
        // eigenes JPanel fuer die Buttons
        JPanel qualitaetsanforderungenButtons = new JPanel(new FlowLayout());
        qualitaetsanforderungenButtons.setMaximumSize(new Dimension(1000, 100));
        qualitaetsanforderungenButtons.add(fuegeQualitaetsanforderungenReiheHinzu);
        qualitaetsanforderungenButtons.add(loescheQualitaetsanforderungenReihe);
        
        // Buttons dem Listener zuordnen
        fuegeQualitaetsanforderungenReiheHinzu.addActionListener(this);
        loescheQualitaetsanforderungenReihe.addActionListener(this);
        
        qualitaetsanforderungenJPanel.setLayout(new BoxLayout(qualitaetsanforderungenJPanel, BoxLayout.Y_AXIS));
        qualitaetsanforderungenJPanel.add(qualitaetsanforderungenButtons);
        
        JScrollPane qualitaetsanforderungenPane = new JScrollPane(qualitaetsanforderungenJTable);
        qualitaetsanforderungenJPanel.add(qualitaetsanforderungenPane);
        
        // Inhalt des Glossars erstellen
        glossarJTable = new JTable(0, 2);
        glossarJTableModel = (DefaultTableModel) glossarJTable.getModel();
        glossarJTable.getColumnModel().getColumn(0).setHeaderValue(Resources.bezeichnung);
        glossarJTable.getColumnModel().getColumn(1).setHeaderValue(Resources.wert);
        fuegeGlossarHinzu();
        
        // neue Buttons fuer die Tabelle
        fuegeGlossarReiheHinzu = new JButton(Resources.fuegeProduktleistungHinzu);
        loescheGlossarReihe = new JButton(Resources.loescheProduktleistung);
        
        // eigenes JPanel fuer die Buttons
        JPanel glossarButtons = new JPanel(new FlowLayout());
        glossarButtons.setMaximumSize(new Dimension(1000, 100));
        glossarButtons.add(fuegeGlossarReiheHinzu);
        glossarButtons.add(loescheGlossarReihe);
        
        // Buttons dem Listener zuordnen
        fuegeGlossarReiheHinzu.addActionListener(this);
        loescheGlossarReihe.addActionListener(this);
        
        glossarJPanel.setLayout(new BoxLayout(glossarJPanel, BoxLayout.Y_AXIS));
        glossarJPanel.add(glossarButtons);
        
        JScrollPane glossarPane = new JScrollPane(glossarJTable);
        glossarJPanel.add(glossarPane);
        
        
		
	//	labelAufschaetzungsfunktion = new JLabel("Bevor Satrt -> Update");
		
     
        // Erzeugung eines JTabbedPane-Objektes
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
 
        // Registerkarten diesem hinzugefuegen
        tabs.addTab(Resources.deckblatt, deckblattJPanel);
        tabs.addTab(Resources.kundenBeschreibung, kundenbeschreibungPanel);
        tabs.addTab(Resources.zielbestimmung, zielbestimmungJPanel);
        tabs.addTab(Resources.produkteinsatz, produkteinsatzJPanel);
        
        tabs.addTab(Resources.produktfunktionen, produktfunktionenJPanel);
        tabs.addTab(Resources.produktdaten, produktdatenJPanel);
        tabs.addTab(Resources.produktleistungen, produktleistungenJPanel);
        
        tabs.addTab(Resources.qualitaetsanforderungen, qualitaetsanforderungenJPanel);
        tabs.addTab(Resources.ergaenzungen, ergaenzungenJPanel);
        tabs.addTab(Resources.glossar, glossarJPanel);
        tabs.addTab(Resources.aufwandsabschaetzung, aufwandsabschaetzungJPanel);
        
        // JTabbedPane dem Frame hinzufuegen        
        JPanel hauptfensterJPanel= new JPanel();
        hauptfensterJPanel.setLayout(new BoxLayout(hauptfensterJPanel, BoxLayout.Y_AXIS));
        hauptfensterJPanel.add(menueleiste);
        hauptfensterJPanel.add(tabs);
        
        hauptfenster.getContentPane().add(hauptfensterJPanel);
        
        // JFrame anzeigen
        hauptfenster.setVisible(true);
    }
   
 
    		
 
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Quelle der Aktion mit Buttons abgleichen
        if(ae.getSource() == this.exportieren){
        		
        			File datei = showSpeichernUnterDialog();    
        			control.exportiere(datei);  
        }
        
        if(ae.getSource() == this.importieren || ae.getSource() == this.laden){
            File datei = showOeffnenDialog();
            showFull();
            control.importiere(datei);  
           
        }
        
        if(ae.getSource() == this.erstellen) {   
        		 hauptfenster.setVisible(false);
             hauptfenster.dispose();
            	 showFull();
        }
        
        if(ae.getSource() == this.schliessen) {
        		hauptfenster.setVisible(false);
            hauptfenster.dispose();
        		showEmpty();
        }

        if(ae.getSource() == this.speichern){
        	 
        		if(!Control.getalreadySaved()) {
        			File datei = showSpeichernUnterDialog();    
        			control.exportiere(datei);  
        		}else {
        			control.exportiere();   
        		}
        		        
            
        }
        
        
        if(ae.getSource() == this.loeschen){
    			if(showLoeschenDialog()) {
    		try {
    			control.loeschen();   
    		}catch(Exception e) {}
    		hauptfenster.setVisible(false);
        hauptfenster.dispose();
    		showEmpty();}
        
    }
        
    
           

        if(ae.getSource() == this.fuegeFunktionenReiheHinzu){
            fuegeFunktionHinzu();
            produktfunktionenJTableModel.fireTableDataChanged();
        }
        
        if(ae.getSource() == this.loescheFunktionenReihe){
            loescheFunktion();
            produktfunktionenJTableModel.fireTableDataChanged();
        }
        
        if(ae.getSource() == this.fuegeDatenReiheHinzu){
            fuegeDatumHinzu();
            produktdatenJTableModel.fireTableDataChanged();
        }
        
        if(ae.getSource() == this.loescheDatenReihe){
            loescheDatum();
            produktdatenJTableModel.fireTableDataChanged();
        }
        
        if(ae.getSource() == this.fuegeLeistungenReiheHinzu){
            fuegeLeistungHinzu();
            produktleistungenJTableModel.fireTableDataChanged();
        }
        
        if(ae.getSource() == this.loescheLeistungenReihe){
            loescheLeistung();
            produktleistungenJTableModel.fireTableDataChanged();
        }
        
        if(ae.getSource() == this.fuegeQualitaetsanforderungenReiheHinzu){
            fuegeQualitaetsanforderungHinzu();
            qualitaetsanforderungenJTableModel.fireTableDataChanged();
        }
        
        if(ae.getSource() == this.loescheQualitaetsanforderungenReihe){
            loescheQualitaetsanforderung();
            qualitaetsanforderungenJTableModel.fireTableDataChanged();
        }
        
        if(ae.getSource() == this.fuegeGlossarReiheHinzu){
            fuegeGlossarHinzu();
            glossarJTableModel.fireTableDataChanged();
        }
        
        if(ae.getSource() == this.loescheGlossarReihe){
            loescheGlossar();
            glossarJTableModel.fireTableDataChanged();
        }
        
    /*    if(ae.getSource() == this.update){
            
        }*/
        
    }
    
    private File showSpeichernUnterDialog() {
        // JFileChooser-Objekt erstellen
        JFileChooser selektor = new JFileChooser();
        // Dialog zum Oeffnen von Dateien anzeigen
        int rueckgabeWert = selektor.showSaveDialog(hauptfenster);
        
        // Abfrage, ob auf "Speichern" geklickt wurde
        if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
        {
            // Ausgabe der ausgewaehlten Datei
            System.out.println("Die zu speichernde Datei ist: " +  selektor.getSelectedFile().getPath());
            return selektor.getSelectedFile();
        }
        
        return null;
    }
    
    
    
   /* private File showExportierenDialog() {
        // JFileChooser-Objekt erstellen
        JFileChooser selektor = new JFileChooser();
        // Dialog zum Oeffnen von Dateien anzeigen
        int rueckgabeWert = selektor.showSaveDialog(hauptfenster);
        
        // Abfrage, ob auf "Speichern" geklickt wurde
        if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
        {
            // Ausgabe der ausgewaehlten Datei
            System.out.println("Die zu exportierende Datei ist: " +  selektor.getSelectedFile().getPath());
            return selektor.getSelectedFile();
        }
        
        return null;
    }*/
    
    private File showOeffnenDialog() {
        // JFileChooser-Objekt erstellen
        JFileChooser selektor = new JFileChooser();
        // Dialog zum Oeffnen von Dateien anzeigen
        int rueckgabeWert = selektor.showOpenDialog(hauptfenster);
        
        // Abfrage, ob auf "Oeffnen" geklickt wurde
        if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
        {
            // Ausgabe der ausgewaehlten Datei
            System.out.println("Die zu öffnende Datei ist: " +  selektor.getSelectedFile().getPath());
            
            return selektor.getSelectedFile();
        }
        
        return null;
    }
    
    private Boolean showLoeschenDialog() {
    		int auswahl = JOptionPane.showConfirmDialog(hauptfenster, "Sicher?", Resources.aaTool, JOptionPane.YES_NO_OPTION);
        
        if (auswahl == JOptionPane.YES_OPTION) 
            return true;       
        else 
        	 	return false;
        
    }
    

    
    public void fuegeFunktionHinzu() {
        // alle Zeilen zur Tabelle hinzufuegen, die fuer eine Funktion gebraucht werden
    	
        Object[] tmp1 = {"/LF/", ""}; 
        produktfunktionenJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Funktion", ""};            
        produktfunktionenJTableModel.addRow(tmp2);
        Object[] tmp3 = {"   Beschreibung", ""};            
        produktfunktionenJTableModel.addRow(tmp3);
        Object[] tmp4 = {"   Akteur", ""};            
        produktfunktionenJTableModel.addRow(tmp4);
    }
    
    public void loescheFunktion() {
        // alle Zeilen entfernen, die zu einer Funktion gehoeren
        try {
            for(int i = 0; i < 4; i++) {
                produktfunktionenJTableModel.removeRow(produktfunktionenJTableModel.getRowCount()-1);
            }
        }
        catch(Exception e) {

        }
    }
    
    public void fuegeDatumHinzu() {
        // alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden
   
        Object[] tmp1 = {"/LD/", ""};
        produktdatenJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Name", ""};
        produktdatenJTableModel.addRow(tmp2);
        Object[] tmp3 = {"   Beschreibung", ""};
        produktdatenJTableModel.addRow(tmp3);
    }
    
    public void loescheDatum() {
        // alle Zeilen entfernen, die zu einem Produktdatum gehoeren
        try {
            for(int i = 0; i < 3; i++) {
                produktdatenJTableModel.removeRow(produktdatenJTableModel.getRowCount()-1);
            }
        }
        catch(Exception e) {

        }
    }
    
    public void fuegeLeistungHinzu() {
    	// alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden
    		
    		Object[] tmp1 = {"/LE/", ""};
        produktleistungenJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Name", ""};
        produktleistungenJTableModel.addRow(tmp2);
        Object[] tmp3 = {"   Beschreibung", ""};
        produktleistungenJTableModel.addRow(tmp3);
    }
    
    public void loescheLeistung() {
        // alle Zeilen entfernen, die zu einem Produktdatum gehoeren
    		try {
            for(int i = 0; i < 3; i++) {
                produktleistungenJTableModel.removeRow(produktleistungenJTableModel.getRowCount()-1);
            }
        }
        catch(Exception e) {

        }
    }
    
    public void fuegeQualitaetsanforderungHinzu() {
    	// alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden
    		Object[] tmp1 = {"/Q /", ""};
        qualitaetsanforderungenJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Name", ""};
        qualitaetsanforderungenJTableModel.addRow(tmp2);
    }
    
    public void loescheQualitaetsanforderung() {
        // alle Zeilen entfernen, die zu einem Produktdatum gehoeren
    		try {
            for(int i = 0; i < 2; i++) {
            	qualitaetsanforderungenJTableModel.removeRow(qualitaetsanforderungenJTableModel.getRowCount()-1);
            }
        }
        catch(Exception e) {

        }
    }
    
    public void fuegeGlossarHinzu() {
    	// alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden
    		Object[] tmp1 = {"Begriff", ""};
        glossarJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Bedeutung", ""};
        glossarJTableModel.addRow(tmp2);
        // weitere Punkte fehlen
    }
    
    public void loescheGlossar() {
        // alle Zeilen entfernen, die zu einem Produktdatum gehoeren
    		try {
            for(int i = 0; i < 3; i++) {
            	glossarJTableModel.removeRow(glossarJTableModel.getRowCount()-1);
            }
        }
        catch(Exception e) {

        }
    }
    
    public ViewModelConnector getViewConnector() {
        return viewConnector;
    }
    
    public void setViewConnector(ViewModelConnector _viewConnector) {
        viewConnector = _viewConnector;
    }
    
    public JTextArea getDeckblattTextField() {
        return deckblattTextField;
    }
    
    public void setKundenbeschreibung(JTextArea textArea) {
    	kundenbeschreibung = textArea;
    }
    
    public JTextArea getKundenbeschreibung() {
        return kundenbeschreibung;
    }
    
    public void setDeckblattTextField(JTextArea textArea) {
        deckblattTextField = textArea;
    }
    
    public JTextArea getZielbestimmungTextField() {
        return zielbestimmungTextField;
    }
    
    public void setZielbestimmungTextField(JTextArea textArea) {
        zielbestimmungTextField = textArea;
    }
    
    public JTextArea getProdukteinsatzTextField() {
        return produkteinsatzTextField;
    }
    
    public void setProdukteinsatzTextField(JTextArea textArea) {
        produkteinsatzTextField = textArea;
    }
    
    public JTextArea getErgaenzungenTextField() {
        return ergaenzungenTextField;
    }
    
    public void setErgaenzungenTextField(JTextArea textArea) {
        ergaenzungenTextField = textArea;
    }
    
    public JTextArea getAufwandTextField() {
        return aufwandTextField;
    }
    
    public void setAufwandTextField(JTextArea textArea) {
        aufwandTextField = textArea;
    }
    
   
    
    
    
    public JTable getProduktfunktionenJTable() {
        return produktfunktionenJTable;
    }
    
    public void setProduktumgebungTextField(JTable jTable) {
        produktfunktionenJTable = jTable;
    }
    
    public JTable getProduktdatenJTable() {
        return produktdatenJTable;
    }
    
    public void setProduktdatenJTable(JTable jTable) {
        produktdatenJTable = jTable;
    }
    
    public JTable getProduktleistungenJTable() {
        return produktleistungenJTable;
    }
    
    public void setProduktleistungenJTable(JTable jTable) {
        produktleistungenJTable = jTable;
    }
    
    public JTable getQualitaetsanforderungenJTable() {
        return qualitaetsanforderungenJTable;
    }
    
    public void setQualitaetsanforderungenJTable(JTable jTable) {
        qualitaetsanforderungenJTable = jTable;
    }
    
    public JTable getGlossarJTable() {
        return glossarJTable;
    }
    
    public void setGlossarJTable(JTable jTable) {
        glossarJTable = jTable;
    }
    
   /* public IAufwandsabschaetzung getAufwandsabschaetzung() {
        return aufwandsabschaetzung;
    }
    
    public void setAufwandsabschaetzung(IAufwandsabschaetzung _aufwandsabschaetzung) {
        aufwandsabschaetzung = _aufwandsabschaetzung;
    }*/
    

}