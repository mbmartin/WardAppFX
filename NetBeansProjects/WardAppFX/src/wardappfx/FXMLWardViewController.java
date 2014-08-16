/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wardappfx;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import wardappfx.model.IOManager;
import wardappfx.model.Patient;

/**
 * FXML Controller class
 *
 * @author mary martin
 */

public class FXMLWardViewController implements Initializable { 
    
    
    @FXML
    private TableView<Patient> myPatientTableView;
    @FXML
    private Label patientIDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label diagnosisLabel;
    @FXML
    private Label timeEnteredLabel;
    @FXML
    private MenuButton menuFile;
    @FXML
    private MenuItem menuItemOpen;
    @FXML
    private MenuItem menuItemSave;
    @FXML
    private MenuItem menuItemSaveAs;
    @FXML
    private TableColumn<Patient,String> firstNameColumn;
    @FXML
    private TableColumn<Patient,String> lastNameColumn;
    @FXML
    private MenuItem menuItemQuit;
    
    //controller data... 
    private ObservableList<Patient> listViewPatientData;
    private ArrayList<Patient> patientDatabase;
    private String currentFileName = "patient.dat";
    @FXML
    private ImageView patientImage;
    @FXML
    private Tab patientTab;
    @FXML
    private Tab detailsTab;
    @FXML
    private Tab imageTab;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Get the patient data and add to an observable list 
        //Read File contents into an ArrayList 
        loadPatients();

        //Get the cellData and set the value of the table cells to first and last names of patients
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Auto resize columns 
        myPatientTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Listen for selection changes and update selected patient details with new selected patient details
        myPatientTableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Patient> observable, Patient oldValue, Patient newValue) -> {
            displayPatientDetails(newValue);
        });
        //Select first entry
        myPatientTableView.getSelectionModel().selectFirst();
    }    

    @FXML
    private void handleFileOpen(ActionEvent event) {
        //Create a FileChooser object
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show open file dialog and load contents of selected file
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            currentFileName = file.getName();
            loadPatients();
        }
    }

    @FXML
    private void handleFileSave(ActionEvent event) {
        File file = new File(currentFileName);
        writePatientDatabase(file);
    }

    @FXML
    private void handleFileSaveAs(ActionEvent event) {
         //Create a FileChooser object
        FileChooser fileChooser = new FileChooser();
        //Show save file dialog and save contents of patientDatabase
        File file = fileChooser.showSaveDialog(null);
        if (null != file) {
            writePatientDatabase(file);
        }
    }
    
    @FXML
    private void handleFileQuit(ActionEvent event) {
        System.exit(0);
    }
    
    private void loadPatients() {
        //Read File contents into ArrayList 
        patientDatabase = IOManager.readTextDatabase(new File(currentFileName));
        //Create a new empty observable list that is backed by an arraylist.
        listViewPatientData = FXCollections.observableArrayList();
        //Set contents of ArrayList in Observable List
        listViewPatientData.setAll(patientDatabase);
        //Set contents of observable list in Table View 
        myPatientTableView.setItems(listViewPatientData);

    }

    private void displayPatientDetails(Patient patient) {
        if (patient != null) {
            patientIDLabel.setText(patient.getPatientID());
            nameLabel.setText(patient.getName());
            diagnosisLabel.setText(patient.getDiagnosis());  
            timeEnteredLabel.setText(patient.getTimeEntered());
        } else {
            patientIDLabel.setText("");
            nameLabel.setText("");
            diagnosisLabel.setText("");
            timeEnteredLabel.setText("");
        }
    }

    private void writePatientDatabase(File file) {
        if (file.getName().endsWith(".dat")) {
            IOManager.writeTextDatabase(file, patientDatabase);
        } else if (file.getName().endsWith(".xml")) {
            IOManager.writeXMLDatabase(file.getName(), patientDatabase);
        }
    }
    
    private void displayPatientImage(Patient patient) {
        Image image;
        if (patient != null) {
            image = new Image(getClass().getResourceAsStream(patient.getImageURL()));
        } else {
            image = new Image(getClass().getResourceAsStream("images/WardAppLogo.png"));
        }
        patientImage.setImage(image);
    }


    @FXML
    private void handleDetailsTabSelected(Event event) {
        myPatientTableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Patient> observable, Patient oldValue, Patient newValue) -> {
            displayPatientDetails(newValue);
        });
    }

    @FXML
    private void handleImageTabSelected(Event event) {
        myPatientTableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Patient> observable, Patient oldValue, Patient newValue) -> {
            displayPatientImage(newValue);
        });
    }


    
    
}
