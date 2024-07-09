package org.example.farmmanagementfx.entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public record Culture(
        SimpleIntegerProperty cultureId,
        SimpleStringProperty name,
        SimpleBooleanProperty isActive) {

    // Constructor for creating a Culture instance
    public Culture(int cultureId, String name, Boolean isActive) {
        this(new SimpleIntegerProperty(cultureId),
                new SimpleStringProperty(name),
                new SimpleBooleanProperty(isActive));
    }

    // Getters for Simple*Property objects
    public SimpleIntegerProperty cultureIdProperty() {
        return cultureId;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleBooleanProperty isActiveProperty() {
        return isActive;
    }

    // Convert BooleanProperty to ObservableValue<Boolean>
    public ObservableValue<Boolean> isActiveObservable() {
        return isActiveProperty().asObject();
    }
}
