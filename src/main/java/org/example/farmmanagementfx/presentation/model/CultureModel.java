package org.example.farmmanagementfx.presentation.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.farmmanagementfx.data.entity.Culture;
import org.example.farmmanagementfx.data.repository.CultureRepository;

public class CultureModel {
    private final ObservableList<Culture> cultures = FXCollections.observableArrayList(CultureRepository.getCultures());
    private final ObjectProperty<Culture> selectedCulture = new SimpleObjectProperty<>();

    // Data manipulation methods

    public void addCulture(Culture culture) {
        try {
            var newCulture = CultureRepository.insertCulture(culture);
            cultures.add(newCulture);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void updateCulture(Culture culture) {
        CultureRepository.updateCulture(culture);
    }

    public void deleteCulture(Culture culture) {
        CultureRepository.deleteCulture(culture);
        cultures.remove(culture);
    }

    // Getters and Setters

    public ObservableList<Culture> getCultures() {
        return cultures;
    }

    public Culture getSelectedCulture() {
        return selectedCulture.get();
    }

    public ObjectProperty<Culture> selectedCultureProperty() {
        return selectedCulture;
    }

    public void setSelectedCulture(Culture selectedCulture) {
        this.selectedCulture.set(selectedCulture);
    }
}
