package user;

import global.Controller;
import global.GlobalControlCodes;
import user.model.LoginModel;
import user.model.RegisterModel;
import user.view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class UserController extends Observable implements Observer, Controller, ActionListener {
    private UserView userView;
    private LoginModel loginModel;
    private RegisterModel registerModel;

    public void initialiseController() {
        loginModel = new LoginModel();
        registerModel = new RegisterModel();
        userView = new UserView();
        linkMVC();
        addListeners();
    }

    private void addListeners() {
        userView.setActionListener(this);
    }

    private void linkMVC() {
        loginModel.addObserver(userView);
        registerModel.addObserver(userView);
        loginModel.addObserver(this);
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
        if (e.getActionCommand().equals(
                String.valueOf(GlobalControlCodes.LOG_IN))) {
            loginModel.authenticate(userView.getUserName()
                    , userView.getUserPassword());
        }
        if (e.getActionCommand().equals(
                String.valueOf(GlobalControlCodes.REGISTERED))) {
            registerModel.attemptRegisterUser(userView.getUserName()
                    , userView.getUserPassword());
        }
    }
}
