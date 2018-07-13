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

import aufwandsabschaetzung.ViewFunctionPoint;
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
		JTable a = ViewFunctionPoint.getTableProduktfunktionen();
		a.setValueAt(true, 0, 1);
		a.setValueAt(true, 1, 2);
		a.setValueAt(true, 2, 3);
		ViewFunctionPoint.setTableProduktfunktionen(a);
		JTable b = ViewFunctionPoint.getTableProduktdaten();
		b.setValueAt(true, 0, 1);
		b.setValueAt(true, 1, 2);
		b.setValueAt(true, 2, 1);
		ViewFunctionPoint.setTableProduktdaten(b);
		JTable c = ViewFunctionPoint.getTableKomplexitaetLF();
		int ftr = 0 + (int)(Math.random() * ((2 - 0) + 1));
		int det = 0 + (int)(Math.random() * ((15 - 0) + 1));
		c.setValueAt(1, 0, 1);c.setValueAt(4, 0, 2); // low
		//ftr = 2;
		//det = 0 + (int)(Math.random() * ((15 - 0) + 1));
		c.setValueAt(2, 1, 1);c.setValueAt(14, 1, 2);// av
		ftr=3;
		c.setValueAt(3, 2, 1);c.setValueAt(15, 2, 2);//high
		ViewFunctionPoint.setTableKomplexitaetLF(c);
		JTable d = ViewFunctionPoint.getTableKomplexitaetLD();
		d.setValueAt(1, 0, 1);d.setValueAt(4, 0, 2);// low
		d.setValueAt(2, 1, 1);d.setValueAt(10, 1, 2);// av
		d.setValueAt(5, 2, 1);d.setValueAt(20, 2, 2);//high
		ViewFunctionPoint.setTableKomplexitaetLD(d);
		ae = new ActionEvent(view, i, "Lade optimierte Einflussfaktoren einer letzten Aufwandsabschaetzung");
		control.processViewAction(ae);
		i ++;
		
		ae = new ActionEvent(view, i, "Functionpoints berechnen");
		control.processViewAction(ae);
		i ++;
		
		
	}
	@Test
	public final void testhochOptimierung() {
		ViewFunctionPoint.setZielEinflussfaktor("Rechenoperationen");
		ViewFunctionPoint.setZielFunctionPoints("45");
		ae = new ActionEvent(view, i, "Optimieren");
		control.processViewAction(ae);
		i ++;
		assertTrue((double)ViewFunctionPoint.getTableUebersichtFunctionpoints().getValueAt(2, 2)>=(double)45);
	}
	@Test
	public final void testrunterOptimierung() {
		ViewFunctionPoint.setZielEinflussfaktor("Rechenoperationen");
		ViewFunctionPoint.setZielFunctionPoints("40");
		ae = new ActionEvent(view, i, "Optimieren");
		control.processViewAction(ae);
		i ++;
		assertTrue((double)ViewFunctionPoint.getTableUebersichtFunctionpoints().getValueAt(2, 2)<=(double)40);
	}
	
	@Test
	public final void testzuWeitRunterOptimierung() {
		ViewFunctionPoint.setZielEinflussfaktor("Rechenoperationen");
		ViewFunctionPoint.setZielFunctionPoints("30");
		ae = new ActionEvent(view, i, "Optimieren");
		control.processViewAction(ae);
		i ++;
		assertFalse((double)ViewFunctionPoint.getTableUebersichtFunctionpoints().getValueAt(2, 2)<=(double)30);
		assertEquals(0, ViewFunctionPoint.getTableEinflussfaktoren().getValueAt(4, 1));
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
