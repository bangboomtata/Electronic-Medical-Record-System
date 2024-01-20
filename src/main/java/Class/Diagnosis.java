package Class;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Diagnosis {
    private String diagnosisId;
    private String DisName;
    private String Symptoms;
    private String Stage;
    private String Type;

    @JsonCreator
    public Diagnosis(
            @JsonProperty("diagnosisId") String diagnosisId,
            @JsonProperty("Disease Name") String DisName,
            @JsonProperty("Symptoms") String Symptoms,
            @JsonProperty("Stage") String Stage,
            @JsonProperty("Type") String Type) {

        this.diagnosisId = diagnosisId;
        this.DisName = DisName;
        this.Symptoms = Symptoms;
        this.Stage = Stage;
        this.Type = Type;
    }

    public String getDiagnosisID() {
        return diagnosisId;
    }

    public void setDiagnosisID(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    // Disease Name
    public String getDiseaseName() {
        return DisName;
    }

    public void setDiseaseName(String DisName) {
        this.DisName = DisName;
    }

    // Symptoms
    public String getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(String Symptoms) {
        this.Symptoms = Symptoms;
    }

    // Stage
    public String getStage() {
        return Stage;
    }

    public void setStage(String Stage) {
        this.Stage = Stage;
    }

    // Type
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
}