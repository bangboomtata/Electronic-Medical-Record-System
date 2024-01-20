package Class;

import java.util.ArrayList;
import java.util.List;

public class PatientList {

    private List<Patient> patientList;

    public PatientList() {
        this.patientList = new ArrayList<Patient>();
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

}
