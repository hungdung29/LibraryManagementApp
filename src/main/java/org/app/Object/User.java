package org.app.Object;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
//    ObservableList<Book> listOfBorrowedBooks;

    public User(String username, String password, String name, String phoneNumber, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
//        this.listOfBorrowedBooks = FXCollections.observableArrayList();
    }

    public User() {
        this.username = "nan";
        this.name = "nan";
        this.phoneNumber = "nan";
        this.address = "nan";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //    public ObservableList<Book> getListOfBorrowedBooks() {
//        return listOfBorrowedBooks;
//    }
}
