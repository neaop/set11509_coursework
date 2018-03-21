package track.model;

import data.DatabaseConnection;
import data.DatabaseConnector;
import global.CurrentUser;
import track.TrackErrorCodes;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Vector;

public class TrackModel extends Observable {
    private DatabaseConnector connection;
    private int shareId;
    private int sharePrice;

    public TrackModel() {
        connection = new DatabaseConnection();
    }

    public void trackShare(int[] values) {
        int minValue = values[0];
        int maxValue = values[1];
        setChanged();
        if (verifyValues(sharePrice, minValue, maxValue)) {
            String query = generateTrackInsert(shareId, minValue, maxValue);
            executeTrackInsert(query);
            notifyObservers(true);
        }
        clearChanged();
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

    private String generateTrackInsert(int shareId, int minValue
            , int maxValue) {
        CurrentUser currentUser = CurrentUser.getInstance();
        int userId = currentUser.getUserId();

        return String.format("INSERT INTO track " +
                        "(track_user_id, track_share_id, track_min, track_max) " +
                        "VALUES (%1$d, %2$d, %3$d, %4$d) " +
                        "ON DUPLICATE KEY " +
                        "UPDATE " +
                        "track_min = VALUES(track_min)," +
                        "track_max = VALUES(track_max);"
                , userId, shareId, minValue, maxValue);
    }

    public void setShareInfo(Vector<Vector<Object>> share) {
        shareId = (int) share.get(0).get(0);
        sharePrice = (int) share.get(0).get(3);

        setChanged();
        notifyObservers(share);
    }

    private boolean verifyValues(int sharePrice, int minValue, int maxValue) {
        return (verifyMinValue(sharePrice, minValue)
                && verifyMaxValue(sharePrice, maxValue));
    }

    private boolean verifyMinValue(int sharePrice, int minValue) {
        if ((minValue < sharePrice) && (minValue > 0)) {
            return true;
        } else {
            setChanged();
            notifyObservers(TrackErrorCodes.MIN_INVALID);
        }
        return false;
    }

    private boolean verifyMaxValue(int sharePrice, int maxValue) {
        if (maxValue > sharePrice) {
            return true;
        } else {
            setChanged();
            notifyObservers(TrackErrorCodes.MAX_INVALID);
        }
        return false;
    }

}
