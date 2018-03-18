package user.model;

import data.DatabaseConnection;
import data.DatabaseConnector;
import global.GlobalControlCodes;

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
        GlobalControlCodes result = GlobalControlCodes.FAILED;
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
            result = GlobalControlCodes.NO_SUCH_USER;
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

    public void reset() {
        this.userId = -1;
        this.authenticated = false;
        this.userName = null;
        this.userAdmin = false;
    }

    private String generateLookupQuerey(String name, String password) {
        return String.format("SELECT * " +
                        "FROM user " +
                        "WHERE user_name ='%1$s' " +
                        "AND user_password = '%2$s'"
                , name, password);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public Boolean getUserAdmin() {
        return userAdmin;
    }
}
