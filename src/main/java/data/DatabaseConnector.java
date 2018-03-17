package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnector {

    public Connection myConnection = null;

    public void connect();

    public void closeConnection();

    public ResultSet query(String query) throws SQLException;

}
