package org.app.Object;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Member {
    private String username;
    private String name;
    private String phoneNumber;
    private String address;
    ObservableList<Book> listOfBorrowedBooks;

    public Member(String username, String name, String phoneNumber, String address) {
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.listOfBorrowedBooks = FXCollections.observableArrayList();
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public ObservableList<Book> getListOfBorrowedBooks() {
        return listOfBorrowedBooks;
    }
}
