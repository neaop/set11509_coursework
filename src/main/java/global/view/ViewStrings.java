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

    public static Vector<String> getShareColumnNames() {
        return new Vector<>(Arrays.asList(shareColumnNames));
    }

}
