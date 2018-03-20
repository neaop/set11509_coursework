package track.model;

import data.DatabaseConnection;
import data.DatabaseConnector;
import global.CurrentUser;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Vector;

public class TrackModel extends Observable {
    private DatabaseConnector connection;
    private Vector share;

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

    private String generateTrackInsert(int shareId, float minValue
            , float maxValue) {
        CurrentUser currentUser = CurrentUser.getInstance();
        int userId = currentUser.getUserId();

        return String.format("INSERT INTO track " +
                        "(track_user_id, track_share_id, track_min, track_max) " +
                        "VALUES (%1$d, %2$d, %3$f, %4$f);"
                , userId, shareId, minValue, maxValue);
    }

    public void setShare(Vector share) {
        this.share = share;
        setChanged();
        notifyObservers(share);
    }
}
