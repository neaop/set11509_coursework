package model;

import data.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel extends java.util.Observable {

    private int userId;
    private String userName;
    private Boolean userAdmin;
    private boolean authenticated;
    private DatabaseConnector connection;

    public void authenticate(String name, String password) {
        boolean auth = false;

        try {
            auth = lookupUser(name, password);
        } catch (InstantiationException e) {
            System.out.println("Model has no database connection");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Invalid sql query");
            e.printStackTrace();
        }
        if(auth){
            System.out.println("User authenticated");
        }else{
            System.out.println("Username / Password not recognized");
        }
        authenticated = auth;
        setChanged();
        notifyObservers(authenticated);
    }

    private boolean lookupUser(String name, String password) throws InstantiationException, SQLException {
        if (connection != null) {

            ResultSet result = connection.query(generateLookupQuerey(name
                    , password));

            if (result.next()) {
                authenticated = true;
                userId = result.getInt(1);
                userName = result.getString(2);
                userAdmin = result.getBoolean(4);
                return true;
            } else {
                return false;
            }

        } else {
            throw new InstantiationException();
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
