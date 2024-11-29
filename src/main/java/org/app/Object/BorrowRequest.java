package org.app.Object;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

public class BorrowRequest {
    private CheckBox checkBox;
    private String userName;
    private String bookTitle;
    private int bookID;
    private String dateRequested;

    public BorrowRequest(String userName, String bookTitle, String dateRequested) {
        this.checkBox = new CheckBox();
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.dateRequested = dateRequested;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(String dateRequested) {
        this.dateRequested = dateRequested;
    }
}
