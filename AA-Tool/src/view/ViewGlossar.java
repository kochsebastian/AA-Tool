/**
 *
 */
package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import basis.Resources;

/**
 * @author SebastianKoch
 */
public class ViewGlossar extends AViewTableModule implements IViewModule {

    private JTable glossarJTable;
    private DefaultTableModel glossarJTableModel;
    private JButton fuegeGlossarReiheHinzu;
    private JButton loescheGlossarReihe;
    private int i = 1;
    /**
     *
     */
    public ViewGlossar(ActionListener actionListener, JPanel glossarJPanel) {
        // Inhalt des Glossars erstellen
        glossarJTable = new JTable(0, 2);
        glossarJTableModel = (DefaultTableModel) glossarJTable.getModel();
        glossarJTable.getColumnModel().getColumn(0).setHeaderValue(Resources.bezeichnung);
        glossarJTable.getColumnModel().getColumn(1).setHeaderValue(Resources.wert);
        this.fuegeHinzu();

        // neue Buttons fuer die Tabelle
        fuegeGlossarReiheHinzu = new JButton(Resources.fuegeGlossarHinzu);
        loescheGlossarReihe = new JButton(Resources.loescheGlossar);

        // eigenes JPanel fuer die Buttons
        JPanel glossarButtons = new JPanel(new FlowLayout());
        glossarButtons.setMaximumSize(new Dimension(1000, 100));
        glossarButtons.add(fuegeGlossarReiheHinzu);
        glossarButtons.add(loescheGlossarReihe);

        // Buttons dem Listener zuordnen
        fuegeGlossarReiheHinzu.addActionListener(actionListener);
        loescheGlossarReihe.addActionListener(actionListener);

        glossarJPanel.setLayout(new BoxLayout(glossarJPanel, BoxLayout.Y_AXIS));
        glossarJPanel.add(glossarButtons);

        JScrollPane glossarPane = new JScrollPane(glossarJTable);
        glossarJPanel.add(glossarPane);
    }
    

	@Override
	public void fuegeHinzu() {
		// alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden
        Object[] tmp1 = {"Begriff", ""};
        glossarJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Bedeutung", ""};
        glossarJTableModel.addRow(tmp2);
        // weitere Punkte fehlen
        glossarJTableModel.fireTableDataChanged();
		
	}
	
	@Override
	public void fuegeSimHinzu() {
		// alle Zeilen zur Tabelle hinzufuegen, die fuer ein Produktdatum gebraucht werden
		String BspBegriff = "Beispielbegriff" + Integer.toString(i);
		i ++;
        Object[] tmp1 = {"Begriff", BspBegriff};
        glossarJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Bedeutung", ""};
        glossarJTableModel.addRow(tmp2);
        // weitere Punkte fehlen
        glossarJTableModel.fireTableDataChanged();
		
	}

	@Override
	public void loesche() {
		// alle Zeilen entfernen, die zu einem Produktdatum gehoeren
        try {
            for (int i = 0; i < 3; i++) {
                glossarJTableModel.removeRow(glossarJTableModel.getRowCount() - 1);
            }
        } catch (Exception e) {

        }
        glossarJTableModel.fireTableDataChanged();
	}

	@Override
	public JTable getJTable() {
		return glossarJTable;
	}

	@Override
	public void setJTable(JTable jTable) {
		glossarJTable = jTable;
	}
    

}
