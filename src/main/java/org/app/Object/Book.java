package org.app.Object;

import org.app.DataBase.BorrowData;

public class Book {
    private int idBook;
    private String title;
    private String author;
    private String isbn;
    private String description;
    private String content;
    private String catalog;
    private double price;
    private String imagePath;
    private int remaining;
    private String publisher;
    private int quantity;

    private Book(Builder builder) {
        this.idBook = builder.idBook;
        this.title = builder.title;
        this.author = builder.author;
        this.isbn = builder.isbn;
        this.description = builder.description;
        this.content = builder.content;
        this.catalog = builder.catalog;
        this.price = builder.price;
        this.imagePath = builder.imagePath;
        this.remaining = builder.remaining;
        this.publisher = builder.publisher;
        this.quantity = builder.quantity;
    }

    public int getIdBook() {
        return idBook;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getCatalog() {
        return catalog;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getRemaining() {
        return remaining;
    }

    public String getPublisher() {
        return publisher;
    }

    public static class Builder {
        private int idBook;
        private String title;
        private String author;
        private String isbn;
        private String description;
        private String content;
        private String catalog;
        private double price;
        private String imagePath;
        private int remaining;
        private String publisher;
        private int quantity;

        public Builder setIdBook(int idBook) {
            this.idBook = idBook;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCatalog(String catalog) {
            this.catalog = catalog;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Builder setRemaining(int remaining) {
            this.remaining = remaining;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public int getQuantity() {
        return BorrowData.getQuantity(isbn);
    }
}