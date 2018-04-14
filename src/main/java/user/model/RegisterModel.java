package user.model;

import data.DatabaseConnection;
import data.DatabaseConnector;
import user.UserControlCodes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

/**
 * The model element used to register new users in the user module.
 */
public class RegisterModel extends Observable implements Serializable {
    private DatabaseConnector databaseConnector;

    /**
     * Default constructor.
     */
    public RegisterModel() {
        databaseConnector = new DatabaseConnection();
    }

    /**
     * Try to add a new user to the database.
     *
     * @param userName     the user's name
     * @param userPassword the user's password
     */
    public void attemptRegisterUser(String userName, String userPassword) {
        UserControlCodes result;

        if (checkInvalidCredential(userName) || checkInvalidCredential(userPassword)) {
            System.out.println("RegisterModel: user credential invalid");
            result = UserControlCodes.INVALID_CREDENTIAL;
        } else if (checkUserExists(userName)) {
            System.out.println("RegisterModel: user already exists");
            result = UserControlCodes.USER_EXISTS;
        } else {
            registerUser(userName, userPassword);
            System.out.println("RegisterModel: user added to database");
            result = UserControlCodes.REGISTER;
        }
        setChanged();
        notifyObservers(result);
    }

    /**
     * Query database to see if usename is already in use.
     *
     * @param userName the name of the user.
     * @return true if username in use, false otherwise
     */
    private boolean checkUserExists(String userName) {
        boolean exists = true;
        databaseConnector.connect();
        try {
            ResultSet result = databaseConnector.query(generateUserExistsQuery(userName));
            exists = result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.closeConnection();
        return exists;
    }

    /**
     * Execute query to register new user in database.
     *
     * @param userName     the name of the new user
     * @param userPassword the password of the new user
     * @return true if successful, false otherwise
     */
    private boolean registerUser(String userName, String userPassword) {
        boolean registered;
        databaseConnector.connect();
        try {
            databaseConnector.query(generateRegisterUserQuery(userName, userPassword));
            registered = true;
        } catch (SQLException e) {
            e.printStackTrace();
            registered = false;
        }

        databaseConnector.closeConnection();
        return registered;
    }

    /**
     * Validate user credentials are not empty Strings.
     *
     * @param credential the input to be checked
     * @return true if credential are not valid, false otherwise
     */
    private boolean checkInvalidCredential(String credential) {
        return (credential == null || credential.isEmpty());
    }

    /**
     * Create a SQL querey to check if user is already registered.
     *
     * @param userName the name of the user
     * @return the SQL query in String form
     */
    private String generateUserExistsQuery(String userName) {
        return String.format("SELECT * " +
                        "FROM user " +
                        "WHERE user_name = '%1$s';"
                , userName);
    }

    /**
     * Create a SQL query to register a new user.
     *
     * @param userName     the user's name
     * @param userPassword the user's password
     * @return the SQL query in String form
     */
    private String generateRegisterUserQuery(String userName, String userPassword) {
        return String.format("INSERT " +
                        "INTO user (user_name, user_password, user_admin) " +
                        "VALUES ('%1$s', '%2$s', 0);"
                , userName, userPassword);
    }

}
