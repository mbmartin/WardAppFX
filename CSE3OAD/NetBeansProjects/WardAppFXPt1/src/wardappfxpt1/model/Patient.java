package wardappfxpt1.model;


import java.text.MessageFormat;
import java.util.Date;
import java.util.StringTokenizer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author mary
 * @version 1.0
 * @created 11-Aug-2012 3:56:18 PM
 * @since July 2014
 */
public final class Patient {
       
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty diagnosis;
    private StringProperty imageURL;   
    private StringProperty patientID;
    private StringProperty timeEntered;
    
    /**
     * no argument constructor
     */
    public Patient() {

        String timeString = MessageFormat.format("{0,time, HH:mm}", new Date());
        setPatient("", "", "", "", "", timeString);
    }
/**
 * six argument (all details) constructor 
 * @param patientID
 * @param firstName
 * @param lastName
 * @param imageURL
 * @param diagnosis
 * @param timeEntered 
 */
    public Patient(String patientID, String firstName, String lastName, String imageURL, String diagnosis, String timeEntered) {

        setPatient(firstName, lastName, patientID, imageURL, diagnosis, timeEntered);
    }
    
/**
 * Set all details for patient as simple string properties...
 * @param firstName1
 * @param lastName1
 * @param patientID1
 * @param imageURL1
 * @param diagnosis1
 * @param timeEntered1 
 */
    private void setPatient(String firstName1, String lastName1, String patientID1, String imageURL1, String diagnosis1, String timeEntered1) {
        this.firstName = new SimpleStringProperty(firstName1);
        this.lastName = new SimpleStringProperty(lastName1);
        this.patientID = new SimpleStringProperty(patientID1);
        this.imageURL = new SimpleStringProperty(imageURL1);
        this.diagnosis = new SimpleStringProperty(diagnosis1);
        this.timeEntered = new SimpleStringProperty(timeEntered1);
    }

    /**
     * 
     * @return text string representation of Patient object
     */
    @Override
    public String toString() {
        return "Patient{" + "diagnosis=" + diagnosis + ", firstName=" + firstName + ", imageURL=" + getImageURL() + ", lastName=" + lastName + ", patientID=" + patientID + ", timeEntered=" + timeEntered + '}';
    }
/**
 * 
 * @return text string containing patient details separated by commas
 */
    public String toCSV() {
        return patientID + "," + firstName + "," + lastName + "," + getImageURL() + "," + getDiagnosis() + "," + timeEntered;
    }
/**
 * 
 * @param line text string containing patient details from external store
 * @return Patient object
 */
    public static Patient createPatient(String line) {
        StringTokenizer theTokens = new StringTokenizer(line, ",");
        Patient newPatient = new Patient();
        if (theTokens.countTokens() == 6) {
            newPatient.setPatientID(theTokens.nextToken().trim());
            newPatient.setFirstName(theTokens.nextToken().trim());
            newPatient.setLastName(theTokens.nextToken().trim());
            newPatient.setImageURL(theTokens.nextToken().trim());
            newPatient.setDiagnosis(theTokens.nextToken().trim());
            newPatient.setTimeEntered(theTokens.nextToken().trim());
            return newPatient;
        } else {
            return null;
        }
    }

    /**
     * @return the diagnosis
     */
    public String getDiagnosis() {
        return diagnosis.get();
    }

    /**
     * @param diagnosis the diagnosis to set
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis.set(diagnosis);
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL.get();
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL.set(imageURL);
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     *
     * @return getName
     */
    public String getName() {
        return firstName.get() + " " + lastName.get();
    }

    /**
     * @return the patientID
     */
    public String getPatientID() {
        return patientID.get();
    }

    /**
     * @param patientID the patientID to set
     */
    public void setPatientID(String patientID) {
        this.patientID.set(patientID);
    }

    /**
     * @return the timeEntered
     */
    public String getTimeEntered() {
        return timeEntered.get();
    }

    /**
     * @param timeEntered the timeEntered to set
     */
    public void setTimeEntered(String timeEntered) {
        this.timeEntered.set(timeEntered);
    }
}
