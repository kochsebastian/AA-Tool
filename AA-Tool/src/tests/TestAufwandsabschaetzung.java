/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JTable;
import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import aufwandsabschaetzung.ViewFunctionPoint;
import basis.Resources;
import control.SimControl;
import view.ModelViewConnector;
import view.View;

/**
 * @author SebastianKoch
 *
 */
public class TestAufwandsabschaetzung {
	SimControl control;
	View view;
	ActionEvent ae ;
	File f;
	JTextArea testArea = new JTextArea("Dies ist ein Test");
	ModelViewConnector mvc;
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
	    mvc = new ModelViewConnector(view);
	    control.setView(view);
	    
	    ae = new ActionEvent(view, i, Resources.erstellen);
	    control.processViewAction(ae);
	    i++;
	    
	    ae = new ActionEvent(view, i, Resources.loescheProduktfunktion);
	    control.processViewAction(ae);
	    i++;
	    ae = new ActionEvent(view, i, Resources.loescheProduktdatum);
	    control.processViewAction(ae);
	    i++;
	    
	    for(int i = 0; i<5;i++) {
	    	ae = new ActionEvent(view, i, Resources.fuegeSimHinzu);// simuliert die erstellung von tabellenzeilen
	    	control.processViewAction(ae);
	    	i ++;
	    }
        
        
		ae = new ActionEvent(view, i, Resources.ladeDaten);
		control.actionPerformed(ae);
		i ++;
		
		JTable a = ViewFunctionPoint.getTableProduktfunktionen();
		a.setValueAt(true, 0, 1);
		a.setValueAt(true, 1, 1);
		a.setValueAt(true, 2, 1);
		a.setValueAt(true, 3, 1);
		a.setValueAt(true, 4, 1);
		ViewFunctionPoint.setTableProduktfunktionen(a);
	}

	@Test
	public final void ladeDaten() {
		
		assertTrue(true);
	}

}
