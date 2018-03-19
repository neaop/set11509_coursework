package menu;

import global.Controller;
import menu.controller.LogoffController;
import menu.controller.ShareController;
import menu.controller.TradeController;
import menu.view.MenuView;

import java.util.Observable;
import java.util.Observer;

public class MenuController extends Observable implements Observer, Controller {
    private MenuView menuView;
    private LogoffController logoffController;
    private ShareController shareController;
    private TradeController tradeController;

    @Override
    public void initialiseUi() {
        menuView = new MenuView();
        logoffController = new LogoffController();
        shareController = new ShareController();
        tradeController = new TradeController();

        addControllers();
        observeControllers();
    }

    @Override
    public void showUi() {
        menuView.showView();
    }

    @Override
    public void closeUi() {
        menuView.hideHubeView();
    }

    private void addControllers() {
        menuView.setLogoffController(logoffController);
        menuView.setShareController(shareController);
        menuView.setTradeController(tradeController);
    }

    private void observeControllers() {
        logoffController.addObserver(this);
        shareController.addObserver(this);
        tradeController.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("MenuController: " + arg);
        setChanged();
        notifyObservers(arg);
    }


}
