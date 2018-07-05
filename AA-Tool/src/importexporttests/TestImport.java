/**
 * 
 */
package importexporttests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JTextArea;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import basis.Resources;
import control.SimControl;
import view.View;

/**
 * @author SebastianKoch
 *
 */
public class TestImport {
	SimControl control;
	View view;
	ActionEvent ae ;
	JTextArea testArea = new JTextArea("Dies ist ein Test");
	int i = 0;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
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

	@Test
	public final void test() {
		ae = new ActionEvent(view, i, Resources.importieren);
		control.actionPerformed(ae);
		System.out.println("");
		Assert.assertEquals(testArea.getText(), view.getDeckblattTextField().getText());
	}
	
	

}
