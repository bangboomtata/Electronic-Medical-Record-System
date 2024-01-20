package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.App;

public class loginPageController {
    @FXML
    private TextField usernameTB;

    @FXML
    private PasswordField passwordTB;

    private String adminUname = "admin123";
    private String adminPass = "admin123";

    @FXML
    public void login() {
        String unameEntered = usernameTB.getText();
        String passEntered = passwordTB.getText();

        if (unameEntered.equals(adminUname) && passEntered.equals(adminPass)) {
            toMainPage();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: Invalid Login Credentials");
            alert.setHeaderText("Error");
            alert.setContentText("Wrong username or password");
            alert.showAndWait();
        }
    }

    private void toMainPage() {
        try {
            App.setRoot("mainPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
