package org.app.Object;

public class Member {
    private String username;
    private String phoneNumber;
    private String address;
    private int numberOfBorrowedBooks;

    public Member(String username, String phoneNumber, String address, int numberOfBorrowedBooks) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.numberOfBorrowedBooks = numberOfBorrowedBooks;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public int getNumberOfBorrowedBooks() {
        return numberOfBorrowedBooks;
    }
}
