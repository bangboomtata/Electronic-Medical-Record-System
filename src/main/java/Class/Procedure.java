package Class;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/*include one more element within the object during serialization 
to let json know which subclass does the object belongs to so
that json can deserialize the class without any error*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "classType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SurgeryProcedure.class, name = "Surgery"),
    @JsonSubTypes.Type(value = MedicineProcedure.class, name = "Medicine")
})

public abstract class Procedure {
    private String patientID;
    private String treatmentID;
    private String procedureID;
    private String date;
    private String time;
    private String type;

    @JsonCreator
    public Procedure(
            @JsonProperty("patientID") String patientID,
            @JsonProperty("treatmentID") String treatmentID,
            @JsonProperty("procedureID") String procedureID,
            @JsonProperty("date") String date,
            @JsonProperty("time") String time,
            @JsonProperty("type") String type) {
        this.patientID = patientID;
        this.treatmentID = treatmentID;
        this.procedureID = procedureID;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
        
    public String getTreatmentID() {
        return treatmentID;
    }

    public void setTreatmentID(String treatmentID) {
        this.treatmentID = treatmentID;
    }

    public String getProcedureID() {
        return procedureID;
    }

    public void setProcedureID(String procedureID) {
        this.procedureID = procedureID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    
}
