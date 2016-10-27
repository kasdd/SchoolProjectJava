/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Materiaal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Robin Van den Broeck
 */
public class MateriaalDetailEventHandler implements  EventHandler<MouseEvent> {

    private TableView materiaalTable;
    private DomeinController dc;
    private TableRow row;
    private AnchorPane detailAnchorPane;
    
    public MateriaalDetailEventHandler(TableView materiaalTable, DomeinController dc, TableRow row, AnchorPane detailAnchorPane){
        this.materiaalTable = materiaalTable;
        this.dc= dc;
        this.row = row;
        this.detailAnchorPane = detailAnchorPane;
    }
    @Override
    public void handle(MouseEvent event) {
        
        if (! row.isEmpty()){
        int index = materiaalTable.getSelectionModel().getSelectedIndex();
        Materiaal materiaal = (Materiaal) materiaalTable.getItems().get(index);
        
        detailAnchorPane = new MateriaalDetailController(materiaal, dc);
        }
    }

}