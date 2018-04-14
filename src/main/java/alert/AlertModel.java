package alert;

import data.DatabaseConnection;
import data.DatabaseConnector;
import global.CurrentUserModel;

import javax.swing.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class to notify the current user of changes to tracked shares.
 */
public class AlertModel implements Serializable {
    private DatabaseConnector databaseConnection;
    private ArrayList<TrackedShare> trackedShares;
    private boolean sharesUpdated;

    /**
     * Class Constructor.
     */
    public AlertModel() {
        databaseConnection = new DatabaseConnection();
        trackedShares = new ArrayList<>();
    }

    /**
     * Notify user of any changes to tracked shares.
     */
    public void checkTrackedShares() {
        if (CurrentUserModel.getInstance().isAuthenticated()) {
            String query = generateQueryString();
            try {
                queryTrackedShares(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (sharesUpdated) {
                sharesUpdated = false;
                displayUpdate(generateUpdatesString());
            }
        }
    }

    /**
     * Query current status of user's tracked shares.
     *
     * @param query the SQL query to be executed
     * @throws SQLException if error with database instance or query
     */
    private void queryTrackedShares(String query) throws SQLException {
        databaseConnection.connect();
        ResultSet resultSet = null;
        resultSet = databaseConnection.query(query);
        extractTrackedShares(resultSet);
        databaseConnection.closeConnection();
    }

    /**
     * Creates a string to list the names of updated shares.
     *
     * @return SQL query in string format
     */
    private String generateUpdatesString() {
        StringBuilder updatedShares = new StringBuilder();
        for (TrackedShare trackedShare : trackedShares) {
            if (trackedShare.isUpdated()) {
                trackedShare.setUpdated(false);
                updatedShares.append(String.format(
                        "Share: %s value has been updated\n"
                        , trackedShare.getShareCode()));
            }
        }
        return updatedShares.toString();
    }

    /**
     * Parses a SQLResultSet to extract updated shares.
     *
     * @param resultSet the results from the SQL query
     * @throws SQLException if error with database instance or query
     */
    private void extractTrackedShares(ResultSet resultSet) throws SQLException {
        sharesUpdated = false;
        while (resultSet.next()) {
            boolean previouslyTracked = false;

            int currentTrackedShareId = resultSet.getInt(1);
            int currentTrackedValue = resultSet.getInt(8);
            String currentTrackedShareCode = resultSet.getString(13);

            for (TrackedShare existingTrackedShare : trackedShares) {

                if (existingTrackedShare.getTrackId() == currentTrackedShareId) {
                    previouslyTracked = true;
                    updateExistingTrackedShare(existingTrackedShare
                            , currentTrackedValue);
                }
            }

            if (!previouslyTracked) {
                addNewTrackedShare(currentTrackedShareId, currentTrackedValue
                        , currentTrackedShareCode);
            }
        }
    }

    /**
     * Updates the value of a share that the system has already parsed.
     *
     * @param existingTrackedShare the existing parsed share
     * @param shareCurrentValue    the updated value of the parsed share
     */
    private void updateExistingTrackedShare(TrackedShare existingTrackedShare
            , int shareCurrentValue) {
        if (existingTrackedShare.getTrackLastValue() != shareCurrentValue) {
            existingTrackedShare.setTrackLastValue(shareCurrentValue);
            existingTrackedShare.setUpdated(true);
            sharesUpdated = true;
        }
    }

    /**
     * Registers the newly parsed share to the the list of tracked shares.
     *
     * @param currentTrackedShareId   the ID of the current share
     * @param currentTrackedValue     the value of the current share
     * @param currentTrackedShareCode the trading code of the current share
     */
    private void addNewTrackedShare(int currentTrackedShareId
            , int currentTrackedValue, String currentTrackedShareCode) {
        TrackedShare newTrackedShare = new TrackedShare();
        newTrackedShare.setTrackId(currentTrackedShareId);
        newTrackedShare.setTrackLastValue(currentTrackedValue);
        newTrackedShare.setShareCode(currentTrackedShareCode);
        newTrackedShare.setUpdated(true);
        trackedShares.add(newTrackedShare);
        sharesUpdated = true;
    }

    /**
     * Creates a string used to query the database.
     *
     * @return SQL query in string format
     */
    private String generateQueryString() {
        int userId = CurrentUserModel.getInstance().getUserId();

        return String.format("SELECT * " +
                        "FROM track t " +
                        "LEFT JOIN share s " +
                        "ON t.track_share_id = s.share_id " +
                        "LEFT JOIN company c " +
                        "ON s.share_company_id = c.company_id " +
                        "WHERE t.track_user_id = %d " +
                        "AND " +
                        "s.share_price NOT BETWEEN t.track_min AND t.track_max;",
                userId);
    }

    /**
     * Shows a popup of updated shares.
     *
     * @param message the shares that have been updated
     */
    private void displayUpdate(String message) {
        JOptionPane.showMessageDialog(null, message
                , "Share Monitor", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Subclass to retain information regarding the current user's tracked shares.
     */
    private class TrackedShare {
        private int trackId;
        private int trackLastValue;
        private String shareCode;
        private boolean updated;

        /**
         * Class constructor.
         */
        private TrackedShare() {

        }

        /**
         * Returns the share's previous value.
         *
         * @return the share's market value
         */
        int getTrackLastValue() {
            return trackLastValue;
        }

        /**
         * Registers the shares last checked value.
         *
         * @param trackLastValue the value of the share last time it was queried
         */
        void setTrackLastValue(int trackLastValue) {
            this.trackLastValue = trackLastValue;
        }

        /**
         * Returns the share's ID number.
         *
         * @return the ID of the share
         */
        int getTrackId() {
            return trackId;
        }

        /**
         * Register the share's ID number.
         *
         * @param trackId the ID of the share
         */
        void setTrackId(int trackId) {
            this.trackId = trackId;
        }

        /**
         * Boolean to track whether the share's value has been updated.
         *
         * @return true if the share's value has been altered, false otherwise
         */
        boolean isUpdated() {
            return updated;
        }

        /**
         * Register whether the shares value has changed since last query.
         *
         * @param updated true if value has changed, false otherwise
         */
        void setUpdated(boolean updated) {
            this.updated = updated;
        }

        /**
         * Returns the trade code of the share.
         *
         * @return the shares trading code
         */
        String getShareCode() {
            return shareCode;
        }

        /**
         * Register the share's trading code.
         *
         * @param shareCode the trading code of the share
         */
        void setShareCode(String shareCode) {
            this.shareCode = shareCode;
        }
    }
}
