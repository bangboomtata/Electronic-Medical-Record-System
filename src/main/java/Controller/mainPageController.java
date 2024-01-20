package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Iterator;

import Class.PHistory;
import Class.PHistoryList;
import Class.Patient;
import Class.PatientList;
import Class.Procedure;
import Class.ProcedureList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.App;
import main.DataManager;

public class mainPageController implements Initializable {

    @FXML
    private ListView<Patient> patientListView;

    @FXML
    private ListView<PHistory> historyListView;

    @FXML
    private ListView<Procedure> procedureListView;

    @FXML
    private ImageView patientImg;

    @FXML
    private ImageView glucoseLevelChart;

    @FXML
    private Label patientName;

    @FXML
    private Label patientID;

    @FXML
    private Label patientAge;
    @FXML
    private ComboBox<String> createCombo;

    @FXML
    private ComboBox<String> editCombo;

    @FXML
    private ComboBox<String> deleteCombo;

    @FXML
    private Label admissionDateLabel;

    @FXML
    private Label departmentLabel;

    @FXML
    private Label directedByLabel;

    @FXML
    private Label medHistory;

    @FXML
    private Label analysisDate;

    @FXML
    private Label hResult;

    @FXML
    private Label fResult;

    @FXML
    private Label oResult;

    @FXML
    private Label rResult;

    @FXML
    private Label diseaseLabel;

    @FXML
    private Label SymptomsLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label stageLabel;

    @FXML
    private Button effectiveLabel;

    @FXML
    private Label treatmentIDTB;

    @FXML
    private BarChart<String, Number> chart;

    Patient patientSelected = null;
    PHistory historySelected = null;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        // make sure nothing is selected
        SelectedHistory.getInstance().setHistory(null);
        SelectedPatient.getInstance().setPatient(null);
        SelectedProcedure.getInstance().setProcedure(null);

        // edit the cell of ListView
        patientListView.setCellFactory(param -> new ListCell<Patient>() {
            @Override
            protected void updateItem(Patient item, boolean empty) {
                // make sure the basic update item methods are done before adding own
                // modification
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // make sure the cell to edit is cleared before modification
                    setText(null);
                    setGraphic(null);

                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("patientItem.fxml"));
                    try {
                        HBox hbox = fxmlLoader.load();
                        patientItemController pic = fxmlLoader.getController();
                        pic.setInfo(item);
                        setGraphic(hbox);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        historyListView.setCellFactory(param -> new ListCell<PHistory>() {
            @Override
            protected void updateItem(PHistory item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(null);
                    setGraphic(null);
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("historyItem.fxml"));
                    try {
                        VBox vBox = fxmlLoader.load();
                        HistoryItemController hic = fxmlLoader.getController();
                        hic.setInfo(item);
                        setGraphic(vBox);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        procedureListView.setCellFactory(param -> new ListCell<Procedure>() {
            @Override
            protected void updateItem(Procedure item, boolean empty) {
                // make sure the basic update item methods are done before adding own
                // modification
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // make sure the cell to edit is cleared before modification
                    setText(null);
                    setGraphic(null);

                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("procedureItem.fxml"));
                    try {
                        HBox hbox = fxmlLoader.load();
                        procedureItemController pic = fxmlLoader.getController();
                        pic.setInfo(item);
                        setGraphic(hbox);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        // load patient details into ListView
        if (!DataManager.patientFileEmpty()) {
            ObservableList<Patient> oa = FXCollections
                    .observableArrayList(DataManager.JSONDeserializePatient().getPatientList());
            if (oa.size() > 0) {
                patientListView.setItems(oa);
            }
        }

        // load data based on selected patient
        patientListView.setOnMouseClicked(e -> {
            patientSelected = patientListView.getSelectionModel().getSelectedItem();
            // load singleton class
            SelectedPatient.getInstance().setPatient(patientSelected);
            SelectedHistory.getInstance().setHistory(null);
            // set all data to default first
            if (chart.getData() != null) {
                chart.getData().clear();
            }
            admissionDateLabel.setText("Admission Date: ");
            departmentLabel.setText("Department: ");
            directedByLabel.setText("Directed By: ");
            medHistory.setText("");
            analysisDate.setText("Date: ");
            hResult.setText("HbA1c: ");
            fResult.setText("FPG: ");
            oResult.setText("OGTT: ");
            rResult.setText("RPG: ");
            diseaseLabel.setText("Disease: ");
            SymptomsLabel.setText("Symptoms: ");
            typeLabel.setText("Type: ");
            stageLabel.setText("Stage: ");
            effectiveLabel.setText("Effective: ");
            treatmentIDTB.setText("Treatment Course : ");
            // when patient is selected
            if (patientSelected != null) {
                patientID.setText("ID: " + patientSelected.getPatientID());
                patientName.setText("Name: " + patientSelected.getName());
                patientAge.setText("Age " + patientSelected.getAge());
                patientImg.setImage(
                        new Image(mainPageController.class.getResourceAsStream(patientSelected.getPictureURL())));

                if (!DataManager.historyFileEmpty()) {
                    List<PHistory> historyList = DataManager.JSONDeserializeHistory().getPatientHistoryList();
                    List<PHistory> listToDisplay = new ArrayList<>();
                    Iterator<PHistory> i = historyList.iterator();

                    // find the patient's history list
                    while (i.hasNext()) {
                        PHistory pHistory = i.next();
                        if (pHistory.getPatientID().equals(patientSelected.getPatientID())) {
                            listToDisplay.add(pHistory);
                        }
                    }
                    ObservableList<PHistory> oa = FXCollections.observableArrayList(listToDisplay);

                    // load patient's history to listview
                    historyListView.setItems(oa);
                    procedureListView.setItems(null);
                }
            }
        });

        // load data based on selected history
        historyListView.setOnMouseClicked(e -> {
            historySelected = historyListView.getSelectionModel().getSelectedItem();
            SelectedHistory.getInstance().setHistory(historySelected);

            admissionDateLabel.setText("Admission Date: " + historySelected.getAdmissionDate());
            departmentLabel.setText("Department: " + historySelected.getDepartment());
            directedByLabel.setText("Directed By: " + historySelected.getDirectedBy());
            medHistory.setText(historySelected.getMedHistory());

            // set all the value to default first
            if (chart.getData() != null) {
                chart.getData().clear();
            }
            analysisDate.setText("Date: ");
            hResult.setText("HbA1c: ");
            fResult.setText("FPG: ");
            oResult.setText("OGTT: ");
            rResult.setText("RPG: ");
            diseaseLabel.setText("Disease: ");
            SymptomsLabel.setText("Symptoms: ");
            typeLabel.setText("Type: ");
            stageLabel.setText("Stage: ");
            effectiveLabel.setText("Effective: ");
            treatmentIDTB.setText("Treatment Course : ");
            // check is the history has treatmentcourse, then load tc data
            if (historySelected.getTc() != null) {
                effectiveLabel.setText("Effective: " + historySelected.getTc().getEffective());
                treatmentIDTB.setText("Treatment Course : " + historySelected.getTc().getTreatmentID());

                if (historySelected.getTc().getAnalysis() != null) {
                    analysisDate.setText("Date: " + historySelected.getTc().getAnalysis().getAnalysisDate());
                    hResult.setText("HbA1c: " + historySelected.getTc().getAnalysis().gethResult() + " %");
                    fResult.setText("FPG: " + historySelected.getTc().getAnalysis().getfResult() + " mmol/l");
                    oResult.setText("OGTT: " + historySelected.getTc().getAnalysis().getoResult() + "mg/dL");
                    rResult.setText("RPG: " + historySelected.getTc().getAnalysis().getrResult() + "mg/dL");

                    XYChart.Series<String, Number> series = new XYChart.Series<>();
                    series.getData().add(new XYChart.Data<>("1",
                            Integer.parseInt(historySelected.getTc().getAnalysis().gethResult())));
                    series.getData().add(new XYChart.Data<>("2",
                            Integer.parseInt(historySelected.getTc().getAnalysis().getfResult())));
                    series.getData().add(new XYChart.Data<>("3",
                            Integer.parseInt(historySelected.getTc().getAnalysis().getoResult())));
                    series.getData().add(new XYChart.Data<>("4",
                            Integer.parseInt(historySelected.getTc().getAnalysis().getrResult())));

                    CategoryAxis xAxis = (CategoryAxis) chart.getXAxis();
                    xAxis.setAnimated(false);
                    chart.getData().add(series);
                }

                if (historySelected.getTc().getDiagnosis() != null) {
                    diseaseLabel.setText("Disease: " + historySelected.getTc().getDiagnosis().getDiseaseName());
                    SymptomsLabel.setText("Symptoms: " + historySelected.getTc().getDiagnosis().getSymptoms());
                    typeLabel.setText("Type: " + historySelected.getTc().getDiagnosis().getType());
                    stageLabel.setText("Stage: " + historySelected.getTc().getDiagnosis().getStage());
                }
            }

            // check if there if procedure in the treatment course, then load the listview
            if (!DataManager.procedureFileEmpty()) {
                List<Procedure> procedureList = DataManager.JSONDeserializeProcedure().getProcedureList();
                List<Procedure> listToDisplay = new ArrayList<>();
                ObservableList<Procedure> oa = FXCollections.observableArrayList();
                if (historySelected.getTc() != null) {
                    Iterator<Procedure> i = procedureList.iterator();

                    while (i.hasNext()) {
                        Procedure procedure = i.next();
                        if (procedure.getTreatmentID().equals(historySelected.getTc().getTreatmentID())) {
                            listToDisplay.add(procedure);
                        }
                    }
                    oa = FXCollections.observableArrayList(listToDisplay);
                }
                procedureListView.setItems(oa);
            }
        });

        // load singleton class
        procedureListView.setOnMouseClicked(e -> {
            SelectedProcedure.getInstance().setProcedure(procedureListView.getSelectionModel().getSelectedItem());
        });

        // load the combobox
        createCombo.getItems().addAll("Patient", "History", "Treatment", "Procedure");
        editCombo.getItems().addAll("Patient", "History", "Treatment", "Procedure");
        deleteCombo.getItems().addAll("Patient", "History", "Procedure");

        // handle events when combobox are selected
        createCombo.setOnAction(e -> {
            String selectedAction = createCombo.getSelectionModel().getSelectedItem();
            if (selectedAction != null) {

                if (selectedAction.equals("Patient")) {
                    createPatient();
                } else if (selectedAction.equals("History")) {
                    createHistory();
                } else if (selectedAction.equals("Treatment")) {
                    createTreatmentc();
                } else if (selectedAction.equals("Procedure")) {
                    createProcedure();
                }
            }
        });

        editCombo.setOnAction(e -> {

            String selectedAction = editCombo.getSelectionModel().getSelectedItem();

            if (selectedAction != null) {
                if (selectedAction.equals("Patient")) {
                    editPatient();
                } else if (selectedAction.equals("History")) {
                    editHistory();
                } else if (selectedAction.equals("Treatment")) {
                    editTreatment();
                } else if (selectedAction.equals("Procedure")) {
                    editProcedure();
                }
            }
        });

        deleteCombo.setOnAction(e -> {
            String selectedAction = deleteCombo.getSelectionModel().getSelectedItem();
            if (selectedAction != null) {
                if (selectedAction.equals("Patient")) {
                    try {
                        deletePatient();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else if (selectedAction.equals("History")) {
                    deleteHistory();
                } else if (selectedAction.equals("Procedure")) {
                    deleteProcedure();
                }
            }

        });

    }

    private void refreshPage() {
        try {
            App.setRoot("mainPage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void toProcedurePage() {
        try {
            App.setRoot("Procedure");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void toPatientPage() {
        try {
            App.setRoot("patientform");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void toTreatmentPage() {
        try {
            App.setRoot("treatment");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void toLoginPage() {
        try {
            App.setRoot("Login");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void toHistoryPage() {
        try {
            App.setRoot("patienthistory");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createPatient() {
        SelectedPatient.getInstance().setPatient(null);
        toPatientPage();
    }

    @FXML
    private void editPatient() {
        // make sure one of the patient is selected for edit
        if (patientSelected != null) {
            toPatientPage();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: No Patient Selected");
            alert.setHeaderText("Error");
            alert.setContentText("No Patient Selected");
            alert.showAndWait();
            refreshPage();
        }
    }

    private void deletePatient() throws IOException {
        // make sure patient is selected to perform delete
        if (patientSelected != null) {
            // delete patient
            PatientList pL = DataManager.JSONDeserializePatient();
            List<Patient> patientRecord = pL.getPatientList();
            patientRecord.removeIf(patient -> patient.getPatientID().equals(patientSelected.getPatientID()));
            pL.setPatientList(patientRecord);

            // delete patient history and procedure by searching using patientID
            PHistoryList hL = DataManager.JSONDeserializeHistory();
            List<PHistory> historyList = hL.getPatientHistoryList();
            historyList.removeIf(pHistory -> pHistory.getPatientID().equals(patientSelected.getPatientID()));
            hL.setPatientHistoryList(historyList);

            ProcedureList proL = DataManager.JSONDeserializeProcedure();
            List<Procedure> procedureList = proL.getProcedureList();
            procedureList.removeIf(procedure -> procedure.getPatientID().equals(patientSelected.getPatientID()));
            proL.setProcedureList(procedureList);

            DataManager.JSONSerializePatient(pL);
            DataManager.JSONSerializeHistory(hL);
            DataManager.JSONSerializeProcedure(proL);
            refreshPage();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: No Patient Selected");
            alert.setHeaderText("Error");
            alert.setContentText("No Patient Selected");
            alert.showAndWait();
            refreshPage();
        }

    }

    private void createHistory() {
        SelectedHistory.getInstance().setHistory(null);
        toHistoryPage();
    }

    private void editHistory() {
        // make sure history is selected
        if (SelectedHistory.getInstance().getHistory() != null) {
            toHistoryPage();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: No History Selected");
            alert.setHeaderText("Error");
            alert.setContentText("No History Selected");
            alert.showAndWait();
            refreshPage();
        }
    }

    private void deleteHistory() {
        // make sure history is selected
        if (SelectedHistory.getInstance().getHistory() != null) {
            List<PHistory> historyList = DataManager.JSONDeserializeHistory().getPatientHistoryList();
            historyList.removeIf(pHistory -> pHistory.getHistoryID()
                    .equals(SelectedHistory.getInstance().getHistory().getHistoryID()));
            SelectedHistory.getInstance().setHistory(null);

            PHistoryList hL = new PHistoryList();
            hL.setPatientHistoryList(historyList);
            DataManager.JSONSerializeHistory(hL);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: No History Selected");
            alert.setHeaderText("Error");
            alert.setContentText("No History Selected");
            alert.showAndWait();
            refreshPage();
        }
    }

    private void createTreatmentc() {
        // make sure history is selected to create treatment course within a history
        SelectedHistory.getInstance().setHistory(null);
        if (patientSelected != null) {
            toTreatmentPage();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: No Patient Selected");
            alert.setHeaderText("Error");
            alert.setContentText("No Patient Selected");
            alert.showAndWait();
            refreshPage();
        }
    }

    private void editTreatment() {
        // make sure history is selected
        if (SelectedHistory.getInstance().getHistory() != null) {
            // and the history does contain treatment course
            if (SelectedHistory.getInstance().getHistory().getTc() != null) {
                toTreatmentPage();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error: Treatment Course is not created");
                alert.setHeaderText("Error");
                alert.setContentText("There is no treatment course detected in the current patient history");
                alert.showAndWait();
                refreshPage();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: No History Selected");
            alert.setHeaderText("Error");
            alert.setContentText("No History Selected");
            alert.showAndWait();
            refreshPage();
        }
    }

    private void createProcedure() {
        SelectedProcedure.getInstance().setProcedure(null);
        // make sure history is selected
        if (SelectedHistory.getInstance().getHistory() != null) {
            // and the history does contain treatment course
            if (SelectedHistory.getInstance().getHistory().getTc() != null) {
                toProcedurePage();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: No History Selected");
            alert.setHeaderText("Error");
            alert.setContentText("No History Selected");
            alert.showAndWait();
            refreshPage();
        }
    }

    private void editProcedure() {
        // make sure procedure is selected
        if (SelectedProcedure.getInstance().getProcedure() != null) {
            toProcedurePage();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: No Procedure Selected");
            alert.setHeaderText("Error");
            alert.setContentText("No Procedure Selected");
            alert.showAndWait();
            refreshPage();
        }
    }

    private void deleteProcedure() {
        if (SelectedProcedure.getInstance().getProcedure() != null) {
            List<Procedure> procedureList = DataManager.JSONDeserializeProcedure().getProcedureList();
            procedureList.removeIf(procedure -> procedure.getProcedureID()
                    .equals(SelectedProcedure.getInstance().getProcedure().getProcedureID()));
            SelectedProcedure.getInstance().setProcedure(null);
            DataManager.JSONSerializeProcedure(new ProcedureList(procedureList));
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: No Procedure Selected");
            alert.setHeaderText("Error");
            alert.setContentText("No Procedure Selected");
            alert.showAndWait();
        }
        refreshPage();
    }

    @FXML
    private void logout() {
        toLoginPage();
    }
}