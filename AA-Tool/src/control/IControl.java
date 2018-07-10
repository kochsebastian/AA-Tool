package control;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import view.View;

public interface IControl extends ActionListener {
	public void setView(View view);
	public void exportiere(File datei);
	public void importiere(File datei);
    public void processViewAction(ActionEvent action);
}
