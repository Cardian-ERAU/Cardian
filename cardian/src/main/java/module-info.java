module com.cardian {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive javafx.graphics;
    requires java.desktop;
    requires htmlunit;
    opens com.cardian to javafx.fxml;
    exports com.cardian;
}
