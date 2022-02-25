module ferranti.ciao {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ferranti.ciao to javafx.fxml;
    opens ferranti.ciao.models to javafx.base;
    opens ferranti.ciao.controllers to javafx.fxml;
    exports ferranti.ciao;
}
