package org.example.farmmanagementfx.presentation.controller;

import org.example.farmmanagementfx.presentation.model.CultureModel;
import org.example.farmmanagementfx.presentation.model.FarmerModel;
import org.example.farmmanagementfx.presentation.model.LandLotModel;
import org.example.farmmanagementfx.presentation.view.*;

public class MainController {

    private final MainView mainView;

    private final CultureController cultureController;
    private final LandLotController landLotController;
    private final FarmerController farmerController;

    public MainController(MainView mainView) {
        this.mainView = mainView;

        var homeView = new HomeView();
        mainView.setHomeTabContent(homeView);

        var cultureModel = new CultureModel();
        var cultureView = new CultureView(cultureModel);
        cultureController = new CultureController(cultureView, cultureModel);
        mainView.setCulturesTabContent(cultureView);

        var landLotModel = new LandLotModel();
        var landLotView = new LandLotView(landLotModel);
        landLotController = new LandLotController(landLotView, landLotModel);
        mainView.setLandLotsTabContent(landLotView);

        var farmerModel = new FarmerModel();
        var farmerView = new FarmerView(farmerModel);
        farmerController = new FarmerController(farmerView, farmerModel);
        mainView.setFarmersTabContent(farmerView);
    }
}
