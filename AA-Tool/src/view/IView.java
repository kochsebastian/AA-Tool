package view;

import java.awt.event.ActionListener;

import java.util.Observer;

public interface IView extends Observer
{
    public void displayView(ActionListener actionListener);
    public void closeWindow();

}
