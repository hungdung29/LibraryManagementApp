package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.Member;

public class MemberData extends DataBaseAccessor {
    private final ObservableList<Member> members;

    public MemberData() {
        connect();
        members = FXCollections.observableArrayList();
        getMembersInfo();
    }

    public ObservableList<Member> getMembers() {
        return members;
    }

    private void getMembersInfo() {
        // sql query
        members.add(new Member("tho", "00011", "Vinh Phuc", 12));
        members.add(new Member("phan", "120200254", "Vinh Tuong", 1));
        members.add(new Member("ba", "35425234", "Ngu Kien", 2));
        members.add(new Member("test", "1252346", "sfgdfh", 21));
    }
}
