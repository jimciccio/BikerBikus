module com.example.ciao {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;


    opens com.example.ciao to javafx.fxml;
    exports com.example.ciao;
}