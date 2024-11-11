package org.app.DataBase;

public class HandleUserAccount extends DataBaseAccessor {
    public static final int ACCOUNT_NOT_FOUND = -1;
    public static final int WRONG_PASSWORD = 0;
    public static final int LOG_IN_SUCCESS = 1;

    public static boolean isUsernameExist(String username) {
        return false;
    }

    public static int checkLogIn(String username, String password) {
        if (!isUsernameExist(username)) { return ACCOUNT_NOT_FOUND; }

        // check user account info in database

        return LOG_IN_SUCCESS;
    }

    public static void addAccount(String username, String password) {
        // add account to database
    }
}