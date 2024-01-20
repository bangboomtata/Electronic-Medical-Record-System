package Class;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TreatmentCourse {
    private String treatmentID;
    private String department;
    private String startDate;
    private String endDate;
    private String historyID;
    private String effective;
    private Diagnosis diagnosis;
    private Analysis analysis;

    @JsonCreator
    public TreatmentCourse(@JsonProperty("treatmentID") String treatmentID,
            @JsonProperty("department") String department,
            @JsonProperty("startDate") String startDate,
            @JsonProperty("endDate") String endDate,
            @JsonProperty("historyID") String historyID,
            @JsonProperty("effective") String effective,
            @JsonProperty("diagnosis") Diagnosis diagnosis,
            @JsonProperty("analysis") Analysis analysis) {
        this.treatmentID = treatmentID;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.historyID = historyID;
        this.effective = effective;
        this.diagnosis = diagnosis;
        this.analysis = analysis;
    }

    public TreatmentCourse(String treatmentID,
            String department,
            String startDate,
            String endDate,
            String historyID,
            String effective) {
        this.treatmentID = treatmentID;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.historyID = historyID;
        this.effective = effective;
        this.analysis = null;
        this.diagnosis = null;
    }

    public String getTreatmentID() {
        return treatmentID;
    }

    public void setTreatmentID(String treatmentID) {
        this.treatmentID = treatmentID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getHistoryID() {
        return historyID;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

}
