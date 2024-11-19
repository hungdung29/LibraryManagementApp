package org.app.DataBase;

// statement to create database in this file.
// Call 1 time, but it is necessary for put it here.

import org.app.Object.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO:
// create database
// insert sample data
// method
// createDatabase
// addSample

public class CreateDataBase extends DataBaseAccessor {
    public static void createDatabase() {
        String query1 = """
            CREATE TABLE IF NOT EXISTS books (
                idBook INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                author TEXT,
                description TEXT,
                price REAL,
                content TEXT,
                catalog TEXT,
                ISBN INTEGER NOT NULL UNIQUE,
                publisher TEXT DEFAULT 'NXB GD',
                remaining INTEGER NOT NULL,
                image_path TEXT
            );
            """;
        String query2 = """
            CREATE TABLE IF NOT EXISTS users (
                id_user INTEGER PRIMARY KEY AUTOINCREMENT,
                Name TEXT NOT NULL,
                accountName TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                phoneNumber TEXT NOT NULL,
                address TEXT NOT NULL,
                gmail TEXT,
                birthday TEXT
            );
            """;
        String query3 = """        
            CREATE TABLE IF NOT EXISTS borrows (
               id_borrow INTEGER PRIMARY KEY AUTOINCREMENT,
               date_borrow DATE NOT NULL,
               date_give_back DATE,
               book_idBook INTEGER NOT NULL,
               user_username INTEGER NOT NULL,
               comment TEXT,
               FOREIGN KEY (book_idBook) REFERENCES book(idBook),
               FOREIGN KEY (user_username) REFERENCES user(accountName)
            );
        """;

        String query4 = """
                CREATE TABLE IF NOT EXISTS comments (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_username TEXT NOT NULL,
                book_ISBN INTEGER NOT NULL,
                comment TEXT NOT NULL,
                created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (book_ISBN) REFERENCES books (ISBN),
                FOREIGN KEY (user_username) REFERENCES user(accountName)
        );
        """; // add comments table

        try (Statement stmt = connection.createStatement()) {
//            // Execute the SQL statement
            stmt.execute(query1);
            stmt.execute(query2);
            stmt.execute(query3);
            stmt.execute(query4);

            System.out.println("Database has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addSample() {
        String query1 = """
            INSERT INTO books (title, author, description, price, content, catalog, ISBN, publisher, remaining) VALUES
            ('The Lord of the Rings', 'J.R.R. Tolkien', 'An epic fantasy adventure', 29.99, '...', 'Fantasy', '9780547991000', 'HarperCollins', 10),
            ('Pride and Prejudice', 'Jane Austen', 'A timeless love story', 14.99, '...', 'Romance', '9780141439501', 'Penguin Books', 15),
            ('To Kill a Mockingbird', 'Harper Lee', 'A classic tale of injustice', 12.99, '...', 'Fiction', '9780061120002', 'HarperCollins', 8),
            ('The Catcher in the Rye', 'J.D. Salinger', 'A coming-of-age story', 11.99, '...', 'Fiction', '9780316769403', 'Little, Brown and Company', 12),
            ('The Great Gatsby', 'F. Scott Fitzgerald', 'A tale of wealth and disillusionment', 15.99, '...', 'Fiction', '9780743273504', 'Scribner', 18),
            ('1984', 'George Orwell', 'A dystopian novel', 13.99, '...', 'Science Fiction', '9780452284205', 'Penguin Books', 9),
            ('The Hitchhiker Guide to the Galaxy', 'Douglas Adams', 'A comedic science fiction series', 12.99, '...', 'Science Fiction', '9745391806', 'Crown Publishing Group', 14),
            ('The Alchemist', 'Paulo Coelho', 'A spiritual journey', 11.99, '', 'Fiction', '9780062316807', 'HarperCollins', 16),
            ('The Little Prince', 'Antoine de Saint-Exupéry', 'A childrens story with profound meaning', 9.99, '...', 'Children s Literature', '9780679782908', 'Harcourt Brace Jovanovich', 20),
            ('The Art of War', 'Sun Tzu', 'A classic military strategy book', 14.99, '...', 'Non-Fiction', '9780140171509', 'Penguin Books', 11),
            ('Think and Grow Rich', 'Napoleon Hill', 'A self-help book on success', 12.99, '...', 'Self-Help', '9781585424710', 'Penguin Books', 13),
            ('The 7 Habits of Highly Effective People', 'Stephen Covey', 'A guide to personal and professional effectiveness', 15.99, '...', 'Self-Help', '9781585424711', 'Simon & Schuster', 17),
            ('The Power of Positive Thinking', 'Norman Vincent Peale', 'A motivational book on optimism', 11.99, '...', 'Self-Help', '9780671720212', 'Penguin Books', 12),
            ('The Secret', 'Rhonda Byrne', 'A book about the law of attraction', 12.99, '...', 'Self-Help', '9781582701713', 'Atria Books', 15),
            ('Rich Dad Poor Dad', 'Robert Kiyosaki', 'A personal finance book', 14.99, '...', 'Personal Finance', '9781612680014', 'Warner Books', 18),
            ('The Intelligent Investor', 'Benjamin Graham', 'A classic investment book', 18.99, '...', 'Investing', '9780060144115', 'HarperCollins', 10),
            ('The Lean Startup', 'Eric Ries', 'A guide to building successful startups', 16.99, '...', 'Business', '9780307887816', 'Crown Business', 12),
            ('The 48 Laws of Power', 'Robert Greene', 'A book on power and manipulation', 15.99, '...', 'Non-Fiction', '9780140280117', 'Viking', 14),
            ('The Prince', 'Niccolò Machiavelli', 'A political philosophy book', 12.99, '...', 'Non-Fiction', '9780140444218', 'Penguin Books', 16),
            ('The Art of Seduction', 'Robert Greene', 'A book on seduction and persuasion', 15.99, '...', 'Non-Fiction', '9780140280119', 'Viking', 11);
            """;
        String query2 = """
            INSERT INTO users (Name, accountName, password, phoneNumber, address, gmail, birthday) VALUES
            ('John Doe', 'johndoe123', 'password123', '123-456-7890', '123 Main St, Anytown, CA 12345', 'johndoe@example.com', '1990-01-01'),
            ('Jane Smith', 'janesmith456', 'password456', '456-789-0123', '456 Elm St, Anytown, CA 12345', 'janesmith@example.com', '1992-02-02'),
            ('Michael Johnson', 'michaeljohnson789', 'password789', '789-012-3456', '789 Oak St, Anytown, CA 12345', 'michaeljohnson@example.com', '1988-03-03'),
            ('Emily Brown', 'emilybrown012', 'password012', '012-345-6789', '012 Pine St, Anytown, CA 12345', 'emilybrown@example.com', '1995-04-04'),
            ('David Lee', 'davidlee345', 'password345', '345-678-9012', '345 Cedar St, Anytown, CA 12345', 'davidlee@example.com', '1998-05-05'),
            ('Sarah Kim', 'sarahkim678', 'password678', '678-901-2345', '678 Walnut St, Anytown, CA 12345', 'sarahkim@example.com', '1991-06-06'),
            ('Christopher Martinez', 'christophermartinez901', 'password901', '901-234-5678', '901 Willow St, Anytown, CA 12345', 'christophermartinez@example.com', '1987-07-07'),
            ('Olivia Taylor', 'oliviataylor234', 'password234', '234-567-8901', '234 Maple St, Anytown, CA 12345', 'oliviataylor@example.com', '1993-08-08'),
            ('Ethan Nguyen', 'ethannnguyen567', 'password567', '567-890-1234', '567 Birch St, Anytown, CA 12345', 'ethannnguyen@example.com', '1996-09-09'),
            ('Sophia Rodriguez', 'sophiarodriguez890', 'password890', '890-123-4567', '890 Oak St, Anytown, CA 12345', 'sophiarodriguez@example.com', '1999-10-10');
            """;
        String query3 = """
            INSERT INTO borrows (date_borrow, date_give_back, book_idBook, user_username, comment) VALUES
            ('2023-11-15', NULL, 1, 'johndoe123', NULL),
            ('2023-11-16', NULL, 2, 'johndoe123', NULL),
            ('2023-11-17', NULL, 3, 'johndoe123', NULL)
        """;

        String query4 = """
            INSERT INTO comments (user_username, book_ISBN, comment, created_at) VALUES
            ('johndoe123', '9745391806', 'This book is a hilarious and thought-provoking sci-fi adventure.', '2023-11-19'),
            ('johndoe123', '9780060144115', 'A classic investment guide that has stood the test of time.', '2023-11-20'),
            ('johndoe123', '9780061120002', 'A powerful story about racial injustice and the human spirit.', '2023-11-21'),
            ('johndoe123', '9780062316807', 'A beautiful and inspiring tale of self-discovery and following your dreams.', '2023-11-22'),
            ('johndoe123', '9780140171509', 'A timeless classic on military strategy and tactics.', '2023-11-23'),
            ('johndoe123', '9780140280117', 'A fascinating exploration of power, manipulation, and seduction.', '2023-11-24'),
            ('johndoe123', '9780140280119', 'A deep dive into the psychology of human behavior and influence.', '2023-11-25'),
            ('johndoe123', '9780140444218', 'A controversial but insightful look at political power and leadership.', '2023-11-26'),
            ('johndoe123', '9780141439501', 'A romantic masterpiece that explores themes of love, class, and societal expectations.', '2023-11-27'),
            ('johndoe123', '9780307887816', 'A groundbreaking book on entrepreneurship and innovation.', '2023-11-28');
        """;

        try (Statement stmt = connection.createStatement()) {
            // Execute the SQL statement
            stmt.execute(query1);
            stmt.execute(query2);
            stmt.execute(query3);
            stmt.execute(query4);

            System.out.println("Insert successful.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showSample() {

    }
}