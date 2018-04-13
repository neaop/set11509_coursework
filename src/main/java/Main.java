import global.GlobalController;

import java.io.Serializable;
import java.sql.SQLException;

public class Main implements Serializable {

    public static void main(String[] args) throws SQLException {
        GlobalController globalController = new GlobalController();
        globalController.runApplication();
    }

}
