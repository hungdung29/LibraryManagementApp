package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.app.Object.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageData extends DataBaseAccessor {
//    private static boolean isUsernameExist(String username) {
//        PreparedStatement preparedStatement;
//        String query = "SELECT 1 FROM users WHERE accountName = ?";
//        try {
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return resultSet.next();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }

    public static ObservableList<Message> getMessageData(String user, String partner) {
        ObservableList<Message> messages = FXCollections.observableArrayList();;
        messages.add( new Message("sao", "admin", "tho") );

        return messages;
    }

    public static ObservableList<String> getListFriend(String username) {
        ObservableList<String> friends = FXCollections.observableArrayList();
        friends.add("admin");

        return friends;
    }

    public static void addMessageToDataBase(String message, String username, String partner) {
        
    }
}
