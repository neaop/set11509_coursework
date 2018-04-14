package trade.model;

import data.DatabaseConnection;
import data.DatabaseConnector;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Vector;

/**
 * Model element for the trade module.
 */
public class TradeModel extends Observable implements Serializable {
    private DatabaseConnector databaseConnection;
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Default constructor.
     */
    public TradeModel() {
        databaseConnection = new DatabaseConnection();
    }

    /**
     * Notify the view of trade information.
     *
     * @param fromDate    the starting date
     * @param tillDate    the ending date
     * @param companyCode the trading code of the company
     * @param sellerBuyer the name of the buyer/seller
     * @param broker      the name of the broker
     */
    public void searchTrades(String fromDate, String tillDate,
                             String companyCode, String sellerBuyer,
                             String broker) {
        if (checkDateValues(fromDate) && checkDateValues(tillDate)) {
            Vector trades = executeTradeSearch(fromDate, tillDate, companyCode,
                    sellerBuyer, broker);
            setChanged();
            notifyObservers(trades);
        }
    }

    /**
     * Query the database for trade information.
     *
     * @param fromDate    the starting date
     * @param tillDate    the ending date
     * @param companyCode the trading code of the company
     * @param sellerBuyer the name of the buyer/seller
     * @param broker      the name of the broker
     * @return a Vector of trade information
     */
    private Vector executeTradeSearch(String fromDate, String tillDate,
                                      String companyCode, String sellerBuyer,
                                      String broker) {
        ResultSet resultSet;
        Vector results = null;
        databaseConnection.connect();
        String query = combineQuery(fromDate, tillDate, companyCode,
                sellerBuyer, broker);
        try {
            resultSet = databaseConnection.query(query);
            results = processResults(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return results;
    }

    /**
     * Parse a SQL ResultSet to a Vector of information.
     *
     * @param resultSet the query results
     * @return the Vector of trade information
     * @throws SQLException if error with database instance or query
     */
    private Vector processResults(ResultSet resultSet) throws SQLException {
        Vector<Vector<Object>> results = new Vector<>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<>();
            for (int i = 1; i < columnCount + 1; i++) {
                vector.add(resultSet.getObject(i));
            }
            results.add(vector);
        }
        return results;
    }

    /**
     * Generate a single query string from a base query and conditions.
     *
     * @param fromDate    the starting date
     * @param tillDate    the ending date
     * @param companyCode the trading code of the company
     * @param sellerBuyer the name of the buyer/seller
     * @param broker      the name of the broker
     * @return a SQL query in String form
     */
    private String combineQuery(String fromDate, String tillDate,
                                String companyCode, String sellerBuyer,
                                String broker) {
        String query = generateBaseQuery();
        String condition = generateDateCondition(fromDate, tillDate);
        condition = generateCompanyCondition(condition, companyCode);
        condition = generateSellerBuyer(condition, sellerBuyer);
        condition = generateBrokerCondition(condition, broker);
        return query + condition;
    }

    /**
     * Create a default SQL query to return all trade information.
     *
     * @return the SL query in String form
     */
    private String generateBaseQuery() {
        return ("SELECT t.trade_id, c.company_name, " +
                "c.company_code, t.trade_date, t.trade_price, " +
                "shs.shareholder_name, shb.shareholder_name, b.broker_name " +
                "FROM trade t " +
                "LEFT JOIN stake s ON t.trade_stake_id = s.stake_id " +
                "LEFT JOIN company c ON s.stake_company_id = c.company_id " +
                "LEFT JOIN shareholder shs ON t.trade_seller_id = shs.shareholder_id " +
                "LEFT JOIN shareholder shb ON t.trade_buyer_id = shb.shareholder_id " +
                "LEFT JOIN broker b ON t.trade_broker_id = b.broker_id ");
    }

    /**
     * Create a conditional query based on provided dates.
     *
     * @param from the starting date
     * @param till the ending date
     * @return the SQL query in String from
     */
    private String generateDateCondition(String from, String till) {
        if (isValidDate(from) && isValidDate(till)) {
            return String.format("WHERE t.trade_date BETWEEN '%1$s' AND '%2$s' ",
                    from, till);
        } else if (isValidDate(from)) {
            return String.format("WHERE t.trade_date > '%1$s' ", from);
        } else if (isValidDate(till))
            return String.format("WHERE t.trade_date < '%1$s' ", till);
        return "";
    }

    /**
     * Create a conditional query based on provided company trading code.
     *
     * @param currentCondition the current conditional query
     * @param company          the company trading code
     * @return the SQL query in String form
     */
    private String generateCompanyCondition(String currentCondition,
                                            String company) {
        if (isNotEmpty(currentCondition) && isNotEmpty(company)) {
            return currentCondition += String.format(
                    "AND c.company_code = '%1$s'", company);
        } else if (isNotEmpty(company)) {
            return String.format("WHERE c.company_code = '%1$s'", company);
        } else {
            return currentCondition;
        }
    }

    /**
     * Create a conditional query based on provided buyer/seller name.
     *
     * @param currentCondition the current conditional query
     * @param buyerSeller      the name of the buyer/seller
     * @return the SQL query in String form.
     */
    private String generateSellerBuyer(String currentCondition,
                                       String buyerSeller) {
        if (isNotEmpty(currentCondition) && isNotEmpty(buyerSeller)) {
            return currentCondition
                    += String.format("AND shs.shareholder_name = '%s' " +
                            "OR shb.shareholder_name = '%s' "
                    , buyerSeller, buyerSeller);
        } else if (isNotEmpty(buyerSeller)) {
            return String.format("WHERE shs.shareholder_name = '%s' " +
                            "OR shb.shareholder_name = '%s' "
                    , buyerSeller, buyerSeller);
        }
        return currentCondition;
    }

    /**
     * Create a conditonal query base on provided broker name.
     *
     * @param currentCondition the current conditional query
     * @param broker           the name of the broker
     * @return the SQL query in String form
     */
    private String generateBrokerCondition(String currentCondition,
                                           String broker) {
        if (isNotEmpty(currentCondition) && isNotEmpty(broker)) {
            return currentCondition
                    += String.format("AND b.broker_name = '%s' ", broker);
        } else if (isNotEmpty(broker)) {
            return String.format("WHERE b.broker_name = '%s' ", broker);
        }
        return currentCondition;
    }

    /**
     * Validate that a String is of SQL date format - notifies View if not.
     *
     * @param date the String to checked
     * @return true if valid date, false otherwise
     */
    private boolean checkDateValues(String date) {
        if (isNotEmpty(date)) {
            if (!isValidDate(date)) {
                setChanged();
                notifyObservers(false);
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * Validate that a String is of SQL date format.
     *
     * @param date the String to checked
     * @return true if valid date, false otherwise
     */
    private boolean isValidDate(String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Validates that a String contains characters.
     *
     * @param string the String to be checked
     * @return true if contains any values, false otherwise
     */
    private boolean isNotEmpty(String string) {
        return (!string.trim().isEmpty() && string != null);
    }
}

