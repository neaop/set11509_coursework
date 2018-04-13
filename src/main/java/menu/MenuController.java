package menu;

import global.CurrentUserModel;
import global.GlobalControlCodes;
import global.controller.Controller;
import menu.view.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Controller for the main menu module.
 */
public class MenuController extends Observable implements
        Observer, Controller, ActionListener, Serializable {
    private MenuView menuView;

    @Override
    public void initialiseController() {
        menuView = new MenuView();
        addListeners();
    }

    @Override
    public void showView() {
        menuView.showView();
    }

    @Override
    public void closeView() {
        menuView.closeView();
    }

    /**
     * Sets the Controller as the View's listener.
     */
    private void addListeners() {
        menuView.setActionListeners(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("MenuController: " + arg);
        setChanged();
        notifyObservers(arg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.LOG_OFF.name())) {
            CurrentUserModel currentUser = CurrentUserModel.getInstance();
            currentUser.logoutUser();
            setChanged();
            notifyObservers(GlobalControlCodes.LOG_OFF);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.SHARE_OPEN.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHARE_OPEN);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.TRADE_OPEN.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRADE_OPEN);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.BROKER_OPEN.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.BROKER_OPEN);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.SHAREHOLDER_OPEN.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHAREHOLDER_OPEN);
        }
    }

}
