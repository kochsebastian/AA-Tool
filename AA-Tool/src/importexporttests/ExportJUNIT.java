/**
 * 
 */
package importexporttests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import basis.Resources;
import control.Control;
import control.SimControl;
import view.View;

/**
 * @author SebastianKoch
 *
 */
class ExportJUNIT {
	SimControl control;
	View view;
	ActionEvent ae ;
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
        view.setDeckblattTextField(new JTextArea("Dies ist ein Test"));
        i ++;
  
	}
	
	
	@Test
	final void test() {
		ae = new ActionEvent(view, i, Resources.exportieren);
		control.actionPerformed(ae);
		File f = new File("test" + File.separator + "testxml.xml");
		assertTrue(f.exists() && !f.isDirectory());
		
		
		try {
			Files.deleteIfExists(f.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
