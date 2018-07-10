package view;

import basis.Resources;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewProduktfunktion extends AViewTableModule implements IViewModule {

    private JTable produktfunktionenJTable;
    private DefaultTableModel produktfunktionenJTableModel;
    private JButton fuegeFunktionenReiheHinzu;
    private JButton loescheFunktionenReihe;
    private int LFcount = 10;

    public ViewProduktfunktion(ActionListener actionListener, JPanel produktfunktionenJPanel) {
        super();
        // Inhalt der Prduktfunktionen erstellen
        this.produktfunktionenJTable = new JTable(0, 2);
        this.produktfunktionenJTableModel = (DefaultTableModel) produktfunktionenJTable.getModel();
        this.produktfunktionenJTable.getColumnModel().getColumn(0).setHeaderValue(Resources.bezeichnung);
        this.produktfunktionenJTable.getColumnModel().getColumn(1).setHeaderValue(Resources.wert);
        this.fuegeHinzu();

        // neue Buttons fuer die Tabelle
        this.fuegeFunktionenReiheHinzu = new JButton(Resources.fuegeProduktfunktionHinzu);
        this.loescheFunktionenReihe = new JButton(Resources.loescheProduktfunktion);

        // eigenes JPanel fuer die Buttons
        JPanel produktfunktionenButtons = new JPanel(new FlowLayout());
        produktfunktionenButtons.setMaximumSize(new Dimension(1000, 100));
        produktfunktionenButtons.add(fuegeFunktionenReiheHinzu);
        produktfunktionenButtons.add(loescheFunktionenReihe);

        // Buttons dem Listener zuordnen
        this.fuegeFunktionenReiheHinzu.addActionListener(actionListener);
        this.loescheFunktionenReihe.addActionListener(actionListener);

        produktfunktionenJPanel.setLayout(new BoxLayout(produktfunktionenJPanel, BoxLayout.Y_AXIS));
        produktfunktionenJPanel.add(produktfunktionenButtons);

        JScrollPane pane = new JScrollPane(produktfunktionenJTable);
        produktfunktionenJPanel.add(pane);

    }

    @Override
	public void fuegeHinzu() {
        // alle Zeilen zur Tabelle hinzufuegen, die fuer eine Funktion gebraucht werden
        Object[] tmp1 = {"/LF/", ""};
        this.produktfunktionenJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Funktion", ""};
        this.produktfunktionenJTableModel.addRow(tmp2);
        Object[] tmp3 = {"   Beschreibung", ""};
        this.produktfunktionenJTableModel.addRow(tmp3);
        Object[] tmp4 = {"   Akteur", ""};
        this.produktfunktionenJTableModel.addRow(tmp4);
        this.produktfunktionenJTableModel.fireTableDataChanged();
	}

	@Override
	public void fuegeSimHinzu() {
        // alle Zeilen zur Tabelle hinzufuegen, die fuer eine Funktion gebraucht werden
		String LFnummer = "/LF" + Integer.toString(LFcount) + "/";
		LFcount += 10;
        Object[] tmp1 = {"/LF/", LFnummer};
        this.produktfunktionenJTableModel.addRow(tmp1);
        Object[] tmp2 = {"   Funktion", "Testfunktion"};
        this.produktfunktionenJTableModel.addRow(tmp2);
        Object[] tmp3 = {"   Beschreibung", "lalalla"};
        this.produktfunktionenJTableModel.addRow(tmp3);
        Object[] tmp4 = {"   Akteur", "Max Mustermensch"};
        this.produktfunktionenJTableModel.addRow(tmp4);
        this.produktfunktionenJTableModel.fireTableDataChanged();
	}


	@Override
	public void loesche() {
		// alle Zeilen entfernen, die zu einer Funktion gehoeren
        try {
            for (int i = 0; i < 4; i++) {
                this.produktfunktionenJTableModel.removeRow(produktfunktionenJTableModel.getRowCount() - 1);
            }
        } catch (Exception e) {

        }
        this.produktfunktionenJTableModel.fireTableDataChanged();
		
	}


	@Override
	public JTable getJTable() {
		return this.produktfunktionenJTable;
	}


	@Override
	public void setJTable(JTable jTable) {
		this.produktfunktionenJTable = jTable;
		
	}


}
