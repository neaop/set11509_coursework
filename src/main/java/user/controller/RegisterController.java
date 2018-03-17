package user.controller;

import user.model.RegisterModel;
import user.view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    private RegisterModel registerModel;
    private UserView userView;

    @Override
    public void actionPerformed(ActionEvent e) {
        registerModel.attemptRegisterUser(userView.getUserName()
                , userView.getUserPassword());
    }

    public void setRegisterModel(RegisterModel registerModel) {
        this.registerModel = registerModel;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }
}
