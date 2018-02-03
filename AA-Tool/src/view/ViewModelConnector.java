
package view;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import model.ATab;
import model.Inhalt;
import model.Model;
import model.Tab;

/**
 * 
 * @author SebastianKoch
 *
 */
public class ViewModelConnector {

    View view;
    
    ViewModelConnector(View _view) {
        view = _view;
    }
    
    // schreibe alle Daten aus dem Model in die Textfelder/Tabellen/Elemente des GUI
    public void leseViewAusModel() {
        Model model = Model.getInstanz();
        
        ATab aktuellerTab = model.getRoot();
        
        while(aktuellerTab.getNext() != null) {
            ArrayList <ATab> next = aktuellerTab.getNext();
            
            for(int j = 0; j < next.size(); j++) {
                if(next.get(j) instanceof Inhalt) {
                    
                    Inhalt tmpNext = (Inhalt) next.get(j);
                    
                    setInhalt(tmpNext.getName(), tmpNext.getInhalt());
                }
            }
                        
            for(int j = 0; j < next.size(); j++) {
                                
                if(next.get(j) instanceof Tab || next.get(j) instanceof Inhalt) {
                    aktuellerTab = next.get(j);
                }
            }
        }
    }
    
    // gib je nach Tabnamen den passenden Inhalt davon aus dem GUI zurueck
    public String[][] getInhalt(String name) {
        if(name.matches("Deckblatt")) {
            return getDeckblattInhalt();
        }
        if(name.matches("Kundenbeschreibung")) {
            return getKundenbeschreibungInhalt();
        }
        if(name.matches("Zielbestimmung")) {
            return getZielbestimmungInhalt();
        }
        if(name.matches("Produkteinsatz")) {
            return getProdukteinsatzInhalt();
        }
        if(name.matches("Produktfunktionen")) {
            return getProduktfunktionenInhalt();
        }
        if(name.matches("Produktdaten")) {
            return getProduktdatenInhalt();
        }
        if(name.matches("Produktleistungen")) {
            return getProduktleistungenInhalt();
        }
        if(name.matches("Qualitätsanforderungen")) {
            return getQualitaetsanforderungenInhalt();
        }
        if(name.matches("Ergänzungen")) {
            return getErgaenzungenInhalt();
        }
        if(name.matches("Glossar")) {
            return getGlossarInhalt();
        }
        if(name.matches("Aufwandsabschätzung")) {
            return getAufwandsabschaetzungInhalt();
        }
        
        return null;
    }
    
    // setze je nach Tabnamen den passenden Inhalt des GUI
    public void setInhalt(String name, String[][] inhalt) {
        if(name.matches("Deckblatt")) {
            setDeckblattInhalt(inhalt);
        }
        if(name.matches("Kundenbeschreibung")) {
            setKundenbeschreibungInhalt(inhalt);
        }
        if(name.matches("Zielbestimmung")) {
            setZielbestimmungInhalt(inhalt);
        }
        if(name.matches("Produkteinsatz")) {
            setProdukteinsatzInhalt(inhalt);
        }
        if(name.matches("Produktfunktionen")) {
            setProduktfunktionenInhalt(inhalt);
        }
        if(name.matches("Produktdaten")) {
            setProduktdatenInhalt(inhalt);
        }
        if(name.matches("Produktleistungen")) {
            setProduktleistungenInhalt(inhalt);
        }
        if(name.matches("Qualitätsanforderungen")) {
            setQualitaetsanforderungenInhalt(inhalt);
        }
        if(name.matches("Ergänzungen")) {
            setErgaenzungenInhalt(inhalt);
        }
        if(name.matches("Glossar")) {
            setGlossarInhalt(inhalt);
        }
        if(name.matches("Aufwandsabschätzung")) {
            setAufwandsabschaetzungInhalt(inhalt);
        }
    }
    
    // jeweilige angepasste Implementierung des Rueckgebens und Setzens des Inhaltes aus dem/in das GUI
    private String[][] getDeckblattInhalt() {
        String tmpString[][] = new String[1][1];
        tmpString[0][0] = view.getDeckblattTextField().getText();
        
        return tmpString;
    }
    
    private void setDeckblattInhalt(String[][] text) {
        view.getDeckblattTextField().setText(text[0][0]);
    }
    
    private String[][] getZielbestimmungInhalt() {
        String tmpString[][] = new String[1][1];
        tmpString[0][0] =view.getZielbestimmungTextField().getText();
        
        return tmpString;
    }
    
    private void setZielbestimmungInhalt(String[][] text) {
        view.getZielbestimmungTextField().setText(text[0][0]);
    }
    
    private String[][] getProdukteinsatzInhalt() {
        String tmpString[][] = new String[1][1];
        tmpString[0][0] = view.getProdukteinsatzTextField().getText();
        
        return tmpString;
    }
    
    private void setProdukteinsatzInhalt(String[][] text) {
        view.getProdukteinsatzTextField().setText(text[0][0]);
    }
    
    private String[][] getKundenbeschreibungInhalt() {
        String tmpString[][] = new String[1][1];
        tmpString[0][0] = view.getKundenbeschreibung().getText();
        
        return tmpString;
    }
    
    private void setKundenbeschreibungInhalt(String[][] text) {
        view.getKundenbeschreibung().setText(text[0][0]);
    }
    
    private String[][] getErgaenzungenInhalt() {
        String tmpString[][] = new String[1][1];
        tmpString[0][0] = view.getErgaenzungenTextField().getText();
        
        return tmpString;
    }
    
    private void setErgaenzungenInhalt(String[][] text) {
        view.getErgaenzungenTextField().setText(text[0][0]);
    }
    
    
    
    private String[][] getProduktfunktionenInhalt() {
                
        DefaultTableModel tableModel = (DefaultTableModel) view.getProduktfunktionenJTable().getModel();
                
        String tmpString[][] = new String[tableModel.getRowCount()][tableModel.getColumnCount()];
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                if (tableModel.getValueAt(i, j) != null) {
                    tmpString[i][j] = tableModel.getValueAt(i, j).toString();
               }
            }
        }
        return tmpString;
    }
    
    private void setProduktfunktionenInhalt(String tmpString[][]) {
        
        DefaultTableModel tableModel = (DefaultTableModel) view.getProduktfunktionenJTable().getModel();
        
        // alle Reihen weg
        for (int i = 0; i < tmpString.length; i++) {
            tableModel.removeRow(tableModel.getRowCount()-1);
        }
        // alle noetigen Reihen hinzu
        for (int i = 0; i < tmpString.length; i+=4) {
            
            if(!(tmpString[i][1].matches("") && tmpString[i+1][1].matches("") && tmpString[i+2][1].matches("") &&
                 tmpString[i+3][1].matches(""))) {
            		
                Object[] tmp1 = {"/LF/", ""};
                tableModel.addRow(tmp1);
                Object[] tmp2 = {"   Funktion", ""};
                tableModel.addRow(tmp2);
                Object[] tmp3 = {"   Akteure", ""};
                tableModel.addRow(tmp3);
                Object[] tmp4 = {"   Beschreibung", ""};            
                tableModel.addRow(tmp4);
                
            }
        }
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(tmpString[i][j], i, j);
            }
        }
        tableModel.fireTableDataChanged();
    }
    
    private String[][] getProduktdatenInhalt() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getProduktdatenJTable().getModel();
                
        String tmpString[][] = new String[tableModel.getRowCount()][tableModel.getColumnCount()];
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                if (tableModel.getValueAt(i, j) != null) {
                    tmpString[i][j] = tableModel.getValueAt(i, j).toString();
               }
            }
        }
        return tmpString;
    }
    
    private void setProduktdatenInhalt(String tmpString[][]) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getProduktdatenJTable().getModel();
        
        // alle Reihen weg
        for (int i = 0; i < tmpString.length; i++) {
            tableModel.removeRow(tableModel.getRowCount()-1);
        }
        
        // alle noetigen Reihen hinzu
        for (int i = 0; i < tmpString.length; i+=3) {
            
            if(!(tmpString[i][1].matches("") && tmpString[i+1][1].matches("") && tmpString[i+2][1].matches(""))) {
                Object[] tmp1 = {"/LD/", ""};
                tableModel.addRow(tmp1);
                Object[] tmp2 = {"   Name", ""};
                tableModel.addRow(tmp2);
                Object[] tmp3 = {"   Beschreibung", ""};
                tableModel.addRow(tmp3);
            }            
        }
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(tmpString[i][j], i, j);
            }
        }
        tableModel.fireTableDataChanged();
    }
    
    private String[][] getProduktleistungenInhalt() {
    		DefaultTableModel tableModel = (DefaultTableModel) view.getProduktleistungenJTable().getModel();
        
        String tmpString[][] = new String[tableModel.getRowCount()][tableModel.getColumnCount()];
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                if (tableModel.getValueAt(i, j) != null) {
                    tmpString[i][j] = tableModel.getValueAt(i, j).toString();
               }
            }
        }
        return tmpString;
    }
    
    private void setProduktleistungenInhalt(String tmpString[][]) {
    		DefaultTableModel tableModel = (DefaultTableModel) view.getProduktleistungenJTable().getModel();
        
        // alle Reihen weg
        for (int i = 0; i < tmpString.length; i++) {
            tableModel.removeRow(tableModel.getRowCount()-1);
        }
        
        // alle noetigen Reihen hinzu
        for (int i = 0; i < tmpString.length; i+=3) {
            
            if(!(tmpString[i][1].matches("") && tmpString[i+1][1].matches("") && tmpString[i+2][1].matches(""))) {
            		Object[] tmp1 = {"/LE/", ""};
                tableModel.addRow(tmp1);
                Object[] tmp2 = {"   Name", ""};
                tableModel.addRow(tmp2);
                Object[] tmp3 = {"   Beschreibung", ""};
                tableModel.addRow(tmp3);
            }            
        }
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(tmpString[i][j], i, j);
            }
        }
        tableModel.fireTableDataChanged();
    }
    
    private String[][] getQualitaetsanforderungenInhalt() {
    		DefaultTableModel tableModel = (DefaultTableModel) view.getQualitaetsanforderungenJTable().getModel();
        
        String tmpString[][] = new String[tableModel.getRowCount()][tableModel.getColumnCount()];
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                if (tableModel.getValueAt(i, j) != null) {
                    tmpString[i][j] = tableModel.getValueAt(i, j).toString();
               }
            }
        }
        return tmpString;
    }    

    private void setQualitaetsanforderungenInhalt(String tmpString[][]) {
    		DefaultTableModel tableModel = (DefaultTableModel) view.getQualitaetsanforderungenJTable().getModel();
        
        // alle Reihen weg
        for (int i = 0; i < tmpString.length; i++) {
            tableModel.removeRow(tableModel.getRowCount()-1);
        }
        
        // alle noetigen Reihen hinzu
        for (int i = 0; i < tmpString.length; i+=2) {
            
            if(!(tmpString[i][1].matches("") && tmpString[i+1][1].matches(""))) {
                Object[] tmp1 = {"/Q /", ""};
                tableModel.addRow(tmp1);
                Object[] tmp2 = {"   Name", ""};
                tableModel.addRow(tmp2);
                
            }            
        }
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(tmpString[i][j], i, j);
            }
        }
        tableModel.fireTableDataChanged();
    }
    

    
    private void setGlossarInhalt(String tmpString[][]) {
    		DefaultTableModel tableModel = (DefaultTableModel) view.getGlossarJTable().getModel();
        
        // alle Reihen weg
        for (int i = 0; i < tmpString.length; i++) {
            tableModel.removeRow(tableModel.getRowCount()-1);
        }
        
        // alle noetigen Reihen hinzu
        for (int i = 0; i < tmpString.length; i+=2) {
            
            if(!(tmpString[i][1].matches("") && tmpString[i+1][1].matches(""))) {
                Object[] tmp1 = {"/G /", ""};
                tableModel.addRow(tmp1);
                Object[] tmp2 = {"   Name", ""};
                tableModel.addRow(tmp2);
                
            }            
        }
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(tmpString[i][j], i, j);
            }
        }
        tableModel.fireTableDataChanged();
    }
    
    private String[][] getGlossarInhalt() {
    		DefaultTableModel tableModel = (DefaultTableModel) view.getGlossarJTable().getModel();
        
        String tmpString[][] = new String[tableModel.getRowCount()][tableModel.getColumnCount()];
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                if (tableModel.getValueAt(i, j) != null) {
                    tmpString[i][j] = tableModel.getValueAt(i, j).toString();
               }
            }
        }
        return tmpString;
    }
    
    private String[][] getAufwandsabschaetzungInhalt() {
        String tmpString[][] = new String[1][1];
        tmpString =view.getAufwandsabschaetzung().getInhalt();
        
        return tmpString;
    }
    
    private void setAufwandsabschaetzungInhalt(String[][] text) {
        view.getAufwandsabschaetzung().setInhalt(text);
    }
    
    
    
}