package org.example.farmmanagementfx.presentation.controller;

import org.example.farmmanagementfx.data.entity.Farmer;
import org.example.farmmanagementfx.data.util.DateUtil;
import org.example.farmmanagementfx.presentation.model.FarmerModel;
import org.example.farmmanagementfx.presentation.view.FarmerView;

public class FarmerController {

    private final FarmerView farmerView;
    private final FarmerModel farmerModel;

    public FarmerController(FarmerView farmerView, FarmerModel farmerModel) {
        this.farmerView = farmerView;
        this.farmerModel = farmerModel;

        farmerModel.attach(farmerView);

        initialize();
    }

    private void initialize() {
        farmerView.setOnRowSelected((observable, oldValue, newValue) -> {
            farmerView.getFormTitleLabel().setText("Edit farmer");
            if (newValue == null) {
                return;
            }
            farmerView.getNameTextField().setText(newValue.getName());
            farmerView.getAssignedCultureNameComboBox().getSelectionModel().select(newValue.getAssignedCulture().getName());
            farmerView.getEmploymentDatePicker().setValue(DateUtil.convertToLocalDate(newValue.getEmploymentDate()));
        });

        farmerView.getSaveButton().setOnAction(event -> {
            if (farmerView.getTableView().getSelectionModel().getSelectedItem() == null) {
                var assignedCultureName = farmerView.getAssignedCultureNameComboBox().getSelectionModel().getSelectedItem();
                var assignedCulture = farmerModel.getCultures().stream()
                        .filter(culture -> culture.getName().equals(assignedCultureName))
                        .findFirst()
                        .orElse(null);

                var employmentDate = DateUtil.convertToDate(farmerView.getEmploymentDatePicker().getValue());

                var farmer = new Farmer(
                        0,
                        farmerView.getNameTextField().getText(),
                        assignedCulture,
                        employmentDate,
                        true
                );
                farmerModel.addFarmer(farmer);
            } else {
                var farmer = farmerView.getTableView().getSelectionModel().getSelectedItem();
                farmer.name().set(farmerView.getNameTextField().getText());
                farmer.assignedCulture().set(farmerModel.getCultures().stream()
                        .filter(culture -> culture.getName().equals(farmerView.getAssignedCultureNameComboBox().getSelectionModel().getSelectedItem()))
                        .findFirst()
                        .orElse(null));
                farmer.employmentDate().set(DateUtil.convertToDate(farmerView.getEmploymentDatePicker().getValue()));
                farmerModel.updateFarmer(farmer);
            }

            deselectFarmer();
        });

        farmerView.getDeleteButton().setOnAction(event -> {
            var farmer = farmerView.getTableView().getSelectionModel().getSelectedItem();
            if (farmer == null) {
                return;
            }
            farmerModel.deleteFarmer(farmer);
        });

        farmerView.getDiscardButton().setOnAction(event -> {
            deselectFarmer();
        });
    }

    private void deselectFarmer() {
        farmerView.getTableView().getSelectionModel().clearSelection();
        farmerView.getFormTitleLabel().setText("Add farmer");
        farmerView.getNameTextField().clear();
        farmerView.getAssignedCultureNameComboBox().getSelectionModel().clearSelection();
        farmerView.getEmploymentDatePicker().setValue(null);
    }
}
