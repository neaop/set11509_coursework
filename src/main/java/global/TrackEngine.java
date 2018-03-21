package global;

import data.DatabaseConnection;
import data.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackEngine {
    DatabaseConnector databaseConnection;
    ArrayList<TrackModule> trackModules;

    public TrackEngine() {
        databaseConnection = new DatabaseConnection();
        trackModules = new ArrayList<>();
    }

    public String checkTrackedShares() {
        String query = generateQueryString();
        try {
            getTrackModules(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkTrackModules();
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
        for (TrackModule trackModule : trackModules) {
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
        while (resultSet.next()) {
            boolean added = false;

            int trackId = resultSet.getInt(1);
            int trackValue = resultSet.getInt(8);
            String shareCode = resultSet.getString(13);

            for (TrackModule t : trackModules) {
                if (t.trackId == trackId) {
                    added = true;
                    if (t.trackLastValue != trackValue) {
                        t.setTrackLastValue(trackValue);
                        t.setUpdated(true);
                    }
                }
            }
            if (!added) {
                TrackModule trackModule = new TrackModule();
                trackModule.setTrackId(trackId);
                trackModule.setTrackLastValue(trackValue);
                trackModule.setShareCode(shareCode);
                trackModule.setUpdated(true);
                trackModules.add(trackModule);
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

    private class TrackModule {
        private int trackId;
        private int trackLastValue;
        private String shareCode;
        private boolean updated;

        public int getTrackLastValue() {
            return trackLastValue;
        }

        public void setTrackLastValue(int trackLastValue) {
            this.trackLastValue = trackLastValue;
        }

        public int getTrackId() {
            return trackId;
        }

        public void setTrackId(int trackId) {
            this.trackId = trackId;
        }

        public boolean isUpdated() {
            return updated;
        }

        public void setUpdated(boolean updated) {
            this.updated = updated;
        }

        public String getShareCode() {
            return shareCode;
        }

        public void setShareCode(String shareCode) {
            this.shareCode = shareCode;
        }
    }
}
