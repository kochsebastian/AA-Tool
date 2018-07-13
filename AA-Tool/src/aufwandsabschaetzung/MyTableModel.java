package aufwandsabschaetzung;

import javax.swing.table.DefaultTableModel;

/**
 * TabellenModel für die Tabellen der Aufwandsabschaetzung
 * @author ChrisBoger, SebastianKoch, AnneBlomeier
 *
 */

@SuppressWarnings("serial")
class MyTableModel extends DefaultTableModel {   

    public MyTableModel(Object[] strings, int i) {
		super(strings, i);
	}

    /*
     * dafür da, dass Checkboxen statt Text (True/False) da steht
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Block the first column to edit
     */
    public boolean isCellEditable(int row, int col) {
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }
}
