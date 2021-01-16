package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.lang.Math;

public class Controller {
    @FXML private TextField num1TF;
    @FXML private TextField num2TF;
    @FXML private Label resultLbl;
    @FXML private Button plusBtn;
    @FXML private Button minusBtn;
    @FXML private Button timesBtn;
    @FXML private Button divideBtn;
    @FXML private Button powerBtn;
    @FXML private Button aboutBtn;

    @FXML
    private void handleOperation(ActionEvent event) {
        try {
            double num1 = Double.parseDouble(num1TF.getText());
            double num2 = Double.parseDouble(num2TF.getText());
            double ans;
            if (event.getSource() == plusBtn) {
                ans = num1 + num2;
            } else if (event.getSource() == minusBtn) {
                ans = num1 - num2;
            } else if (event.getSource() == timesBtn) {
                ans = num1 * num2;
            } else if (event.getSource() == divideBtn) {
                ans = num1 / num2;
            } else {
                ans = Math.pow(num1, num2);
            }
            if (Math.floor(ans) == ans) {
                resultLbl.setText(String.format("%.0f", ans));
            }else{
                resultLbl.setText(removeRandomZeroes(String.format("%f", ans)));
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid input!");
            alert.setContentText("Please input a valid value!");
            alert.showAndWait();
        }
    }

    public String removeRandomZeroes(String str) {
        if (str.contains(".")) {
            while (str.charAt(str.length()-1) == '0') {
                str = str.substring(0, str.length()-1);
            }
            if (str.charAt(str.length()-1) == '.') {
                str = str.substring(0, str.length()-1);
            }
        }
        return str;
    }

    @FXML
    private void handleAbout() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("about.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("About the Programmer");
            stage.getIcons().add(new Image(("file:calculator.jpg")));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(aboutBtn.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
