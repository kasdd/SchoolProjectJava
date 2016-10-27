/* DoubleClickableTableCellFactory.java
 * ============================================================
 * Copyright (C) 2012-2013 Universiteit Gent
 * 
 * Bijlage bij het vak 'Programmeren 2'.
 * 
 * Auteur: Kris Coolsaet
 */
package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

/**
 * Table cell factory die cellen aanmaakt waarop je kan dubbelklikken. Je kan
 * een actiegebeurtenisverwerker registeren die op dit dubbelklikken reageert.
 */
public class DoubleClickableTableCellFactory<S, T> extends TableCellFactory<S, T> {

    private EventHandler<ActionEvent> actionEventHandler;

    public void setOnAction(EventHandler<ActionEvent> actionEventHandler) {
        this.actionEventHandler = actionEventHandler;
    }

    private class DoubleClickableTableCell<S, T> extends TextFieldTableCell<S, T>
            implements EventHandler<MouseEvent> {

        public DoubleClickableTableCell() {
            setOnMouseClicked(this);
        }

        @Override
        public void handle(MouseEvent t) {
            if (t.getClickCount() > 1) {
                ActionEvent ae = new ActionEvent(this, null);
                actionEventHandler.handle(ae);
            }
        }
    }

    @Override
    public TableCell<S, T> createTableCell(TableColumn<S, T> column) {
        return new DoubleClickableTableCell<>();
    }
}
