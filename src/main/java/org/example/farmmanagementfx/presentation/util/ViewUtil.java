package org.example.farmmanagementfx.presentation.util;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.example.farmmanagementfx.data.util.DateUtil;

import java.util.List;

public class ViewUtil {

    public static double MAX_TABLE_WIDTH = 552;

    public static Region createTitlePane(String title, String imageName) {
        var pane = new BorderPane();
        pane.getStyleClass().add("tab-left-container");

        var titleLabel = new Label(title);
        titleLabel.getStyleClass().add("tab-title");
        var titlePane = new HBox(titleLabel);
        titlePane.setAlignment(Pos.CENTER);
        pane.setTop(titlePane);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(160.0);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        Image image = new Image(ResourceUtil.getImagePath(imageName));
        imageView.setImage(image);
        pane.setBottom(imageView);

        pane.getStylesheets().add(ResourceUtil.getStyleSheetPath("style.css"));

        return pane;
    }

    @SafeVarargs
    public static <S> Region createTablePane(
            String title,
            Button deleteButton,
            TableView<S> tableView,
            TableColumn<S, ?>... tableViews) {
        var tablePane = new GridPane();

        tablePane.setHgap(8);
        tablePane.setVgap(8);
        tablePane.setAlignment(Pos.CENTER);

        var titleLabel = new Label(title);
        titleLabel.getStyleClass().add("form-subtitle");
        tablePane.add(titleLabel, 0, 0, 2, 1);

        tableView.setMaxWidth(Double.MAX_VALUE);

        for (var tableColumn : tableViews) {
            tableColumn.setPrefWidth(MAX_TABLE_WIDTH / tableViews.length);
            tableColumn.getStyleClass().add("table-column");
            tableView.getColumns().add(tableColumn);
        }

        tablePane.add(tableView, 0, 1, 2, 1);

        FlowPane bottomPane = new FlowPane();
        bottomPane.setPrefWidth(Double.MAX_VALUE);
        bottomPane.setAlignment(Pos.CENTER_RIGHT);
        bottomPane.setHgap(8);

        deleteButton.setId("deleteButton");
        deleteButton.getStyleClass().add("form-button");

        bottomPane.getChildren().add(deleteButton);
        tablePane.add(bottomPane, 0, 2, 2, 1);

        tablePane.getStylesheets().add(ResourceUtil.getStyleSheetPath("style.css"));

        return tablePane;
    }

    public static Region createFormPane(
            String entity,
            Label formTitleLabel,
            Button saveButton,
            Button discardButton,
            List<String> labels,
            Control... controls) {
        var formPane = new GridPane();

        formPane.setHgap(8);
        formPane.setVgap(8);
        formPane.setAlignment(Pos.CENTER);
        formPane.getStyleClass().add("form-container");

        formTitleLabel.getStyleClass().add("form-subtitle");

        formPane.add(formTitleLabel, 0, 0, 2, 1);

        for (var i = 0; i < controls.length; i++) {
            var controlLabel = new Label(labels.get(i).substring(0, 1).toUpperCase() + labels.get(i).substring(1));
            controlLabel.getStyleClass().add("form-label");

            switch (controls[i]) {
                case ComboBox<?> comboBox ->
                        comboBox.setPromptText("Select the " + entity.toLowerCase() + "'s " + labels.get(i));
                case DatePicker datePicker -> {
                    datePicker.setPromptText("Select the " + entity.toLowerCase() + "'s " + labels.get(i));
                    datePicker.setConverter(DateUtil.getDateConverter());
                }
                case TextField textField ->
                        textField.setPromptText("Enter the " + entity.toLowerCase() + "'s " + labels.get(i));
                case Spinner<?> spinner -> {
                    spinner.setPromptText("Enter the " + entity.toLowerCase() + "'s " + labels.get(i) + " (square meters)");
                    spinner.setEditable(true);
                }
                default -> {
                }
            }

            controls[i].getStyleClass().add("form-text-field");
            controls[i].setMaxWidth(Double.MAX_VALUE);

            formPane.add(controlLabel, 0, i + 1);
            formPane.add(controls[i], 1, i + 1);
        }

        var buttonPane = new FlowPane();
        buttonPane.setPrefWidth(Double.MAX_VALUE);
        buttonPane.setHgap(8);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);

        saveButton.getStyleClass().add("form-button");
        discardButton.getStyleClass().add("form-button");

        buttonPane.getChildren().addAll(saveButton, discardButton);
        formPane.add(buttonPane, 0, controls.length + 1, 2, 1);

        formPane.getStylesheets().add(ResourceUtil.getStyleSheetPath("style.css"));

        return formPane;
    }
}
