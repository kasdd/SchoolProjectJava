/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Beheerder;
import domein.InlogController;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author Robin Van den Broeck
 */
public class InlogPaneelController extends AnchorPane {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane hoofdPane;
    
    @FXML
    private TextField gebruikerTextField;
    @FXML
    private PasswordField wachtwoordPasswordField;
    @FXML
    private Button inlogButton;
    @FXML
    private Label errorLabel;

    private InlogController inlogController;

    public InlogPaneelController(InlogController ic) throws IOException {
        this.inlogController = ic;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InlogPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(InlogPaneelController.class.getName()).log(Level.SEVERE, null, ex);
        }

        inlogButton.setOnAction(this::aanmelden);

    }

    private void aanmelden(ActionEvent event) {
        
        errorLabel.setVisible(false);
        if (gebruikerTextField.getText().trim().isEmpty()) {
            errorLabel.setText("Uw gebruikersnaam ingeven.");
            errorLabel.setVisible(true);
            return;
        } else if (wachtwoordPasswordField.getText().trim().isEmpty()) {
            errorLabel.setText("Uw wachtwoord ingeven.");
            errorLabel.setVisible(true);
            return;
        } 
        try {
            Beheerder b = inlogController.meldAan(gebruikerTextField.getText().trim(), wachtwoordPasswordField.getText().trim());
            hoofdPane.getChildren().setAll(new GlobalFrameController(b));
        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException(e);
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(InlogPaneelController.class.getName()).log(Level.SEVERE, null, ex);
        } catch(JSONException e){
            errorLabel.setText("Gelieve een correct wachtwoord in te geven");
            errorLabel.setVisible(true);
        }     
    }
}
