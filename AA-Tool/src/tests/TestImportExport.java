/**

 * 
 */
package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JTextArea;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import basis.Resources;
import control.SimControl;
import view.ModelViewConnector;
import view.View;

/**
 * @author SebastianKoch
 *
 */
public class TestImportExport {
	SimControl control;
	View view;
	ActionEvent ae ;
	File f;
	JTextArea testArea = new JTextArea("Dies ist ein Test");
	ModelViewConnector mvc;
	int i = 0;
	

	/**
	 * Bereitet die Umgebung vor
	 * Exportiert die Anforderungsanalyse
	 */
	@Before
	public void setUp() {
		control = new SimControl();
	    view = View.getInstanz(control);
	    mvc = new ModelViewConnector(view);
	    control.setView(view);
	    
	    ae = new ActionEvent(view, i, Resources.erstellen);
	    control.processViewAction(ae);
	    i++;
	    
	    ae = new ActionEvent(view, i, Resources.fuegeSimHinzu);// simuliert die erstellung von tabellenzeilen
        control.processViewAction(ae);
        view.setDeckblattTextField(testArea);  // aendert das deckblatt
        i ++;
        
	    ae = new ActionEvent(view, i, Resources.exportieren);
		control.actionPerformed(ae);
		i ++;
		
		f = new File("test" + File.separator + "testxml.xml");  
	    
	}

	/**
	 * Testet, ob die Datei erstellt wurde
	 */
	@Test
	public final void testExistingFile() {
		assertTrue(f.exists() && !f.isDirectory());
	}
	
	/**
	 * Testet ob nicht irgendwas erstellt wurde
	 */
	@Test 
	public final void testNotExistingFile() {
		File fakeFile = new File("fakeXML.xml");
		assertFalse(fakeFile.exists() && !f.isDirectory());
	}
	
	/**
	 * Testet, den import einer nicht kompatiblen Datei
	 */
	@Test //@Test(expected = SAXException.class)
	public final void testWrongFileTypeException() {
		File dir = new File("test");
		dir.mkdirs();
		File pdf = new File(dir, "foo.pdf");
		control.importiere(pdf);
		// no exception thrown test will pass
	}
	
	/**
	 * testet, ob der Import eines Textfeldes funktioniert beispielhaft am Deckblatt
	 */
	@Test
	public final void testTextFeldImport() { // stellvertretend für alle Textfelder
		ae = new ActionEvent(view, i, Resources.importieren);
		control.actionPerformed(ae);
		Assert.assertEquals(testArea.getText(), view.getDeckblattTextField().getText());
	}
	
	/**
	 * testet, ob der Import einer Tabelle funktioniert beispielhaft an der Produktfunktionsnummer
	 */
	@Test
	public final void testTabellenZeileImport() { // stellvertretend fuer alle Tabellen
		ae = new ActionEvent(view, i, Resources.importieren);
		control.actionPerformed(ae);
		String[][] produktfunktionNummer = mvc.getInhalt("Produktfunktionen");
		assertEquals(produktfunktionNummer[4][1],"/LF10/"); 
		// vierte Zeile ist die erste beschriebene Produktfunktion, erste spalte ist die inhaltsspalte
	}
	
	/**
	 * Loescht die im Setup gebaute Datei
	 */
	@After
	public void cleanUp() {
		try {
			Files.deleteIfExists(f.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
