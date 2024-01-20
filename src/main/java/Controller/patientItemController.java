package Controller;

import Class.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class patientItemController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label diseaseLabel;

    @FXML
    private ImageView patientImg;

    public void setInfo(Patient p) {
        nameLabel.setText(p.getName());
        idLabel.setText(p.getNationalID());
        diseaseLabel.setText(p.getGender());

        Image img = null;
        try {
            img = new Image(patientItemController.class.getResourceAsStream(p.getPictureURL()));
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (img == null) {
            img = new Image(patientItemController.class.getResourceAsStream("/patientImg/anonymous.png"));
        }
        patientImg.setImage(img);
    }

}
