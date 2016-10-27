package gui;

import domein.DomeinController;
import domein.Materiaal;
import domein.Reservatie;
import domein.ReservatieDetail;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 *
 * @author Kas De Durpel
 */
public class ReservatieFrameController extends AnchorPane implements Initializable {

    @FXML
    private TextField filterTextField;
    @FXML
    private ComboBox filterComboBox;
    @FXML
    private Label errorLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Reservatie> reservatieTable;
    @FXML
    private TableColumn<Reservatie, String> naamColumn;
    @FXML
    private TableColumn<Reservatie, String> beginDatumColumn;
    @FXML
    private TableColumn<Reservatie, String> eindDatumColumn;
    @FXML
    private TableColumn<Reservatie, Integer> materiaalColumn;
    @FXML
    private AnchorPane detailAnchorPane;

    private DomeinController dc;
    private ObservableList<Reservatie> filteredData;
    private ObservableList<Reservatie> datumFilterData;
    private ObservableList<Reservatie> reservatieObservableList;

    public ReservatieFrameController(DomeinController domeinController) {
        this.dc = domeinController;
        this.reservatieObservableList = (ObservableList<Reservatie>) dc.geefAlleReservaties();
        filteredData = FXCollections.observableArrayList(reservatieObservableList);
        datumFilterData = FXCollections.observableArrayList(reservatieObservableList);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservatieFrame.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Collections.sort(reservatieObservableList, (Reservatie t, Reservatie t1) -> t.getBegindatum().compareTo(t1.getBegindatum()));

        ObservableList<String> themaItems = FXCollections.observableArrayList("Naam", "Aantal materialen");
        filterComboBox.setItems(themaItems);
        filterComboBox.setValue("Naam");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        naamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGebruiker().toString()));
        materiaalColumn.setCellValueFactory(cellData -> cellData.getValue().getAantalMaterialen().asObject());
        beginDatumColumn.setCellValueFactory((TableColumn.CellDataFeatures<Reservatie, String> reservatie) -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(formatter.format(reservatie.getValue().getBegindatum()));
            return property;
        });
        eindDatumColumn.setCellValueFactory((TableColumn.CellDataFeatures<Reservatie, String> reservatie) -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(formatter.format(reservatie.getValue().getEinddatum()));
            return property;
        });

        reservatieTable.setRowFactory((TableView<Reservatie> tv) -> {
            TableRow<Reservatie> row = new TableRow<>();
            //           MateriaalDetailEventHandler materiaalEventHandler = new MateriaalDetailEventHandler(materiaalTable, dc, row, detailAnchorPane);
            row.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
                if (!row.isEmpty()) {
                    int index = reservatieTable.getSelectionModel().getSelectedIndex();
                    Reservatie reservatie = (Reservatie) reservatieTable.getItems().get(index);

                    detailAnchorPane.getChildren().setAll(new ReservatieDetailController(reservatie, dc));
                }
            });
            return row;
        });

        reservatieObservableList.addListener((ListChangeListener.Change<? extends Reservatie> change) -> {
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

        datePicker.setValue(LocalDate.now());
        List<LocalDate> hoofdDatums = new ArrayList<>();

        reservatieObservableList.stream().map((r) -> r.getBegindatum()).map((date) -> date.toInstant()).map((instant) -> instant.atZone(ZoneId.systemDefault()).toLocalDate()).filter((localDate) -> (!hoofdDatums.contains(localDate))).forEach((LocalDate localDate) -> {
            hoofdDatums.add(localDate);
        });
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            int dagVanWeek = -1;

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (dagVanWeek >= 1 && dagVanWeek <= 5) {
                            dagVanWeek++;
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                        if ((item.getDayOfWeek().getValue() == 1) && !(hoofdDatums.contains(item))) {
                            dagVanWeek = 1;
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        } else if ((item.getDayOfWeek().getValue() == 6) || (item.getDayOfWeek().getValue() == 7)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                    }
                };
            }
        };

        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.setOnAction((ActionEvent event) -> {
            LocalDate localDate = datePicker.getValue();
            Date date = asDate(localDate);
            datumFilterData = geefReservatiesVanDatum(date);
            updateFilteredData();
            //ObservableList<Reservatie> tempReservaties = geefReservatiesVanDatum(date);

        });

        reservatieTable.setItems(/*geefReservatiesVanDatum(date)*/filteredData);

    }

    private void updateFilteredData() {
        filteredData.clear();

        datumFilterData.stream().filter((res) -> (matchesFilter(res))).forEach((res) -> {
            filteredData.add(res);
        });
    }

    private boolean matchesFilter(Reservatie res) {
        String filterThema = (String) filterComboBox.getSelectionModel().getSelectedItem();
        String filterString = filterTextField.getText();

        if (filterString == null || filterString.isEmpty()) {
            return true;
        }

        String tempString = (String) filterComboBox.getSelectionModel().getSelectedItem();
        switch (tempString/*(String) filterComboBox.getSelectionModel().getSelectedItem()*/) {
            case "Naam":
                errorLabel.setVisible(false);
                if (res.getGebruiker().toString().toLowerCase().contains(filterString.toLowerCase())) {
                    return true;
                }
                break;
            case "Aantal materialen":
                try {
                    if (res.getAantalMaterialen().get() == Integer.parseInt(filterString.trim())) {
                        errorLabel.setVisible(false);
                        return true;
                    }
                    break;
                } catch (Exception e) {
                    errorLabel.setText("Aantal moet een getal zonder komma zijn.");
                    errorLabel.setVisible(true);
                }
        }

        return false;
    }

    public ObservableList<Reservatie> geefReservatiesVanDatum(Date datum) {
        return dc.geefReservatiesVanDatum(datum);
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
