package data;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Concrete implementation of the DatabaseConnector interface.
 *
 * @see data.DatabaseConnector
 */
public class DatabaseConnection implements DatabaseConnector, Serializable {
    private final String PROPERTY_FILE_NAME = "database.properties";
    private String databaseHost;
    private String databasePort;
    private String databaseUser;
    private String databasePass;
    private String databaseUrl;
    private String databaseName;
    private InputStream inputStream;
    private Properties properties;
    private Connection connection;

    /**
     * Default constructor for the database connection.
     */
    public DatabaseConnection() {
        inputStream = null;
        properties = new Properties();
        generateDatabaseUrl();
    }

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection(
                    databaseUrl, databaseUser, databasePass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a default database connection string.
     */
    private void generateDatabaseUrl() {
        readPropertyFiles();
        databaseUrl = String.format("jdbc:mysql://%1$s:%2$s/%3$s",
                databaseHost, databasePort, databaseName);
    }

    /**
     * Extracts database connection information from existing property files.
     */
    private void readPropertyFiles() {
        try {
            readDefaultPropertyFile();
            readOverridePropertyFile();

            databaseHost = properties.getProperty("host");
            databasePort = properties.getProperty("port");
            databaseUser = properties.getProperty("user");
            databasePass = properties.getProperty("pass");
            databaseName = properties.getProperty("name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts connection information from the default property file.
     *
     * @throws IOException if error reading file
     */
    private void readDefaultPropertyFile() throws IOException {
        inputStream = ClassLoader.getSystemResourceAsStream(PROPERTY_FILE_NAME);
        properties.load(inputStream);
    }

    /**
     * Extracts connection information from secondary property file.
     *
     * @throws IOException if error reading file
     */
    private void readOverridePropertyFile() throws IOException {
        File overrideFile = new File(PROPERTY_FILE_NAME);
        if (overrideFile.exists() && !overrideFile.isDirectory()) {
            inputStream = new FileInputStream(overrideFile);
            properties.load(inputStream);
        }
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet query(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        return statement.getResultSet();

    }

}
