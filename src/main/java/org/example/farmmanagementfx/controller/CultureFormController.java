package org.example.farmmanagementfx.controller;

import org.example.farmmanagementfx.model.CultureFormModel;
import org.example.farmmanagementfx.view.CultureFormView;

public class CultureFormController {

    private final CultureFormView cultureFormView;
    private final CultureFormModel cultureFormModel;

    public CultureFormController(CultureFormView cultureFormView, CultureFormModel cultureFormModel) {
        this.cultureFormView = cultureFormView;
        this.cultureFormModel = cultureFormModel;
    }
}
