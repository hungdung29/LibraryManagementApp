package org.app.DataBase;

// statement to create database in this file.
// Call 1 time, but it is necessary for put it here.

import org.app.Object.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDataBase extends DataBaseAccessor {
    public static void createDatabase() {
        String sql = """
            CREATE TABLE IF NOT EXISTS example_table (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                age INTEGER
            );
        """;

        try (Statement stmt = connection.createStatement()) {
            // Execute the SQL statement
            stmt.execute(sql);
            System.out.println("Table 'example_table' has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addSample() {
        String sql = """
            insert into example_table (name, age) values 
                ('tho', 18), ('phan', 19);
        """;

        try (Statement stmt = connection.createStatement()) {
            // Execute the SQL statement
            stmt.execute(sql);
            System.out.println("Insert successful.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showSample() {
        String sql = """
            select * from example_table;
        """;

        try (Statement stmt = connection.createStatement()) {
            // system out all content in table
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


/**
 * USE `library` ;
 *
 * -- -----------------------------------------------------
 * -- Table `library`.`book`
 * -- -----------------------------------------------------
 * CREATE TABLE IF NOT EXISTS `library`.`book` (
 *   `idBook` INT UNSIGNED NOT NULL AUTO_INCREMENT,
 *   `title` VARCHAR(45) NOT NULL,
 *   `author` VARCHAR(45) NULL DEFAULT NULL,
 *   `description` VARCHAR(45) NULL DEFAULT NULL,
 *   `price` FLOAT UNSIGNED NULL DEFAULT NULL,
 *   `content` TEXT NULL DEFAULT NULL,
 *   `catalog` TEXT NULL DEFAULT NULL,
 *   `IBNS` INT UNSIGNED NOT NULL,
 *   `publisher` VARCHAR(45) NULL DEFAULT 'NXB GD',
 *   `remaining` INT ZEROFILL UNSIGNED NOT NULL,
 *   `image_path` VARCHAR(45) NULL,
 *   PRIMARY KEY (`idBook`),
 *   UNIQUE INDEX `IBNS_UNIQUE` (`IBNS` ASC) VISIBLE)
 * ENGINE = InnoDB
 * AUTO_INCREMENT = 2
 * DEFAULT CHARACTER SET = utf8mb3;
 *
 *
 * -- -----------------------------------------------------
 * -- Table `library`.`user`
 * -- -----------------------------------------------------
 * CREATE TABLE IF NOT EXISTS `library`.`user` (
 *   `id_user` INT UNSIGNED NOT NULL AUTO_INCREMENT,
 *   `Name` VARCHAR(45) NOT NULL,
 *   `accountName` VARCHAR(45) NOT NULL,
 *   `password` VARCHAR(45) NOT NULL,
 *   `phoneNumber` VARCHAR(45) NOT NULL,
 *   `address` VARCHAR(45) NOT NULL,
 *   `gmail` VARCHAR(45) NULL,
 *   `birthday` DATETIME NULL,
 *   PRIMARY KEY (`id_user`),
 *   UNIQUE INDEX `id_user_UNIQUE` (`id_user` ASC) VISIBLE,
 *   UNIQUE INDEX `account_UNIQUE` (`accountName` ASC) VISIBLE)
 * ENGINE = InnoDB
 * DEFAULT CHARACTER SET = utf8mb3;
 *
 *
 * -- -----------------------------------------------------
 * -- Table `library`.`borrows`
 * -- -----------------------------------------------------
 * CREATE TABLE IF NOT EXISTS `library`.`borrows` (
 *   `id_borrow` INT UNSIGNED NOT NULL AUTO_INCREMENT,
 *   `date_borrow` DATE NOT NULL,
 *   `date_give_back` DATE NULL DEFAULT NULL,
 *   `book_idBook` INT UNSIGNED NOT NULL,
 *   `user_id_user` INT UNSIGNED NOT NULL,
 *   `comment` VARCHAR(45) NULL,
 *   PRIMARY KEY (`id_borrow`),
 *   INDEX `fk_borrows_book` (`book_idBook` ASC) VISIBLE,
 *   INDEX `fk_borrows_user1` (`user_id_user` ASC) VISIBLE,
 *   CONSTRAINT `fk_borrows_book`
 *     FOREIGN KEY (`book_idBook`)
 *     REFERENCES `library`.`book` (`idBook`),
 *   CONSTRAINT `fk_borrows_user1`
 *     FOREIGN KEY (`user_id_user`)
 *     REFERENCES `library`.`user` (`id_user`))
 * ENGINE = InnoDB
 * DEFAULT CHARACTER SET = utf8mb3;
 */