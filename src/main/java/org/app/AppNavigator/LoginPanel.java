package org.app.AppNavigator;

import org.app.DataBase.DataBaseAccessor;

import java.util.Scanner;

public class LoginPanel extends DataBaseAccessor {
    public static final int ADMIN = 1;
    public static final int NORM_USER = 2;
    public static final int USER_NOT_FOUND = 0;
    static final String ADMIN_PASSWORD = "baconsoi";

    private String user;
    private String password;
    private int userType;

    private boolean exit;

    private final Scanner scanner;

    public LoginPanel() {
        scanner = new Scanner(System.in);

        user = null;
        password = null;

        userType = USER_NOT_FOUND;

        Connect();

        while (!exit && userType == USER_NOT_FOUND)       navigate();
    }

    private void navigate() {
        System.out.println("Welcome to App Navigator");
        System.out.println("1. Sign in\n2. Sign up\n3. Exit");

        int choice = scanner.nextInt();     // nextInt not read character "\n"
        scanner.nextLine();                 // as cin.ignore in cpp
        switch (choice) {
            case 1:     signIn();       break;
            case 2:     signUp();       break;
            case 3:     exit = true;    break;
        }
    }


    /** In simple version. This method extract data from keyboard.
     *  Assign value for username and password.
     */
    private void extractData() {
        System.out.print("Username: ");
        user = scanner.nextLine();

        System.out.print("Password: ");
        password = scanner.nextLine();
    }

    /**
     *  Sign up operator.
     *  extractData to get username and password
     *  Check if username is existed.
     *  Add user data to DBMS.
     */
    private void signUp() {
        System.out.println("Please sign up to use triple wolves application ");
        extractData();

        // Add user and password to database
        //-----------YOUR CODE HERE-----------


    }

    /**
     * Check user and password in database.
     */
    private void signIn() {
        System.out.println("Please log in to use triple wolves application ");
        extractData();

        if (user.equals("admin")) {
            if (password.equals(ADMIN_PASSWORD)) {
                userType = ADMIN;
            } else {
                System.out.println("Wrong password. Please Try again.");
                signIn();
            }
        } else {
            // check username and password of normal user
            // ---------YOUR CODE HERE-------------------
            userType = NORM_USER;
        }
    }

    public int getUserType() {
        return userType;
    }

    public boolean isExit() {
        return exit;
    }
}
