/**
 * 
 */
package aufwandsabschaetzung;

import java.awt.event.ActionListener;

/**
 * Factory, zum erstellen jeder neuen Aufwandsabschaetzung
 * @author SebastianKoch, ChrisBoger, AnneBlomeier
 *
 */
public class AufwandsabschaetzungFactory {

	public IAufwandsabschaetzung createAufwandsabschaetzungView(String aufwandsabschaetzungTyp, ActionListener aL) {
		if(aufwandsabschaetzungTyp.equalsIgnoreCase("FunctionPointView")) {
			return new FunctionpointAnalyse(aL);
		}
		else
			return null;
	}

}
