module com.codewithmosh.librarytry2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.codewithmosh.librarytry2 to javafx.fxml;
    exports com.codewithmosh.librarytry2;
}