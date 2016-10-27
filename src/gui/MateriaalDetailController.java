/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Doelgroep;
import domein.DomeinController;
import domein.Materiaal;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author Robin Van den Broeck
 */
public class MateriaalDetailController extends AnchorPane implements Initializable {

    private Materiaal materiaal;

    @FXML
    private GridPane gridPane;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField naamTextField;
    @FXML
    private IntTextField aantalTextField;
    @FXML
    private CheckComboBox doelgroepenCheckComboBox;
    @FXML
    private Button afbeeldingButton;
    @FXML
    private TextField firmaNaamTextField;
    @FXML
    private TextField firmaMailTextField;
    @FXML
    private TextField firmanummerTextField;
    @FXML
    private TextArea omschrijvingTextArea;
    @FXML
    private DoubleTextField prijsTextField;
    @FXML
    private CheckComboBox leergebiedenCheckComboBox;
    @FXML
    private TextField plaatsTextField;
    @FXML
    private Button toevoegButton;
    @FXML
    private Button verwijderButton;

    private DomeinController dc;

    public MateriaalDetailController(Materiaal materiaal, DomeinController domeinController) {
        this.materiaal = materiaal;

        this.dc = domeinController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MateriaalDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        ImageView myImageView = new ImageView();

        EventHandler<ActionEvent> btnLoadEventListener;
        btnLoadEventListener = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();

                //Set extension filter
                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

                //Show open file dialog
                File file = fileChooser.showOpenDialog(null);

                try {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    myImageView.setImage(image);
                } catch (IOException ex) {
                    Logger.getLogger(MateriaalFrameController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };

        afbeeldingButton.setOnAction(btnLoadEventListener);

        toevoegButton.setOnAction(this::wijzigMateriaal);
        
        verwijderButton.setOnAction(this::verwijderMateriaal);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        naamTextField.setText(materiaal.getNaam());
        aantalTextField = new IntTextField();
        gridPane.add(aantalTextField, 2, 2);
        aantalTextField.setText(String.valueOf(materiaal.getAantal()));
        doelgroepenCheckComboBox = new CheckComboBox<>((ObservableList<String>) dc.geefObservableListAlleDoelgroepen());
        materiaal.getDoelgroepen().stream().forEach((doelgroep) -> {
            doelgroepenCheckComboBox.getCheckModel().check(doelgroep.getNaam());
        });
        gridPane.add(doelgroepenCheckComboBox, 2, 3);
        firmaNaamTextField.setText(materiaal.getFirma());
        firmaMailTextField.setText(materiaal.getEmail());
        //firmanummerTextField.setText(materiaal.get);
        omschrijvingTextArea.setText(materiaal.getOmschrijving());
        prijsTextField = new DoubleTextField();
        gridPane.add(prijsTextField, 5, 2);
        prijsTextField.setText(String.valueOf(materiaal.getPrijs()));
        leergebiedenCheckComboBox = new CheckComboBox<>((ObservableList<String>) dc.geefObservableListAlleLeergebieden());
        gridPane.add(leergebiedenCheckComboBox, 5, 3);
        materiaal.getLeergebieden().stream().forEach((leergebied) -> {
            leergebiedenCheckComboBox.getCheckModel().check(leergebied.getNaam());
        });
        plaatsTextField.setText(materiaal.getPlaats());
    }

    private void wijzigMateriaal(ActionEvent event) {
        errorLabel.setVisible(false);

        if (naamTextField.getText().trim().isEmpty()) {
            
            errorLabel.setText("Het artikelenaam moet worden ingevuld.");
            errorLabel.setTextFill(Color.web("#fc0101"));
            errorLabel.setVisible(true);
            return;
        } else if (aantalTextField.getText().trim().isEmpty()) {
            
            errorLabel.setText("De hoeveelheid exemplaren van uw artikel moet worden ingevuld.");
            errorLabel.setTextFill(Color.web("#fc0101"));
            errorLabel.setVisible(true);
            return;
            
        }
        int aantalNumber = (int) controlIntTextField(aantalTextField);
        double prijsNumber = (double) controlDoubleTextField(prijsTextField);
        String foto = "foto.jpg";

        dc.wijzigMateriaal(materiaal, naamTextField.getText().trim(), omschrijvingTextArea.getText().trim(), prijsNumber, aantalNumber, 0, true, plaatsTextField.getText().trim(), firmaNaamTextField.getText().trim(), firmaMailTextField.getText().trim(), doelgroepenCheckComboBox.getCheckModel().getCheckedItems(), leergebiedenCheckComboBox.getCheckModel().getCheckedItems(), foto);;
        //foto moet nog worden toegevoegd.
//        dc.voegMateriaalToe(naamTextField.getText().trim(), omschrijvingTextArea.getText().trim(),
//                prijsTextField.get, 0, 0, true, plaats, firma, email, doelgroepen, leergebieden, "foto.jpg");
    }
    
    private void verwijderMateriaal(ActionEvent event) {
        errorLabel.setVisible(false);
        
        dc.verwijderMateriaal(materiaal);
        errorLabel.setTextFill(Color.web("#02f91b"));
        errorLabel.setText("Het materiaal is verwijderd.");
        errorLabel.setVisible(true);
    }

    protected Number controlIntTextField(IntTextField ntf) {

        try {
            return ntf.getIntText();
        } catch (Exception e) {
            errorLabel.setText("In het de velden \"aantal\" en \"prijs\" mogen enkel nummers worden ingevuld.");
            errorLabel.setVisible(true);
        }
        return 0;
    }

    protected Number controlDoubleTextField(DoubleTextField ntf) {

        try {
            return ntf.getDoubleText();
        } catch (Exception e) {
            errorLabel.setText("In het de velden \"aantal\" en \"prijs\" mogen enkel nummers worden ingevuld.");
            errorLabel.setVisible(true);
        }
        return 0;
    }
}
