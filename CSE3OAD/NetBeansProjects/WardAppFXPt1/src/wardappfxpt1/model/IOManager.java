/*
 * No licence
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardappfxpt1.model;

/**
 *
 * @author mary martin
 */
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 **
 ** Class name: IOManager - class providing methods for reading and writing files in APPWMS
 *
 ** @author Mary Martin
 ** @version 4 *
 *
 */
public class IOManager {

    public static ArrayList<Patient> readTextDatabase(File file) {
        //-------------------------------------
        ArrayList<Patient> p = new ArrayList<>();
        FileReader fr;
        BufferedReader br;
        String line;
        Patient newPatient;

        System.out.println("File name and path: " + file.getName() + " " + file);

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.err.println("No Patient Database ");
            return p;
        }
        try {
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                newPatient = Patient.createPatient(line);
                if (newPatient != null) {
                    p.add(newPatient);
                }
            }
        } catch (EOFException eof) {
            System.out.println("Finished reading file\n");
        } catch (IOException e) {
            System.err.println("Error during read\n" + e.toString());
        }
        try {
            fr.close();
        } catch (IOException e) {
            System.err.println("File not closed properly\n" + e.toString());
            System.exit(1);
        }
        return p;
    }

    public static void writeTextDatabase(File file, ArrayList<Patient> p) {
        //-----------------------------------

        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (Patient patient : p) {
                    bw.write(patient.toCSV() + "\n");
                }
                System.out.println("finished writing...");
                bw.flush();
            }
        } catch (IOException e) {
            System.err.println("File not closed properly\n" + e.toString());
            System.exit(1);
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Patient> readXMLDatabase(String fileName) {
        //-------------------------------------
        ArrayList<Patient> p = new ArrayList<>();
        XMLDecoder d;

        try {
            d = new XMLDecoder(
                    new BufferedInputStream(
                    new FileInputStream(fileName)));
            p = (ArrayList<Patient>) d.readObject();

        } catch (FileNotFoundException e) {
            System.err.println("No Patient Database ");
            return p;
        }
        d.close();
        return p;
    }

    public static void writeXMLDatabase(String fileName, ArrayList<Patient> p) {
        //-----------------------------------

        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (Patient patient : p) {
                encoder.writeObject(patient);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not closed properly\n" + e.toString());
            System.exit(1);
        }
    }

    public static String[] readDiagnosisList(String fileName) throws FileNotFoundException {

        String[] diagnosisList = new String[10];
        int listCount = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));//decorator design pattern
            String diagnosisName;

            while ((diagnosisName = br.readLine()) != null && listCount < diagnosisList.length) {
                System.out.println("Diagnosis is..." + diagnosisName);
                diagnosisList[listCount] = diagnosisName;
                listCount++;
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Diagnosis File not Found!" + fnfe.toString());
        } catch (IOException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error during open\n" + ex.toString());
        }

        return diagnosisList;
    }
}
