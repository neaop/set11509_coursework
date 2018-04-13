package broker.model;

import data.DatabaseConnection;
import data.DatabaseConnector;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Vector;

public class BrokerModel extends Observable implements Serializable {
    private DatabaseConnector databaseConnection;

    public BrokerModel() {
        databaseConnection = new DatabaseConnection();
    }

    public Vector queryBrokers() {
        Vector results = null;
        databaseConnection.connect();
        try {
            ResultSet resultSet = databaseConnection.query(generateQueryString());
            results = parseQueryResults(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        setChanged();
        notifyObservers(results);
        return results;
    }

    private Vector parseQueryResults(ResultSet resultSet) throws SQLException {
        Vector<Vector<Object>> results = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<>();
            vector.add(resultSet.getObject(1));
            vector.add(resultSet.getObject(2));
            vector.add(resultSet.getObject(3));
            vector.add(resultSet.getObject(4));
            vector.add(resultSet.getObject(5));
            results.add(vector);
        }
        return results;
    }


    private String generateQueryString() {
        return "SELECT b.broker_id, b.broker_name, b.broker_contact, " +
                "b.broker_grade, GROUP_CONCAT(d.domain_type) " +
                "FROM share_trader.broker b " +
                "LEFT JOIN expertise e ON e.expertise_broker_id = b.broker_id " +
                "LEFT JOIN domain d ON e.expertise_domain_id = d.domain_id " +
                "GROUP BY b.broker_id;";
    }

}
