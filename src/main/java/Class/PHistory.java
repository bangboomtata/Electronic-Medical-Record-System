package Class;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PHistory {
    private String historyID;
    private String patientID;
    private String admissionDate;
    private String ward;
    private String department;
    private String directedBy;
    private String allergy;
    private String majorCom;
    private String medHistory;
    private String treatResult;
    private String specialCom;

    private TreatmentCourse tc;

    @JsonCreator
    public PHistory(@JsonProperty("historyID") String historyID,
            @JsonProperty("patientID") String patientID,
            @JsonProperty("admissionDate") String admissionDate,
            @JsonProperty("ward") String ward,
            @JsonProperty("department") String department,
            @JsonProperty("directedBy") String directedBy,
            @JsonProperty("allergy") String allergy,
            @JsonProperty("majorCom") String majorCom,
            @JsonProperty("medHistory") String medHistory,
            @JsonProperty("treatResult") String treatResult,
            @JsonProperty("specialCom") String specialCom,
            @JsonProperty("tc") TreatmentCourse tc) {
        this.historyID = historyID;
        this.patientID = patientID;
        this.admissionDate = admissionDate;
        this.ward = ward;
        this.department = department;
        this.directedBy = directedBy;
        this.allergy = allergy;
        this.majorCom = majorCom;
        this.medHistory = medHistory;
        this.treatResult = treatResult;
        this.specialCom = specialCom;
        this.tc = tc;
    }

    public String getHistoryID() {
        return historyID;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getMajorCom() {
        return majorCom;
    }

    public void setMajorCom(String majorCom) {
        this.majorCom = majorCom;
    }

    public String getMedHistory() {
        return medHistory;
    }

    public void setMedHistory(String medHistory) {
        this.medHistory = medHistory;
    }

    public String getTreatResult() {
        return treatResult;
    }

    public void setTreatResult(String treatResult) {
        this.treatResult = treatResult;
    }

    public String getSpecialCom() {
        return specialCom;
    }

    public void setSpecialCom(String specialCom) {
        this.specialCom = specialCom;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public TreatmentCourse getTc() {
        return tc;
    }

    public void setTc(TreatmentCourse tc) {
        this.tc = tc;
    }

}
