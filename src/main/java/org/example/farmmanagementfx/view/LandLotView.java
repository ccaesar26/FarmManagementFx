package org.example.farmmanagementfx.view;

import javafx.scene.layout.Region;
import org.example.farmmanagementfx.presentation.util.UiUtil;

public class LandLotView {

    public static Region getLandLotView() {
        return UiUtil.loadFxml(LandLotView.class.getResource("/org/example/farmmanagementfx/layout/LandLotView.fxml"));
    }
}
