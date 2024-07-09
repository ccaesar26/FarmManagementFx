package org.example.farmmanagementfx.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import org.example.farmmanagementfx.controller.CultureTableController;
import org.example.farmmanagementfx.entity.Culture;
import org.example.farmmanagementfx.model.CultureTableModel;
import org.example.farmmanagementfx.presentation.util.UiUtil;

import java.io.IOException;

public class CultureTableView {

    private CultureView cultureView;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Culture> cultureTable;

    @FXML
    private TableColumn<Culture, String> nameColumn;

    private final CultureTableModel cultureTableModel;

    @FXML
    private void initialize() {
        cultureTable.setItems(cultureTableModel.getCultures());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        cultureTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("Selected culture: " + newValue);
                }
        );
    }

    public CultureTableView() {
        this.cultureTableModel = new CultureTableModel();
        var cultureTableController = new CultureTableController(this, cultureTableModel);
    }

    public static Region getView() {
        return UiUtil.loadFxml(CultureTableView.class.getResource("/org/example/farmmanagementfx/layout/CultureTableView.fxml"));
    }

    public void setAddButtonAction(Runnable action) {
        Platform.runLater(() -> addButton.setOnAction(event -> action.run()));
    }

    public void setEditButtonAction(Runnable action) {
        editButton.setOnAction(event -> action.run());
    }

    public void setDeleteButtonAction(Runnable action) {
        deleteButton.setOnAction(event -> action.run());
    }

    public Scene getScene() {
        return cultureTable.getScene();
    }

    public void setCultureView(CultureView cultureView) {
        this.cultureView = cultureView;
    }

    public CultureView getCultureView() {
        return cultureView;
    }
}
