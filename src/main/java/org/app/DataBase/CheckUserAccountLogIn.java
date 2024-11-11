package org.app.DataBase;

public class CheckUserAccountLogIn extends DataBaseAccessor {
    public static final int ACCOUNT_NOT_FOUND = -1;
    public static final int WRONG_PASSWORD = 0;
    public static final int LOG_IN_SUCCESS = 1;

    public static int checkLogIn(String username, String password) {
        // check user account info in database

        return LOG_IN_SUCCESS;
    }
}