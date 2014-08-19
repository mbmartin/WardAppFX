/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardappfx;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import wardappfx.model.IOManager;
import wardappfx.model.Patient;

/**
 * FXML Controller class
 *
 * @author mary martin
 */
public class WardAppViewController implements Initializable {

    @FXML
    private TableView<Patient> myPatientTableView;
    @FXML
    private TableColumn<Patient, String> firstNameColumn;
    @FXML
    private TableColumn<Patient, String> lastNameColumn;
    @FXML
    private MenuButton menuFile;
    @FXML
    private MenuItem menuItemOpen;
    @FXML
    private MenuItem menuItemSave;
    @FXML
    private MenuItem menuItemSaveAs;
    @FXML
    private MenuItem menuItemQuit;
    @FXML
    private MenuButton patientMenu;
    @FXML
    private MenuItem menuItemNew;
    @FXML
    private MenuItem menuItemEdit;
    @FXML
    private MenuItem menuItemDelete;
    @FXML
    private ImageView patientImage;
    @FXML
    private TabPane patientTabPane;
    @FXML
    private Tab patientTab;
    @FXML
    private Tab detailsTab;
    @FXML
    private Tab imageTab;
    @FXML
    private TextField imagePathField;
    @FXML
    private TextField patientIDField;
    @FXML
    private TextField diagnosisField;
    @FXML
    private TextField timeEnteredField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button okButton;
    @FXML
    private TextField messageField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;

    //controller data... 
    private ObservableList<Patient> listViewPatientData;
    private ArrayList<Patient> patientDatabase;
    private String currentFileName = "patient.dat";
    private static final HashMap<String, String> messages = new HashMap<String, String>() {
        {
            put("Loaded", "Patient File Loaded");
            put("Saved", "Patient File Saved");
            put("Adding", "Adding New Patient");
            put("Editing", "Editing Selected Patient");
            put("Deleting", "Deleting Selected Patient");
            put("Deleted", "Patient deleted!!!");
            put("Select", "Please select a patient!!!");
            put("Empty", "No patients!!!");
            put("Cancel", "Operation cancelled");
            put("Reset", "Patient Reset");
            put("Done", "Adding/Editing Patient completed");
        }
    };

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
            switch (patientTabPane.getSelectionModel().getSelectedItem().getText()) {
                case "Details":
                    displayPatientDetails(newValue);
                    break;
                case "Image":
                    displayPatientImage(newValue);
                    break;
            }
        });
        //Select first entry
        myPatientTableView.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleFileOpen(ActionEvent event) {
        //Create a FileChooser object
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files", "*.dat", "*.csv");
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
        if (file != null) {
            writePatientDatabase(file);
        }
    }

    @FXML
    private void handleFileQuit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleNewPatient(ActionEvent event) {
        messageField.setText((String) messages.get("Adding"));
        setPatientDetailFieldsEditable(true);
        setEditControlsVisible(true);
        patientTabPane.getSelectionModel().select(detailsTab);
        Patient patient = new Patient();
        listViewPatientData.add(patient);
        myPatientTableView.getSelectionModel().selectLast();
    }

    @FXML
    private void handleEditPatient(ActionEvent event) {
        messageField.setText(messages.get("Editing"));
        setPatientDetailFieldsEditable(true);
        setEditControlsVisible(true);
        patientTabPane.getSelectionModel().select(detailsTab);
    }

    @FXML
    private void handleDeletePatient(ActionEvent event) {
        messageField.setText(messages.get("Deleting"));
        try {
            int selectedIndex = myPatientTableView.getSelectionModel().getSelectedIndex();
            myPatientTableView.getItems().remove(selectedIndex);
            messageField.setText(messages.get("Deleted"));
        } catch (Exception e) {
            messageField.setText(messages.get("Select"));
        }
        if (myPatientTableView.getSelectionModel().isEmpty()) {
            messageField.setText(messages.get("Empty"));
        } else {
            myPatientTableView.getSelectionModel().selectFirst();
        }
        patientTabPane.getSelectionModel().select(patientTab);
    }

    @FXML
    private void handleDetailsTabSelected(Event event) {
        Patient patient = myPatientTableView.getSelectionModel().getSelectedItem();
        displayPatientDetails(patient);
    }

    @FXML
    private void handleImageTabSelected(Event event) {
        Patient patient = myPatientTableView.getSelectionModel().getSelectedItem();
        displayPatientImage(patient);
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        if (messageField.getText().equals(messages.get("Adding"))) {
            myPatientTableView.getSelectionModel().selectLast();
            int index = myPatientTableView.getSelectionModel().getSelectedIndex();
            myPatientTableView.getItems().remove(index);
        }
        setPatientDetailFieldsEditable(false);
        setEditControlsVisible(false);
        patientTabPane.getSelectionModel().select(patientTab);
        messageField.setText(messages.get("Cancel"));
    }

    @FXML
    private void handleResetButton(ActionEvent event) {
        Patient patient = new Patient();
        int selectedIndex = myPatientTableView.getSelectionModel().getSelectedIndex();
        listViewPatientData.set(selectedIndex, patient);
        messageField.setText(messages.get("Reset"));
    }

    @FXML
    private void handleOkButton(ActionEvent event) {
        int selectedIndex = myPatientTableView.getSelectionModel().getSelectedIndex();
        listViewPatientData.set(selectedIndex, getPatient());
        refreshPatientTable();
        setPatientDetailFieldsEditable(false);
        setEditControlsVisible(false);
        messageField.setText(messages.get("Done"));
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    // Utility methods /////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    private void loadPatients() {
        //Read File contents into ArrayList
        if (currentFileName.endsWith(".dat") || currentFileName.endsWith(".csv")) {
            patientDatabase = IOManager.readTextDatabase(new File(currentFileName));
        }
        //Create a new empty observable list that is backed by an arraylist.
        listViewPatientData = FXCollections.observableArrayList();
        //Set contents of ArrayList in Observable List
        listViewPatientData.setAll(patientDatabase);
        //Set contents of observable list in Table View 
        myPatientTableView.setItems(listViewPatientData);
        messageField.setText((String) messages.get("Loaded"));

    }

    private void writePatientDatabase(File file) {
        if (file.getName().endsWith(".dat")) {
            IOManager.writeTextDatabase(file, patientDatabase);
        } else if (file.getName().endsWith(".xml")) {
            IOManager.writeXMLDatabase(file.getName(), patientDatabase);
        }
        messageField.setText(messages.get("Done"));
    }

    private void displayPatientDetails(Patient patient) {
        try {
            patientIDField.setText(patient.getPatientID());
            firstNameField.setText(patient.getFirstName());
            lastNameField.setText(patient.getLastName());
            diagnosisField.setText(patient.getDiagnosis());
            timeEnteredField.setText(patient.getTimeEntered());
            imagePathField.setText(patient.getImageURL());
        } catch (Exception e) {
            clearPatientDetails();
        }
    }

    private void clearPatientDetails() {
        patientIDField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        diagnosisField.setText("");
        imagePathField.setText("");
        timeEnteredField.setText("");
    }

    private void displayPatientImage(Patient patient) {
        Image image;
        try {
            image = new Image(getClass().getResourceAsStream(patient.getImageURL()));
        } catch (Exception e) {
            image = new Image(getClass().getResourceAsStream("view/images/WardAppLogo.png"));
        }
        patientImage.setImage(image);
    }

    @FXML
    private void handleStringField(KeyEvent event) {
        TextField inputField = (TextField) event.getSource();
        if (inputField.isEditable() && event.getCharacter().matches("[0-9]*")) {
            inputField.getStyleClass().add("error");
            event.consume();
        } else {
            inputField.getStyleClass().remove("error");
        }
        inputField.getStyleClass().remove("error");
    }

    @FXML
    private void handleDigitField(KeyEvent event) {
        if (patientIDField.isEditable() && event.getCharacter().matches("[a-zA-z\\s]*")) {
            patientIDField.getStyleClass().add("error");
            event.consume();
        } else {
            patientIDField.getStyleClass().remove("error");
        }
    }
    
    @FXML
    private void handleMouseMoved(MouseEvent event) {
        patientIDField.getStyleClass().remove("error");
    }


    private void setPatientDetailFieldsEditable(boolean state) {
        patientIDField.setEditable(state);
        firstNameField.setEditable(state);
        lastNameField.setEditable(state);
        diagnosisField.setEditable(state);
        imagePathField.setEditable(state);
    }

    private void setEditControlsVisible(boolean state) {
        okButton.setVisible(state);
        cancelButton.setVisible(state);
        resetButton.setVisible(state);
    }

    private Patient getPatient() {
        Patient patient = new Patient(patientIDField.getText(),
                firstNameField.getText(), lastNameField.getText(),
                imagePathField.getText(), diagnosisField.getText(), timeEnteredField.getText());
        return patient;
    }

    private void refreshPatientTable() {
        int selectedIndex = myPatientTableView.getSelectionModel().getSelectedIndex();
        myPatientTableView.setItems(null);
        myPatientTableView.layout();
        myPatientTableView.setItems(listViewPatientData);
        myPatientTableView.getSelectionModel().select(selectedIndex);
    }

    

}
