package org.example.farmmanagementfx.presentation.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.farmmanagementfx.data.entity.Culture;
import org.example.farmmanagementfx.data.entity.LandLot;
import org.example.farmmanagementfx.data.repository.CultureRepository;
import org.example.farmmanagementfx.data.repository.LandLotRepository;
import org.example.farmmanagementfx.presentation.view.Observer;

import java.util.ArrayList;
import java.util.List;

public class LandLotModel implements Subject {
    private final ObservableList<LandLot> landLots = FXCollections.observableArrayList(LandLotRepository.getLandLots());
    private final ObservableList<Culture> cultures = FXCollections.observableArrayList(CultureRepository.getCultures());
    private final ObjectProperty<LandLot> selectedLandLot = new SimpleObjectProperty<>();

    private final List<Observer> observers = new ArrayList<>();

    // Data manipulation methods

    public void addLandLot(LandLot landLot) {
        try {
            var newLandLot = LandLotRepository.insertLandLot(landLot);
            landLots.add(newLandLot);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void updateLandLot(LandLot landLot) {
        LandLotRepository.updateLandLot(landLot);
        notifyObservers();
    }

    public void deleteLandLot(LandLot landLot) {
        LandLotRepository.deleteLandLot(landLot);
        landLots.remove(landLot);
    }

    // Getters and Setters

    public ObservableList<LandLot> getLandLots() {
        return landLots;
    }

    public ObservableList<Culture> getCultures() {
        return cultures;
    }

    public ObservableList<String> getCultureNames() {
        var cultureNames = FXCollections.<String>observableArrayList();
        cultures.forEach(culture -> cultureNames.add(culture.getName()));
        return cultureNames;
    }

    public LandLot getSelectedLandLot() {
        return selectedLandLot.get();
    }

    public ObjectProperty<LandLot> selectedLandLotProperty() {
        return selectedLandLot;
    }

    public void setSelectedLandLot(LandLot selectedLandLot) {
        this.selectedLandLot.set(selectedLandLot);
    }

    // Observer pattern methods

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::updateTableView);
    }
}
