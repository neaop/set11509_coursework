package broker;

import broker.view.BrokerView;
import global.controller.Controller;
import global.controller.GlobalControlCodes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class BrokerController extends Observable implements Controller, ActionListener {
    private BrokerView brokerView;

    public void initialiseController() {
        brokerView = new BrokerView();
        addListener();
    }

    private void addListener() {
        brokerView.addActionListeners(this);
    }

    public void showView() {
        brokerView.showView();
    }

    public void closeView() {
        brokerView.closeView();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.equals(GlobalControlCodes.BROKER_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.BROKER_CLOSE);
        }
    }
}
