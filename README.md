# Farm Management FX

A simple desktop application for managing farm-related data, built with JavaFX. The application provides a clean, tab-based interface for managing entities such as Cultures, Land Lots, and Farmers.

## Features

-   **Entity Management**: Full CRUD (Create, Read, Update, Delete) functionality for:
    -   Cultures
    -   Land Lots
    -   Farmers
-   **Tab-Based Navigation**: An intuitive interface that separates each management section into its own tab.
-   **Tabular Data Display**: All entities are displayed in a clear, table-based format for easy viewing.

## Technology Stack

-   **Framework**: JavaFX (UI built programmatically in Java, no FXML)
-   **Language**: Java
-   **Database**: PostgreSQL
-   **Build Tool**: Maven / Gradle

## Architecture

This project strictly follows the **Model-View-Controller (MVC)** architectural pattern to ensure a clean separation of concerns:

-   **Model**: Represents the data entities (e.g., `Culture`, `Farmer`) and business logic.
-   **View**: The presentation layer, responsible for the UI. All views are constructed programmatically using JavaFX classes (like the provided `MainView.java`).
-   **Controller**: Acts as the intermediary between the Model and the View, handling user input and updating the UI and data accordingly.
