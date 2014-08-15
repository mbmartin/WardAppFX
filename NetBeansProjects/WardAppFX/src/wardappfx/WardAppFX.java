 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardappfx;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mbmartin
 */
public class WardAppFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // This line to resolve keys against properties for English language
        ResourceBundle i18nBundle = ResourceBundle.getBundle("wardappfx.view.resources.Bundle", new Locale("en", "US"));
        //This line to resolve keys against properties for French language
        //ResourceBundle i18nBundle = ResourceBundle.getBundle("wardappfx.view.resources.Bundle", new Locale("fr", "FR"));
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLWardView.fxml"), i18nBundle);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
