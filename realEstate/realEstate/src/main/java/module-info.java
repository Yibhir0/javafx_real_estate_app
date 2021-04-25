module com.mycompany.realestate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.realestate to javafx.fxml;
    exports com.mycompany.realestate;
    opens com.mycompany.realestate.model to javafx.base;
    exports com.mycompany.realestate.model;
}
