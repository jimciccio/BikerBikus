module ferranti.ciao {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;


    opens ferranti.ciao to javafx.fxml;
    exports ferranti.ciao;
}