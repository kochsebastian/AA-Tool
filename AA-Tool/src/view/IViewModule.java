package view;

import javax.swing.*;

public interface IViewModule {
    public void fuegeHinzu();
    
    public void fuegeSimHinzu();

    public void loesche();

    public JTable getJTable();

    public void setJTable(JTable jTable);
}
