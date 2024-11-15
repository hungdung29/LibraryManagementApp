package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.Book;

public class BookData extends DataBaseAccessor {
    public static void getDataAllBook(ObservableList<Book> books) {
        books.clear();
        // dummy data
        books.add(
                new Book("book 1", "phan tho", "1111", 5)
        );
        //--------
    }

    public static void getCommentOfBook() {

    }
}
