package org.app.DataBase;

public class HandleUserAccount extends DataBaseAccessor {
    public static final int ACCOUNT_NOT_FOUND = -1;
    public static final int WRONG_PASSWORD = 0;
    public static final int NORM_USER_LOG_IN_SUCCESS = 1;
    public static final int ADMIN_LOG_IN_SUCCESS = 2;

    /**
     * Check whether username is existed in database
     */
    private boolean isUsernameExist(String username) {
        connect();
        return true;
    }

    /**
     * Check account information and type of user.
     * @param username username
     * @param password pw
     * @return type of user
     */
    public int checkLogIn(String username, String password) {
        connect();
        if (!isUsernameExist(username)) { return ACCOUNT_NOT_FOUND; }

        // check user account info in database

        return NORM_USER_LOG_IN_SUCCESS;
    }

    public void addAccount(String username, String password) {
        connect();
        // add account to database
    }

    public boolean checkValidAccount(String username, String password, String cfPwText) {
        if (isUsernameExist(username)) {
            return false;
        }

        if (password == null || password.isEmpty()) {
            return false;
        }

        if ( !password.equals(cfPwText) ) {
            return false;
        }


        // check valid and strong password
        return true;
    }
}