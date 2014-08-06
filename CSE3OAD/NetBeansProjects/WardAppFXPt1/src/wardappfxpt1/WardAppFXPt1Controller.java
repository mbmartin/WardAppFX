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

    private ObservableList<Patient> listViewPatientData = FXCollections.observableArrayList();
    private ArrayList<Patient> patientDatabase;
    private final String fileName = "patient.dat";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //populate the list
        listViewPatientData = FXCollections.observableArrayList();
        patientDatabase = IOManager.readTextDatabase(new File(fileName));
        for (Patient pt : patientDatabase) {
            listViewPatientData.add(pt);
        }
        
        // Init ListView.
        myPatientListView.setItems(listViewPatientData);

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
        // Handle ListView selection changes.
        myPatientListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedValue.setText(newValue.getName() + "\n");
        });
    }

}
