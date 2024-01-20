package Controller;

import Class.MedicineProcedure;
import Class.Procedure;
import Class.ProcedureList;
import Class.SurgeryProcedure;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import main.App;
import main.DataManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class procedureFormController implements Initializable {
    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField procedureIDTB;

    @FXML
    private ComboBox<String> surgeryType;

    @FXML
    private RadioButton Medicine;

    @FXML
    private ToggleGroup procedureType;

    @FXML
    private RadioButton Surgery;

    @FXML
    private RadioButton successful;

    @FXML
    private ToggleGroup surgeryResult;

    @FXML
    private RadioButton unsuccessful;

    @FXML
    private ComboBox<String> anesthesia;

    @FXML
    private TextArea additionalCommentTB;

    @FXML
    private StackPane medicineStack;

    @FXML
    private CheckBox insulin;

    @FXML
    private CheckBox glynase;

    @FXML
    private CheckBox glyburide;

    @FXML
    private CheckBox biguanides;

    @FXML
    private CheckBox pramlintide;

    @FXML
    private CheckBox meglitinides;

    @FXML
    private CheckBox alpha;

    @FXML
    private CheckBox metformin;

    @FXML
    private CheckBox dopamine;

    @FXML
    private CheckBox dipeptidyl;

    @FXML
    private TextField time;

    @FXML
    private Button procedureButton;

    @FXML
    private void toMainPage() {
        try {
            App.setRoot("mainPage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private List<String> getSelectedMedicine() {
        // create a list to save all the medicines selected
        List<String> selectedMedicines = new ArrayList<>();
        if (alpha.isSelected()) {
            selectedMedicines.add(alpha.getText());
        }
        if (biguanides.isSelected()) {
            selectedMedicines.add(biguanides.getText());
        }

        if (dipeptidyl.isSelected()) {
            selectedMedicines.add(dipeptidyl.getText());
        }

        if (dopamine.isSelected()) {
            selectedMedicines.add(dopamine.getText());
        }

        if (glyburide.isSelected()) {
            selectedMedicines.add(glyburide.getText());
        }

        if (glynase.isSelected()) {
            selectedMedicines.add(glynase.getText());
        }

        if (insulin.isSelected()) {
            selectedMedicines.add(insulin.getText());
        }

        if (meglitinides.isSelected()) {
            selectedMedicines.add(meglitinides.getText());
        }

        if (metformin.isSelected()) {
            selectedMedicines.add(metformin.getText());
        }

        if (pramlintide.isSelected()) {
            selectedMedicines.add(pramlintide.getText());
        }

        return selectedMedicines;
    }

    @FXML
    private void setCreateButton() {
        procedureButton.setText("Create");
        procedureButton.setOnAction(e -> {
            try {
                List<Procedure> procedureList = DataManager.JSONDeserializeProcedure().getProcedureList();
                // make sure time is interger
                int intTime = Integer.parseInt(time.getText());
                if (intTime > 2359) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error occurred");
                    alert.setContentText("Please make sure all of your input value and format are correct");
                    alert.showAndWait();
                    return;
                }
                if (time.getText().length() < 4) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error occurred");
                    alert.setContentText("Please make sure all of your input value and format are correct");
                    alert.showAndWait();
                    return;
                }

                // if medicine is selected, create MedicineProcedure class object
                if (((RadioButton) procedureType.getSelectedToggle()) == Medicine) {
                    // Check if procedureID, date, time, and medicine are empty
                    if (procedureIDTB.getText().isEmpty() || datePicker.getValue() == null || time.getText().isEmpty()
                            || getSelectedMedicine().isEmpty()) {
                        // Show alert if any of the required fields are empty
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Empty Fields");
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();
                        return;
                    }
                    MedicineProcedure newMedProcedure = new MedicineProcedure(
                            SelectedPatient.getInstance().getPatient().getPatientID(),
                            SelectedHistory.getInstance().getHistory().getTc().getTreatmentID(),
                            procedureIDTB.getText(),
                            (datePicker.getValue()).toString(), time.getText(),
                            ((RadioButton) procedureType.getSelectedToggle()).getText(),
                            getSelectedMedicine());
                    System.out.println(((RadioButton) procedureType.getSelectedToggle()).getText());
                    procedureList.add(newMedProcedure);
                    DataManager.JSONSerializeProcedure(new ProcedureList(procedureList));
                } else {
                    // else SurgeryProcedure class object
                    if (SelectedPatient.getInstance().getPatient().getPatientID() == null ||
                            SelectedHistory.getInstance().getHistory().getTc().getTreatmentID() == null ||
                            procedureIDTB.getText().isEmpty() ||
                            datePicker.getValue() == null ||
                            time.getText().isEmpty() ||
                            procedureType.getSelectedToggle() == null ||
                            surgeryType.getValue() == null ||
                            surgeryResult.getSelectedToggle() == null ||
                            anesthesia.getValue() == null ||
                            additionalCommentTB.getText().isEmpty()) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Empty Fields");
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();
                        
                        return;
                    }
                    SurgeryProcedure newSurProcedure = new SurgeryProcedure(
                            SelectedPatient.getInstance().getPatient().getPatientID(),
                            SelectedHistory.getInstance().getHistory().getTc().getTreatmentID(),
                            procedureIDTB.getText(), (datePicker.getValue()).toString(), time.getText(),
                            ((RadioButton) procedureType.getSelectedToggle()).getText(),
                            surgeryType.getValue(), ((RadioButton) surgeryResult.getSelectedToggle()).getText(),
                            anesthesia.getValue(),
                            additionalCommentTB.getText());
                    System.out.println(((RadioButton) procedureType.getSelectedToggle()).getText());
                    System.out.println(((RadioButton) surgeryResult.getSelectedToggle()).getText());
                    procedureList.add(newSurProcedure);
                    DataManager.JSONSerializeProcedure(new ProcedureList(procedureList));
                }
                toMainPage();
            } catch (Exception ex) {
                // Handle any other exceptions that may occur
                ex.printStackTrace();
                // Show an error alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error occurred");
                alert.setContentText("Please make sure all of your input value and format are correct");
                alert.showAndWait();
            }
        });
    }

    // set button to save function
    private void setSaveButton(Procedure selectedProcedure) {
        this.procedureButton.setText("Save");
        this.procedureButton.setOnAction(e -> {
            List<Procedure> procedureList = DataManager.JSONDeserializeProcedure().getProcedureList();

            for (int i = 0; i < procedureList.size(); i++) {
                if (procedureList.get(i).getProcedureID().equals(selectedProcedure.getProcedureID())) {
                    RadioButton pTypeSelected = (RadioButton) procedureType.getSelectedToggle();
                    if (pTypeSelected == Medicine) {
                        if (procedureIDTB.getText().isEmpty() || datePicker.getValue() == null
                                || time.getText().isEmpty()
                                || getSelectedMedicine().isEmpty()) {
                            // Show alert if any of the required fields are empty
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Empty Fields");
                            alert.setContentText("Please fill in all the required fields.");
                            alert.showAndWait();
                            return;
                        }
                        MedicineProcedure newMedProcedure = new MedicineProcedure(
                                SelectedPatient.getInstance().getPatient().getPatientID(),
                                SelectedHistory.getInstance().getHistory().getTc().getTreatmentID(),
                                procedureIDTB.getText(),
                                (datePicker.getValue()).toString(), time.getText(),
                                ((RadioButton) procedureType.getSelectedToggle()).getText(),
                                getSelectedMedicine());
                        // replace with the old data
                        procedureList.set(i, newMedProcedure);
                    } else if (pTypeSelected == Surgery) {
                        if (SelectedPatient.getInstance().getPatient().getPatientID() == null ||
                                SelectedHistory.getInstance().getHistory().getTc().getTreatmentID() == null ||
                                procedureIDTB.getText().isEmpty() ||
                                datePicker.getValue() == null ||
                                time.getText().isEmpty() ||
                                procedureType.getSelectedToggle() == null ||
                                surgeryType.getValue() == null ||
                                surgeryResult.getSelectedToggle() == null ||
                                anesthesia.getValue() == null ||
                                additionalCommentTB.getText().isEmpty()) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Empty Fields");
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();
                        return;
                        }
                        SurgeryProcedure newSurProcedure = new SurgeryProcedure(
                                SelectedPatient.getInstance().getPatient().getPatientID(),
                                SelectedHistory.getInstance().getHistory().getTc().getTreatmentID(),
                                procedureIDTB.getText(), (datePicker.getValue()).toString(), time.getText(),
                                ((RadioButton) procedureType.getSelectedToggle()).getText(),
                                surgeryType.getValue(), surgeryResult.getSelectedToggle().toString(),
                                anesthesia.getValue(),
                                additionalCommentTB.getText());
                        procedureList.set(i, newSurProcedure);
                    }
                }
            }
            DataManager.JSONSerializeProcedure(new ProcedureList(procedureList));
            toMainPage();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // when medicine is selected, show medicine pane
        Medicine.setOnAction(e -> {
            medicineStack.setVisible(true);
        });

        // when surgery is selected, hide medicine pane to show surgery form
        Surgery.setOnAction(e -> {
            medicineStack.setVisible(false);
        });

        surgeryType.getItems().addAll("Gastric Bypass", "Sleeve Gastrectomy", "Duodenal Switch", "SADI-S");
        anesthesia.getItems().addAll("General Anesthesia", "Regional Anesthesia", "Local Anesthesia");

        Procedure selectedProcedure = SelectedProcedure.getInstance().getProcedure();
        // load procedure info to the form
        if (selectedProcedure != null) {
            loadProcedureDetails(selectedProcedure);
            // set the button to save edit button
            setSaveButton(selectedProcedure);
        } else {
            setCreateButton();
        }
    }

    private void loadProcedureDetails(Procedure selectedP) {
        procedureIDTB.setText(selectedP.getProcedureID());
        LocalDate date = LocalDate.parse(selectedP.getDate());
        datePicker.setValue(date);
        time.setText(selectedP.getTime());
        // check if the selected procedure is instance of MedicineProcedure or
        // SurgeryProcedure
        if (selectedP instanceof MedicineProcedure) {
            Medicine.setSelected(true);
            medicineStack.setVisible(true);

            MedicineProcedure selectedMed = (MedicineProcedure) selectedP;

            Iterator<String> i = selectedMed.getMedicineList().iterator();

            while (i.hasNext()) {
                String med = i.next();
                if (med.equals(alpha.getText())) {
                    alpha.setSelected(true);
                }

                else if (med.equals(biguanides.getText())) {
                    biguanides.setSelected(true);
                }

                else if (med.equals(dipeptidyl.getText())) {
                    dipeptidyl.setSelected(true);
                }

                else if (med.equals(dopamine.getText())) {
                    dopamine.setSelected(true);
                }

                else if (med.equals(glyburide.getText())) {
                    glyburide.setSelected(true);
                }

                else if (med.equals(glynase.getText())) {
                    glynase.setSelected(true);
                }

                else if (med.equals(insulin.getText())) {
                    insulin.setSelected(true);
                }

                else if (med.equals(meglitinides.getText())) {
                    meglitinides.setSelected(true);
                }

                else if (med.equals(metformin.getText())) {
                    metformin.setSelected(true);
                }

                else if (med.equals(pramlintide.getText())) {
                    pramlintide.setSelected(true);
                }
            }
        } else if (selectedP instanceof SurgeryProcedure) {
            Surgery.setSelected(true);
            medicineStack.setVisible(false);

            SurgeryProcedure selectedSur = (SurgeryProcedure) selectedP;

            surgeryType.setValue(selectedSur.getSurgeryType());

            if (selectedSur.getResult().equals("Successful")) {
                successful.setSelected(true);
            } else if (selectedSur.getResult().equals("Unsuccessful")) {
                unsuccessful.setSelected(true);
            }

            anesthesia.setValue(selectedSur.getAnesthesia());
            additionalCommentTB.setText(selectedSur.getAddComment());
        }
    }
}
