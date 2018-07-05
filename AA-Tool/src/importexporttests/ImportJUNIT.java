/**
 * 
 */
package importexporttests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import basis.Resources;
import control.SimControl;
import view.View;

/**
 * @author SebastianKoch
 *
 */
class ImportJUNIT {
	SimControl control;
	View view;
	ActionEvent ae ;
	JTextArea testArea = new JTextArea("Dies ist ein Test");
	int i = 0;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		control = new SimControl();
        view = View.getInstanz(control);
        control.setView(view);
        ae = new ActionEvent(view, i, Resources.erstellen);
        control.processViewAction(ae);
        i++;
        ae = new ActionEvent(view, i, Resources.fuegeSimHinzu);
        control.processViewAction(ae);
        view.setDeckblattTextField(testArea);
        i ++;
        ae = new ActionEvent(view, i, Resources.exportieren);
		control.actionPerformed(ae);
		i++;
		ae = new ActionEvent(view, i, Resources.schliessen);
		control.actionPerformed(ae);
	}

	/**
	 * Test method for {@link xmlFramework.XMLParser#parseXML(java.io.File)}.
	 */
	@Test
	final void testParseXML() {
		ae = new ActionEvent(view, i, Resources.importieren);
		control.actionPerformed(ae);
		System.out.println("");
		Assert.assertEquals(testArea.getText(), view.getDeckblattTextField().getText());
	}

}
