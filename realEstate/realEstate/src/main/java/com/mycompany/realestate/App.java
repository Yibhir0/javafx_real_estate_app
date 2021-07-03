package com.mycompany.realestate;
import com.mycompany.realestate.model.database.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * main class
 * JavaFX App
 * @author Yassine Ibhir
 */
public class App extends Application {
    private static final PropertyDAO []  access = new PropertyDAO[3];
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("shelter"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        
        access[0] = (new HouseDbAccess());
        access[1] = (new PlexDbAccess());
        access[2] = (new CondoDbAccess());
        load();
        launch();
    }
    /**
     * loads all properties from database
     */
    private static void load(){
        for (PropertyDAO acces : access) {
            acces.instantiateProperties();
        }
    }
    

}