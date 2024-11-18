package org.app.Object;

public class Book {
    private int idBook;
    private String title;
    private String author;
    private String isbn;
    private String description;
    private String content;
    private String catalog;
    private double price;
    private String image_path;
    private int remaining;
    private String publisher;

    public Book(String title, String author, int idBook, String isbn,
                String description, String content, double price,
                String image_path, String catalog, int remaining, String publisher) {
        this.title = title;
        this.author = author;
        this.idBook = idBook;
        this.isbn = isbn;
        this.description = description;
        this.content = content;
        this.price = price;
        this.image_path = image_path;
        this.catalog = catalog;
        this.remaining = remaining;
        this.publisher = publisher;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}