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

    public static Vector<String> getShareColumnNames() {
        return new Vector<>(Arrays.asList(shareColumnNames));
    }

    public static Vector<String> getTradeColumnNames() {
        return new Vector<>(Arrays.asList(tradeColumnNames));
    }
}
