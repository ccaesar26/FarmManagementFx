package org.example.farmmanagementfx.presentation.view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class MainView extends BorderPane {

    private final TabPane tabPane;
    private final Tab homeTab;
    private final Tab culturesTab;
    private final Tab landLotsTab;
    private final Tab farmersTab;

    public MainView() {
        tabPane = new TabPane();
        homeTab = new Tab("Home");
        culturesTab = new Tab("Cultures");
        landLotsTab = new Tab("Land Lots");
        farmersTab = new Tab("Farmers");

        tabPane.getTabs().addAll(homeTab, culturesTab, landLotsTab, farmersTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        setCenter(tabPane);
    }

    public void setHomeTabContent(Region content) {
        homeTab.setContent(content);
    }

    public void setCulturesTabContent(Region content) {
        culturesTab.setContent(content);
    }

    public void setLandLotsTabContent(Region content) {
        landLotsTab.setContent(content);
    }

    public void setFarmersTabContent(Region content) {
        farmersTab.setContent(content);
    }
}
