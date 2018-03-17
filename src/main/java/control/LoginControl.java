package control;

import model.LoginModel;
import view.LoginForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginControl implements ActionListener {
    private LoginModel model;
    private LoginForm view;

    @Override
    public void actionPerformed(ActionEvent e) {
        model.authenticate(view.getUserName(), view.getUserPassword());
    }

    public void setModel(LoginModel model) {
        this.model = model;
    }

    public void setView(LoginForm view) {
        this.view = view;
    }
}
