package global.view;

import java.util.Arrays;
import java.util.Vector;

public class ViewStrings {

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

    public static Vector<String> getShareColumnNames() {
        return new Vector<>(Arrays.asList(shareColumnNames));
    }

    public static Vector<String> getTradeColumnNames() {
        return new Vector<>(Arrays.asList(tradeColumnNames));
    }

    public static Vector<String> getBrokerColumnNames() {
        return new Vector<>(Arrays.asList(brokerColumnNames));
    }
}
