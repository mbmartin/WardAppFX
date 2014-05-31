/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardappfxpt1;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import wardappfxpt1.model.IOManager;
import wardappfxpt1.model.Patient;

/**
 * FXML Controller class
 *
 * @author mary martin
 */
public class WardAppFXPt1Controller implements Initializable {
    @FXML
    private ListView<String> patientNameList;
    @FXML
    private TextField selectedValue;
    private ObservableList<String> patientNames;
    private ArrayList<Patient> patientDatabase;
    private String fileName = "patient.dat";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //populate the list
        patientNames = FXCollections.observableArrayList();
        patientDatabase = IOManager.readTextDatabase(new File(fileName));

        for (Patient pt : patientDatabase) {
            patientNames.add(pt.getName());
        }
        patientNameList.setItems(patientNames);
    }    

    @FXML
    private void handleMouseClick(MouseEvent event) {
        selectedValue.setText(patientNameList.getSelectionModel().getSelectedItem());
    }
    
}
