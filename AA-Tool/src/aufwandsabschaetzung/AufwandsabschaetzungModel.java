/**
 *
 */
package aufwandsabschaetzung;

/**
 * @author SebastianKoch
 * <p>
 * for (i : length(LF)) {
 * x = 0;
 * <p>
 * for(bis j = 2) {
 * <p>
 * if(xy[i][j] == true) {
 * x = j+1;
 * break;
 * <p>
 * }
 * <p>
 * }
 * switch(x)
 * case 0 -> EO.add(LF[i][j])
 * case 1 -> EI.add(LF[i][j])
 * case 2 -> EQ.add(LF[i][j])
 * <p>
 * <p>
 * }
 */
public abstract class AufwandsabschaetzungModel {
    /*private static AufwandsabschaetzungModel instanz;
	//private IAufwandsabschaetzung instanzAufwandabschaetzung;
	
	private Boolean aufwandabschaetzungErstellt = false;
	private JPanel aufwandsabschaetzungJPanel;
	    
	private final JButton buttonHinzufügen;
	private final JButton starteBerechnung;
	private final JLabel labelAufschaetzungsfunktion;
	private final Choice listeVorhandenerAufwandsschaetzfunktionen;
	   
	public AufwandsabschaetzungModel() {
		aufwandsabschaetzungJPanel = new JPanel();
        
        aufwandsabschaetzungJPanel.setLayout(new BorderLayout());
        
        buttonHinzufügen = new JButton("+");
        buttonHinzufügen.addActionListener(this);
        labelAufschaetzungsfunktion = new JLabel("Alle verfügbaren Aufwandsabschätzung können über die Liste ausgewählt werden"); 
        
        starteBerechnung= new JButton("Berechnung starten");
        starteBerechnung.addActionListener(this);
        
        listeVorhandenerAufwandsschaetzfunktionen   = new Choice();  
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
*/
}
