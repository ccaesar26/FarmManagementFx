package org.example.farmmanagementfx.view;

import javafx.scene.layout.Region;
import org.example.farmmanagementfx.presentation.util.UiUtil;

public class ReportView {

    public static Region getView() {
        return UiUtil.loadFxml(ReportView.class.getResource("/org/example/farmmanagementfx/layout/ReportView.fxml"));
    }
}
