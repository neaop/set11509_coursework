package user;

import global.GlobalControlCodes;
import global.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * The Controller element of the user module.
 */
public class UserController extends Observable
        implements Observer, Controller, ActionListener, Serializable {
    private UserView userView;
    private LoginModel loginModel;
    private RegisterModel registerModel;

    @Override
    public void initialiseController() {
        loginModel = new LoginModel();
        registerModel = new RegisterModel();
        userView = new UserView();
        linkMVC();
        setActionListeners();
    }

    /**
     * Connect share Model and View.
     */
    private void linkMVC() {
        loginModel.addObserver(userView);
        registerModel.addObserver(userView);
    }

    @Override
    public void showView() {
        userView.showView();
    }

    @Override
    public void closeView() {
        userView.closeView();
    }

    /**
     * Set this Controller as the View's listener.
     */
    private void setActionListeners() {
        userView.setActionListeners(this);
        loginModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UserController: " + arg);
        setChanged();
        notifyObservers(arg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.LOG_IN.name())) {
            loginModel.authenticate(
                    userView.getUserName(), userView.getUserPassword());
        }
        if (e.getActionCommand().equals(UserControlCodes.REGISTER.name())) {
            registerModel.attemptRegisterUser(
                    userView.getUserName(), userView.getUserPassword());
        }
        if (e.getActionCommand().equals(GlobalControlCodes.EXIT.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.EXIT);
        }
    }
}
