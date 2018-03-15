package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection implements DatabaseConnector {
    private final String PROPERTY_FILE_NAME = "database.properties";
    private String databaseHost;
    private String databasePort;
    private String databaseUser;
    private String databasePass;
    private String databaseUrl;
    private String databaseInternal;
    private String databaseExternal;
    private InputStream inputStream;
    private Properties properties;
    private Connection connection;


    private DatabaseConnection() {
        inputStream = null;
        properties = new Properties();
    }

    public DatabaseConnection internalConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        connection.generateInternalUrl();
        return connection;
    }

    public DatabaseConnection externalConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        connection.generateExternalUrl();
        return connection;
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

    private void generateDatabaseUrl() {
        readPropertyFiles();
        databaseUrl = String.format("jdbc:mysql://%1$s:%2$s/",
                databaseHost, databasePort);
    }

    private void generateInternalUrl() {
        generateDatabaseUrl();
        databaseUrl += databaseInternal;
    }

    private void generateExternalUrl() {
        generateDatabaseUrl();
        databaseUrl += databaseExternal;
    }

    private void readPropertyFiles() {
        try {
            readDefaultPropertyFile();
            readOverridePropertyFile();

            databaseHost = properties.getProperty("host");
            databasePort = properties.getProperty("port");
            databaseUser = properties.getProperty("user");
            databasePass = properties.getProperty("pass");
            databaseInternal = properties.getProperty("internal");
            databaseExternal = properties.getProperty("external");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readDefaultPropertyFile() throws IOException {
        inputStream = ClassLoader.getSystemResourceAsStream(PROPERTY_FILE_NAME);
        properties.load(inputStream);
    }

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
    public ResultSet query(String query) {
        return null;
    }

}
