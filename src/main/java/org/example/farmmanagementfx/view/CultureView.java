package org.example.farmmanagementfx.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import org.example.farmmanagementfx.presentation.util.UiUtil;

public class CultureView {
    @FXML
    private BorderPane mainPane;

    public static Region getView() {
        return UiUtil.loadFxml(CultureView.class.getResource("/org/example/farmmanagementfx/layout/CultureView.fxml"));
    }

    public void setMainPaneContent(Region region) {
        mainPane.setCenter(region);
    }
}
