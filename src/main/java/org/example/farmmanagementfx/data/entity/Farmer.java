package org.example.farmmanagementfx.data.entity;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import org.example.farmmanagementfx.data.util.DateUtil;

import java.util.Date;

public record Farmer(
        SimpleIntegerProperty id,
        SimpleStringProperty name,
        SimpleObjectProperty<Culture> assignedCulture,
        SimpleObjectProperty<Date> employmentDate,
        SimpleBooleanProperty isActive) {

    // Constructor for creating a Farmer instance
    public Farmer(int farmerId, String name, Culture assignedCulture, Date employmentDate, Boolean isActive) {
        this(new SimpleIntegerProperty(farmerId),
                new SimpleStringProperty(name),
                new SimpleObjectProperty<>(assignedCulture),
                new SimpleObjectProperty<>(employmentDate),
                new SimpleBooleanProperty(isActive));
    }

    // Getters for Simple*Property objects
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleObjectProperty<Culture> assignedCultureProperty() {
        return assignedCulture;
    }

    public SimpleObjectProperty<Date> employmentDateProperty() {
        return employmentDate;
    }

    public StringProperty employmentDateAsString() {
        return DateUtil.formatDate(employmentDate.get());
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

    public Culture getAssignedCulture() {
        return assignedCulture.get();
    }

    public Date getEmploymentDate() {
        return employmentDate.get();
    }

    public String getEmploymentDateAsString() {
        return DateUtil.formatDate(employmentDate.get()).get();
    }

    public boolean getIsActive() {
        return isActive.get();
    }

    // Setters for Simple*Property values
    public void setId(int farmerId) {
        id.set(farmerId);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAssignedCulture(Culture assignedCulture) {
        this.assignedCulture.set(assignedCulture);
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate.set(employmentDate);
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }
}
