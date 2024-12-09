package org.app.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.app.BookAPI.GoogleBookSearch;
import org.app.DataBase.BookData;
import org.app.MainApp;
import org.app.Object.Book;
import org.app.Utils.Utils;

public class AddBookController extends BookController implements Initializable {
    public TextField searchTextField;
    public Button searchButton;
    public Button addBookButton;
    public Button backButton;

    public TableView bookTable;
    public TableColumn titleColumn;
    public TableColumn authorColumn;
    public TableColumn categoryColumn;
    public TableColumn publisherColumn;

    public Button importButton;

    public TextField titleField;
    public TextField authorField;
    public TextField publisherField;
    public TextField isbnField;
    public TextField quantityField;
    public TextField descriptionField;

    public VBox infoBookVBox;
    public ImageView bookImage;
    public Label isbnBookDetailLabel;
    public Label languageDetailLabel;
    public Label descriptionDetailLabel;

    public Label messageLabel;

    private Book addBook = null;
    private Book choosingBook = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
	   configTable();
	   infoBookVBox.setVisible(false);

	   // Add a listener for row selection
	   bookTable.getSelectionModel().selectedItemProperty().addListener(
			 (observable, oldValue, newValue) -> {
				if (newValue!=null) {
				    infoBookVBox.setVisible(true);

				    Book selectedBook = (Book) newValue;
				    handleBookSelection("admin", selectedBook);
				} else {
				    infoBookVBox.setVisible(false);
				}
			 });
    }

    /**
	* Handle back button clicked.
	*
	* @param actionEvent event
	*/
    public void onBackButtonClicked(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene("admin-view.fxml#adminTabPane#book_management");
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
    }

    /**
	* Handle search button clicked.
	*
	* @param actionEvent event
	*/
    public void onSearchButtonClicked(ActionEvent actionEvent) {
	   getDataEntireBook();
	   bookTable.setItems(entireBooks);
	   Utils.setTextAndShakingLabel(messageLabel, "Number of books found: " + entireBooks.size());
		Utils.setTextAndShakingLabel(messageLabel, "Number of books found: " + entireBooks.size());
    }

    /**
	* Config table view.
	*/
    public void configTable() {
	   bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	   titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
	   authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
	   categoryColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("catalog"));
	   publisherColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
    }

    /**
	* Handle add book button clicked.
	*
	* @param actionEvent event
	*/
    public void onAddBookButtonClicked(ActionEvent actionEvent) {
	   if (titleField.getText().isEmpty() || authorField.getText().isEmpty()
			 || publisherField.getText().isEmpty() || isbnField.getText().isEmpty()
			 || descriptionField.getText().isEmpty() || quantityField.getText().isEmpty()) {
		  Utils.setTextAndShakingLabel(messageLabel, "Please fill in all fields");
	   } else {
		  // Check book from API or not
		  if (addBook==null || (!addBook.getTitle().equals(titleField.getText())
				|| !addBook.getAuthor().equals(authorField.getText())
				|| !addBook.getPublisher().equals(publisherField.getText())
				|| !addBook.getIsbn().equals(isbnField.getText())
				|| !addBook.getDescription().equals(descriptionField.getText()))) {
			 Book newBook = new Book.Builder()
				    .setTitle(titleField.getText())
				    .setAuthor(authorField.getText())
				    .setPublisher(publisherField.getText())
				    .setIsbn(isbnField.getText())
				    .setDescription(descriptionField.getText())
				    .setRemaining(Integer.parseInt(quantityField.getText()))
				    .build();
			 BookData.addBook(newBook);
			 Utils.setTextAndShakingLabel(messageLabel, "Book was added successfully");
			 try {
				MainApp.navigateToScene("admin-view.fxml#adminTabPane#book_management");
			 } catch (Exception e) {
				e.printStackTrace();
			 }
		  } else {
			 addBook.setRemaining(Integer.parseInt(quantityField.getText()));
			 BookData.addBook(addBook);
			 Utils.setTextAndShakingLabel(messageLabel, "Book was added successfully");
			 try {
				MainApp.navigateToScene("admin-view.fxml#adminTabPane#book_management");
			 } catch (Exception e) {
				e.printStackTrace();
			 }
		  }
	   }
    }

    /**
	* Handle import button clicked.
	*
	* @param actionEvent event
	*/
    public void onImportButtonClicked(ActionEvent actionEvent) {
	   if (BookData.isBookExist(choosingBook.getIsbn())) {
		  Utils.setTextAndShakingLabel(messageLabel, "Book was found in library");
		  titleField.setText("");
		  authorField.setText("");
		  publisherField.setText("");
		  isbnField.setText("");
		  descriptionField.setText("");
		  quantityField.setText("");
		  return;
	   }
	   addBook = choosingBook;
	   titleField.setText(addBook.getTitle());
	   authorField.setText(addBook.getAuthor());
	   publisherField.setText(addBook.getPublisher());
	   isbnField.setText(addBook.getIsbn());
	   descriptionField.setText(addBook.getDescription());
	   Utils.setTextAndShakingLabel(messageLabel, "Book was imported successfully");
    }

    @Override
    public void handleBookSelection(String username, Book book) {
	   Utils.setTextAndShakingLabel(messageLabel, "");
	   choosingBook = book;
	   if (book.getImagePath()!=null) {
		  bookImage.setImage(new Image(book.getImagePath()));
	   } else {
		  bookImage.setImage(new Image("file:image/NoAvailable.jpg"));
	   }
	   isbnBookDetailLabel.setText("ISBN: " + book.getIsbn());
	   languageDetailLabel.setText("Language: " + book.getContent());
	   descriptionDetailLabel.setText("Description: " + book.getDescription());
    }

    @Override
    public void getDataEntireBook() {
	   String query = convertStringHaveSpacing(searchTextField.getText());
	   if (query.isEmpty()) {
		  Utils.setTextAndShakingLabel(messageLabel, "Please type in search field");
		  return;
	   }
	   Task<ObservableList<Book>> getBookTask = new Task<ObservableList<Book>>() {
		  @Override
		  protected ObservableList<Book> call() throws Exception {
			 return GoogleBookSearch.getBookFromAPI(query);
		  }
	   };
	   getBookTask.setOnSucceeded(event -> {
		  entireBooks.clear();
		  entireBooks = getBookTask.getValue();
		  bookTable.setItems(entireBooks);
		  if (entireBooks!=null) {
			 Utils.setTextAndShakingLabel(messageLabel, "Number of books found: " + entireBooks.size());
		  }
	   });

	   getBookTask.setOnFailed(event -> {
		  Throwable exception = getBookTask.getException();
		  exception.printStackTrace();
		  ;
		  Utils.setTextAndShakingLabel(messageLabel, "Error occurred during searching");
	   });

	   Thread getBookFromAPIThread = new Thread(getBookTask);
	   getBookFromAPIThread.setDaemon(true);
	   getBookFromAPIThread.start();

	   cloneListBook();
    }

    /**
	* Convert string have spacing.
	*
	* @param str string
	* @return string
	*/
    public String convertStringHaveSpacing(String str) {
	   return str.replace(" ", "+");
    }
}