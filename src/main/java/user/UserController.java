package user;

import global.controller.Controller;
import global.GlobalControlCodes;
import user.model.LoginModel;
import user.model.RegisterModel;
import user.view.NewUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class UserController extends Observable implements Observer, Controller, ActionListener {
    private NewUserView userView;
    private LoginModel loginModel;
    private RegisterModel registerModel;

    public void initialiseController() {
        loginModel = new LoginModel();
        registerModel = new RegisterModel();
        userView = new NewUserView();
        linkMVC();
        setActionListeners();
    }

    private void setActionListeners() {
        userView.setActionListeners(this);
        loginModel.addObserver(this);
    }

    private void linkMVC() {
        loginModel.addObserver(userView);
        registerModel.addObserver(userView);
    }

    public void showView() {
        userView.showView();
    }

    public void closeView() {
        userView.closeView();
    }

    public void update(Observable o, Object arg) {
        System.out.println("UserController: " + arg);
        setChanged();
        notifyObservers(arg);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.LOG_IN.name())) {
            loginModel.authenticate(userView.getUserName()
                    , userView.getUserPassword());
        }
        if (e.getActionCommand().equals(GlobalControlCodes.REGISTERED.name())) {
            registerModel.attemptRegisterUser(userView.getUserName()
                    , userView.getUserPassword());
        }
        if (e.getActionCommand().equals(GlobalControlCodes.EXIT.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.EXIT);
        }
    }
}
