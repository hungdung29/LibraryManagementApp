package org.app.Controller;

import javafx.scene.control.Tab;

public class UserController {
    public Tab user_info;
    public Tab borrow_book;
    public Tab return_book;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }
}