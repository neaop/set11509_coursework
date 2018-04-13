package broker;

import broker.model.BrokerModel;
import broker.view.BrokerViewForm;
import global.GlobalControlCodes;
import global.controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;

/**
 * Controller class for the broker module.
 */
public class BrokerController extends Observable
        implements Controller, ActionListener, Serializable {
    private BrokerViewForm brokerView;
    private BrokerModel brokerModel;

    @Override
    public void initialiseController() {
        brokerView = new BrokerViewForm();
        brokerModel = new BrokerModel();

        linkMVC();
        addListener();
        brokerModel.queryBrokers();
    }

    /**
     * Connects the Model to the View.
     */
    private void linkMVC() {
        brokerModel.addObserver(brokerView);
    }

    /**
     * Sets the Controller as the View's listener.
     */
    private void addListener() {
        brokerView.addActionListeners(this);
    }

    @Override
    public void showView() {
        brokerView.showView();
    }

    @Override
    public void closeView() {
        brokerView.closeView();
    }

    /**
     * Extracts the selected broker from the View.
     *
     * @return the name of the currently selected broker.
     */
    public String getSelectedBrokerName() {
        return brokerView.getBrokerName();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.BROKER_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.BROKER_CLOSE);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.BROKER_HISTORY.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.BROKER_HISTORY);
        }
    }
}
