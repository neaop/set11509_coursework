package track;

import data.DatabaseConnection;
import data.DatabaseConnector;
import global.CurrentUserModel;
import track.TrackErrorCodes;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Vector;

/**
 * Model element for the track module.
 */
public class TrackModel extends Observable implements Serializable {
    private DatabaseConnector connection;
    private int shareId;
    private int sharePrice;

    /**
     * Default constructor.
     */
    public TrackModel() {
        connection = new DatabaseConnection();
    }

    /**
     * Track share with user's preferred min and max values.
     *
     * @param values the minimum and maximum values set by the user
     */
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

    /**
     * Insert the tracked share into the database
     *
     * @param query the query String to be executed
     */
    private void executeTrackInsert(String query) {
        connection.connect();
        try {
            connection.query(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.closeConnection();
    }

    /**
     * Create a String of a SQL query to track a new share.
     *
     * @param shareId  the ID number of the share to be tracked
     * @param minValue the minimum value to be tracked
     * @param maxValue the maximum value to be tracked
     * @return the SQL query in String form
     */
    private String generateTrackInsert(int shareId, int minValue
            , int maxValue) {
        CurrentUserModel currentUser = CurrentUserModel.getInstance();
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

    /**
     * Register the selected share's information
     *
     * @param share the Vector of share information
     */
    public void setShareInfo(Vector<Vector<Object>> share) {
        shareId = (int) share.get(0).get(0);
        sharePrice = (int) share.get(0).get(3);

        setChanged();
        notifyObservers(share);
    }

    /**
     * Check that the user's min/max values are within the shares limits.
     *
     * @param sharePrice the price of the share
     * @param minValue   the user's minimum value
     * @param maxValue   the user's maxim value
     * @return true if values are within system limits, false otherwise
     */
    private boolean verifyValues(int sharePrice, int minValue, int maxValue) {
        return (verifyMinValue(sharePrice, minValue)
                && verifyMaxValue(sharePrice, maxValue));
    }

    /**
     * Check that the user's min value is under the share's value, and above 0.
     *
     * @param sharePrice the current share's value
     * @param minValue   the user's minimum value
     * @return true is within limits, false otherwise
     */
    private boolean verifyMinValue(int sharePrice, int minValue) {
        if ((minValue < sharePrice) && (minValue > 0)) {
            return true;
        } else {
            setChanged();
            notifyObservers(TrackErrorCodes.MIN_INVALID);
        }
        return false;
    }

    /**
     * Check the user's max value is greater that the share's current value.
     *
     * @param sharePrice the current price of the share
     * @param maxValue   the user's maximum value
     * @return true if within limit, false otherwise
     */
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
