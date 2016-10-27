/* TableCellFactory.java
 * ============================================================
 * Copyright (C) 2012-2013 Universiteit Gent
 * 
 * Bijlage bij het vak 'Programmeren 2'.
 * 
 * Auteur: Kris Coolsaet
 */

package gui;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Gemeenschappelijke bovenklasse voor table cell factories.<p>
 * Het is gemakkelijker om deze klasse uit te breiden dan om rechtstreeks de
 * interface {@link Callback} te implementeren met al haar parameters.
 */
public abstract class TableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    @Override
    public final TableCell<S, T> call(TableColumn<S, T> p) {
        return createTableCell(p);
    }
    
    protected abstract TableCell<S, T> createTableCell (TableColumn<S,T> column);
}
