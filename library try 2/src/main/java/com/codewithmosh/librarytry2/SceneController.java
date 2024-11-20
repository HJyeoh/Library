package com.codewithmosh.librarytry2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SceneController {

    public static String filePath = "D:\\IntelliJ SAVING\\library.txt";
    private Stage stage;
    private Scene scene;
    private Parent root;

    private void switchScene(ActionEvent event, String fxmlFile, String styleClass) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        root.getStyleClass().add(styleClass); // Add style

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("PageStyling.css").toExternalForm()); // Link CSS
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "MainPage.fxml", "main-page");
    }

    public void switchToUserMode(ActionEvent event) throws IOException {
        switchScene(event, "UserMode.fxml", "user-mode");
    }

    public void switchToLibrarianMode(ActionEvent event) throws IOException {
        switchScene(event, "LibrarianMode.fxml", "librarian-mode");
    }

    public void switchToBorrowBookPage(ActionEvent event) throws IOException {
        switchScene(event, "BorrowBook.fxml", "borrow-book");
    }


    // Library Searched Book Display
    @FXML
    private TextField searchTextField;
    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label isbnLabel;
    @FXML
    private Label availabilyLabel;
    @FXML
    private Label borrowerNameLabel;


    public void search() {
        String searchTerm = searchTextField.getText();
        String[] result = Library.SearchFunction(filePath, searchTerm);

        if (result != null) {
            titleLabel.setText("Title:  " + result[0]);
            authorLabel.setText("Author:  " + result[1]);
            isbnLabel.setText("ISBN:  " + result[2]);
            availabilyLabel.setText("Availability:  " + result[3]);
            borrowerNameLabel.setText("Borrower Name:  " + result[4]);
        } else {
            titleLabel.setText("No match found.");
            authorLabel.setText("");
            isbnLabel.setText("");
            availabilyLabel.setText("");
            borrowerNameLabel.setText("");
        }
    }

    // Library Book List Display
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> availabilityColumn;
    @FXML
    private TableColumn<Book, String> borrowerNameColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;

    public void displayLibraryBook() {
        List<Book> books = Book.displayLibraryBook(filePath);

        if (books != null && !books.isEmpty()) {
            // check if books are being loaded
            System.out.println("Loaded books: " + books.size());

            // set up the columns to map to the Book properties
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
            borrowerNameColumn.setCellValueFactory(new PropertyValueFactory<>("borrowerName"));

            // set observable list to the table
            ObservableList<Book> observableBooks = FXCollections.observableArrayList(books);
            table.setItems(observableBooks);
        } else {
            // if no books are found
            System.out.println("No books found or empty list.");
        }
    }

    // Add Book
    @FXML
    private TextField addTitleField;
    @FXML
    private TextField addAuthorField;
    @FXML
    private TextField addIsbnField;
    @FXML
    private TextField addAvailabilityField;
    @FXML
    private TextField addBorrowerNameField;

    public void addBook() {
        // Get the book details from the fields
        ArrayList<String> bookDetails = new ArrayList<>();
        bookDetails.add(addTitleField.getText());
        bookDetails.add(addAuthorField.getText());
        bookDetails.add(addIsbnField.getText());
        bookDetails.add(addAvailabilityField.getText());
        bookDetails.add(addBorrowerNameField.getText());

        // Write the book details to the file without overwriting
        Library.addBooksToFile(bookDetails, filePath);

        // Clear the fields after saving
        addTitleField.clear();
        addAuthorField.clear();
        addIsbnField.clear();
        addAvailabilityField.clear();
        addBorrowerNameField.clear();
    }
}


