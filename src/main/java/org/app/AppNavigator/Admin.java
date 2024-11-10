package org.app.AppNavigator;

import java.util.Scanner;

public class Admin extends User {
    public Admin() {
        super();
    }

    protected void navigate() {
        System.out.println("Admin Menu");
        System.out.println("1. Show All Books");
        System.out.println("2. Show All user");
        System.out.println("3. Add Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Edit Book");
        System.out.println("6. Logout");
        System.out.println("7. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1: showAllBooks();     break;
            case 2: showAllUsers();     break;
            case 3: addBook();          break;
            case 4: deleteBook();       break;
            case 5: editBook();         break;
            case 6: logOut = true;      break;
            case 7: exit = true;        break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void showAllBooks() {

    }

    private void showAllUsers() {

    }

    private void addBook() {

    }

    private void deleteBook() {

    }

    private void editBook() {

    }
}
