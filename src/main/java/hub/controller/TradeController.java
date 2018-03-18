package hub.controller;

import global.GlobalControlCodes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class TradeController extends Observable implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("TradeController: Trade button clicked");
        setChanged();
        notifyObservers(GlobalControlCodes.TRADE);
    }
}
