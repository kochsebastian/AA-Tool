package aufwandsabschaetzung;

import javax.swing.JPanel;

/**
 * @author SebastianKoch
 *
 */
public interface IAufwandsabschaetzung {

	public JPanel getJPanel();
    public void setJPanel(JPanel jPanel);
    public String[][] getInhalt();
    public void setInhalt(String text[][]);
 

}
