package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.User;

public class MemberData extends DataBaseAccessor {
    private final ObservableList<User> members;

    public MemberData() {
//        connect();
        members = FXCollections.observableArrayList();
        getMembersInfo();
    }

    public ObservableList<User> getMembers() {
        return members;
    }

    private void getMembersInfo() {
        // sql query
        members.add(new User("tho", "Thọ" ,"00011", "Vinh Phuc"));
        members.add(new User("phan", "Phan" ,"120200254", "Vinh Tuong"));
        members.add(new User("ba", "Bá" ,"35425234", "Ngu Kien"));
        members.add(new User("test", "Test","1252346", "sfgdfh"));
    }
}