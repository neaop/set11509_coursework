package hub.controller;

import global.GlobalControlCodes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class LogoffController extends Observable implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("LogoffController: Log Off pressed");
        setChanged();
        notifyObservers(GlobalControlCodes.LOG_OFF);
    }
}
