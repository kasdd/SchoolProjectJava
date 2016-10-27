/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Materiaal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Kas De Durpel
 */
public class MateriaalBeheerFrameController extends AnchorPane implements Initializable {

    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox filterComboBox;
    @FXML
    private TextField filterTextField;
    @FXML
    private TableView<Materiaal> materiaalTable;
    @FXML
    private TableColumn<Materiaal, String> naamColumn;
    @FXML
    private TableColumn<Materiaal, String> omschrijvingColumn;
    @FXML
    private TableColumn<Materiaal, Integer> aantalColumn;
    @FXML
    private TableColumn<Materiaal, String> doelgroepColumn;
    @FXML
    private TableColumn<Materiaal, String> leergebiedColumn;
    @FXML
    private TableColumn<Materiaal, String> plaatsColumn;
    @FXML
    private TableColumn<Materiaal, String> firmaColumn;
    @FXML
    private TableColumn<Materiaal, Double> prijsColumn;

    @FXML
    private AnchorPane detailAnchorPane;

    private ObservableList<Materiaal> filteredData;
    private ObservableList<Materiaal> materiaalObservableList;
    private DomeinController dc;

    public MateriaalBeheerFrameController(DomeinController domeinController) {

        this.dc = domeinController;

        //   this.materiaalObservableList = FXCollections.observableArrayList();
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MateriaalBeheerFrame.fxml"));
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

        this.materiaalObservableList = (ObservableList<Materiaal>) dc.geefAlleMaterialen();
        filteredData = FXCollections.observableArrayList(materiaalObservableList);
        ObservableList<String> themaItems = FXCollections.observableArrayList("Naam", "Omschrijving",
                "Aantal", "Doelgroepen", "Leergebieden", "Plaats", "Firma", "Prijs");
        filterComboBox.setItems(themaItems);
        filterComboBox.setValue("Naam");

        naamColumn.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());
        omschrijvingColumn.setCellValueFactory(cellData -> cellData.getValue().getOmschrijvingProperty());
        aantalColumn.setCellValueFactory(cellData -> cellData.getValue().getAantalProperty().asObject());
        doelgroepColumn.setCellValueFactory(cellData -> cellData.getValue().getDoelgroepenProperty().asString());
        leergebiedColumn.setCellValueFactory(cellData -> cellData.getValue().getLeergebiedenProperty().asString());
        plaatsColumn.setCellValueFactory(cellData -> cellData.getValue().getPlaatsProperty());
        firmaColumn.setCellValueFactory(cellData -> cellData.getValue().getFirmaProperty());
        prijsColumn.setCellValueFactory(cellData -> cellData.getValue().getPrijsProperty().asObject());

        materiaalTable.setRowFactory((TableView<Materiaal> tv) -> {
            TableRow<Materiaal> row = new TableRow<>();
            //           MateriaalDetailEventHandler materiaalEventHandler = new MateriaalDetailEventHandler(materiaalTable, dc, row, detailAnchorPane);
            row.setOnMouseClicked((MouseEvent event) -> {
                if (!row.isEmpty()) {
                    int index = materiaalTable.getSelectionModel().getSelectedIndex();
                    Materiaal materiaal = (Materiaal) materiaalTable.getItems().get(index);
                    
                    detailAnchorPane.getChildren().setAll(new MateriaalDetailController(materiaal, dc));
                }
            });
            return row;
        });

        //      materiaalTable.setOnMouseClicked(materiaalEventHandler);
        materiaalObservableList.addListener((ListChangeListener.Change<? extends Materiaal> change) -> {
            updateFilteredData();
        });

        filterTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            updateFilteredData();
        });
        
        filterComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                updateFilteredData();
            }
        });

        materiaalTable.setItems(filteredData);
    }

    private void updateFilteredData() {
        filteredData.clear();

        materiaalObservableList.stream().filter((mat) -> (matchesFilter(mat))).forEach((mat) -> {
            filteredData.add(mat);
        });
    }

    private boolean matchesFilter(Materiaal mat) {
        String filterThema = (String) filterComboBox.getSelectionModel().getSelectedItem();
        String filterString = filterTextField.getText();

        if (filterString == null || filterString.isEmpty()) {
            return true;
        }

        switch ((String) filterComboBox.getSelectionModel().getSelectedItem()) {
            case "Naam":
                errorLabel.setVisible(false);
                if (mat.getNaam().toLowerCase().contains(filterString.toLowerCase().trim())) {
                    return true;
                }
                break;
            case "Omschrijving":
                errorLabel.setVisible(false);
                if (mat.getOmschrijving().toLowerCase().contains(filterString.toLowerCase().trim())) {
                    return true;
                }
                break;
            case "Aantal":
                try {
                    if (mat.getAantal() == Integer.parseInt(filterString.trim())) {
                        errorLabel.setVisible(false);
                        return true;
                    }
                    break;
                } catch (Exception e) {
                    errorLabel.setText("Aantal moet een getal zonder komma zijn.");
                    errorLabel.setVisible(true);
                }
            case "Doelgroepen":
                errorLabel.setVisible(false);
                if (mat.getDoelgroepen().toString().toLowerCase().contains(filterString.toLowerCase().trim())) {
                    return true;
                }
                break;
            case "Leergebieden":
                errorLabel.setVisible(false);
                if (mat.getLeergebieden().toString().toLowerCase().contains(filterString.toLowerCase().trim())) {
                    return true;
                }
                break;
            case "Plaats":
                errorLabel.setVisible(false);
                if (mat.getPlaats().toLowerCase().contains(filterString.toLowerCase().trim())) {
                    return true;
                }
                break;
            case "Firma":
                errorLabel.setVisible(false);
                if (mat.getFirma().toLowerCase().contains(filterString.toLowerCase().trim())) {
                    return true;
                }
                break;
            case "Prijs":
                try {
                    if (mat.getPrijs() == Double.parseDouble(filterString.trim())) {
                        errorLabel.setVisible(false);
                        return true;
                    }
                    break;
                } catch (Exception e) {
                    errorLabel.setText("Prijs moet een getal zijn, al dan niet met komma.");
                    errorLabel.setVisible(true);
                }
        }

        return false;
    }
}
