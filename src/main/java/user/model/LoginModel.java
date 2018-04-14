package user.model;

import data.DatabaseConnection;
import data.DatabaseConnector;
import global.CurrentUserModel;
import global.GlobalControlCodes;
import user.UserControlCodes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Model element used to login users as part of the user module.
 */
public class LoginModel extends java.util.Observable implements Serializable {
    private DatabaseConnector connection;

    /**
     * The default constructor.
     */
    public LoginModel() {
        connection = new DatabaseConnection();
    }

    /**
     * Attempt to login a user.
     *
     * @param name     the user's name
     * @param password the user's password
     */
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

    /**
     * Validate that user existsin the database.
     *
     * @param name     the name of the user
     * @param password the user's password
     * @return true if user exists, false otherwise
     * @throws SQLException if error with database instance or query
     */
    private boolean checkValidUser(String name, String password)
            throws SQLException {
        connection.connect();

        ResultSet result = connection.query(generateLookupQuery(name
                , password));

        if (result.next()) {
            int userId = result.getInt(1);
            String userName = result.getString(2);
            boolean userAdmin = result.getBoolean(4);
            CurrentUserModel currentUser = CurrentUserModel.getInstance();
            currentUser.loginUser(userId, userName, userAdmin);

            connection.closeConnection();
            return true;
        } else {
            connection.closeConnection();
            return false;
        }
    }

    /**
     * Create SQL query to check is user exists.
     *
     * @param name     the user's name
     * @param password the user's password.
     * @return the SQL query is String form
     */
    private String generateLookupQuery(String name, String password) {
        return String.format("SELECT * " +
                        "FROM user " +
                        "WHERE user_name ='%1$s' " +
                        "AND user_password = '%2$s'",
                name, password);
    }
}
