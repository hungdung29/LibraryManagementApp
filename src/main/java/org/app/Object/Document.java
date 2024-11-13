package org.app.Object;

public abstract class Document {

    private String title;
    private String author;
    private String documentID;
    private String description;
    private double price;
    private String image_path;
    public String additionDateTime;
    public int remaining;

    /**
     * Constructor.
     * @param title Title of Docs.
     * @param author Author of Docs.
     * @param documentID ID of Docs.
     */
    public Document(String title, String author, String documentID) {
        this.title = title;
        this.author = author;
        this.documentID = documentID;
        this.description = "N/A";
        this.price = 0.00;
        this.additionDateTime = "01-01-2024";
        this.remaining = 1;
    }

    public Document(String title, String author, String documentID, int remaining) {
        this.title = title;
        this.author = author;
        this.documentID = documentID;
        this.description = "N/A";
        this.price = 0.00;
        this.additionDateTime = "01-01-2024";
        this.remaining = remaining;
    }

    /**
     * Constructor.
     * @param documentID id.
     * @param title Title of Docs.
     * @param author Author of Docs.
     * @param additionDateTime additionDateTime.
     */
    public Document(String documentID, String title, String author, String additionDateTime) {
        this.documentID = documentID;
        this.title = title;
        this.author = author;
        this.description = "N/A";
        this.price = 0.00;
        this.additionDateTime = additionDateTime;
    }

    /**
     * Constructor.
     * @param documentID id.
     * @param title Title of Docs.
     * @param author Author of Docs.
     * @param description Description of Docs.
     * @param additionDateTime additionDateTime.
     */
    public Document(String documentID, String title, String author, String description,
                    String additionDateTime) {
        this.documentID = documentID;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = 0.00;
        this.additionDateTime = additionDateTime;
    }

    /**
     * Constructor.
     * @param documentID id.
     * @param title Title of Docs.
     * @param author Author of Docs.
     * @param price Price of Docs.
     * @param additionDateTime additionDateTime.
     */
    public Document(String documentID, String title, String author, double price,
                    String additionDateTime) {
        this.documentID = documentID;
        this.title = title;
        this.author = author;
        this.description = "N/A";
        this.price = price;
        this.additionDateTime = additionDateTime;
    }

    /**
     * Constructor.
     * @param documentID id.
     * @param title Title of Docs.
     * @param author Author of Docs.
     * @param description Description of Docs.
     * @param price Price of Docs.
     * @param image_path Image of Docs.
     * @param additionDateTime additionDateTime.
     */
    public Document(String documentID, String title, String author,
                    String description, double price, String image_path,
                    String additionDateTime) {
        this.documentID = documentID;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.image_path = image_path;
        this.additionDateTime = additionDateTime;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAdditionDateTime() {
        return additionDateTime;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAdditionDateTime(String additionDateTime) {
        this.additionDateTime = additionDateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    //public abstract void printInfo();
}