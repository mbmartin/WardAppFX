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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import wardappfxpt1.model.IOManager;
import wardappfxpt1.model.Patient;

/**
 * FXML Controller class
 *
 * @author mary martin
 */
public class WardAppFXPt1Controller implements Initializable {

    @FXML
    private ListView<Patient> myPatientListView;
    @FXML
    private TextField selectedValue;

    private ObservableList<Patient> listViewPatientData;
    private ArrayList<Patient> patientDatabase;
    private final String fileName = "patient.dat";

    /**
     * Initialize the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Get the patient data and add to an observable list 
        patientDatabase = IOManager.readTextDatabase(new File(fileName));
        listViewPatientData = FXCollections.observableArrayList();
        for (Patient pt : patientDatabase) {
            listViewPatientData.add(pt);
        }
        
        //set the data model for our ListView object (myPatientListView)
        myPatientListView.setItems(listViewPatientData);
        //create, set the ListView cells and populate with patient name (if one exists)
        myPatientListView.setCellFactory((listViewPatientData) -> {
            return new ListCell<Patient>() {
                @Override
                protected void updateItem(Patient patient, boolean empty) {
                    super.updateItem(patient, empty);

                    if (patient == null || empty) {
                        setText(null);
                    } else {
                        setText(patient.getName());
                    }
                }
            };
        });
        // handle ListView selection changes.
        myPatientListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedValue.setText(newValue.getName() + "\n");
        });
    }

}
