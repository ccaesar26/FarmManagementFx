package org.example.farmmanagementfx.presentation.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.farmmanagementfx.data.entity.Culture;
import org.example.farmmanagementfx.data.entity.Farmer;
import org.example.farmmanagementfx.data.repository.CultureRepository;
import org.example.farmmanagementfx.data.repository.FarmerRepository;
import org.example.farmmanagementfx.presentation.view.Observer;

import java.util.ArrayList;
import java.util.List;

public class FarmerModel implements Subject {
    private final ObservableList<Farmer> farmers = FXCollections.observableArrayList(FarmerRepository.getFarmers());
    private final ObservableList<Culture> cultures = FXCollections.observableArrayList(CultureRepository.getCultures());
    private final ObjectProperty<Farmer> selectedFarmer = new SimpleObjectProperty<>();

    private final List<Observer> observers = new ArrayList<>();

    // Data manipulation methods

    public void addFarmer(Farmer farmer) {
        try {
            var newFarmer = FarmerRepository.insertFarmer(farmer);
            farmers.add(newFarmer);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void updateFarmer(Farmer farmer) {
        FarmerRepository.updateFarmer(farmer);
        notifyObservers();
    }

    public void deleteFarmer(Farmer farmer) {
        FarmerRepository.deleteFarmer(farmer);
        farmers.remove(farmer);
    }

    // Getters and Setters

    public ObservableList<Farmer> getFarmers() {
        return farmers;
    }

    public ObservableList<Culture> getCultures() {
        return cultures;
    }

    public ObservableList<String> getCultureNames() {
        var cultureNames = FXCollections.<String>observableArrayList();
        cultures.forEach(culture -> cultureNames.add(culture.getName()));
        return cultureNames;
    }

    public Farmer getSelectedFarmer() {
        return selectedFarmer.get();
    }

    public ObjectProperty<Farmer> selectedFarmerProperty() {
        return selectedFarmer;
    }

    public void setSelectedFarmer(Farmer selectedFarmer) {
        this.selectedFarmer.set(selectedFarmer);
    }

    // Subject methods

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::updateTableView);
    }
    
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }
}
