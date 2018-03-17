package user.model;

import data.DatabaseConnection;
import data.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel extends java.util.Observable {

    private int userId;
    private String userName;
    private Boolean userAdmin;
    private boolean authenticated;
    private DatabaseConnector connection;

    public LoginModel() {
        connection = new DatabaseConnection();
    }

    public void authenticate(String name, String password) {
        UserErrorCodes result = UserErrorCodes.FAILED;
        boolean userExists = false;
        try {
            userExists = checkValidUser(name, password);
        } catch (SQLException e) {
            System.out.println("Invalid sql query");
            e.printStackTrace();
        }
        if (userExists) {
            System.out.println("User authenticated");
            result = UserErrorCodes.LOG_IN;
        } else {
            System.out.println("Username / Password not recognized");
            result = UserErrorCodes.NO_SUCH_USER;
        }
        setChanged();
        notifyObservers(result);
    }

    private boolean checkValidUser(String name, String password) throws SQLException {
        connection.connect();

        ResultSet result = connection.query(generateLookupQuerey(name
                , password));

        if (result.next()) {
            authenticated = true;
            userId = result.getInt(1);
            userName = result.getString(2);
            userAdmin = result.getBoolean(4);

            connection.closeConnection();
            return true;
        } else {
            connection.closeConnection();
            return false;
        }
    }


    private String generateLookupQuerey(String name, String password) {
        return String.format("SELECT * " +
                        "FROM user " +
                        "WHERE user_name ='%1$s' " +
                        "AND user_password = '%2$s'"
                , name, password);
    }

    public void logOff() {
        if (authenticated) {
            authenticated = false;
            userName = null;
            userAdmin = null;
            userId = -1;
        }
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getUserName() {
        return userName;
    }

    public void setConnection(DatabaseConnector connection) {
        this.connection = connection;
    }

    public int getUserId() {
        return userId;
    }

    public Boolean getUserAdmin() {
        return userAdmin;
    }
}
