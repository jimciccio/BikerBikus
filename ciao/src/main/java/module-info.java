module com.example.ciao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ciao to javafx.fxml;
    exports com.example.ciao;
}