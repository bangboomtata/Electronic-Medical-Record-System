package Class;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicineProcedure extends Procedure {

    private List<String> medicineList;

    @JsonCreator
    public MedicineProcedure(
            @JsonProperty("patientID") String patientID,
            @JsonProperty("treatmentID") String treatmentID,
            @JsonProperty("procedureID") String procedureID,
            @JsonProperty("date") String date,
            @JsonProperty("time") String time,
            @JsonProperty("type") String type,
            @JsonProperty("medicineList") List<String> medicineList) {
        super(patientID, treatmentID, procedureID, date, time, type);
        this.medicineList = medicineList;
    }

    public List<String> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<String> medicineList) {
        this.medicineList = medicineList;
    }

}
