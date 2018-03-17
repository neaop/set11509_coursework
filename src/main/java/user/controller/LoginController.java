package user.controller;

import user.model.LoginModel;
import user.view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginModel loginModel;
    private UserView userView;

    @Override
    public void actionPerformed(ActionEvent e) {
        loginModel.authenticate(userView.getUserName()
                , userView.getUserPassword());
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

}
