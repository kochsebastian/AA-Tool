/**
 * 
 */
package importexporttests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JTextArea;

import org.junit.After;
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
public class TestExport {
	SimControl control;
	View view;
	ActionEvent ae ;
	File f;
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
	    view.setDeckblattTextField(new JTextArea("Dies ist ein Test"));
	    i ++;
	}

	@Test
	public final void test() {
		ae = new ActionEvent(view, i, Resources.exportieren);
		control.actionPerformed(ae);
		 f = new File("test" + File.separator + "testxml.xml");
		assertTrue(f.exists() && !f.isDirectory());
		
		
		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void cleanUp() throws Exception {
		try {
			Files.deleteIfExists(f.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
