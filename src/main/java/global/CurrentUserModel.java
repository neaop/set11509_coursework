package global;

import java.io.Serializable;

public class CurrentUserModel implements Serializable{
    private int userId;
    private String userName;
    private boolean userAdmin;
    private static CurrentUserModel currentUser = new CurrentUserModel();
    private boolean authenticated = false;

    private CurrentUserModel() {
    }

    public static CurrentUserModel getInstance() {
        return currentUser;
    }

    public void loginUser(int userId, String userName, boolean userAdmin) {
        currentUser.userId = userId;
        currentUser.userName = userName;
        currentUser.userAdmin = userAdmin;
        authenticated = true;
    }

    public void logoutUser() {
        currentUser.userId = -1;
        currentUser.userName = null;
        currentUser.userAdmin = false;
        authenticated = false;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isUserAdmin() {
        return userAdmin;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
