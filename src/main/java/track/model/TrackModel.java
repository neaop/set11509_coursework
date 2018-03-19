package track.model;

import data.DatabaseConnection;
import data.DatabaseConnector;
import global.CurrentUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class TrackModel extends Observable {
    private DatabaseConnector connection;

    public TrackModel() {
        connection = new DatabaseConnection();
    }

    public void trackShare(int shareId
            , float minValue, float maxValue) {
        String query = generateTrackInsert(shareId, minValue, maxValue);
        executeTrackInsert(query);
    }

    private void executeTrackInsert(String query) {
        connection.connect();
        try {
            connection.query(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.closeConnection();
    }

    public void getShareFromId(int shareId) {
        java.util.Vector<Object> shareData = null;
        String query = generateSelectShare(shareId);

        connection.connect();
        try {
            ResultSet result = connection.query(query);
            shareData = cleanQueryResult(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.closeConnection();

        setChanged();
        notifyObservers(shareData);
    }

    private String generateTrackInsert(int shareId, float minValue
            , float maxValue) {
        CurrentUser currentUser = CurrentUser.getInstance();
        int userId = currentUser.getUserId();

        return String.format("INSERT INTO track " +
                        "(track_user_id, track_share_id, track_min, track_max) " +
                        "VALUES (%1$d, %2$d, %3$f, %4$f);"
                , userId, shareId, minValue, maxValue);
    }

    private String generateSelectShare(int shareId) {
        return String.format("SELECT * FROM share s LEFT JOIN company c ON s.share_company_id = c.company_id WHERE s.share_id = %1$d", shareId);
    }

    private java.util.Vector<Object> cleanQueryResult(ResultSet queryResult) throws SQLException {
        java.util.Vector<Object> result = new java.util.Vector<>();
        while (queryResult.next()) {
            result.add(queryResult.getObject(1));
            result.add(queryResult.getObject(8));
            result.add(queryResult.getObject(7));
            result.add(queryResult.getObject(3));
            result.add(queryResult.getObject(4));
            result.add(queryResult.getObject(5));
        }
        return result;
    }

}
