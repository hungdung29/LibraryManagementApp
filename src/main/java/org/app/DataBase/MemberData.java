package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.Member;

public class MemberData extends DataBaseAccessor {
    private final ObservableList<Member> members;

    public MemberData() {
        Connect();
        members = FXCollections.observableArrayList();
        getMembersInfo();
    }

    public ObservableList<Member> getMembers() {
        return members;
    }

    private void getMembersInfo() {
        // sql query
        members.add(new Member("tho", "Thọ" ,"00011", "Vinh Phuc"));
        members.add(new Member("phan", "Phan" ,"120200254", "Vinh Tuong"));
        members.add(new Member("ba", "Bá" ,"35425234", "Ngu Kien"));
        members.add(new Member("test", "Test","1252346", "sfgdfh"));
    }
}
