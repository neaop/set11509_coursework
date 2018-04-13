package data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface to describe database connection methods.
 */
public interface DatabaseConnector {

    /**
     * Open a connection to a database instance.
     */
    void connect();

    /**
     * Close connection to the database instance.
     */
    void closeConnection();

    /**
     * Queries the database with SQL query string.
     *
     * @param query the query string to be executed
     * @return results of the executed query
     * @throws SQLException if error with database instance or query
     */
    ResultSet query(String query) throws SQLException;

}
