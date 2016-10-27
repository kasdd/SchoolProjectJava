/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Beheerder;
import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Kas De Durpel
 */
public class BeheerderFrameController extends AnchorPane implements Initializable {

    @FXML
    private TableView<Beheerder> beheerTable;

    @FXML
    private TableColumn<Beheerder, String> typeColumn;

    @FXML
    private TableColumn<Beheerder, String> emailColumn;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private Button btnVoegToe;

    @FXML
    private Label errorLabel;

    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    private Button btnVerwijder;

    private List<Beheerder> beheerders;
    private DomeinController dc;

    public BeheerderFrameController(DomeinController domeinController) {
        this.dc = domeinController;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerderFrame.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        btnVoegToe.setOnAction(this::voegBeheerderToe);
        btnVerwijder.setOnAction(this::verwijderBeheerder);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        beheerders = dc.geefAlleBeheerders();
        ObservableList<Beheerder> beheerderObservableList = FXCollections.observableArrayList(beheerders);
        ObservableList<String> beheerdersEmail = FXCollections.observableArrayList(geefEmailAdressen());
        ObservableList<String> cbValues = FXCollections.observableArrayList("Hoofdbeheerder", "Beheerder");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        comboBox.setItems(cbValues);
        comboBox1.setItems(beheerdersEmail);
        beheerTable.setItems(beheerderObservableList);
    }

    private void voegBeheerderToe(ActionEvent event) {
        if (dc.getHuidigeBeheerder().isIsHoofdbeheerder()) {
            errorLabel.setVisible(false);
            errorLabel.setTextFill(Color.web("#fc0101"));
            if (txtFieldEmail.getText().trim().isEmpty()) {
                errorLabel.setText("Email adres moet worden ingevuld");
                errorLabel.setVisible(true);
            }
            if (comboBox.getValue() == null) {
                errorLabel.setText("Gelieve een type aan te klikken");
                errorLabel.setVisible(true);
            }
            dc.voegBeheerToe(txtFieldEmail.getText().trim(), comboBox.getValue());
            errorLabel.setText(txtFieldEmail.getText().trim() + " is toegevoegd!");
            errorLabel.setVisible(true);
        } else {
            errorLabel.setText("Enkel hoofdbeheerder kan beheerder toevoegen!");
            errorLabel.setVisible(true);
        }
    }

    private void verwijderBeheerder(ActionEvent event) {
        errorLabel.setVisible(false);
        errorLabel.setTextFill(Color.web("#fc0101"));
        if (dc.getHuidigeBeheerder().getEmail().equalsIgnoreCase(comboBox1.getValue())) {
            errorLabel.setText("Gelieve jezelf niet te verwijderen!");
            errorLabel.setVisible(true);
        } else if (dc.getHuidigeBeheerder().isIsHoofdbeheerder()) {
            if (comboBox1.getValue() == null) {
                errorLabel.setText("Gelieve een gebruiker aan te klikken");
                errorLabel.setVisible(true);
            }
            dc.verwijderBeheerder(comboBox1.getValue());
            errorLabel.setText(comboBox1.getValue() + " is verwijderd!");
            errorLabel.setVisible(true);
        } else {
            errorLabel.setText("Enkel hoofdbeheerder kan beheerder verwijderen!");
            errorLabel.setVisible(true);
        }
    }

    private List<String> geefEmailAdressen() {
        ObservableList<String> beheerdersEmail = FXCollections.observableArrayList();
        beheerders.stream().forEach((b) -> {
            beheerdersEmail.add(b.getEmail());
        });
        return beheerdersEmail;
    }
}
