package view;

import javax.swing.*;

/**
 * 
 * @author SebastianKoch
 *
 */
public interface IViewModule extends UserSimulation {
    public void fuegeHinzu();

    public void loesche();

    public JTable getJTable();

    public void setJTable(JTable jTable);
}
