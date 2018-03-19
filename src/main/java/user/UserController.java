package user;

import global.Controller;
import user.controller.LoginController;
import user.controller.RegisterController;
import user.model.LoginModel;
import user.model.RegisterModel;
import user.view.UserView;

import java.util.Observable;
import java.util.Observer;

public class UserController extends Observable implements Observer, Controller {
    private UserView userView;
    private LoginModel loginModel;
    private LoginController loginController;
    private RegisterModel registerModel;
    private RegisterController registerController;

    @Override
    public void initialiseUi() {
        loginController = new LoginController();
        registerController = new RegisterController();

        loginModel = new LoginModel();
        registerModel = new RegisterModel();

        userView = new UserView();

        loginModel.addObserver(userView);
        loginModel.addObserver(this);
        registerModel.addObserver(userView);

        loginController.setLoginModel(loginModel);
        loginController.setUserView(userView);

        registerController.setRegisterModel(registerModel);
        registerController.setUserView(userView);

        userView.setRegisterController(registerController);
        userView.setLoginControl(loginController);
    }

    @Override
    public void showUi() {
        userView.showUi();
    }

    @Override
    public void closeUi() {
        userView.hideUserView();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UserController: " + arg);
        setChanged();
        notifyObservers(arg);
    }
}
