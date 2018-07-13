/**
 *
 */
package view;

import basis.Resources;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author SebastianKoch
 */
public class ViewProduktdatum extends AViewTableModule implements IViewModule{

    private JTable produktdatenJTable;
    private DefaultTableModel produktdatenJTableModel;
    private JButton fuegeDatenReiheHinzu;
    private JButton loescheDatenReihe;
    private int LDcount = 10;
    /**
     *
     */
    public ViewProduktdatum(ActionListener actionListener, JPanel produktdatenJPanel) {
        // Inhalt der Prduktdaten erstellen
        produktdatenJTable = new JTable(0, 2);
        produktdatenJTableModel = (DefaultTableModel) produktdatenJTable.getModel();
        produktdatenJTable.getColumnModel().getColumn(0).setHeaderValue(Resources.bezeichnung);
        produktdatenJTable.getColumnModel().getColumn(1).setHeaderValue(Resources.wert);
        fuegeHinzu();

        // neue Buttons fuer die Tabelle
        fuegeDatenReiheHinzu = new JButton(Resources.fuegeProduktdatumHinzu);
        loescheDatenReihe = new JButton(Resources.loescheProduktdatum);

        // eigenes JPanel fuer die Buttons
        JPanel produktdatenButtons = new JPanel(new FlowLayout());
        produktdatenButtons.setMaximumSize(new Dimension(1000, 100));
        produktdatenButtons.add(fuegeDatenReiheHinzu);
        produktdatenButtons.add(loescheDatenReihe);

        // Buttons dem Listener zuordnen
        fuegeDatenReiheHinzu.addActionListener(actionListener);
        loescheDatenReihe.addActionListener(actionListener);

        produktdatenJPanel.setLayout(new BoxLayout(produktdatenJPanel, BoxLayout.Y_AXIS));
        produktdatenJPanel.add(produktdatenButtons);

        JScrollPane datenPane = new JScrollPane(produktdatenJTable);
        produktdatenJPanel.add(datenPane);
    }
    
	@Override
	public void fuegeHinzu() {
		// alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden

        Object[] tmp1 = {"/LD/", ""};
        produktdatenJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Name", ""};
        produktdatenJTableModel.addRow(tmp2);
        Object[] tmp3 = {"   Beschreibung", ""};
        produktdatenJTableModel.addRow(tmp3);
        produktdatenJTableModel.fireTableDataChanged();
		
	}
	
	@Override
	public void fuegeSimHinzu() {
		// alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden
		String LDnummer = "/" + Integer.toString(LDcount) + "/";
		LDcount += 10;
        Object[] tmp1 = {"/LD/", LDnummer};
        produktdatenJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Name", ""};
        produktdatenJTableModel.addRow(tmp2);
        Object[] tmp3 = {"   Beschreibung", ""};
        produktdatenJTableModel.addRow(tmp3);
        produktdatenJTableModel.fireTableDataChanged();
	}

	@Override
	public void loesche() {
		 // alle Zeilen entfernen, die zu einem Produktdatum gehoeren
        try {
            for (int i = 0; i < 3; i++) {
                produktdatenJTableModel.removeRow(produktdatenJTableModel.getRowCount() - 1);
            }
        } catch (Exception e) {

        }
        produktdatenJTableModel.fireTableDataChanged();
	}

	@Override
	public JTable getJTable() {
		return produktdatenJTable;
	}

	@Override
	public void setJTable(JTable jTable) {
		produktdatenJTable = jTable;
		
	}

	

}
