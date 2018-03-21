package global;

// TODO - find better place for these
public class StaticMethods {

    public static double convertPenniesToPounds(int pennies) {
        double pounds;
        pounds = (double) pennies / 100;
        return pounds;
    }

    public static int convertPoundsToPennies(double pounds) {
        int pennies;
        pennies = (int) pounds * 100;
        return pennies;
    }
}
