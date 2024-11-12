package org.app.Object;

public class Member {
    public String username;
    public String phoneNumber;
    public String address;
    public int numberOfBorrowedBooks;

    public Member(String username, String phoneNumber, String address, int numberOfBorrowedBooks) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.numberOfBorrowedBooks = numberOfBorrowedBooks;
    }
}
