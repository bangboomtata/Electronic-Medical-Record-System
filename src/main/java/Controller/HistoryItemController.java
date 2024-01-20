package Controller;

import Class.PHistory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

//a controller class used to set information for history ListView cells
public class HistoryItemController {
    @FXML
    private Label historyIDLabel;

    @FXML
    private Label dateLabel;

    public void setInfo(PHistory pH) {
        historyIDLabel.setText(pH.getHistoryID());
        dateLabel.setText(pH.getAdmissionDate());
    }
}
