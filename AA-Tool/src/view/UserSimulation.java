/**
 * 
 */
package view;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import basis.Resources;

/**
 * @author SebastianKoch
 *
 */
public abstract class UserSimulation {

	public JTextArea setTextArea(String simString) {
		return new JTextArea(simString);
	}
	public static void setLFTable(int indexLF) {
	  JTable produktfunktionenJTable ;
	  DefaultTableModel produktfunktionenJTableModel ;
	  produktfunktionenJTable = new JTable(0, 2);
      produktfunktionenJTableModel = (DefaultTableModel) produktfunktionenJTable.getModel();
      produktfunktionenJTable.getColumnModel().getColumn(0).setHeaderValue(Resources.bezeichnung);
      produktfunktionenJTable.getColumnModel().getColumn(1).setHeaderValue(Resources.wert);
      String indexStringLF = Integer.toString(indexLF);
	  Object[] tmp1 = {"/LF/", indexStringLF};
      produktfunktionenJTableModel.addRow(tmp1);
      Object[] tmp2 = {"   Funktion", "xx"};
      produktfunktionenJTableModel.addRow(tmp2);
      Object[] tmp3 = {"   Beschreibung", "User Beschreibung"};
      produktfunktionenJTableModel.addRow(tmp3);
      Object[] tmp4 = {"   Akteur", "Max Mustermann"};
      produktfunktionenJTableModel.addRow(tmp4);
      produktfunktionenJTableModel.fireTableDataChanged();
     // return produktfunktionenJTable;
	}

}
