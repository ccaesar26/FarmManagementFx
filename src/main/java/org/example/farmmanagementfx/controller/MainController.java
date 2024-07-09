package org.example.farmmanagementfx.controller;

import javafx.scene.layout.Region;
import org.example.farmmanagementfx.view.CultureView;
import org.example.farmmanagementfx.view.HomeView;
import org.example.farmmanagementfx.view.MainView;

public class MainController {

    private final MainView mainView;

    private final HomeView homeView = new HomeView();

    private final CultureView cultureView = new CultureView();

    public MainController(MainView mainView) {
        this.mainView = mainView;
    }
}
