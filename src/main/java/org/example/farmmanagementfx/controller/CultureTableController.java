package org.example.farmmanagementfx.controller;

import javafx.scene.layout.Region;
import org.example.farmmanagementfx.model.CultureFormModel;
import org.example.farmmanagementfx.model.CultureTableModel;
import org.example.farmmanagementfx.view.CultureFormView;
import org.example.farmmanagementfx.view.CultureTableView;
import org.example.farmmanagementfx.view.CultureView;

public class CultureTableController {

    private final CultureTableView cultureTableView;
    private final CultureTableModel cultureTableModel;

    public CultureTableController(CultureTableView cultureTableView, CultureTableModel cultureTableModel) {
        this.cultureTableView = cultureTableView;
        this.cultureTableModel = cultureTableModel;

        cultureTableView.setAddButtonAction(() -> {
            var cultureFormModel = new CultureFormModel();
            var cultureFormView = new CultureFormView();
            var cultureFormController = new CultureFormController(cultureFormView, cultureFormModel);
            var cultureFormRegion = CultureFormView.getView();
            var cultureView = CultureView.getView();

            //cultureTableView.setFormView(cultureFormRegion);
        });
    }

    public CultureTableView getView() {
        return cultureTableView;
    }
}
