module com.cardian {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires htmlunit;

    opens com.cardian to javafx.fxml;
    exports com.cardian;
}
