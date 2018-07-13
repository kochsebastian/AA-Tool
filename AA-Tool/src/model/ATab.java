package model;


import java.util.ArrayList;

/**
 * Stellt Tabfunktionen zur verfuegung
 * @author SebastianKoch, ChrisBoger, AnneBlomeier
 */
public abstract class ATab {

    private ArrayList<ATab> next;

    private String tabName;

    public ATab(String name) {
        this.tabName = name;
    }

    public ArrayList<ATab> getNext() {
        return next;
    }
    /**
     * Verkettung der Tabs
     * @param _next
     */
    public void addNext(ATab _next) { 
        if (next == null) {
            next = new ArrayList<ATab>();
        }
        next.add(_next);
    }

    public String getName() {
        return tabName;
    }

    public void setName(String tabName) {
        this.tabName = tabName;
    }
}
