package wardappfxpt1.model;

/**
 *
 * @author mary
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.MessageFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * @author mary
 * @version 1.0
 * @created 11-Aug-2012 3:56:18 PM
 */
public final class Patient {

    private String diagnosis;
    private String firstName;
    private String imageURL;
    private String lastName;
    private String patientID;
    private String timeEntered;

    public Patient() {

        String timeString = MessageFormat.format("{0,time, HH:mm}", new Date());
        setPatient(new Patient("", "", "", "", "", timeString));
    }

    public Patient(String patientID, String firstName, String lastName, String imageURL, String diagnosis, String timeEntered) {

        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
        this.diagnosis = diagnosis;
        this.timeEntered = timeEntered;
    }

    @Override
    public String toString() {
        return "Patient{" + "diagnosis=" + diagnosis + ", firstName=" + firstName + ", imageURL=" + imageURL + ", lastName=" + lastName + ", patientID=" + patientID + ", timeEntered=" + timeEntered + '}';
    }

    public String toCSV() {
        return patientID + "," + firstName + "," + lastName + "," + imageURL + "," + diagnosis + "," + timeEntered;
    }

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
        return diagnosis;
    }

    /**
     * @param diagnosis the diagnosis to set
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return getName
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * @return the patientID
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * @param patientID the patientID to set
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * @return the timeEntered
     */
    public String getTimeEntered() {
        return timeEntered;
    }

    /**
     * @param timeEntered the timeEntered to set
     */
    public void setTimeEntered(String timeEntered) {
        this.timeEntered = timeEntered;
    }

    /**
     *
     * @param p
     */
    public void setPatient(Patient p) {
        this.patientID = p.getPatientID();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.imageURL = p.getImageURL();
        this.diagnosis = p.getDiagnosis();
        this.timeEntered = p.getTimeEntered();
    }
}
