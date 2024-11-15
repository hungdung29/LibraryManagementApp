package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.Book;

// TODO:
// get book data
// get all book in database
// get all book that specifier user have borrowed
// get comment of book

// method
// getDataAllBook(books) ==> insert directly list of books
// getDataBorrowedBook(username, books)
// getCommentOfBook(isbn)

public class BookData extends DataBaseAccessor {
    public static void getDataAllBook(ObservableList<Book> books) {
        books.clear();
        
        // dummy data
        books.add(
                new Book("book 1", "phan tho", "1111", 5)
        );
        //--------
    }

    public static ObservableList<String> getCommentOfBook(String isbn) {
        ObservableList<String> comments = FXCollections.observableArrayList();

        comments.add("nghi comment cho sach kho qua");
        comments.add("nghi comment cho sach kho qua abc d cho cau comment that dai cho xuong dong check size cua cai list");
        comments.add("nghi comment cho sach de qua");

        return comments;
    }
}
