package main;

import user.UserInit;

import java.sql.SQLException;

public class Main {
    private static String name = "test";
    private static String pass = "test";

    public static void main(String[] args) throws SQLException {
        UserInit.initaliseUserForm();

    }
}
