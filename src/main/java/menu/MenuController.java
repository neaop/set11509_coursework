package menu;

import global.controller.Controller;
import global.controller.GlobalControlCodes;
import global.model.CurrentUser;
import menu.view.NewMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class MenuController extends Observable implements Observer, Controller, ActionListener {
    private NewMenuView menuView;

    public void initialiseController() {
        menuView = new NewMenuView();
        addListeners();
    }

    @Override
    public void showView() {
        menuView.showView();
    }

    public void closeView() {
        menuView.closeView();
    }

    private void addListeners() {
        menuView.setActionListeners(this);
    }

    public void update(Observable o, Object arg) {
        System.out.println("MenuController: " + arg);
        setChanged();
        notifyObservers(arg);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.LOG_OFF.name())) {
            CurrentUser currentUser = CurrentUser.getInstance();
            currentUser.logoutUser();
            setChanged();
            notifyObservers(GlobalControlCodes.LOG_OFF);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.SHARE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHARE);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.TRADE_OPEN.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRADE_OPEN);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.BROKER_OPEN.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.BROKER_OPEN);
        }
    }

}
