package main;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import Class.*;

public class DataManager {
    private final static File patientRecord = new File("src/main/java/records/patient.JSON");
    private final static File patientHistory = new File("src/main/java/records/patientHistory.JSON");
    private final static File procedureRecord = new File("src/main/java/records/procedureRecord.JSON");

    public static File getPatientFile() {
        return patientRecord;
    }

    public static File getPatientHistoryFile() {
        return patientHistory;
    }

    public static File getProcedureFile() {
        return procedureRecord;
    }

    public static boolean patientFileEmpty() {
        return patientRecord.length() == 0;
    }

    public static boolean historyFileEmpty() {
        return patientHistory.length() == 0;
    }

    public static boolean procedureFileEmpty() {
        return procedureRecord.length() == 0;
    }

    public static void JSONSerializePatient(PatientList pL) {

        // using objectmapper and write the value of the object into jason file
        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(patientRecord, pL);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // desirialize the list of patient from json file
    public static PatientList JSONDeserializePatient() {

        if (!patientFileEmpty()) {
            ObjectMapper om = new ObjectMapper();
            try {
                return om.readValue(patientRecord, PatientList.class);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void JSONSerializeHistory(PHistoryList hL) {

        // using objectmapper and write the value of the object into jason file
        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(patientHistory, hL);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // desirialize the list of patient from json file
    public static PHistoryList JSONDeserializeHistory() {

        if (!historyFileEmpty()) {
            ObjectMapper om = new ObjectMapper();
            try {
                return om.readValue(patientHistory, PHistoryList.class);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void JSONSerializeProcedure(ProcedureList pL) {

        // using objectmapper and write the value of the object into jason file
        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(procedureRecord, pL);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ProcedureList JSONDeserializeProcedure() {

        if (!historyFileEmpty()) {
            ObjectMapper om = new ObjectMapper();
            try {
                return om.readValue(procedureRecord, ProcedureList.class);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
