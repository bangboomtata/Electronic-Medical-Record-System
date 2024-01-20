package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import Class.PHistory;
import Class.PHistoryList;
import Class.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;
import main.DataManager;

public class PHistoryFormController implements Initializable {

    private PHistoryList patientHistoryList = new PHistoryList();

    @FXML
    private TextField historyIDTB;

    @FXML
    private DatePicker admissionDatePicker;

    @FXML
    private TextField directedByTB;

    @FXML
    private TextField wardNoTB;

    @FXML
    private TextField departmentTB;

    @FXML
    private TextField allergiesTB;

    @FXML
    private TextField majorCompTB;

    @FXML
    private TextField mHistoryTB;

    @FXML
    private TextField treatmentResultsTB;

    @FXML
    private TextField specialCommentTB;

    @FXML
    private ComboBox<String> patientIDCB;

    @FXML
    private Button formButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // TODO Auto-generated method stub
        List<Patient> patientList = DataManager.JSONDeserializePatient().getPatientList();
        Iterator<Patient> i = patientList.iterator();

        // load the combobox with all patientID
        while (i.hasNext()) {
            patientIDCB.getItems().add(i.next().getPatientID());
        }

        // if there is a history selected from the main page, set the button to edit
        // function and load the data
        // else set to create
        if (SelectedHistory.getInstance().getHistory() != null) {
            PHistory pHistory = SelectedHistory.getInstance().getHistory();
            formButton.setText("Save");
            historyIDTB.setText(pHistory.getHistoryID());
            patientIDCB.setValue(pHistory.getPatientID());
            admissionDatePicker.setValue(LocalDate.parse(pHistory.getAdmissionDate()));
            wardNoTB.setText(pHistory.getWard());
            departmentTB.setText(pHistory.getDepartment());
            directedByTB.setText(pHistory.getDirectedBy());
            allergiesTB.setText(pHistory.getAllergy());
            majorCompTB.setText(pHistory.getMajorCom());
            mHistoryTB.setText(pHistory.getMedHistory());
            treatmentResultsTB.setText(pHistory.getTreatResult());
            specialCommentTB.setText(pHistory.getSpecialCom());
            formButton.setOnAction(e -> {
                editHistory();
            });
        } else {
            formButton.setText("Create");
            formButton.setOnAction(e -> {
                createHistory();
            });
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

    private boolean checkFields() {
    if(historyIDTB.getText().isEmpty() || patientIDCB.getValue() == null || admissionDatePicker.getValue() == null ||
        wardNoTB.getText().isEmpty() || departmentTB.getText().isEmpty() || directedByTB.getText().isEmpty() ||
        allergiesTB.getText().isEmpty() || majorCompTB.getText().isEmpty() || mHistoryTB.getText().isEmpty() ||
        treatmentResultsTB.getText().isEmpty() ) {
        return false;
    }
        return true;
    }

    private void createHistory() {
        if (!checkFields()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: Empty Input");
            alert.setHeaderText("Error");
            alert.setContentText("Kindly check your input data, make sure everything is filled up.");
            alert.showAndWait();
            return;
        }
        PHistory newHistory = new PHistory(historyIDTB.getText(), patientIDCB.getValue(),
                admissionDatePicker.getValue().toString(),
                wardNoTB.getText(), departmentTB.getText(), directedByTB.getText(), allergiesTB.getText(),
                majorCompTB.getText(),
                mHistoryTB.getText(), treatmentResultsTB.getText(), specialCommentTB.getText(), null);

        List<PHistory> historyList = new ArrayList<>();

        if (!DataManager.historyFileEmpty()) {
            historyList = DataManager.JSONDeserializeHistory().getPatientHistoryList();
        }

        historyList.add(newHistory);
        patientHistoryList.setPatientHistoryList(historyList);
        DataManager.JSONSerializeHistory(patientHistoryList);

        toMainPage();
    }

    private void editHistory() {

        List<PHistory> historyList = DataManager.JSONDeserializeHistory().getPatientHistoryList();

        for (PHistory pH : historyList) {
            if (pH.getHistoryID().equals(SelectedHistory.getInstance().getHistory().getHistoryID())) {
                if (!checkFields()) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error: Empty Input");
                    alert.setHeaderText("Error");
                    alert.setContentText("Kindly check your input data, make sure everything is filled up.");
                    alert.showAndWait();
                    return;
                }
                pH.setHistoryID(historyIDTB.getText());
                pH.setPatientID(patientIDCB.getValue());
                pH.setAdmissionDate(admissionDatePicker.getValue().toString());
                pH.setWard(wardNoTB.getText());
                pH.setDepartment(departmentTB.getText());
                pH.setDirectedBy(directedByTB.getText());
                pH.setAllergy(allergiesTB.getText());
                pH.setMajorCom(majorCompTB.getText());
                pH.setMedHistory(mHistoryTB.getText());
                pH.setTreatResult(treatmentResultsTB.getText());
                pH.setSpecialCom(specialCommentTB.getText());
            }
        }

        patientHistoryList.setPatientHistoryList(historyList);
        DataManager.JSONSerializeHistory(patientHistoryList);
        toMainPage();
    }
}
