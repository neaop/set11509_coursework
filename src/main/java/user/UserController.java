package user;

import user.controller.LoginController;
import user.controller.RegisterController;
import user.model.LoginModel;
import user.model.RegisterModel;
import user.view.UserView;

public class UserInit {
    public static void initialiseUserForm() {
        LoginController loginController = new LoginController();
        RegisterController registerController = new RegisterController();

        LoginModel loginModel = new LoginModel();
        RegisterModel registerModel = new RegisterModel();

        UserView view = new UserView();

        loginModel.addObserver(view);
        registerModel.addObserver(view);

        loginController.setModel(loginModel);
        loginController.setView(view);

        registerController.setModel(registerModel);
        registerController.setView(view);

        view.setRegisterController(registerController);
        view.setLoginControl(loginController);

    }
}
