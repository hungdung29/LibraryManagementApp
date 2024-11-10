package org.app.AppNavigator;

import java.util.Scanner;

abstract public class User {
    protected final Scanner scanner;
    protected boolean exit;
    protected boolean logOut;

    public User() {
        scanner = new Scanner(System.in);
        exit = false;
        logOut = false;
        while (!exit && !logOut)       navigate();
    }

    abstract void navigate();

    public boolean isExit() {
        return exit;
    }
}
