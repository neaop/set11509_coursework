package user.controller;

import user.model.LoginModel;
import user.view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginModel model;
    private UserView view;

    @Override
    public void actionPerformed(ActionEvent e) {
        model.authenticate(view.getUserName(), view.getUserPassword());
    }

    public void setModel(LoginModel model) {
        this.model = model;
    }

    public void setView(UserView view) {
        this.view = view;
    }

    public static enum RESULT {
        NO_SUCH_USER, INVALID_CREDENTIAL, USER_EXISTS, REGISTERED, LOG_IN, FAILED

    }
}
