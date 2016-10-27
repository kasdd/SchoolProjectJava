    package gui;

import domein.Doelgroep;
import domein.DomeinController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javax.imageio.ImageIO;
import org.controlsfx.control.CheckComboBox;

/**
 *
 * @author Kas De Durpel
 */
public class MateriaalFrameController extends AnchorPane implements Initializable {

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
    private TextField artikelnummerTextField;
    @FXML
    private TextArea omschrijvingTextArea;
    @FXML
    private DoubleTextField prijsTextField;
    @FXML
    private CheckComboBox leergebiedenCheckComboBox;
    @FXML
    private Button toevoegButton;
    @FXML
    private TextField plaatsTextField;

    private DomeinController dc;

    public MateriaalFrameController(DomeinController domeinController) {

        this.dc = domeinController;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MateriaalFrame.fxml"));
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

        toevoegButton.setOnAction(this::voegMateriaalToe);

//        doelgroepenCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
//            @Override
//            public void onChanged(ListChangeListener.Change<? extends String> c) {
//                System.out.println(doelgroepenCheckComboBox.getCheckModel().getCheckedItems());
//            }
//        });
//        leergebiedenCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
//            @Override
//            public void onChanged(ListChangeListener.Change<? extends String> c) {
//                System.out.println(leergebiedenCheckComboBox.getCheckModel().getCheckedItems());
//            }
//        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        aantalTextField = new IntTextField();
        gridPane.add(aantalTextField, 2, 2);
        doelgroepenCheckComboBox = new CheckComboBox<>((ObservableList<String>) dc.geefObservableListAlleDoelgroepen());
        gridPane.add(doelgroepenCheckComboBox, 2, 3);
        prijsTextField = new DoubleTextField();
        gridPane.add(prijsTextField, 5, 2);
        leergebiedenCheckComboBox = new CheckComboBox<>((ObservableList<String>) dc.geefObservableListAlleLeergebieden());
        gridPane.add(leergebiedenCheckComboBox, 5, 3);
    }

    private void voegMateriaalToe(ActionEvent event) {

        errorLabel.setVisible(false);
        errorLabel.setTextFill(Color.web("#fc0101"));

        if (naamTextField.getText().trim().isEmpty()) {
            errorLabel.setText("Het artikelenaam moet worden ingevuld.");
            errorLabel.setVisible(true);
            return;
        } else if (aantalTextField.getText().trim().isEmpty()) {
            errorLabel.setText("De hoeveelheid exemplaren van uw artikel moet worden ingevuld.");
            errorLabel.setVisible(true);
            return;
        } else if (firmaNaamTextField.getText().trim().isEmpty()) {
            errorLabel.setText("De firmanaam moet worden ingevuld.");
            errorLabel.setVisible(true);
            return;
        } else if (firmaMailTextField.getText().trim().isEmpty()) {
            errorLabel.setText("Het e-mailadres van de firma moet worden ingevuld.");
            errorLabel.setVisible(true);
            return;
        } else if (omschrijvingTextArea.getText().trim().isEmpty()) {
            errorLabel.setText("Gelieve een omschrijving van het artikel te geven.");
            errorLabel.setVisible(true);
            return;
        } else if (prijsTextField.getText().trim().isEmpty()) {
            errorLabel.setText("De prijs van het artikel moet worden ingevuld.");
            errorLabel.setVisible(true);
            return;
        }

        int aantalNumber = (int) controlIntTextField(aantalTextField);
        double prijsNumber = (double) controlDoubleTextField(prijsTextField);
        String foto = "foto.jpg";

        dc.voegMateriaalToe(naamTextField.getText().trim(), omschrijvingTextArea.getText().trim(), prijsNumber, aantalNumber, 0, true, plaatsTextField.getText().trim(), firmaNaamTextField.getText().trim(), firmaMailTextField.getText().trim(), doelgroepenCheckComboBox.getCheckModel().getCheckedItems(), leergebiedenCheckComboBox.getCheckModel().getCheckedItems(), foto);
        
        errorLabel.setTextFill(Color.web("#02f91b"));
        errorLabel.setText("Het artikel is succesvol toegevoegd.");
        errorLabel.setVisible(true);
//foto moet nog worden toegevoegd.
//        dc.voegMateriaalToe(naamTextField.getText().trim(), omschrijvingTextArea.getText().trim(),
//                prijsTextField.get, 0, 0, true, plaats, firma, email, doelgroepen, leergebieden, "foto.jpg");
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

    // and listen to the relevant events (e.g. when the selected indices or 
    // selected items change).
   /* public List<Doelgroep> getDoelgroepenCheckComboBox(ActionEvent event) {
        doelgroepenCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                System.out.println(doelgroepenCheckComboBox.getCheckModel().getCheckedItems());
            }
        });
        return null;
    }*/
}
