package user;

import user.controller.LoginController;
import user.controller.RegisterController;
import user.model.LoginModel;
import user.model.RegisterModel;
import user.view.UserView;

public class UserController {
    public static void initialiseUserForm() {
        LoginController loginController = new LoginController();
        RegisterController registerController = new RegisterController();

        LoginModel loginModel = new LoginModel();
        RegisterModel registerModel = new RegisterModel();

        UserView view = new UserView();

        loginModel.addObserver(view);
        registerModel.addObserver(view);

        loginController.setLoginModel(loginModel);
        loginController.setUserView(view);

        registerController.setRegisterModel(registerModel);
        registerController.setUserView(view);

        view.setRegisterController(registerController);
        view.setLoginControl(loginController);

    }
}
