package Controller;

import Class.Patient;

// singleton class to restrict user from creating multiple object of this class
public class SelectedPatient {

    private static SelectedPatient instance = null;
    private Patient patient;

    private SelectedPatient() {
    }

    public static SelectedPatient getInstance() {
        if (instance == null) {
            instance = new SelectedPatient();
        }
        return instance;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
