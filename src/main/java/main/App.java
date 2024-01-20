package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Class.PHistory;
import Class.PHistoryList;
import Class.Patient;
import Class.PatientList;
import Class.ProcedureList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Login"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws Exception{
        if (!DataManager.getPatientFile().exists()) {
            DataManager.getPatientFile().createNewFile();
        }
        if (!DataManager.getPatientHistoryFile().exists()) {
            DataManager.getPatientHistoryFile().createNewFile();
        }
        if (!DataManager.getProcedureFile().exists()) {
            DataManager.getProcedureFile().createNewFile();
        }

        if (DataManager.patientFileEmpty()) {
            PatientList pL = new PatientList();
            List<Patient> patientList = new ArrayList<>();
            pL.setPatientList(patientList);
            DataManager.JSONSerializePatient(pL);
        }

        if (DataManager.historyFileEmpty()) {
            PHistoryList hL = new PHistoryList();
            List<PHistory> historyList = new ArrayList<>();
            hL.setPatientHistoryList(historyList);
            DataManager.JSONSerializeHistory(hL);
        }

        if (DataManager.procedureFileEmpty()) {
            ProcedureList proL = new ProcedureList(new ArrayList<>());
            DataManager.JSONSerializeProcedure(proL);
        }
        
        launch();
    }

}