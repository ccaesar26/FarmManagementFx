package org.example.farmmanagementfx.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import org.example.farmmanagementfx.presentation.util.UiUtil;

import java.io.IOException;

public class CultureFormView {

    public static Region getView() {
        return UiUtil.loadFxml(CultureFormView.class.getResource("/org/example/farmmanagementfx/layout/CultureFormView.fxml"));
    }
}
