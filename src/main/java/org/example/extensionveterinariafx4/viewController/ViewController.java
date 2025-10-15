package org.example.extensionveterinariafx4.viewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.extensionveterinariafx4.App;

import java.io.IOException;

public class ViewController {

    public static void cambiarVista(Stage stage, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
