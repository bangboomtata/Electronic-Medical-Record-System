package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import Class.Diagnosis;
import Class.PHistory;
import Class.PHistoryList;
import Class.TreatmentCourse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;
import main.DataManager;

public class TreatmentFormController implements Initializable {

    private PHistoryList patientHistoryList = new PHistoryList();

    @FXML
    private TextField treatmentIDTB;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField departmentTB;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField historyIDTB;

    @FXML
    private ComboBox<String> effectiveCB;

    @FXML
    private ComboBox<String> historyIDCB;

    @FXML
    private Button treatmentButton;

    @FXML
    private Button saveToMain;

    @FXML
    private ChoiceBox<String> StageCB;

    @FXML
    private ChoiceBox<String> TypeCB;

    @FXML
    private TextField diagnosisId;

    @FXML
    private TextField diseaseName;

    @FXML
    private TextField symptoms;

    @FXML
    private List<PHistory> PHistoryList;
    @FXML
    private List<TreatmentCourse> TreatmentList;

    @Override

    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<String> effectiveCBList = FXCollections.observableArrayList("Yes", "No");
        ObservableList<String> StageCBList = FXCollections.observableArrayList("1", "2", "3", "4");
        ObservableList<String> TypeCBList = FXCollections.observableArrayList("Type 1 Diabetes", "Genetics diabetes ",
                "Gestational diabetes");
        effectiveCB.getItems().addAll(effectiveCBList);
        StageCB.getItems().addAll(StageCBList);
        TypeCB.getItems().addAll(TypeCBList);
        // load historyID to the combo box when creating new treatment course
        if (SelectedHistory.getInstance().getHistory() == null) {
            Iterator<PHistory> i = DataManager.JSONDeserializeHistory().getPatientHistoryList().iterator();
            ObservableList<String> historyIDList = FXCollections.observableArrayList();
            while (i.hasNext()) {
                PHistory history = i.next();
                // load the historyID into the combo box when the history has no treatmentcourse
                // created yet and
                // when the history belongs to the patient selected
                if (history.getTc() == null
                        && history.getPatientID().equals(SelectedPatient.getInstance().getPatient().getPatientID())) {
                    historyIDList.add(history.getHistoryID());
                }
            }

            historyIDCB.getItems().addAll(historyIDList);
            treatmentButton.setOnAction(e -> {
                createTreatment();
            });
        } else {
            // if the history selected already has treatment course in it, load the data to
            // edit
            if (SelectedHistory.getInstance().getHistory().getTc() != null) {
                TreatmentCourse tc = SelectedHistory.getInstance().getHistory().getTc();
                treatmentIDTB.setText(tc.getTreatmentID());
                departmentTB.setText(tc.getDepartment());
                startDatePicker.setValue(LocalDate.parse(tc.getStartDate()));
                endDatePicker.setValue(LocalDate.parse(tc.getEndDate()));
                historyIDCB.getItems().add(tc.getHistoryID());
                historyIDCB.setValue(tc.getHistoryID());
                effectiveCB.setValue(tc.getEffective());
                // if the treatment course has diagnosis already, load the data too
                if (SelectedHistory.getInstance().getHistory().getTc().getDiagnosis() != null) {
                    Diagnosis d = SelectedHistory.getInstance().getHistory().getTc().getDiagnosis();
                    diagnosisId.setText(d.getDiagnosisID());
                    diseaseName.setText(d.getDiseaseName());
                    StageCB.setValue(d.getStage());
                    TypeCB.setValue(d.getType());
                    symptoms.setText(d.getSymptoms());
                }
                // set the function of the button to edit
                treatmentButton.setOnAction(e -> {
                    editTreatment();
                });
            }
        }
    }

    @FXML
    private void toMainPage() {
        try {
            App.setRoot("mainPage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void toAnalysisPage() {
        try {
            App.setRoot("Analysis");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createTreatment() {
    if (treatmentIDTB.getText().isEmpty() || departmentTB.getText().isEmpty() ||
            startDatePicker.getValue() == null || endDatePicker.getValue() == null ||
            historyIDCB.getValue() == null || effectiveCB.getValue() == null ||
            diagnosisId.getText().isEmpty() || diseaseName.getText().isEmpty() ||
            symptoms.getText().isEmpty() || StageCB.getValue() == null || TypeCB.getValue() == null) {
        // Display an error message or handle the error accordingly
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: Please fill in all the details");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in all the details");
            alert.showAndWait();
        return; // Exit the method if there's an error
    }

     // Check if departmentTB contains numbers
    if (departmentTB.getText().matches(".*\\d.*")) {
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: Departments cannot contain numbers; please try again! ");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in Department again! Departments cannot contain numbers");
            alert.showAndWait();
        return; // Exit the method if there's an error
    }

    // Check if symptoms contain numbers
    
    TreatmentCourse newTreatment = new TreatmentCourse(treatmentIDTB.getText(), departmentTB.getText(),
            startDatePicker.getValue().toString(), endDatePicker.getValue().toString(),
            historyIDCB.getValue(), effectiveCB.getValue());

    Diagnosis newDiagnosis = new Diagnosis(diagnosisId.getText(), diseaseName.getText(), symptoms.getText(),
            StageCB.getValue(), TypeCB.getValue());

    List<PHistory> historyList = DataManager.JSONDeserializeHistory().getPatientHistoryList();
    // find the correct history to insert the new treatment course
    for (PHistory pH : historyList) {
        if (pH.getHistoryID().equals(historyIDCB.getValue())) {
            pH.setTc(newTreatment);
            pH.getTc().setDiagnosis(newDiagnosis);
            SelectedHistory.getInstance().setHistory(pH);
        }
    }
    patientHistoryList.setPatientHistoryList(historyList);
    DataManager.JSONSerializeHistory(patientHistoryList);
    toAnalysisPage();
}

    public void editTreatment() {
        List<PHistory> historyList = DataManager.JSONDeserializeHistory().getPatientHistoryList();
        TreatmentCourse newTreatment = new TreatmentCourse(treatmentIDTB.getText(), departmentTB.getText(),
                startDatePicker.getValue().toString(), endDatePicker.getValue().toString(),
                historyIDCB.getValue(), effectiveCB.getValue());

        Diagnosis newDiagnosis = new Diagnosis(diagnosisId.getText(), diseaseName.getText(), symptoms.getText(),
                StageCB.getValue(), TypeCB.getValue());

        if (treatmentIDTB.getText().isEmpty() || departmentTB.getText().isEmpty() ||
            startDatePicker.getValue() == null || endDatePicker.getValue() == null ||
            historyIDCB.getValue() == null || effectiveCB.getValue() == null ||
            diagnosisId.getText().isEmpty() || diseaseName.getText().isEmpty() ||
            symptoms.getText().isEmpty() || StageCB.getValue() == null || TypeCB.getValue() == null) {
        // Display an error message or handle the error accordingly
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: Please fill in all the details");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in all the details");
            alert.showAndWait();
        return; // Exit the method if there's an error
    }
        // Check if departmentTB contains numbers
        if (departmentTB.getText().matches(".*\\d.*")) {
             Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error: Departments cannot contain numbers; please try again! ");
                alert.setHeaderText("Error");
                alert.setContentText("Please fill in Department again! Departments cannot contain numbers");
                alert.showAndWait();
            return; // Exit the method if there's an error
    }

       
        // find the correct history to replace the treatment coursse with the new one
        for (int i = 0; i < historyList.size(); i++) {
            if (historyList.get(i).getHistoryID().equals(historyIDCB.getValue())) {
                historyList.get(i).setTc(newTreatment);
                historyList.get(i).getTc().setDiagnosis(newDiagnosis);
            }

            PHistoryList pH = new PHistoryList();
            pH.setPatientHistoryList(historyList);

            DataManager.JSONSerializeHistory(pH);

            toAnalysisPage();
        }
    }
}
