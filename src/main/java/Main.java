import global.GlobalController;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        GlobalController globalController = new GlobalController();
        globalController.runApplication();
    }

}
