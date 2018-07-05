package control;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface IController extends ActionListener {
    public void processViewAction(ActionEvent action);
}
