package org.example.farmmanagementfx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.farmmanagementfx.entity.Culture;
import org.example.farmmanagementfx.repository.CultureRepository;

import java.util.List;

public class CultureTableModel {

    private final ObservableList<Culture> cultures;

    public CultureTableModel() {
        this.cultures = FXCollections.observableArrayList(CultureRepository.getCultures());
    }

    public ObservableList<Culture> getCultures() {
        return cultures;
    }
}
