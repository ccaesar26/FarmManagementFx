package org.example.farmmanagementfx.view;

import javafx.scene.layout.Region;
import org.example.farmmanagementfx.presentation.util.UiUtil;

public class HomeView {

    public static Region getView() {
        return UiUtil.loadFxml(HomeView.class.getResource("/org/example/farmmanagementfx/layout/HomeView.fxml"));
    }
}
