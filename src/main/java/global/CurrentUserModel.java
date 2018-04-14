package global;

import java.io.Serializable;

/**
 * Singleton that retains information regarding the currently logged in user.
 */
public class CurrentUserModel implements Serializable {
    private int userId;
    private String userName;
    private boolean userAdmin;
    private static CurrentUserModel currentUser = new CurrentUserModel();
    private boolean authenticated = false;

    /**
     * Private constructor to prevent repeated instantiation.
     */
    private CurrentUserModel() {
    }

    /**
     * Returns the instance of the CurrentUserModel.
     *
     * @return the existing current user
     */
    public static CurrentUserModel getInstance() {
        return currentUser;
    }

    /**
     * Assigns the current user's information.
     *
     * @param userId    the ID number of the user
     * @param userName  the name of the User
     * @param userAdmin true for administrators, false otherwise
     */
    public void loginUser(int userId, String userName, boolean userAdmin) {
        currentUser.userId = userId;
        currentUser.userName = userName;
        currentUser.userAdmin = userAdmin;
        authenticated = true;
    }

    /**
     * Logout the current user.
     */
    public void logoutUser() {
        currentUser.userId = -1;
        currentUser.userName = null;
        currentUser.userAdmin = false;
        authenticated = false;
    }

    /**
     * Returns the ID of current user.
     *
     * @return the current user's ID number
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Check whether a user is logged in.
     *
     * @return true if user is logged, false if otherwise
     */
    public boolean isAuthenticated() {
        return authenticated;
    }
}
