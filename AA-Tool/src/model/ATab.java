
package model;

import java.util.ArrayList;

/**
 * 
 * @author SebastianKoch
 *
 */
public abstract class ATab {
    // Composite Design Pattern, jedoch hat jeder Reiter nur ein Blatt und ein Kompositum
    private ArrayList <ATab> next;
    
    private String tabName;
    
    public ATab(String name) {
        this.tabName = name;
    }
    
    public ArrayList <ATab> getNext() {
        return next;
    }
    
    public void addNext(ATab _next) {
        if(next == null) {
            next = new ArrayList <ATab>();
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
