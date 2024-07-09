module org.example.farmmanagementfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.farmmanagementfx to javafx.fxml;
    exports org.example.farmmanagementfx;
    exports org.example.farmmanagementfx.view;
    opens org.example.farmmanagementfx.view to javafx.fxml;
}