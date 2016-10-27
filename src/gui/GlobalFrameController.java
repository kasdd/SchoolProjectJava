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
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Kas De Durpel
 */
public class GlobalFrameController extends AnchorPane implements Initializable {

    private MateriaalFrameController materiaalFrameController;
    private MateriaalBeheerFrameController materiaalBeheerFrameController;
    private ReservatieFrameController reservatieFrameController;
    private BeheerderFrameController beheerderFrameController;
    private SysteemConfigurerenFrameController systeemConfigurerenFrameController;

    @FXML
    private AnchorPane tableAnchorPane;

    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    private ListView<String> globalListView;

    List<String> menu = Arrays.asList("Materialen toevoegen", "Materialen beheren", "Reservaties", "Beheerders"/*, "Wijzigingen"*/);

    private DomeinController domeinController;

    public GlobalFrameController(Beheerder b) throws ParseException {
        domeinController = new DomeinController();
        domeinController.setHuidigeBeheerder(b);
        //    materiaalFrameController = new MateriaalFrameController(domeinController);
        //    materiaalBeheerFrameController = new MateriaalBeheerFrameController(domeinController);
        //    reservatieFrameController = new ReservatieFrameController(domeinController);
        //beheerderFrameController = new BeheerderFrameController(domeinController);
        //systeemConfigurerenFrameController = new SysteemConfigurerenFrameController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GlobalFrame.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(InlogPaneelController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //KLIK OP MATERIAAL TOEVOEGEN
        globalListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + globalListView.getSelectionModel().getSelectedItems());
                switch (globalListView.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        materiaalFrameController = new MateriaalFrameController(domeinController);
                        tableAnchorPane.getChildren().setAll(materiaalFrameController);
                        break;
                    case 1:
                        materiaalBeheerFrameController = new MateriaalBeheerFrameController(domeinController);
                        tableAnchorPane.getChildren().setAll(materiaalBeheerFrameController);
                        break;
                    case 2:
                        reservatieFrameController = new ReservatieFrameController(domeinController);
                        tableAnchorPane.getChildren().setAll(reservatieFrameController);
                        break;
                    case 3:
                        beheerderFrameController = new BeheerderFrameController(domeinController);
                        tableAnchorPane.getChildren().setAll(beheerderFrameController);
                        break;
                    case 4:
                     //   tableAnchorPane.getChildren().setAll(systeemConfigurerenFrameController);
                        break;
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        globalListView.setItems(FXCollections.observableArrayList(menu));
    }
}
