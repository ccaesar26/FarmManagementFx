package org.example.farmmanagementfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.farmmanagementfx.presentation.controller.MainController;
import org.example.farmmanagementfx.presentation.view.MainView;

import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        var mainView = new MainView();
        var controller = new MainController(mainView);
        var scene = new Scene(mainView, 800, 600);
        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("image/AppIcon.png"))));
        stage.setTitle("Farm Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}