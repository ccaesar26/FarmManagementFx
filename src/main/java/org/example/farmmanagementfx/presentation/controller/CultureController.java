package org.example.farmmanagementfx.presentation.controller;

import org.example.farmmanagementfx.data.entity.Culture;
import org.example.farmmanagementfx.presentation.model.CultureModel;
import org.example.farmmanagementfx.presentation.view.CultureView;

public class CultureController {

    private final CultureView cultureView;
    private final CultureModel cultureModel;

    public CultureController(CultureView cultureView, CultureModel cultureModel) {
        this.cultureView = cultureView;
        this.cultureModel = cultureModel;

        initialize();
    }

    private void initialize() {
        cultureView.setOnRowSelected((observable, oldValue, newValue) -> {
            cultureView.getFormTitleLabel().setText("Edit plantedCulture");
            if (newValue == null) {
                return;
            }
            cultureView.getNameTextField().setText(newValue.getName());
        });

        cultureView.getSaveButton().setOnAction(event -> {
            if (cultureView.getTableView().getSelectionModel().getSelectedItem() == null) {
                var culture = new Culture(
                        0,
                        cultureView.getNameTextField().getText(),
                        true
                );
                cultureModel.addCulture(culture);
            } else {
                var culture = cultureView.getTableView().getSelectionModel().getSelectedItem();
                culture.name().set(cultureView.getNameTextField().getText());
                cultureModel.updateCulture(culture);
            }

            deselectCulture();
        });

        cultureView.getDeleteButton().setOnAction(event -> {
            var culture = cultureView.getTableView().getSelectionModel().getSelectedItem();
            if (culture == null) {
                return;
            }
            cultureModel.deleteCulture(culture);
        });

        cultureView.getDiscardButton().setOnAction(event -> {
            deselectCulture();
        });
    }

    private void deselectCulture() {
        cultureView.getTableView().getSelectionModel().clearSelection();
        cultureView.getFormTitleLabel().setText("Add plantedCulture");
        cultureView.getNameTextField().clear();
    }
}
