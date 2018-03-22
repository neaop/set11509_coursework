package global.model;

import data.DatabaseConnection;
import data.DatabaseConnector;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShareMonitor {
    private DatabaseConnector databaseConnection;
    private ArrayList<TrackedShare> trackedShares;
    private boolean updates;

    public ShareMonitor() {
        databaseConnection = new DatabaseConnection();
        trackedShares = new ArrayList<>();
    }

    public void checkTrackedShares() {
        if (CurrentUser.getInstance().isAuthenticated()) {
            String query = generateQueryString();
            try {
                getTrackModules(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (updates) {
                updates = false;
                displayUpdate(checkTrackModules());
            }
        }
    }

    private void getTrackModules(String query) throws SQLException {
        databaseConnection.connect();
        ResultSet resultSet = null;
        resultSet = databaseConnection.query(query);
        extractTrackModules(resultSet);
        databaseConnection.closeConnection();
    }

    private String checkTrackModules() {
        StringBuilder updatedShares = new StringBuilder();
        for (TrackedShare trackModule : trackedShares) {
            if (trackModule.isUpdated()) {
                trackModule.setUpdated(false);
                updatedShares.append(String.format(
                        "Share: %s value has been updated\n"
                        , trackModule.getShareCode()));
            }
        }
        return updatedShares.toString();
    }

    private void extractTrackModules(ResultSet resultSet) throws SQLException {
        updates = false;
        while (resultSet.next()) {
            boolean added = false;

            int trackId = resultSet.getInt(1);
            int trackValue = resultSet.getInt(8);
            String shareCode = resultSet.getString(13);

            for (TrackedShare t : trackedShares) {
                if (t.trackId == trackId) {
                    added = true;
                    if (t.trackLastValue != trackValue) {
                        t.setTrackLastValue(trackValue);
                        t.setUpdated(true);
                        updates = true;
                    }
                }
            }
            if (!added) {
                TrackedShare trackModule = new TrackedShare();
                trackModule.setTrackId(trackId);
                trackModule.setTrackLastValue(trackValue);
                trackModule.setShareCode(shareCode);
                trackModule.setUpdated(true);
                trackedShares.add(trackModule);
                updates = true;
            }
        }
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

        public int getTrackLastValue() {
            return trackLastValue;
        }

        void setTrackLastValue(int trackLastValue) {
            this.trackLastValue = trackLastValue;
        }

        public int getTrackId() {
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
