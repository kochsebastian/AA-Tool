/**
 * 
 */
package aufwandsabschaetzung;

import java.awt.event.ActionListener;

/**
 * @author SebastianKoch
 *
 */
public class AufwandsabschaetzungFactory {

	public IAufwandsabschaetzungView createAufwandsabschaetzungView(String aufwandsabschaetzungTyp, ActionListener aL) {
		if(aufwandsabschaetzungTyp.equalsIgnoreCase("FunctionPointView")) {
			return new ViewFunctionPoint(aL);
		}
		else
			return null;
	}

}
