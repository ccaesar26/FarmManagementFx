package org.example.farmmanagementfx.data.entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public record Culture(
        SimpleIntegerProperty id,
        SimpleStringProperty name,
        SimpleBooleanProperty isActive) {

    // Constructor for creating a Culture instance
    public Culture(int cultureId, String name, Boolean isActive) {
        this(new SimpleIntegerProperty(cultureId),
                new SimpleStringProperty(name),
                new SimpleBooleanProperty(isActive));
    }

    // Getters for Simple*Property objects
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleBooleanProperty isActiveProperty() {
        return isActive;
    }

    // Getters for Simple*Property values
    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public boolean getIsActive() {
        return isActive.get();
    }

    // Setters for Simple*Property values
    public void setId(int cultureId) {
        id.set(cultureId);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }
}
