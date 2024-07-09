package org.example.farmmanagementfx.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import org.example.farmmanagementfx.presentation.util.UiUtil;

import java.io.IOException;

public class MainView {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab homeTab;

    @FXML
    private Tab culturesTab;

    @FXML
    private Tab landLotsTab;

    @FXML
    private Tab farmersTab;

    @FXML
    private Tab reportsTab;

    public static Region getView() {
        return UiUtil.loadFxml(MainView.class.getResource("/org/example/farmmanagementfx/layout/MainView.fxml"));
    }

    public void setHomeTabContent(Region region) {
        homeTab.setContent(region);
    }

    public void setCulturesTabContent(Region region) {
        culturesTab.setContent(region);
    }

    public void setLandLotsTabContent(Region region) {
        landLotsTab.setContent(region);
    }

    public void setFarmersTabContent(Region region) {
        farmersTab.setContent(region);
    }

    public void setReportsTabContent(Region region) {
        reportsTab.setContent(region);
    }
}
