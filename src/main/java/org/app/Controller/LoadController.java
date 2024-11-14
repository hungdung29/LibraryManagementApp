package org.app.Controller;

import org.app.DataBase.DataBaseAccessor;
import java.util.ResourceBundle;

public class LoadController {

    public void initialize(ResourceBundle resourceBundle) {
        DataBaseAccessor.checkTable("User", "CREATE TABLE `library`.`user` (`Name` TEXT NULL, `Username` TEXT NOT NULL, `Password` TEXT NULL, `Usertype` TEXT NOT NULL, `PhoneNumber` TEXT NULL, `Address` TEXT NULL, PRIMARY KEY (`Username`)) ENGINE = InnoDB;  ");
    }
}
