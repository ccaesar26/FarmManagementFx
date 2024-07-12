package org.example.farmmanagementfx.presentation.model;

import org.example.farmmanagementfx.presentation.view.Observer;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}
