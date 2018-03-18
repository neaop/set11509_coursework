package hub.controller;

import controller.GlobalControlCodes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ShareController extends Observable implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ShareController: Share button pressed");
        setChanged();
        notifyObservers(GlobalControlCodes.SHARE);
    }
}
