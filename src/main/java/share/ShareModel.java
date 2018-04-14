package share;

import data.DatabaseConnection;
import data.DatabaseConnector;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Vector;

/**
 * Model element for the share module.
 */
public class ShareModel extends Observable implements Serializable {
    private DatabaseConnector databaseConnection;

    /**
     * Default constructor.
     */
    public ShareModel() {
        databaseConnection = new DatabaseConnection();
    }

    /**
     * Retrieve share data from database and notifier Observer.
     */
    public void getShareData() {
        Vector shareData = queryShares();
        setChanged();
        notifyObservers(shareData);
    }

    /**
     * Query database and return Vector of results.
     *
     * @return Vector of share data
     */
    private Vector queryShares() {
        Vector shareData = null;
        ResultSet resultSet;

        databaseConnection.connect();
        try {
            resultSet = databaseConnection.query("SELECT * " +
                    "FROM share s " +
                    "LEFT JOIN company c " +
                    "ON s.share_company_id = c.company_id;");

            shareData = convertResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
        return shareData;
    }

    /**
     * Convert SQL ResultSet to a Vector of share data.
     *
     * @param data the SQL ResultSet
     * @return Vector of share data
     * @throws SQLException if error with database instance or query
     */
    private Vector convertResult(ResultSet data) throws SQLException {
        Vector<Vector<Object>> result = new Vector<>();
        while (data.next()) {
            Vector<Object> vector = new Vector<>();
            vector.add(data.getObject(1));
            vector.add(data.getObject(8));
            vector.add(data.getObject(7));
            vector.add(data.getObject(3));
            vector.add(data.getObject(4));
            vector.add(data.getObject(5));
            result.add(vector);
        }

        return result;
    }


}
