module org.example.extensionveterinariafx4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.extensionveterinariafx4 to javafx.fxml;
    opens org.example.extensionveterinariafx4.controller to javafx.fxml;
    opens org.example.extensionveterinariafx4.model to javafx.base;

    exports org.example.extensionveterinariafx4;
    exports org.example.extensionveterinariafx4.controller;
}
