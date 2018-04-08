package alert;

import data.DatabaseConnection;
import data.DatabaseConnector;
import global.CurrentUser;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlertModel {
    private DatabaseConnector databaseConnection;
    private ArrayList<TrackedShare> trackedShares;
    private boolean sharesUpdated;

    public AlertModel() {
        databaseConnection = new DatabaseConnection();
        trackedShares = new ArrayList<>();
    }

    public void checkTrackedShares() {
        if (CurrentUser.getInstance().isAuthenticated()) {
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

    private void queryTrackedShares(String query) throws SQLException {
        databaseConnection.connect();
        ResultSet resultSet = null;
        resultSet = databaseConnection.query(query);
        extractTrackedShares(resultSet);
        databaseConnection.closeConnection();
    }

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

    private void updateExistingTrackedShare(TrackedShare existingTrackedShare
            , int shareCurrentValue) {
        if (existingTrackedShare.getTrackLastValue() != shareCurrentValue) {
            existingTrackedShare.setTrackLastValue(shareCurrentValue);
            existingTrackedShare.setUpdated(true);
            sharesUpdated = true;
        }
    }

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

    private String generateQueryString() {
        int userId = CurrentUser.getInstance().getUserId();

        return String.format("SELECT * " +
                        "FROM track t " +
                        "LEFT JOIN share s " +
                        "ON t.track_share_id = s.share_id " +
                        "LEFT JOIN company c " +
                        "ON s.share_company_id = c.company_id " +
                        "WHERE t.track_user_id = %d " +
                        "AND " +
                        "s.share_price NOT BETWEEN t.track_min AND t.track_max;"
                , userId);
    }

    private void displayUpdate(String message) {
        JOptionPane.showMessageDialog(null, message
                , "Share Monitor", JOptionPane.INFORMATION_MESSAGE);

    }

    private class TrackedShare {
        private int trackId;
        private int trackLastValue;
        private String shareCode;
        private boolean updated;

        int getTrackLastValue() {
            return trackLastValue;
        }

        void setTrackLastValue(int trackLastValue) {
            this.trackLastValue = trackLastValue;
        }

        int getTrackId() {
            return trackId;
        }

        void setTrackId(int trackId) {
            this.trackId = trackId;
        }

        boolean isUpdated() {
            return updated;
        }

        void setUpdated(boolean updated) {
            this.updated = updated;
        }

        String getShareCode() {
            return shareCode;
        }

        void setShareCode(String shareCode) {
            this.shareCode = shareCode;
        }
    }
}
