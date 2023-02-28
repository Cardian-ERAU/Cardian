module com.cardian {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cardian to javafx.fxml;
    exports com.cardian;
}
