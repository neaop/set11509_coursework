package shareholder.model;

import data.DatabaseConnection;
import data.DatabaseConnector;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Vector;

public class ShareholderModel extends Observable implements Serializable {
    private DatabaseConnector databaseConnection;

    public ShareholderModel() {
        databaseConnection = new DatabaseConnection();
    }

    public Vector queryShareHolders() {
        Vector results = null;
        ResultSet queryResults;
        databaseConnection.connect();
        try {
            queryResults = databaseConnection.query(generateQueryString());
            results = paresResults(queryResults);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(results);
        return results;
    }

    private Vector paresResults(ResultSet resultSet) throws SQLException {
        Vector<Vector<Object>> results = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<>();
            vector.add(resultSet.getObject(1));
            vector.add(resultSet.getObject(3));
            vector.add(resultSet.getObject(3));
            vector.add(resultSet.getObject(4));
            results.add(vector);
        }
        return results;
    }

    private String generateQueryString() {
        return "SELECT c.company_name, c.company_code, sh.shareholder_name, st.stake_quantity " +
                "FROM shareholder sh " +
                "RIGHT JOIN stake st on sh.shareholder_id = st.stake_stakeholder_id " +
                "LEFT JOIN company c on st.stake_company_id = c.company_id " +
                "ORDER BY c.company_name, st.stake_quantity;";
    }
}
