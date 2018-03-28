package user.model;

import data.DatabaseConnection;
import data.DatabaseConnector;
import global.CurrentUser;
import global.GlobalControlCodes;
import user.UserControlCodes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel extends java.util.Observable {
    private DatabaseConnector connection;
    private CurrentUser currentUser;

    public LoginModel() {
        connection = new DatabaseConnection();
    }

    public void authenticate(String name, String password) {
        Enum result;
        boolean userExists = false;
        try {
            userExists = checkValidUser(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userExists) {
            System.out.println("LoginModel: User authenticated");
            result = GlobalControlCodes.LOG_IN;
        } else {
            System.out.println("LoginModel: Username / Password not recognized");
            result = UserControlCodes.NO_SUCH_USER;
        }
        setChanged();
        notifyObservers(result);
    }

    private boolean checkValidUser(String name, String password) throws SQLException {
        connection.connect();

        ResultSet result = connection.query(generateLookupQuery(name
                , password));

        if (result.next()) {
            int userId = result.getInt(1);
            String userName = result.getString(2);
            boolean userAdmin = result.getBoolean(4);
            currentUser = CurrentUser.getInstance();
            currentUser.loginUser(userId, userName, userAdmin);

            connection.closeConnection();
            return true;
        } else {
            connection.closeConnection();
            return false;
        }
    }

    private String generateLookupQuery(String name, String password) {
        return String.format("SELECT * " +
                        "FROM user " +
                        "WHERE user_name ='%1$s' " +
                        "AND user_password = '%2$s'"
                , name, password);
    }
}
