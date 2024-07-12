package org.example.farmmanagementfx.data.entity;

import javafx.beans.property.*;
import org.example.farmmanagementfx.data.util.DateUtil;

import java.util.Date;

public record LandLot(
        SimpleIntegerProperty id,
        SimpleStringProperty location,
        SimpleDoubleProperty area,
        SimpleObjectProperty<Date> plantingDate,
        SimpleObjectProperty<Date> harvestDate,
        SimpleObjectProperty<Culture> plantedCulture,
        SimpleBooleanProperty isActive) {

    // Constructor for creating a LandLot instance
    public LandLot(int id, String location, double area, Date plantingDate, Date harvestDate, Culture plantedCulture, boolean isActive) {
        this(new SimpleIntegerProperty(id),
                new SimpleStringProperty(location),
                new SimpleDoubleProperty(area),
                new SimpleObjectProperty<>(plantingDate),
                new SimpleObjectProperty<>(harvestDate),
                new SimpleObjectProperty<>(plantedCulture),
                new SimpleBooleanProperty(isActive));
    }

    // Getters for Simple*Property objects
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public SimpleDoubleProperty areaProperty() {
        return area;
    }

    public SimpleObjectProperty<Date> plantingDateProperty() {
        return plantingDate;
    }

    public StringProperty plantingDatePropertyAsString() {
        return DateUtil.formatDate(plantingDate.get());
    }

    public SimpleObjectProperty<Date> harvestDateProperty() {
        return harvestDate;
    }

    public StringProperty harvestDatePropertyAsString() {
        return DateUtil.formatDate(harvestDate.get());
    }

    public SimpleObjectProperty<Culture> plantedCultureProperty() {
        return plantedCulture;
    }

    public SimpleBooleanProperty isActiveProperty() {
        return isActive;
    }

    // Getters for Simple*Property values
    public int getId() {
        return id.get();
    }

    public String getLocation() {
        return location.get();
    }

    public double getArea() {
        return area.get();
    }

    public Date getPlantingDate() {
        return plantingDate.get();
    }

    public String getPlantingDateAsString() {
        return DateUtil.formatDate(plantingDate.get()).get();
    }

    public Date getHarvestDate() {
        return harvestDate.get();
    }

    public String getHarvestDateAsString() {
        return DateUtil.formatDate(harvestDate.get()).get();
    }

    public Culture getPlantedCulture() {
        return plantedCulture.get();
    }

    public boolean getIsActive() {
        return isActive.get();
    }

    // Setters for Simple*Property values
    public void setId(int id) {
        this.id.set(id);
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public void setArea(double area) {
        this.area.set(area);
    }

    public void setPlantingDate(Date plantingDate) {
        this.plantingDate.set(plantingDate);
    }

    public void setHarvestDate(Date harvestDate) {
        this.harvestDate.set(harvestDate);
    }

    public void setPlantedCulture(Culture culture) {
        this.plantedCulture.set(culture);
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }
}
