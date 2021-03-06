/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JTable;
import javax.swing.JTextArea;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import aufwandsabschaetzung.FunctionpointAnalyse;
import basis.Resources;
import control.SimControl;
import view.ModelViewConnector;
import view.View;

/**
 * @author SebastianKoch
 *
 */
public class TestSelbstoptimierung {
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
	    
	    for(int i = 0; i<3;i++) {
	    	ae = new ActionEvent(view, i, Resources.fuegeSimHinzu);// simuliert die erstellung von tabellenzeilen
	    	control.processViewAction(ae);
	    	i ++;
	    }
        
        
		ae = new ActionEvent(view, i, Resources.exportieren);
		control.processViewAction(ae);
		i ++;
		ae = new ActionEvent(view, i, Resources.ladeDaten);
		control.processViewAction(ae);
		i ++;
		JTable a = FunctionpointAnalyse.getTableProduktfunktionen();
		a.setValueAt(true, 0, 1);
		a.setValueAt(true, 1, 2);
		a.setValueAt(true, 2, 3);
		FunctionpointAnalyse.setTableProduktfunktionen(a);
		JTable b = FunctionpointAnalyse.getTableProduktdaten();
		b.setValueAt(true, 0, 1);
		b.setValueAt(true, 1, 2);
		b.setValueAt(true, 2, 1);
		FunctionpointAnalyse.setTableProduktdaten(b);
		JTable c = FunctionpointAnalyse.getTableKomplexitaetLF();
		int ftr = 0 + (int)(Math.random() * ((2 - 0) + 1));
		int det = 0 + (int)(Math.random() * ((15 - 0) + 1));
		c.setValueAt(1, 0, 1);c.setValueAt(4, 0, 2); // low
		//ftr = 2;
		//det = 0 + (int)(Math.random() * ((15 - 0) + 1));
		c.setValueAt(2, 1, 1);c.setValueAt(14, 1, 2);// av
		ftr=3;
		c.setValueAt(3, 2, 1);c.setValueAt(15, 2, 2);//high
		FunctionpointAnalyse.setTableKomplexitaetLF(c);
		JTable d = FunctionpointAnalyse.getTableKomplexitaetLD();
		d.setValueAt(1, 0, 1);d.setValueAt(4, 0, 2);// low
		d.setValueAt(2, 1, 1);d.setValueAt(10, 1, 2);// av
		d.setValueAt(5, 2, 1);d.setValueAt(20, 2, 2);//high
		FunctionpointAnalyse.setTableKomplexitaetLD(d);
		ae = new ActionEvent(view, i, Resources.ladeSelbstoptimierung);
		control.processViewAction(ae);
		i ++;
		
		ae = new ActionEvent(view, i, Resources.berechneFunctionpoints);
		control.processViewAction(ae);
		i ++;
		
		
	}
	@Test
	public final void testhochOptimierung() {
		FunctionpointAnalyse.setZielEinflussfaktor("Rechenoperationen");
		FunctionpointAnalyse.setZielFunctionPoints("45");
		ae = new ActionEvent(view, i, Resources.optimieren);
		control.processViewAction(ae);
		i ++;
		assertTrue((double)FunctionpointAnalyse.getTableUebersichtFunctionpoints().getValueAt(2, 2)>=(double)45);
	}
	@Test
	public final void testrunterOptimierung() {
		FunctionpointAnalyse.setZielEinflussfaktor("Rechenoperationen");
		FunctionpointAnalyse.setZielFunctionPoints("40");
		ae = new ActionEvent(view, i, Resources.optimieren);
		control.processViewAction(ae);
		i ++;
		assertTrue((double)FunctionpointAnalyse.getTableUebersichtFunctionpoints().getValueAt(2, 2)<=(double)40);
	}
	
	@Test
	public final void testzuWeitRunterOptimierung() {
		FunctionpointAnalyse.setZielEinflussfaktor("Rechenoperationen");
		FunctionpointAnalyse.setZielFunctionPoints("30");
		ae = new ActionEvent(view, i, Resources.optimieren);
		control.processViewAction(ae);
		i ++;
		assertFalse((double)FunctionpointAnalyse.getTableUebersichtFunctionpoints().getValueAt(2, 2)<=(double)30);
		assertEquals(0, FunctionpointAnalyse.getTableEinflussfaktoren().getValueAt(4, 1));
	}
	
	@Test//(expected = NumberFormatException.class)
	public final void teststringEingabe() {
		FunctionpointAnalyse.setZielFunctionPoints("test");
		assertEquals((double) 0, FunctionpointAnalyse.getZielErgebnis(), 0);
	}
	
	@Test
	public final void nichtexistierenderEinflussfaktor() {
		// nothing happens
		return;
	}
	
	/**
	 * Loescht die im Setup gebaute Datei
	 */
	@After
	public void cleanUp() {
		File dir = new File("test");
		dir.mkdirs();
		File tmp = new File(dir, "testxml.xml");
		tmp.delete();
	}	
}
