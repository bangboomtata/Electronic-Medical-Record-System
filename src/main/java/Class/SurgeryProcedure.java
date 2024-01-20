package Class;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SurgeryProcedure extends Procedure {

    private String surgeryType;
    private String result;
    private String anesthesia;
    private String addComment;

    @JsonCreator
    public SurgeryProcedure(
            @JsonProperty("patientID") String patientID,
            @JsonProperty("treatmentID") String treatmentID,
            @JsonProperty("procedureID") String procedureID,
            @JsonProperty("date") String date,
            @JsonProperty("time") String time,
            @JsonProperty("type") String type,
            @JsonProperty("surgeryType") String surgeryType,
            @JsonProperty("result") String result,
            @JsonProperty("anesthesia") String anesthesia,
            @JsonProperty("addComment") String addComment) {
        super(patientID, treatmentID, procedureID, date, time, type);
        this.surgeryType = surgeryType;
        this.result = result;
        this.anesthesia = anesthesia;
        this.addComment = addComment;
    }

    public String getSurgeryType() {
        return surgeryType;
    }

    public void setSurgeryType(String surgeryType) {
        this.surgeryType = surgeryType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAnesthesia() {
        return anesthesia;
    }

    public void setAnesthesia(String anesthesia) {
        this.anesthesia = anesthesia;
    }

    public String getAddComment() {
        return addComment;
    }

    public void setAddComment(String addComment) {
        this.addComment = addComment;
    }

}
