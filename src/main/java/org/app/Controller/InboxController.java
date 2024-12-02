package org.app.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.app.DataBase.MessageData;
import org.app.Object.Book;
import org.app.Object.Message;

import java.net.URL;
import java.util.ResourceBundle;

public class InboxController implements Initializable {
    public TextField searchFrTextField;
    public Button findButton;

    public ListView<String> friendListView;
    ObservableList<String> friendList = FXCollections.observableArrayList();

    public TextField messageTextField;
    public Button sendButton;

    public ScrollPane messageScrollPane;
    public VBox messageContainer;

    public ObservableList<Message> messageList = FXCollections.observableArrayList();

    public String username, partner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username = SignInViewController.username;
        partner = "";

        messageContainer.heightProperty().addListener((observable, oldValue, newValue) ->
                messageScrollPane.setVvalue(1.0)
        );
        messageContainer.setVisible(false);

        friendList = MessageData.getListFriend(username);
        friendListView.setItems(friendList);

        friendListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                messageContainer.setVisible(true);
                messageContainer.getChildren().clear();

                partner = newValue;
                showMessage();
            } else {
                messageContainer.setVisible(false);
            }
        });
    }

    private void showMessage() {
        messageList =  MessageData.getMessageData(username, partner);

        for (Message m : messageList) {
            addMessage(m.getContent(), (m.getReceiver().equals(partner)));
        }
    }

    private void addMessage(String message, boolean isUser) {
        // Create an HBox for the message
        HBox messageBox = new HBox();
        messageBox.prefWidthProperty().bind(messageContainer.widthProperty());

        messageBox.setStyle("-fx-background-color: #ffffff;");
        messageBox.setAlignment(isUser ? Pos.BOTTOM_RIGHT : Pos.BOTTOM_LEFT);

        // Create a TextFlow to hold the message text
        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);

        // Style the message box
        textFlow.setStyle("-fx-background-color: " + (isUser ? "#D1F8D1" : "#ffffff") + "; " +
                "-fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        textFlow.setMaxWidth(500); // Set a maximum width for wrapping

        // Add the TextFlow to the HBox
        messageBox.getChildren().add(textFlow);

        // Add the message box to the message container
        messageContainer.getChildren().add(messageBox);
    }

    public void onFindButtonClicked(ActionEvent actionEvent) {
//        String keyword = searchFrTextField.getText();
//        // restore shown books = entire book
////        getDataEntireBook();
//
//        if (keyword == null || keyword.isEmpty()) {
//            return;
//        }
//        FilteredList<String> filteredBooks = new FilteredList<>(entireBooks, book ->
//                book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
//                        book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
//                        book.getIsbn().toLowerCase().contains(keyword.toLowerCase())
//        );
//
//        shownBooks.setAll(filteredBooks);
    }

    public void onSendButtonClicked(ActionEvent actionEvent) {
        String message = messageTextField.getText().trim();
        if (!message.isEmpty()) {
            // Add user message (right-aligned)
            addMessage(message, true);
            MessageData.addMessageToDataBase(message, username, partner);

            messageTextField.clear();
            friendList = MessageData.getListFriend(username);
            friendListView.setItems(friendList);
        }
    }
}
