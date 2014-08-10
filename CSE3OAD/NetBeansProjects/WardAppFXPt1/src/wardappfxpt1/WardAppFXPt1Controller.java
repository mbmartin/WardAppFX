/*
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
    
    //View Objects
    @FXML
    private ListView<Patient> myPatientListView;
     @FXML
    private TextField diagnosisField;
    @FXML
    private TextField timeEnteredField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField patientIDField;
    
    //Model Objects
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
        listViewPatientData.setAll(patientDatabase);
        //Set the data model for our ListView object (myPatientListView)
        myPatientListView.setItems(listViewPatientData);
        
        //Create, set the ListView cells and populate with patient name (if one exists)
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
        
        // handle ListView selection changes: get the new Patient data and set the View Objects.
        myPatientListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            patientIDField.setText(newValue.getPatientID());
            nameField.setText(newValue.getName());
            diagnosisField.setText(newValue.getDiagnosis());
            timeEnteredField.setText(newValue.getTimeEntered());
        });
    }

}
