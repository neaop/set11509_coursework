package menu.controller;

import global.CurrentUser;
import global.GlobalControlCodes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class LogoffController extends Observable implements ActionListener {
    private CurrentUser currentUser;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("LogoffController: Log Off pressed");
        currentUser = CurrentUser.getInstance();
        currentUser.logoutUser();
        setChanged();
        notifyObservers(GlobalControlCodes.LOG_OFF);
    }
}
