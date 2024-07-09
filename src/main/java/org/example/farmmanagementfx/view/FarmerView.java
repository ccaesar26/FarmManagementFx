package org.example.farmmanagementfx.view;

import javafx.scene.layout.Region;
import org.example.farmmanagementfx.presentation.util.UiUtil;

public class FarmerView {

    public static Region getView() {
        return UiUtil.loadFxml(FarmerView.class.getResource("/org/example/farmmanagementfx/layout/FarmerView.fxml"));
    }
}
