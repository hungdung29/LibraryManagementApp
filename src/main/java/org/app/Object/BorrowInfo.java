package org.app.Object;

public class BorrowInfo {
    private String borrowDate;
    private String returnDate;

    private String comment;

    public BorrowInfo(String borrowDate, String returnDate, String comment) {
        this.comment = comment;
        this.returnDate = returnDate;
        this.borrowDate = borrowDate;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
