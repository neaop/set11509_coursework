package global;

public class CurrentUser {
    private int userId;
    private String userName;
    private boolean userAdmin;
    private static CurrentUser currentUser = new CurrentUser();

    private CurrentUser() {
    }

    public static CurrentUser getInstance() {
        return currentUser;
    }

    public void loginUser(int userId, String userName, boolean userAdmin) {
        currentUser.userId = userId;
        currentUser.userName = userName;
        currentUser.userAdmin = userAdmin;
    }

    public void logoutUser() {
        currentUser.userId = -1;
        currentUser.userName = null;
        currentUser.userAdmin = false;
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

}
