package Class;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Analysis {
    private String analysisDate;
    private String hResult;
    private String fResult;
    private String oResult;
    private String rResult;
    private String hAnalysis;
    private String fAnalysis;
    private String oAnalysis;
    private String rAnalysis;
    private String geneticResult;
    private String antibodyResult;
    private static String treatmentID;

    @JsonCreator
    public Analysis(@JsonProperty("analysisDate") String analysisDate,
            @JsonProperty("hResult") String hResult,
            @JsonProperty("fResult") String fResult,
            @JsonProperty("oResult") String oResult,
            @JsonProperty("rResult") String rResult,
            @JsonProperty("hAnalysis") String hAnalysis,
            @JsonProperty("fAnalysis") String fAnalysis,
            @JsonProperty("oAnalysis") String oAnalysis,
            @JsonProperty("rAnalysis") String rAnalysis,
            @JsonProperty("geneticResult") String geneticResult,
            @JsonProperty("antibodyResult") String antibodyResult) {

        this.analysisDate = analysisDate;
        this.hResult = hResult;
        this.fResult = fResult;
        this.oResult = oResult;
        this.rResult = rResult;
        this.hAnalysis = hAnalysis;
        this.fAnalysis = fAnalysis;
        this.oAnalysis = oAnalysis;
        this.rAnalysis = rAnalysis;
        this.geneticResult = geneticResult;
        this.antibodyResult = antibodyResult;
    }

    public String getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(String analysisDate) {
        this.analysisDate = analysisDate;
    }

    public String gethResult() {
        return hResult;
    }

    public void setHResult(String hResult) {
        this.hResult = hResult;
    }

    public String getfResult() {
        return fResult;
    }

    public void setFResult(String fResult) {
        this.fResult = fResult;
    }

    public String getoResult() {
        return oResult;
    }

    public void setOResult(String oResult) {
        this.oResult = oResult;
    }

    public String getrResult() {
        return rResult;
    }

    public void setRResult(String rResult) {
        this.rResult = rResult;
    }

    public String getHAnalysis() {
        return hAnalysis;
    }

    public void setHAnalysis(String hAnalysis) {
        this.hAnalysis = hAnalysis;
    }

    public String getFAnalysis() {
        return fAnalysis;
    }

    public void setFAnalysis(String fAnalysis) {
        this.fAnalysis = fAnalysis;
    }

    public String getOAnalysis() {
        return oAnalysis;
    }

    public void setOAnalysis(String oAnalysis) {
        this.oAnalysis = oAnalysis;
    }

    public String getRAnalysis() {
        return rAnalysis;
    }

    public void setRAnalysis(String rAnalysis) {
        this.rAnalysis = rAnalysis;
    }

    public String getGeneticResult() {
        return geneticResult;
    }

    public void setGeneticResult(String geneticResult) {
        this.geneticResult = geneticResult;
    }

    public String getAntibodyResult() {
        return antibodyResult;
    }

    public void setAntibodyResult(String antibodyResult) {
        this.antibodyResult = antibodyResult;
    }

    public static String getTreatmentID() {
        return treatmentID;
    }

}
