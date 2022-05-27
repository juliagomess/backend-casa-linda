module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}