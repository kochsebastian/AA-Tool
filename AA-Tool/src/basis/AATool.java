package basis;


import control.Control;
import view.View;

/**
 * @author SebastianKoch
 */
public class AATool {


    /**
     * @param args
     */
    public static void main(String[] args) {

        Control control = new Control();
        View view = View.getInstanz(control);
        control.setView(view);


    }

}
