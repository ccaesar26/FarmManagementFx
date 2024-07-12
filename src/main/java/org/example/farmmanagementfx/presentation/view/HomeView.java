package org.example.farmmanagementfx.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.farmmanagementfx.presentation.util.ResourceUtil;

public class HomeView extends BorderPane {

    public HomeView() {
        // Create the VBox layout and set its properties
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20.0);
        vbox.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));

        // Create the Label and apply style class
        Label welcomeLabel = new Label("Welcome to Farm Management!");
        welcomeLabel.getStyleClass().add("welcome-label");

        // Create the ImageView and set the image
        ImageView imageView = new ImageView();
        imageView.setFitHeight(400.0);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);

        Image image = new Image(ResourceUtil.getImagePath("FarmerLandscape.png"));
        imageView.setImage(image);

        // Add the Label and ImageView to the VBox
        vbox.getChildren().addAll(welcomeLabel, imageView);

        // Set the VBox to the center of the BorderPane
        this.setCenter(vbox);

        // Load the stylesheet
        this.getStylesheets().add(ResourceUtil.getStyleSheetPath("style.css"));
    }
}