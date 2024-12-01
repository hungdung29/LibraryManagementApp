package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.app.Object.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageData extends DataBaseAccessor {
    public static ObservableList<Message> getMessageData(String user, String partner) {
        ObservableList<Message> messages = FXCollections.observableArrayList();

        String query = "select * from messages " +
                "where (sender = ? and receiver = ?) or (sender = ? and receiver = ?) " +
                "order by created_at";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, partner);
            preparedStatement.setString(3, partner);
            preparedStatement.setString(4, user);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                messages.add(
                        new Message(
                                resultSet.getString("content"),
                                resultSet.getString("sender"),
                                resultSet.getString("receiver"))
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    public static ObservableList<String> getListFriend(String username) {
        ObservableList<String> friends = FXCollections.observableArrayList();

        String query = "select accountName from users where accountName != ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                friends.add(resultSet.getString("accountName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return friends;
    }

    public static void addMessageToDataBase(String message, String username, String partner) {
        String query = "insert into messages (sender, receiver, content, created_at) VALUES (?, ?, ?, datetime('now'))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, partner);
            preparedStatement.setString(3, message);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
