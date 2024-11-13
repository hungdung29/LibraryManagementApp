package org.app.Object;

public class Book extends Document {
    private String publisher;

    public Book(String title, String author, String documentID, String publisher) {
        super(title, author, documentID);
        this.publisher = publisher;
    }

    public String getTitle() {
        return super.getTitle();
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public String getAuthor() {
        return super.getAuthor();
    }

    public void setBookAuthor(String author) {
        super.setAuthor(author);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookISBN() {
        return super.getDocumentID();
    }

    public void setBookISBN(String isbn) {
        super.setDocumentID(isbn);
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
