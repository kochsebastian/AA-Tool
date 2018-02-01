
package model;

/**
 * 
 * @author SebastianKoch
 *
 */
public class Inhalt extends ATab {
    private String[][] inhalt;

    public Inhalt(String name) {
        super(name);
    }
    
    public String[][] getInhalt() {
        return inhalt;
    }
    
    public void setInhalt(String[][] inhalt) {
        this.inhalt = inhalt;
    }
}
