package menu;

import menu.controller.LogoffController;
import menu.controller.ShareController;
import menu.controller.TradeController;
import menu.view.MenuView;

import java.util.Observable;
import java.util.Observer;

public class MenuController extends Observable implements Observer {
    private MenuView hubView;
    private LogoffController logoffController;
    private ShareController shareController;
    private TradeController tradeController;

    public void initialiseUI() {
        hubView = new MenuView();
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
        System.out.println("MenuController: " + arg);
        setChanged();
        notifyObservers(arg);
    }
}
