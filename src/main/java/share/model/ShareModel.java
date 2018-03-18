package share.model;

import data.DatabaseConnection;
import data.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Vector;

public class ShareModel extends Observable {
    private DatabaseConnector databaseConnection;

    public ShareModel() {
        databaseConnection = new DatabaseConnection();
    }

    public Vector getShareData() {
        return queryShares();
    }

    private Vector queryShares() {
        ResultSet data = null;
        Vector result = null;
        databaseConnection.connect();
        try {
            data = databaseConnection.query("SELECT * FROM share s LEFT JOIN company c ON s.share_company_id = c.company_id;");
            result = convertResult(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return result;
    }

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
