package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.Book;
import org.app.Object.Member;

public class MemberData extends DataBaseAccessor {
    private static ObservableList<Member> members;

    public MemberData() {
        connect();
        members = FXCollections.observableArrayList();
        getMembersInfo();
    }

    public static ObservableList<Member> getMembers() {
        return members;
    }

    private void getMembersInfo() {
        // sql query

        Book testBook = new Book("De men phieu luu ky", "To Hoai", "123 hihi", "NXB Kim Dong");
        ObservableList<Book> test = FXCollections.observableArrayList(testBook);
        members.add(new Member("tho", "00011", "Vinh Phuc", test));
        members.add(new Member("phan", "120200254", "Vinh Tuong", test));
        members.add(new Member("ba", "35425234", "Ngu Kien", test));
        members.add(new Member("test", "1252346", "sfgdfh", test));
    }
}
