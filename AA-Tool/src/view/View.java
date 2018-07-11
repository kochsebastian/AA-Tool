package view;


import basis.Resources;

import control.Control;
import control.IControl;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



/**
 * @author SebastianKoch
 */
public class View implements IView { 

    private static View view = null;
    private static IControl control;
    private static ModelViewConnector viewConnector;
    
    private JFrame hauptfenster = null;
    private JButton exportieren;
    private JButton importieren;
    private JButton erstellen;
    private JButton speichern;
    private JButton laden;
    private JButton loeschen;
    private JButton schliessen;
    
    private JTextArea kundenbeschreibung;
    private JTextArea deckblattTextField;
    private JTextArea zielbestimmungTextField;
    private JTextArea produkteinsatzTextField;
    private JTextArea ergaenzungenTextField;
    private JTextArea aufwandTextField;
    
    private IViewModule viewFunktion;
    private IViewModule viewDatum;
    private IViewModule viewGlossar;


    private View() {

    }

    /**
     * @param _control
     * @return
     */
    public static View getInstanz(IControl _control) {
        //   aufwandsabschaetzung = _aufwandsabschaetzung;
    	
    	control = _control;
        if (view == null) {
            view = new View();
            viewConnector = new ModelViewConnector(view);
        }
        return view;
    }

    public void setWindow() {

        // setze Nimbus look and feel fuer Anzeige (fuer besseres Design)
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {

        }

        // Erzeugung eines neuen Framefensters
        hauptfenster = new JFrame();
        hauptfenster.setTitle(Resources.aaTool);
        hauptfenster.setSize(1000, 800);
        hauptfenster.setResizable(false);
        // setze das Fenster in die Bildschirmmitte
        try {
            hauptfenster.setLocationRelativeTo(null);
        } catch (Exception e) {

        }

    }


    public void displayEmpty(ActionListener actionListener) {
        setWindow();

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
        laden = new JButton(Resources.laden);
        importieren = new JButton(Resources.importieren);


        // Buttons dem Listener zuordnen 
        importieren.addActionListener(actionListener);
        erstellen.addActionListener(actionListener);
        laden.addActionListener(actionListener);


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
        JTextArea titelblattTextField = new JTextArea(Resources.readme, 44, 76);
        titelblattJPanel.add(titelblattTextField);


        // Erzeugung eines JTabbedPane-Objektes
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);

        // Registerkarten diesem hinzugefuegen
        tabs.addTab("                           ", titelblattJPanel);


        // JTabbedPane dem Frame hinzufuegen        
        JPanel hauptfensterJPanel = new JPanel();
        hauptfensterJPanel.setLayout(new BoxLayout(hauptfensterJPanel, BoxLayout.Y_AXIS));
        hauptfensterJPanel.add(menueleiste);
        hauptfensterJPanel.add(tabs);

        hauptfenster.getContentPane().add(hauptfensterJPanel);

        // JFrame anzeigen
        hauptfenster.setVisible(true);
    }


    public void displayFull(ActionListener actionListener) {
        setWindow();
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
                    if (datei != null) {
                        ((Control) control).exportiere(datei);
                        System.out.println("Call: control.exportiere(datei);");
                    }
                    System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        });

        // Erzeugung der Buttons der Menueleiste
        erstellen = new JButton(Resources.erstellen);
        laden = new JButton(Resources.laden);
        speichern = new JButton(Resources.speichern);
        loeschen = new JButton(Resources.loeschen);
        schliessen = new JButton(Resources.schliessen);
        exportieren = new JButton(Resources.exportieren);
        importieren = new JButton(Resources.importieren);


        // Buttons dem Listener zuordnen
        exportieren.addActionListener(actionListener);
        importieren.addActionListener(actionListener);
        erstellen.addActionListener(actionListener);
        speichern.addActionListener(actionListener);
        laden.addActionListener(actionListener);
        loeschen.addActionListener(actionListener);
        schliessen.addActionListener(actionListener);

        // Menueleiste dem Frame hinzufuegen
        JPanel menueleiste = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menueleiste.setBorder(BorderFactory.createLineBorder(Color.black));
        menueleiste.setMaximumSize(new Dimension(1000, 100));
        menueleiste.add(erstellen);
        menueleiste.add(laden);
        menueleiste.add(speichern);
        menueleiste.add(loeschen);
        menueleiste.add(schliessen);
        menueleiste.add(exportieren);
        menueleiste.add(importieren);


        // Erzeugung der JPanels
        JPanel deckblattJPanel = new JPanel();
        JPanel kundenbeschreibungPanel = new JPanel();
        JPanel zielbestimmungJPanel = new JPanel();
        JPanel produkteinsatzJPanel = new JPanel();
        JPanel ergaenzungenJPanel = new JPanel();
        JPanel produktfunktionenJPanel = new JPanel();
        JPanel produktdatenJPanel = new JPanel();
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

        //Inhalt der Aufwandsabschaetzung
        ViewAufwandsabschaetzung aPanel = new ViewAufwandsabschaetzung(actionListener);
        aufwandsabschaetzungJPanel.add(aPanel);
        JScrollPane aufwandsabschaetzungScrollPane = new JScrollPane(aufwandsabschaetzungJPanel,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        // Inhalt der Prduktfunktionen erstellen
        viewFunktion = new ViewProduktfunktion(actionListener, produktfunktionenJPanel);
        produktfunktionenJPanel.add((Component) viewFunktion);

        // Inhalt der Prduktdaten erstellen
        viewDatum = new ViewProduktdatum(actionListener, produktdatenJPanel);
        produktdatenJPanel.add((Component) viewDatum);
        
        // Inhalt der Glossar erstellen
        viewGlossar = new ViewGlossar(actionListener, glossarJPanel);
        glossarJPanel.add((Component) viewGlossar);

        // Erzeugung eines JTabbedPane-Objektes
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);

        // Registerkarten diesem hinzugefuegen
        tabs.addTab(Resources.deckblatt, deckblattJPanel);
        tabs.addTab(Resources.kundenBeschreibung, kundenbeschreibungPanel);
        tabs.addTab(Resources.zielbestimmung, zielbestimmungJPanel);
        tabs.addTab(Resources.produkteinsatz, produkteinsatzJPanel);

        tabs.addTab(Resources.produktfunktionen, produktfunktionenJPanel);
        tabs.addTab(Resources.produktdaten, produktdatenJPanel);

     //   tabs.addTab(Resources.qualitaetsanforderungen, qualitaetsanforderungenJPanel);
        tabs.addTab(Resources.ergaenzungen, ergaenzungenJPanel);
        tabs.addTab(Resources.glossar, glossarJPanel);
        tabs.addTab(Resources.aufwandsabschaetzung, aufwandsabschaetzungScrollPane);

        // JTabbedPane dem Frame hinzufuegen        
        JPanel hauptfensterJPanel = new JPanel();
        hauptfensterJPanel.setLayout(new BoxLayout(hauptfensterJPanel, BoxLayout.Y_AXIS));
        hauptfensterJPanel.add(menueleiste);
        hauptfensterJPanel.add(tabs);

        hauptfenster.getContentPane().add(hauptfensterJPanel);

        // JFrame anzeigen
        hauptfenster.setVisible(true);

        updateModelView(viewFunktion, viewDatum, viewGlossar);

    }


    private void updateModelView(IViewModule viewFunktion2, IViewModule viewDatum2,
    		IViewModule viewGlossar2) {
        ModelViewConnector.viewFunktion = (ViewProduktfunktion) viewFunktion2;
        ModelViewConnector.viewDatum = (ViewProduktdatum) viewDatum2;
        ModelViewConnector.viewGlossar = (ViewGlossar) viewGlossar2;

    }

    public void closeWindow() {
        hauptfenster.setVisible(false);
        hauptfenster.dispose();
    }


    public File showSpeichernUnterDialog() {
        // JFileChooser-Objekt erstellen
        JFileChooser selektor = new JFileChooser();
        // Dialog zum Oeffnen von Dateien anzeigen
        int rueckgabeWert = selektor.showSaveDialog(hauptfenster);

        // Abfrage, ob auf "Speichern" geklickt wurde
        if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
            // Ausgabe der ausgewaehlten Datei
            System.out.println("Die zu speichernde Datei ist: " + selektor.getSelectedFile().getPath());
            return selektor.getSelectedFile();
        }

        return null;
    }


    private File showExportierenDialog() {
        // JFileChooser-Objekt erstellen
        JFileChooser selektor = new JFileChooser();
        // Dialog zum Oeffnen von Dateien anzeigen
        int rueckgabeWert = selektor.showSaveDialog(hauptfenster);

        // Abfrage, ob auf "Speichern" geklickt wurde
        if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
            // Ausgabe der ausgewaehlten Datei
            System.out.println("Die zu exportierende Datei ist: " + selektor.getSelectedFile().getPath());
            return selektor.getSelectedFile();
        }

        return null;
    }

    public File showOeffnenDialog() {
        // JFileChooser-Objekt erstellen
        JFileChooser selektor = new JFileChooser();
        // Dialog zum Oeffnen von Dateien anzeigen
        int rueckgabeWert = selektor.showOpenDialog(hauptfenster);

        // Abfrage, ob auf "Oeffnen" geklickt wurde
        if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
            // Ausgabe der ausgewaehlten Datei
            System.out.println("Die zu öffnende Datei ist: " + selektor.getSelectedFile().getPath());

            return selektor.getSelectedFile();
        }

        return null;
    }

    public Boolean showLoeschenDialog() {
        int auswahl = JOptionPane.showConfirmDialog(hauptfenster, "Sicher?", Resources.aaTool, JOptionPane.YES_NO_OPTION);

        if (auswahl == JOptionPane.YES_OPTION)
            return true;
        else
            return false;

    }


    public void fuegeFunktionHinzu() {
        // alle Zeilen zur Tabelle hinzufuegen, die fuer eine Funktion gebraucht werden
        viewFunktion.fuegeHinzu();
    }

    public void loescheFunktion() {
        // alle Zeilen entfernen, die zu einer Funktion gehoeren
        viewFunktion.loesche();
    }

    public void fuegeDatumHinzu() {
        // alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden
        viewDatum.fuegeHinzu();
    }

    public void loescheDatum() {
        // alle Zeilen entfernen, die zu einem Produktdatum gehoeren
        viewDatum.loesche();
    }

    public void fuegeGlossarHinzu() {
        // alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden
        viewGlossar.fuegeHinzu();
    }

    public void loescheGlossar() {
        // alle Zeilen entfernen, die zu einem Produktdatum gehoeren
        viewGlossar.loesche();
    }
    
    public void fuegeSimHinzu() {
        // fuegt in jedem Tab eine Simulierte tabelle hinzu
        viewFunktion.fuegeSimHinzu();
        viewDatum.fuegeSimHinzu();
        viewGlossar.fuegeSimHinzu();
        viewFunktion.fuegeSimHinzu();
        viewDatum.fuegeSimHinzu();
        viewGlossar.fuegeSimHinzu();
    }

    public ModelViewConnector getViewConnector() {
        return viewConnector;
    }

    public void setViewConnector(ModelViewConnector _viewConnector) {
        viewConnector = _viewConnector;
    }

    public JTextArea getDeckblattTextField() {
        return deckblattTextField;
    }

    public void setDeckblattTextField(JTextArea textArea) {
        deckblattTextField = textArea;
    }

    public JTextArea getKundenbeschreibung() {
        return kundenbeschreibung;
    }

    public void setKundenbeschreibung(JTextArea textArea) {
        kundenbeschreibung = textArea;
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

}