package main;

import control.LoginControl;
import data.DatabaseConnection;
import data.DatabaseConnector;
import model.LoginModel;
import view.LoginForm;

import java.sql.SQLException;

public class Main {
    private static String name = "test";
    private static String pass = "test";

    public static void main(String[] args) throws SQLException {
        LoginControl control = new LoginControl();
        LoginForm view = new LoginForm();
        LoginModel model = new LoginModel();
        DatabaseConnector connector = new DatabaseConnection();
        connector.connect();

        model.addObserver(view);
        model.setConnection(connector);

        control.setModel(model);
        control.setView(view);

        view.setLoginControl(control);
//        model.authenticate(name,pass);
//        control.actionPerformed(null);


    }
}
