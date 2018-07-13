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
	public final void checkE1() {
		assertEquals(46, ViewFunctionPoint.getTableUebersicht().getValueAt(15, 4));
	}
	@Test
	public final void checkE2() {
		assertEquals(47, ViewFunctionPoint.getTableUebersichtFunctionpoints().getValueAt(0, 2));
	}
	@Test
	public final void checkE3() {
		assertEquals(1.17, ViewFunctionPoint.getTableUebersichtFunctionpoints().getValueAt(1, 2));
	}
	@Test
	public final void checkFunctionPoints() {
		assertEquals(42.12, ViewFunctionPoint.getTableUebersichtFunctionpoints().getValueAt(1, 2));
	}

}
