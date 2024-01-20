package Controller;

import Class.MedicineProcedure;
import Class.Procedure;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class procedureItemController {
    @FXML
    private ImageView procedureImg;

    @FXML
    private Label procedureIDLabel;

    @FXML
    private Label procedureTypeLabel;

    @FXML
    private Label procedureDateLabel;

    public void setInfo(Procedure p) {

        procedureIDLabel.setText(p.getProcedureID());
        procedureDateLabel.setText(p.getDate());
        procedureTypeLabel.setText(p.getType());
        Image img = null;
        if (p instanceof MedicineProcedure) {
            img = new Image(patientItemController.class.getResourceAsStream("/systemImg/medicine.png"));
            procedureImg.setImage(img);
        } else {
            img = new Image(patientItemController.class.getResourceAsStream("/systemImg/surgery.png"));
            procedureImg.setImage(img);
        }
    }
}
