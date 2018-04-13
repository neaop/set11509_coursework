package global.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;

/**
 * Strings utilised and displayed in Views.
 */
public class ViewStrings implements Serializable {

    private static String[] shareColumnNames = {
            "Share ID",
            "Trade Code",
            "Company Name",
            "Share Price",
            "Share Value",
            "Share Quantity"};

    private static String[] tradeColumnNames = {
            "Trade ID",
            "Company Name",
            "Company Code",
            "Trade Date",
            "Trade Value",
            "Seller",
            "Buyer",
            "Broker"
    };

    private static String[] brokerColumnNames = {
            "Broker ID",
            "Broker Name",
            "Broker Contact",
            "Broker Grade",
            "Broker Expertise"
    };

    private static String[] shareholderColumnNames = {
            "Company Name",
            "Company Code",
            "Shareholder Name",
            "Share Quantity"
    };

    /**
     * Returns the share View's column names as a Vector.
     *
     * @return vector of column names
     */
    public static Vector<String> getShareColumnNames() {
        return new Vector<>(Arrays.asList(shareColumnNames));
    }

    /**
     * Returns the trade View's column names as a Vector.
     *
     * @return vector of column names
     */
    public static Vector<String> getTradeColumnNames() {
        return new Vector<>(Arrays.asList(tradeColumnNames));
    }

    /**
     * Returns the broker View's column names as a Vector.
     *
     * @return vector of column names
     */
    public static Vector<String> getBrokerColumnNames() {
        return new Vector<>(Arrays.asList(brokerColumnNames));
    }

    /**
     * Returns the shareholder View's column names as a Vector.
     *
     * @return vector of column names
     */
    public static Vector<String> getShareholderColumnNames() {
        return new Vector<>(Arrays.asList(shareholderColumnNames));
    }
}
