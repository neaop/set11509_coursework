package user.controller;

import user.model.RegisterModel;
import user.view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    private RegisterModel model;
    private UserView view;

    @Override
    public void actionPerformed(ActionEvent e) {
        model.attemptRegisterUser(view.getUserName(), view.getUserPassword());
    }

    public void setModel(RegisterModel model) {
        this.model = model;
    }

    public void setView(UserView view) {
        this.view = view;
    }
}
