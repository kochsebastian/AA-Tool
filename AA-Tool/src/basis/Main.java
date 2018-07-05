package basis;


import control.Control;
import view.View;

/**
 * @author SebastianKoch
 */
public class Main {


    /**
     * @param args
     */
    public static void main(String[] args) {

        Control control = new Control();
        View view = View.getInstanz(control);
        control.setView(view);


    }

}
