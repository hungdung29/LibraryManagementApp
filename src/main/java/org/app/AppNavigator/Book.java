package org.app.AppNavigator;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private String description;
    private String pathToImage;
    private int pages;

    public Book(String title, String author, String publisher, String description, String pathToImage, int pages) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.pathToImage = pathToImage;
        this.pages = pages;
    }

    public Book() {
        title = "";
        author = "";
        publisher = "";
        description = "";
        pathToImage = "";
        pages = 0;
    }
}
