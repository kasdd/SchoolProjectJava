/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Doelgroep;
import domein.Gebruiker;
import domein.Leergebied;
import domein.Materiaal;
import domein.Reservatie;
import domein.ReservatieDetail;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;

/**
 *
 * @author Kas De Durpelreservaties = FXCollections.observableArrayList();
 */
public class ReservatieRepository {

    private ObservableList<Reservatie> reservaties;
    private ObservableList<Materiaal> materialen;
    private ObservableList<Materiaal> materialen1;
    private ObservableList<ReservatieDetail> details1;
    private ObservableList<ReservatieDetail> details2;

    public ReservatieRepository() throws ParseException {
        reservaties = FXCollections.observableArrayList();

        materialen = FXCollections.observableArrayList();
        materialen1 = FXCollections.observableArrayList();
        details1 = FXCollections.observableArrayList();
        details2 = FXCollections.observableArrayList();
        reservatiesAanmaken();
    }

    public ObservableList<Reservatie> geefAlleReservaties() {
        return reservaties;
    }

    public void reservatiesAanmaken() {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = "09/05/2016";
            String dateInString1 = "13/05/2016";
            String dateInString2 = "16/05/2016";
            String dateInString3 = "20/09/2016";

            Date date1 = formatter.parse(dateInString);
            Date date2 = formatter.parse(dateInString1);
            Date date3 = formatter.parse(dateInString2);
            Date date4 = formatter.parse(dateInString3);

            Doelgroep doelgroep = new Doelgroep("Kleuteronderwijs");
            Leergebied leergebied = new Leergebied("Biologie");
            List<Doelgroep> doelgroepen = FXCollections.observableArrayList();
            List<Leergebied> leergebieden = FXCollections.observableArrayList();
            doelgroepen.add(doelgroep);
            leergebieden.add(leergebied);
            Materiaal dobbelsteen = new Materiaal("Dobbelsteen", "Dobbelstenen uit de hema", 1.95, 20, 0, true, "B1.0325", "FirmaNaam", "firma@dobbelstenen.be", doelgroepen, leergebieden, "foto1.jpg");
            Materiaal microscoop = new Materiaal("microscoop", "", 0, 15, 0, true, "", "", "", doelgroepen, leergebieden, "");
            Materiaal spelbord = new Materiaal("monoply", "", 0, 20, 0, true, "", "", "", doelgroepen, leergebieden, "");
            Materiaal spelbord1 = new Materiaal("schaken", "", 0, 10, 0, true, "", "", "", doelgroepen, leergebieden, "");
            Materiaal spelbord2 = new Materiaal("ganzenbordé", "", 0, 50, 0, true, "", "", "", doelgroepen, leergebieden, "");

            ReservatieDetail rd = new ReservatieDetail(dobbelsteen, 3, 0);
            ReservatieDetail rd1 = new ReservatieDetail(microscoop, 2, 0);
            ReservatieDetail rd2 = new ReservatieDetail(spelbord, 2, 0);
            ReservatieDetail rd3 = new ReservatieDetail(spelbord1, 2, 0);
            ReservatieDetail rd4 = new ReservatieDetail(spelbord2, 2, 0);

            details1.add(rd);
            details1.add(rd1);
            details2.add(rd2);
            details2.add(rd3);
            details1.add(rd4);

            materialen.add(dobbelsteen);
            materialen.add(microscoop);
            materialen.add(spelbord);
            materialen.add(spelbord);
            Gebruiker gebruiker1 = new Gebruiker("Kas", "De Durpel", "kas_dedurpel@hotmail.com");
            Gebruiker gebruiker2 = new Gebruiker("Robin", "Van den Broeck", "robinvandenbroeck@hotmail.com");
            Reservatie reservatie1 = new Reservatie(date1, date2, gebruiker1, details1, 10);
            Reservatie reservatie2 = new Reservatie(date3, date4, gebruiker1, details2, 2);
            Reservatie reservatie3 = new Reservatie(date1, date2, gebruiker2, details2, 3);
            Reservatie reservatie4 = new Reservatie(date3, date4, gebruiker2, details1, 5);
            reservaties.add(reservatie1);
            reservaties.add(reservatie2);
            reservaties.add(reservatie3);
            reservaties.add(reservatie4);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}
