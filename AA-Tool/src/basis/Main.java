
package basis;





import aufwandsabschaetzung.Aufwandsabschaetzung;
import aufwandsabschaetzung.IAufwandsabschaetzung;
import control.Control;
import view.View;

/**
 * @author SebastianKoch
 *
 */
public class Main {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		
        Control control = new Control();
        IAufwandsabschaetzung aufwandsabschaetzung = new Aufwandsabschaetzung();
        View view = View.getInstanz(control, aufwandsabschaetzung);
        control.setView(view);
        
        view.showEmpty();

	}

}
