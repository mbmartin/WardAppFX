/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wardappfxpt2.view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import wardappfxpt2.utility.IOManager;
import wardappfxpt2.model.Patient;

/**
 *
 * @author Michael
 */
public class WardAppFXPt2Controller implements Initializable {
    
   
   
    private ArrayList<Patient> patientDatabase;
    private ObservableList<String> patientNames;
    private final String fileName = "patient.dat";
    
    public WardAppFXPt2Controller(){
        
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {

        //populate the list
        patientNames = FXCollections.observableArrayList();
        patientDatabase = IOManager.readTextDatabase(new File(fileName));

        for (Patient pt : patientDatabase) {
            patientNames.add(pt.getName());
        }
        

    }  
    
    
    
}
