/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wardappfx.model;


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
import java.text.MessageFormat;
import java.util.Date;
import java.util.StringTokenizer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public final class Patient {

    private StringProperty patientID;  
    private StringProperty firstName;
    private StringProperty lastName;  
    private StringProperty imageURL;
    private StringProperty diagnosis;
    private StringProperty timeEntered;

    /**
     * no argument constructor
     */
    public Patient() {
        this(null, null, null, null, null, MessageFormat.format("{0,time, HH:mm}", new Date()));
    }

    /**
     * six argument (all details) constructor
     *
     * @param patientID
     * @param firstName
     * @param lastName
     * @param imageURL
     * @param diagnosis
     * @param timeEntered
     */
    public Patient(String patientID, String firstName, String lastName, String imageURL, String diagnosis, String timeEntered) {
        this.patientID = new SimpleStringProperty(patientID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.imageURL = new SimpleStringProperty(imageURL);
        this.diagnosis = new SimpleStringProperty(diagnosis);
        this.timeEntered = new SimpleStringProperty(timeEntered);
    }

    /**
     *
     * @return text string representation of Patient object
     */
    @Override
    public String toString() {
        return "Patient{" + ", patientID=" + getPatientID() + ", firstName=" + getFirstName() + ", lastName=" + getLastName()
                + ", imageURL=" + getImageURL() + "diagnosis=" + getDiagnosis() + ", timeEntered=" + getTimeEntered() + "}";
    }

    /**
     *
     * @return text string containing patient details separated by commas
     */
    public String toCSV() {
        return getPatientID() + "," + getFirstName() + "," + getLastName() + "," + getImageURL() + "," + getDiagnosis() + "," + getTimeEntered();
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

    public StringProperty diagnosisProperty() {
        return diagnosis;
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

    public StringProperty firstNameProperty() {
        return firstName;
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

    public StringProperty imageURLProperty() {
        return imageURL;
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

    public StringProperty lastNameProperty() {
        return lastName;
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

    public StringProperty patientIDProperty() {
        return patientID;
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

    public StringProperty timeEnteredProperty() {
        return timeEntered;
    }
}