/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wardappfxpt2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Michael and Mary and further...
 */
public class WardAppFXPt2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/WardAppFXPt2.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     *
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}