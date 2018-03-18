package hub;

import hub.controller.LogoffController;
import hub.view.HubView;

import java.util.Observable;
import java.util.Observer;

public class HubController extends Observable implements Observer {
    private HubView hubView;
    private LogoffController logoffController;

    public void initialiseUI() {
        hubView = new HubView();
        logoffController = new LogoffController();
        hubView.setLogoffController(logoffController);
        logoffController.addObserver(this);
    }

    public void closeHubView() {
        hubView.hideHubeView();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("HubController: " + arg);
        setChanged();
        notifyObservers(arg);
    }
}
