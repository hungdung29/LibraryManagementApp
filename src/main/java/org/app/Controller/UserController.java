package org.app.Controller;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import org.app.Object.Member;
import org.app.DataBase.MemberData;

public class UserController {
    public Tab user_info;
    public Tab borrow_book;
    public Tab return_book;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public Member getCurrentlyMember() {
        return MemberData.getMembers().stream().filter(member -> member.getUsername().equals(username)).findFirst().orElse(null);
    }


}