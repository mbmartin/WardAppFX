/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication34;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author mbmartin
 */
public class FXMLDocumentController implements Initializable {


    @FXML
    private Button calculateButton;
    @FXML
    private TextField celsiusField;
    @FXML
    private TextField fahrenheitField;
    @FXML
    private Label celsiusLabel;

    private double celsiusValue;
    private double fahrenheitValue;
    @FXML
    private Label fahrenheitLabel;


    @FXML
    private void handleButtonAction(ActionEvent event) {

        try {
            celsiusValue = Double.parseDouble(celsiusField.getText());
        } catch (NumberFormatException numberFormatException) {
            celsiusValue = 0.0;
        }
        fahrenheitValue = Math.round(celsiusValue * 9 / 5 + 32);
        setFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fahrenheitValue = 0.0;
        this.celsiusValue = 0.0;
        setFields();

    }

    private void setFields() {
        fahrenheitField.setText(String.format(Locale.ENGLISH, "%.2f", fahrenheitValue));
        celsiusField.setText(String.format(Locale.ENGLISH, "%.2f", celsiusValue));
    }

}
