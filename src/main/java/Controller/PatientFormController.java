package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Class.Patient;
import Class.PatientList;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.PathElement;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.App;
import main.DataManager;

public class PatientFormController implements Initializable {

    private PatientList patientList = new PatientList();

    @FXML
    private TextField patientIDTB;
    @FXML
    private TextField nameTB;
    @FXML
    private TextField nationalIDTB;
    @FXML
    private DatePicker dOB;
    @FXML
    private TextField emailTB;
    @FXML
    private TextField addressTB;
    @FXML
    private TextField bloodTypeTB;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private TextField occupationTB;
    @FXML
    private TextField eNameTB;
    @FXML
    private TextField eContactTB;
    @FXML
    private TextField eRelationshipTB;
    @FXML
    private Button formButton;
    @FXML
    private TextField contactTB;
    @FXML
    private RadioButton marriedRadio;
    @FXML
    private ToggleGroup maritalStatus;
    @FXML
    private RadioButton notMarriedRadio;
    @FXML
    private ImageView patientImg;
    @FXML
    private Label imgText;

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
    if(patientIDTB.getText().isEmpty() || nationalIDTB.getText().isEmpty() || nameTB.getText().isEmpty() || dOB.getValue() == null ||
        addressTB.getText().isEmpty() || contactTB.getText().isEmpty() || gender.getSelectedToggle() == null || bloodTypeTB.getText().isEmpty() ||
        maritalStatus.getSelectedToggle() == null || occupationTB.getText().isEmpty() || emailTB.getText().isEmpty() || eNameTB.getText().isEmpty() ||
        eContactTB.getText().isEmpty() || eRelationshipTB.getText().isEmpty() || patientImg.getUserData() == null) {
        return false;
    }

    return true;
}
    @FXML
    private void setCreateButton() {
        // set the button to create function
        
        formButton.setOnAction(e -> {
            try {
                if (!checkFields()) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error: Empty Input");
                    alert.setHeaderText("Error");
                    alert.setContentText("Kindly check your input data, make sure everything is filled up.");
                    alert.showAndWait();
                    return;
                }
                Patient newPatient = new Patient();
                newPatient.setPatientID(patientIDTB.getText());
                newPatient.setNationalID(nationalIDTB.getText());
                newPatient.setName(nameTB.getText());
                LocalDate selectedDate = dOB.getValue();
                newPatient.setAge(2023 - selectedDate.getYear());
                newPatient.setAddress(addressTB.getText());
                newPatient.setContactNum(contactTB.getText());
                RadioButton genderSelected = (RadioButton) gender.getSelectedToggle();
                if (genderSelected == maleRadio) {
                    newPatient.setGender("Male");
                } else if (genderSelected == femaleRadio) {
                    newPatient.setGender("Female");
                }
                newPatient.setBloodType(bloodTypeTB.getText());
                newPatient.setDateOfBirth(selectedDate.toString());
                RadioButton selectedMarital = (RadioButton) maritalStatus.getSelectedToggle();
                if (selectedMarital == marriedRadio) {
                    newPatient.setMaritalStatus("Married");
                } else if (selectedMarital == notMarriedRadio) {
                    newPatient.setMaritalStatus("Not Married");
                }
                newPatient.setOccupation(occupationTB.getText());
                newPatient.setEmail(emailTB.getText());

                newPatient.setEmergencyName(eNameTB.getText());
                newPatient.setEmergencyContact(eContactTB.getText());
                newPatient.setEmergencyRel(eRelationshipTB.getText());
                newPatient.setPictureURL(patientImg.getUserData().toString());
                System.out.println();

                List<Patient> patientRecord = new ArrayList<>();

                if (DataManager.getPatientFile().length() != 0) {
                    patientRecord = DataManager.JSONDeserializePatient().getPatientList();
                }

                patientRecord.add(newPatient);
                patientList.setPatientList(patientRecord);
                DataManager.JSONSerializePatient(patientList);

                toMainPage();
            } catch(Exception exp){
                exp.printStackTrace();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error: Invalid input");
                alert.setHeaderText("Error");
                alert.setContentText("Kindly check your input data, make sure everything is filled up and the data type is correct");
                alert.showAndWait();

            }
            
        });

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        // allow user to select image
        patientImg.setOnMouseClicked(e -> {
            selectImg();
        });

        imgText.setOnMouseClicked(e -> {
            selectImg();
        });

        Patient selectedPatient = SelectedPatient.getInstance().getPatient();
        // load patient info to the form if there is selected patient
        if (selectedPatient != null) {
            loadPatientDetails(selectedPatient);
            // set the button to save edit button
            setSaveButton(selectedPatient);
        } else {
            setCreateButton();
            patientImg.setUserData("/patientImg/anonymous.png");
        }
    }

    private void loadPatientDetails(Patient selectedPatient) {
        this.patientIDTB.setText(selectedPatient.getPatientID());
        patientIDTB.setEditable(false);
        this.nameTB.setText(selectedPatient.getName());
        this.nationalIDTB.setText(selectedPatient.getNationalID());
        this.bloodTypeTB.setText(selectedPatient.getBloodType());
        this.occupationTB.setText(selectedPatient.getOccupation());
        this.emailTB.setText(selectedPatient.getEmail());
        this.addressTB.setText(selectedPatient.getAddress());
        this.eNameTB.setText(selectedPatient.getEmergencyName());
        this.eContactTB.setText(selectedPatient.getEmergencyContact());
        this.eRelationshipTB.setText(selectedPatient.getEmergencyRel());

        if (selectedPatient.getDateOfBirth() != null && !selectedPatient.getDateOfBirth().isEmpty()) {
            LocalDate dateOfBirth = LocalDate.parse(selectedPatient.getDateOfBirth());
            this.dOB.setValue(dateOfBirth);
        }

        if (selectedPatient.getGender().equals("Male")) {
            this.maleRadio.setSelected(true);
        } else {
            this.femaleRadio.setSelected(true);
        }

        if (selectedPatient.getMaritalStatus().equals("Married")) {
            this.marriedRadio.setSelected(true);
        } else {
            this.notMarriedRadio.setSelected(true);
        }
        this.contactTB.setText(selectedPatient.getContactNum());
        this.patientImg
                .setImage(new Image(PatientFormController.class.getResourceAsStream(selectedPatient.getPictureURL())));
        this.patientImg.setUserData(selectedPatient.getPictureURL());
    }

    private void setSaveButton(Patient selectedPatient) {
        this.formButton.setText("Save");
        // set the button to save function
        this.formButton.setOnAction(e -> {

            List<Patient> patientRecord = DataManager.JSONDeserializePatient().getPatientList();
            for (int i = 0; i < patientRecord.size(); i++) {
                if (patientRecord.get(i).getPatientID().equals(selectedPatient.getPatientID())) {
                    try {
                        if (!checkFields()) {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Error: Empty Input");
                            alert.setHeaderText("Error");
                            alert.setContentText("Kindly check your input data, make sure everything is filled up.");
                            alert.showAndWait();
                            return;
                        }
                        patientRecord.get(i).setNationalID(nationalIDTB.getText());
                        patientRecord.get(i).setPatientID(patientIDTB.getText());
                        patientRecord.get(i).setName(nameTB.getText());
                        LocalDate selectedDate = dOB.getValue();
                        patientRecord.get(i).setAge(2023 - selectedDate.getYear());
                        patientRecord.get(i).setAddress(addressTB.getText());
                        patientRecord.get(i).setContactNum(contactTB.getText());
                        RadioButton genderSelected = (RadioButton) gender.getSelectedToggle();
                        if (genderSelected == maleRadio) {
                            patientRecord.get(i).setGender("Male");
                        } else if (genderSelected == femaleRadio) {
                            patientRecord.get(i).setGender("Female");
                        }
                        patientRecord.get(i).setBloodType(bloodTypeTB.getText());
                        patientRecord.get(i).setDateOfBirth(selectedDate.toString());
                        RadioButton selectedMarital = (RadioButton) maritalStatus.getSelectedToggle();
                        if (selectedMarital == marriedRadio) {
                            patientRecord.get(i).setMaritalStatus("Married");
                        } else if (genderSelected == notMarriedRadio) {
                            patientRecord.get(i).setMaritalStatus("Not Married");
                        }
                        patientRecord.get(i).setOccupation(occupationTB.getText());
                        patientRecord.get(i).setEmail(emailTB.getText());

                        patientRecord.get(i).setEmergencyName(eNameTB.getText());
                        patientRecord.get(i).setEmergencyContact(eContactTB.getText());
                        patientRecord.get(i).setEmergencyRel(eRelationshipTB.getText());
                        patientRecord.get(i).setPictureURL(patientImg.getUserData().toString());
                        break;
                    } catch(Exception exp) {
                        exp.printStackTrace();
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Error: Invalid input");
                        alert.setHeaderText("Error");
                        alert.setContentText("Kindly check your input data, make sure everything is filled up and the data type is correct");
                        alert.showAndWait();
                    }
                }
            }
            patientList.setPatientList(patientRecord);
            DataManager.JSONSerializePatient(patientList);
            toMainPage();
        });
    }

    private void selectImg() {
        // use file chooser to let user to choose file to upload
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Select Profile Picture (png, jpg, jpeg)");
        // only accpet these file types
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        // pop up for user to select image
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            String destinationFolder = "src\\main\\resources\\patientImg";

            try {
                // Create a Path for the destination directory
                Path destinationPath = Path.of(destinationFolder);

                // If the destination directory doesn't exist, create it
                if (!Files.exists(destinationPath)) {
                    Files.createDirectories(destinationPath);
                }

                // Create a Path for the destination file
                Path destinationFile = destinationPath.resolve(file.getName());

                // Copy the selected file to the destination folder
                Files.copy(file.toPath(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(destinationFile.toString());

                // initiate a pause/delay to make sure the photo is copied to the folder before
                // updating the imgae view
                PauseTransition pause = new PauseTransition(Duration.seconds(1));

                pause.setOnFinished(pe -> {
                    Image patientPic = null;
                    try {
                        patientPic = new Image(
                                PatientFormController.class.getResourceAsStream("/patientImg/" + file.getName()));
                    } catch (NullPointerException npe) {
                        // Image not found or null
                    }

                    // If the image is null, set it to "anonymous.png"
                    if (patientPic == null) {
                        patientPic = new Image(
                                mainPageController.class.getResourceAsStream("/patientImg/anonymous.png"));
                    }

                    // Set the image to the ImageView
                    patientImg.setImage(patientPic);
                    patientImg.setUserData("/patientImg/" + file.getName());
                });
                // end the pause
                pause.play();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
