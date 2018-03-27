package broker;

import broker.model.BrokerModel;
import broker.view.BrokerView;
import global.controller.Controller;
import global.controller.GlobalControlCodes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class BrokerController extends Observable implements Controller, ActionListener {
    private BrokerView brokerView;
    private BrokerModel brokerModel;

    public void initialiseController() {
        brokerView = new BrokerView();
        brokerModel = new BrokerModel();

        linkMVC();
        addListener();
        brokerModel.queryBrokers();
    }

    private void linkMVC() {
        brokerModel.addObserver(brokerView);
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
        if (e.getActionCommand().equals(GlobalControlCodes.BROKER_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.BROKER_CLOSE);
        }
    }
}
