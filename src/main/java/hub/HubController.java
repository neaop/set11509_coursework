package hub;

import hub.controller.LogoffController;
import hub.controller.ShareController;
import hub.controller.TradeController;
import hub.view.HubView;

import java.util.Observable;
import java.util.Observer;

public class HubController extends Observable implements Observer {
    private HubView hubView;
    private LogoffController logoffController;
    private ShareController shareController;
    private TradeController tradeController;

    public void initialiseUI() {
        hubView = new HubView();
        logoffController = new LogoffController();
        shareController = new ShareController();
        tradeController = new TradeController();

        addControllers();
        observeControllers();
    }

    private void addControllers() {
        hubView.setLogoffController(logoffController);
        hubView.setShareController(shareController);
        hubView.setTradeController(tradeController);
    }

    private void observeControllers() {
        logoffController.addObserver(this);
        shareController.addObserver(this);
        tradeController.addObserver(this);
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
