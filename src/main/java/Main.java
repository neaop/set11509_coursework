import global.GlobalController;

import java.sql.SQLException;

public class Main {
    private static String name = "test";
    private static String pass = "test";

    public static void main(String[] args) throws SQLException {
        GlobalController globalController = new GlobalController();
        globalController.runApplication();
    }
}
