package org.app.AppNavigator;

import java.util.Scanner;

public class NormUser extends User {
    public NormUser() {
        super();
    }

    protected void navigate() {
        System.out.println("Options Menu");
        System.out.println("1. Show Information");
        System.out.println("2. Show All Books");
        System.out.println("3. Borrow Books");
        System.out.println("4. Show all borrowed Books");
        System.out.println("5. Return Books");
        System.out.println("6. Logout");
        System.out.println("7. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1: showInfo();             break;
            case 2: showAllBooks();         break;
            case 3: borrowBook();           break;
            case 4: showBorrowedBooks();    break;
            case 5: returnBook();           break;
            case 6: logOut = true;          break;
            case 7: exit = true;            break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void showInfo() {

    }

    public void showAllBooks() {

    }

    public void borrowBook() {

    }

    public void showBorrowedBooks() {

    }

    public void returnBook() {

    }
}
