import global.GlobalController;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * The entry point of the Share Trader application.
 */
public class Main implements Serializable {

    /**
     * Launch the application.
     *
     * @param args system arguments
     * @throws SQLException if error with database instance or query
     */
    public static void main(String[] args) throws SQLException {
        GlobalController globalController = new GlobalController();
        globalController.runApplication();
    }

}
