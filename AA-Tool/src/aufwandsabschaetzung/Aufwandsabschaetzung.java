/**
 *
 */
package aufwandsabschaetzung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author SebastianKoch
 */
public class Aufwandsabschaetzung implements ActionListener {
    private static Aufwandsabschaetzung instanz;
    //private IAufwandsabschaetzung instanzAufwandabschaetzung;
    private final JButton buttonHinzufügen;
    private final JButton starteBerechnung;
    private final JLabel labelAufschaetzungsfunktion;
    private final Choice listeVorhandenerAufwandsschaetzfunktionen;
    private Boolean aufwandabschaetzungErstellt = false;
    private JPanel aufwandsabschaetzungJPanel;

    /**
     *
     */
    public Aufwandsabschaetzung() {
        aufwandsabschaetzungJPanel = new JPanel();

        aufwandsabschaetzungJPanel.setLayout(new BorderLayout());

        buttonHinzufügen = new JButton("+");
        buttonHinzufügen.addActionListener(this);
        labelAufschaetzungsfunktion = new JLabel("Alle verfügbaren Aufwandsabschätzung können über die Liste ausgewählt werden");

        starteBerechnung = new JButton("Berechnung starten");
        starteBerechnung.addActionListener(this);

        listeVorhandenerAufwandsschaetzfunktionen = new Choice();
        listeVorhandenerAufwandsschaetzfunktionen.add("Function-Point");
        listeVorhandenerAufwandsschaetzfunktionen.add("Prozentsatzmethode");

        aufwandsabschaetzungJPanel.add(labelAufschaetzungsfunktion);
        aufwandsabschaetzungJPanel.add(listeVorhandenerAufwandsschaetzfunktionen);
        aufwandsabschaetzungJPanel.add(buttonHinzufügen);
        aufwandsabschaetzungJPanel.add(starteBerechnung);

        FlowLayout experimentLayout = new FlowLayout();

        aufwandsabschaetzungJPanel.setLayout(experimentLayout);
        aufwandsabschaetzungJPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }


    public Boolean getAufwandabschaetzungErstellt() {
        return aufwandabschaetzungErstellt;
    }

    public void setAufwandabschaetzungErstellt(Boolean _aufwandabschaetzungErstellt) {
        aufwandabschaetzungErstellt = _aufwandabschaetzungErstellt;
    }

}
