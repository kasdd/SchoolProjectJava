/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Materiaal;
import domein.Reservatie;
import domein.ReservatieDetail;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author Robin Van den Broeck
 */
public class ReservatieDetailController extends AnchorPane implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label errorLabel;
    @FXML
    private Label naamLabel;
    @FXML
    private DatePicker beginDatumDatePicker;
    @FXML
    private Button reservatieVerwijderButton;
    @FXML
    private Button datumButton;
    @FXML
    private ListView<ReservatieDetail> reservatieDetailListView;
    @FXML
    private IntTextField aantalUitTextField;
    @FXML
    private IntTextField aantalInTextField;
    @FXML
    private Button wijzigButton;
    @FXML
    private Button verwijderButton;

    private DomeinController dc;
    private Reservatie reservatie;
    private ReservatieDetail resDetail;

    ReservatieDetailController(Reservatie reservatie, DomeinController dc) {
        this.reservatie = reservatie;

        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservatieDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        reservatieDetailListView.setItems((ObservableList<ReservatieDetail>) reservatie.getReservatieDetails());
        beginDatumDatePicker.setValue(asLocalDate(reservatie.getBegindatum()));
        
        naamLabel.setText(reservatie.getGebruiker().toString());
        aantalUitTextField = new IntTextField();
        aantalInTextField = new IntTextField();
        gridPane.add(aantalUitTextField, 5, 3);
        gridPane.add(aantalInTextField, 5, 4);
        

        reservatieDetailListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<ReservatieDetail>() {

                    @Override
                    public void changed(ObservableValue<? extends ReservatieDetail> observable, ReservatieDetail oldValue, ReservatieDetail newValue) {
                        if(newValue!=null){
                        resDetail = newValue;
                        aantalUitTextField.setText(String.valueOf(newValue.getAantalUitgeleend()));
                        aantalInTextField.setText(String.valueOf(newValue.getAantalTerug()));
                        }else{
                            aantalUitTextField.setText("");
                        aantalInTextField.setText("");
                        }
                    }
                });

        reservatieVerwijderButton.setOnAction(this::verwijderReservatie);
        datumButton.setOnAction(this::wijzigDatum);
        wijzigButton.setOnAction(this::wijzigMateriaal);
        verwijderButton.setOnAction(this::verwijderMateriaal);
    }
    
    private void verwijderReservatie(ActionEvent event){
        dc.verwijderReservatie(reservatie);
        this.setVisible(false);
    }
    
    private void wijzigDatum(ActionEvent event) {
        Date date = asDate(beginDatumDatePicker.getValue());
        dc.wijzigDatum(reservatie, date);
        errorLabel.setTextFill(Color.web("#02f91b"));
        errorLabel.setText("De datum is aangepast.");
        errorLabel.setVisible(true);
        

    }

    private void wijzigMateriaal(ActionEvent event) {
        errorLabel.setVisible(false);

        if (resDetail == null) {
            errorLabel.setText("Je moet een materiaal aanduiden.");
            errorLabel.setTextFill(Color.web("#fc0101"));
            errorLabel.setVisible(true);
        }

        if (aantalUitTextField.getText().trim().isEmpty()) {

            errorLabel.setText("Het aantal stuks uitgeleend moet worden ingevuld.");
            errorLabel.setTextFill(Color.web("#fc0101"));
            errorLabel.setVisible(true);
            return;
        } else if (aantalInTextField.getText().trim().isEmpty()) {

            errorLabel.setText("Het aantal stuks teruggebracht moet worden ingevuld.");
            errorLabel.setTextFill(Color.web("#fc0101"));
            errorLabel.setVisible(true);
            return;

        }
        int aantalUitNumber = (int) controlIntTextField(aantalUitTextField);
        int aantalInNumber = (int) controlIntTextField(aantalInTextField);

        dc.wijzigReservatieDetail(resDetail, aantalUitNumber, aantalInNumber);
        //foto moet nog worden toegevoegd.
//        dc.voegMateriaalToe(naamTextField.getText().trim(), omschrijvingTextArea.getText().trim(),
//                prijsTextField.get, 0, 0, true, plaats, firma, email, doelgroepen, leergebieden, "foto.jpg");
    }

    private void verwijderMateriaal(ActionEvent event) {
        
        errorLabel.setVisible(false);
        
        if (resDetail == null) {
            errorLabel.setText("Je moet een materiaal aanduiden.");
            errorLabel.setTextFill(Color.web("#fc0101"));
            errorLabel.setVisible(true);
        }

        errorLabel.setVisible(false);

        dc.verwijderReservatieDetail(reservatie, resDetail);
        errorLabel.setTextFill(Color.web("#02f91b"));
        errorLabel.setText("Het materiaal is volledig teruggebracht.");
        errorLabel.setVisible(true);
    }

    protected Number controlIntTextField(IntTextField ntf) {

        try {
            return ntf.getIntText();
        } catch (Exception e) {
            errorLabel.setText("In het de velden \"aantal stuks uitgeleend\" en \"aantal stuks teruggebracht\" mogen enkel nummers worden ingevuld.");
            errorLabel.setVisible(true);
        }
        return 0;
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
