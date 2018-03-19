package menu;

import global.Controller;
import global.CurrentUser;
import global.GlobalControlCodes;
import menu.view.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class MenuController extends Observable implements Observer, Controller, ActionListener {
    private MenuView menuView;

    public void initialiseController() {
        menuView = new MenuView();
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
        if (e.getActionCommand().equals(String.valueOf(GlobalControlCodes.LOG_OFF))) {
            CurrentUser currentUser = CurrentUser.getInstance();
            currentUser.logoutUser();
            setChanged();
            notifyObservers(GlobalControlCodes.LOG_OFF);
        }
        if (e.getActionCommand().equals(String.valueOf(GlobalControlCodes.SHARE))) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHARE);
        }
        if (e.getActionCommand().equals(String.valueOf(GlobalControlCodes.TRADE))) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRADE);
        }
    }

}
