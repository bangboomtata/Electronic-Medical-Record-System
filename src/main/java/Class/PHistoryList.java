package Class;

import java.util.ArrayList;
import java.util.List;

public class PHistoryList {
    private List<PHistory> patientHistory;

    public PHistoryList() {
        this.patientHistory = new ArrayList<PHistory>();
    }

    public List<PHistory> getPatientHistoryList() {
        return patientHistory;
    }

    public void setPatientHistoryList(List<PHistory> patientHistory) {
        this.patientHistory = patientHistory;
    }

}
