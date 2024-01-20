package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import Class.Analysis;
import Class.PHistory;
import Class.PHistoryList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import main.App;
import main.DataManager;

public class AnalysisController implements Initializable {

    private PHistoryList patientHistoryList = new PHistoryList();

    @FXML
    private DatePicker analysisDatePicker;

    @FXML
    private TextField hResult;

    @FXML
    private TextField fResult;

    @FXML
    private TextField oResult;

    @FXML
    private TextField rResult;

    @FXML
    private TextField hAnalysis;

    @FXML
    private TextField fAnalysis;

    @FXML
    private TextField oAnalysis;

    @FXML
    private TextField rAnalysis;

    @FXML
    private TextField geneticResult;

    @FXML
    private TextField antibodyResult;

    @FXML
    private TextField treatmentID;

    @FXML
    private Button saveButton;

    private Analysis newAnalysis;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // set the treatmentcourseID to let user know which treatmentcourse are they
        // using
        getID();

        // if the history selected already has analysis, set data to form
        if (SelectedHistory.getInstance().getHistory().getTc().getAnalysis() != null) {
            Analysis an = SelectedHistory.getInstance().getHistory().getTc().getAnalysis();
            analysisDatePicker.setValue(LocalDate.parse(an.getAnalysisDate()));
            hResult.setText(an.gethResult());
            fResult.setText(an.getfResult());
            oResult.setText(an.getoResult());
            rResult.setText(an.getrResult());
            hAnalysis.setText(an.getHAnalysis());
            fAnalysis.setText(an.getFAnalysis());
            oAnalysis.setText(an.getOAnalysis());
            rAnalysis.setText(an.getRAnalysis());
            geneticResult.setText(an.getGeneticResult());
            antibodyResult.setText(an.getAntibodyResult());

            // change color and text based on inserted value
            calculate();

        }
        saveButton.setOnAction(event -> {
            
            
            if (isEmpty()){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error: Please fill in all the boxes");
                alert.setHeaderText("Error");
                alert.setContentText("Please fill in all the boxes");
                alert.showAndWait();
                
            } 
                  
            else{
                createAnalysis();
            }
     
            
        });

        hResult.setOnKeyPressed(e -> {
            calculateH();
        });

        oResult.setOnKeyPressed(e -> {
            calculateO();
        });

        fResult.setOnKeyPressed(e -> {
            calculateF();
        });

        rResult.setOnKeyPressed(e -> {
            calculateR();
        });

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

    public void calculate(){
        calculateH();
        calculateF();
        calculateO();
        calculateR();
    }

    public  void createAnalysis() {

        try{
            TextField[] textFields = {hResult, fResult, oResult, rResult, geneticResult, antibodyResult};
            for (TextField textField : textFields) {
                Double.parseDouble(textField.getText());
            }

            String date = analysisDatePicker.getValue().toString();

            // create new object
            newAnalysis = new Analysis(date, hResult.getText(), fResult.getText(), oResult.getText(),
                    rResult.getText(), hAnalysis.getText(), fAnalysis.getText(), oAnalysis.getText(), rAnalysis.getText(),
                    geneticResult.getText(), antibodyResult.getText());

            // get data from json file
            List<PHistory> historyList = DataManager.JSONDeserializeHistory().getPatientHistoryList();

            // find the selected history based on historyID and add the analysis
            for (PHistory pH : historyList) {
                if (pH.getHistoryID().equals(SelectedHistory.getInstance().getHistory().getHistoryID())) {
                    pH.getTc().setAnalysis(newAnalysis);
                }
            }

            patientHistoryList.setPatientHistoryList(historyList);
            // store the data
            DataManager.JSONSerializeHistory(patientHistoryList);
            toMainPage();
        
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: Input is invalid");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in the correct data");
            alert.showAndWait();
        }

        

    }

    private void getID() {
        List<PHistory> historyList = DataManager.JSONDeserializeHistory().getPatientHistoryList();

        for (PHistory pH : historyList) {
            if (pH.getHistoryID().equals(SelectedHistory.getInstance().getHistory().getHistoryID())) {
                treatmentID.setText(pH.getHistoryID());
            }
        }
    }

    public void editAnalysis() {

        List<PHistory> historyList = DataManager.JSONDeserializeHistory().getPatientHistoryList();

        for (PHistory pH : historyList) {
            if (pH.getHistoryID().equals(SelectedHistory.getInstance().getHistory().getHistoryID())) {
                pH.getTc().getAnalysis().setAnalysisDate(analysisDatePicker.getValue().toString());
                pH.getTc().getAnalysis().setHResult(hResult.getText());
                pH.getTc().getAnalysis().setFResult(fResult.getText());
                pH.getTc().getAnalysis().setOResult(oResult.getText());
                pH.getTc().getAnalysis().setRResult(rResult.getText());
                pH.getTc().getAnalysis().setHAnalysis(hAnalysis.getText());
                pH.getTc().getAnalysis().setFAnalysis(fAnalysis.getText());
                pH.getTc().getAnalysis().setOAnalysis(oAnalysis.getText());
                pH.getTc().getAnalysis().setRAnalysis(rAnalysis.getText());
                pH.getTc().getAnalysis().setGeneticResult(geneticResult.getText());
                pH.getTc().getAnalysis().setAntibodyResult(antibodyResult.getText());
            }
        }

        patientHistoryList.setPatientHistoryList(historyList);
        DataManager.JSONSerializeHistory(patientHistoryList);
        toMainPage();

    }

    public void calculateH() {
        double hresult;
        try {
            hresult = Double.parseDouble(hResult.getText());
        } catch (NumberFormatException e) {
            hAnalysis.setText("Invalid");
            hAnalysis.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            return;
        }

        if (hresult < 5.7 && hresult >= 0) {
            hAnalysis.setText("Normal");
            hAnalysis.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        } else if (hresult >= 5.7 && hresult <= 6.4) {
            hAnalysis.setText("Prediabetes");
            hAnalysis.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        } else if (hresult > 6.4) {
            hAnalysis.setText("Diabetes");
            hAnalysis.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        } else {
            hAnalysis.setText("Invalid");
        }
    }

    public void calculateO(){

        double oresult;
        try {
            oresult = Double.parseDouble(oResult.getText());
        } catch (NumberFormatException e) {
            oAnalysis.setText("Invalid");
            oAnalysis.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            return;
        }

        if (oresult < 139 && oresult >= 0) {
            oAnalysis.setText("Normal");
            oAnalysis.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        } else if (oresult >= 139 && oresult <= 199) {
            oAnalysis.setText("Prediabetes");
            oAnalysis.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        } else if (oresult > 199) {
            oAnalysis.setText("Diabetes");
            oAnalysis.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        } else {
            oAnalysis.setText("Invalid");
        }
    }

    public void calculateF(){

        double fresult;
        try {
            fresult = Double.parseDouble(fResult.getText());
        } catch (NumberFormatException e) {
            fAnalysis.setText("Invalid");
            fAnalysis.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            return;
        }

        if (fresult < 99 && fresult >= 0) {
            fAnalysis.setText("Normal");
            fAnalysis.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        } else if (fresult >= 99 && fresult <= 125) {
            fAnalysis.setText("Prediabetes");
            fAnalysis.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        } else if (fresult > 125) {
            fAnalysis.setText("Diabetes");
            fAnalysis.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        } else {
            fAnalysis.setText("Invalid");
        }
    }
    
    public void calculateR(){
        double rresult;
        try {
            rresult = Double.parseDouble(rResult.getText());
        } catch (NumberFormatException e) {
            rAnalysis.setText("Invalid");
            rAnalysis.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            return;
        }

        if (rresult < 200 && rresult >= 0) {
            rAnalysis.setText("Normal");
            rAnalysis.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        } else if (rresult >= 200) {
            rAnalysis.setText("Diabetes");
            rAnalysis.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        } else {
            rAnalysis.setText("Invalid");
        }
    }

    private boolean isEmpty(){
        TextField[] textFields = {hResult, fResult, oResult, rResult, geneticResult, antibodyResult};
        LocalDate selectedDate = analysisDatePicker.getValue(); 
        for (TextField textField : textFields) {
        if (textField.getText().isEmpty() || selectedDate == null) {
            return true; // Found an empty text field
            }
        }

        return false;
    }

   
 
}