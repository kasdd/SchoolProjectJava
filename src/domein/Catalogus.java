/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Query;
//import repository.BeheerderRepository;
import repository.MateriaalRepository;
import repository.ReservatieRepository;
import repository.*;

/**
 *
 * @author Robin Van den Broeck
 */
public class Catalogus {

    private MateriaalRepository materiaalRepo;
    private ReservatieRepository reservatieRepo;
   // private BeheerderRepository beheerderRepo;

    private DoelgroepDaoJpa doelgroepDAO;
    private LeergebiedDaoJpa leergebiedDAO;
    private MateriaalDaoJpa materiaalDAO;
    private GebruikerDaoJpa gebruikerDAO;
    private ReservatieDetailDaoJpa reservatieDetailDAO;
    private ReservatieDaoJpa reservatieDAO;
    private BeheerderDaoJpa beheerderDAO;
    
    public Catalogus() throws ParseException {
        this.materiaalRepo = new MateriaalRepository();
        this.reservatieRepo = new ReservatieRepository();
  //      this.beheerderRepo = new BeheerderRepository();

        this.doelgroepDAO = new DoelgroepDaoJpa();
        this.leergebiedDAO = new LeergebiedDaoJpa();
        this.materiaalDAO = new MateriaalDaoJpa();
        this.gebruikerDAO = new GebruikerDaoJpa();
        this.reservatieDetailDAO = new ReservatieDetailDaoJpa();
        this.reservatieDAO = new ReservatieDaoJpa();
        this.beheerderDAO = new BeheerderDaoJpa();

        vulDb();
    }

    public void vulDb() {

        Doelgroep kleuteronderwijs = new Doelgroep("Kleuteronderwijs");
        Doelgroep lagerOnderwijs = new Doelgroep("Lager onderwijs");
        Doelgroep middelbaarOnderwijs = new Doelgroep("Middelbaar onderwijs");

        Leergebied mens = new Leergebied("Mens");
        Leergebied maatschappij = new Leergebied("Maatschappij");
        Leergebied geschiedenis = new Leergebied("Geschiedenis");
        Leergebied wetenschap = new Leergebied("Wetenschap");
        Leergebied biologie = new Leergebied("Biologie");
        Leergebied fysica = new Leergebied("Fysica");
        Leergebied techniek = new Leergebied("Techniek");
        Leergebied aardrijkskunde = new Leergebied("Aardrijkskunde");
        Leergebied wiskunde = new Leergebied("Wiskunde");

        doelgroepDAO.startTransaction();

        doelgroepDAO.insert(kleuteronderwijs);
        doelgroepDAO.insert(lagerOnderwijs);
        doelgroepDAO.insert(middelbaarOnderwijs);

        DoelgroepDaoJpa.commitTransaction();

        leergebiedDAO.startTransaction();

        leergebiedDAO.insert(mens);
        leergebiedDAO.insert(maatschappij);
        leergebiedDAO.insert(geschiedenis);
        leergebiedDAO.insert(wetenschap);
        leergebiedDAO.insert(biologie);
        leergebiedDAO.insert(fysica);
        leergebiedDAO.insert(techniek);
        leergebiedDAO.insert(aardrijkskunde);
        leergebiedDAO.insert(wiskunde);

        leergebiedDAO.commitTransaction();

        ObservableList<Doelgroep> doelgroep1 = FXCollections.observableArrayList();
        doelgroep1.add(kleuteronderwijs);
        doelgroep1.add(lagerOnderwijs);
        ObservableList<Doelgroep> doelgroep2 = FXCollections.observableArrayList();
        doelgroep2.add(middelbaarOnderwijs);
        doelgroep2.add(lagerOnderwijs);
        ObservableList<Leergebied> leergebied1 = FXCollections.observableArrayList();
        leergebied1.add(mens);
        leergebied1.add(wiskunde);
        ObservableList<Leergebied> leergebied2 = FXCollections.observableArrayList();
        leergebied2.add(aardrijkskunde);
        ObservableList<Leergebied> leergebied3 = FXCollections.observableArrayList();
        leergebied3.add(wetenschap);

        Materiaal dobbelsteen = new Materiaal("Dobbelsteen", "Dobbelstenen uit de hema",
                1.95, 20, 0, true, "B1.0325", "FirmaNaam", "firma@dobbelstenen.be", doelgroep1, leergebied1, "foto1.jpg");
        Materiaal wereldbol = new Materiaal("Wereldbol", "Wereldbol uit de hema",
                22.95, 20, 0, true, "B1.0123", "FirmaNaam", "firma@dobbelstenen.be", doelgroep1, leergebied2, "foto1.jpg");
        Materiaal atlas = new Materiaal("Atlas", "Atlas uit dreamland",
                10.95, 20, 0, true, "B2.0325", "FirmaNaam", "firma@dobbelstenen.be", doelgroep2, leergebied2, "foto1.jpg");
        Materiaal vuursteen = new Materiaal("Vuursteen", "Vuurstenen van Pompeiji",
                5.95, 20, 0, true, "B1.0325", "FirmaNaam", "firma@dobbelstenen.be", doelgroep2, leergebied2, "foto1.jpg");
        Materiaal maatbeker = new Materiaal("Maatbeker", "Maatbeker voor chemie",
                2.95, 20, 0, true, "B1.0325", "FirmaNaam", "firma@dobbelstenen.be", doelgroep2, leergebied3, "foto1.jpg");
        Materiaal microscoop = new Materiaal("microscoop", "", 0, 15, 0, true, "", "", "", doelgroep1, leergebied1, "");
        Materiaal spelbord = new Materiaal("monoply", "", 0, 20, 0, true, "", "", "", doelgroep2, leergebied2, "");
        Materiaal spelbord1 = new Materiaal("schaken", "", 0, 10, 0, true, "", "", "", doelgroep2, leergebied3, "");
        Materiaal spelbord2 = new Materiaal("ganzenbordé", "", 0, 50, 0, true, "", "", "", doelgroep1, leergebied1, "");

        //toevoegen aan databank
        materiaalDAO.startTransaction();

        materiaalDAO.insert(maatbeker);
        materiaalDAO.insert(dobbelsteen);
        materiaalDAO.insert(wereldbol);
        materiaalDAO.insert(atlas);
        materiaalDAO.insert(vuursteen);
        materiaalDAO.insert(microscoop);
        materiaalDAO.insert(spelbord);
        materiaalDAO.insert(spelbord1);
        materiaalDAO.insert(spelbord2);
        

        materiaalDAO.commitTransaction();
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "16/05/2016";
        String dateInString1 = "20/05/2016";
        String dateInString2 = "23/05/2016";
        String dateInString3 = "27/05/2016";

        Date date1 = null, date2 = null, date3 = null, date4 = null;

        try {
            date1 = formatter.parse(dateInString);
            date2 = formatter.parse(dateInString1);
            date3 = formatter.parse(dateInString2);
            date4 = formatter.parse(dateInString3);
        } catch (ParseException ex) {
            Logger.getLogger(Catalogus.class.getName()).log(Level.SEVERE, null, ex);
        }

        Gebruiker gebruiker1 = new Gebruiker("Kas", "De Durpel", "kas_dedurpel@hotmail.com");
        Gebruiker gebruiker2 = new Gebruiker("Robin", "Van den Broeck", "robinvandenbroeck@hotmail.com");
        
        gebruikerDAO.startTransaction();
        
        gebruikerDAO.insert(gebruiker2);
        gebruikerDAO.insert(gebruiker1);
        
        gebruikerDAO.commitTransaction();

        ReservatieDetail rd = new ReservatieDetail(dobbelsteen, 3, 0);
        ReservatieDetail rd1 = new ReservatieDetail(microscoop, 2, 0);
        ReservatieDetail rd2 = new ReservatieDetail(spelbord, 2, 0);
        ReservatieDetail rd3 = new ReservatieDetail(spelbord1, 2, 0);
        ReservatieDetail rd4 = new ReservatieDetail(spelbord2, 2, 0);
        
        reservatieDetailDAO.startTransaction();
        
        reservatieDetailDAO.insert(rd);
        reservatieDetailDAO.insert(rd1);
        reservatieDetailDAO.insert(rd2);
        reservatieDetailDAO.insert(rd3);
        reservatieDetailDAO.insert(rd4);
        
        reservatieDetailDAO.commitTransaction();

        ObservableList<ReservatieDetail> details1 = FXCollections.observableArrayList();
        ObservableList<ReservatieDetail> details2 = FXCollections.observableArrayList();
        ObservableList<ReservatieDetail> details3 = FXCollections.observableArrayList();
        ObservableList<ReservatieDetail> details4 = FXCollections.observableArrayList();

        
        details1.add(rd);
        details1.add(rd1);
        details2.add(rd2);
        details3.add(rd3);
        details4.add(rd4);

        Reservatie reservatie1 = new Reservatie(date1, date2, gebruiker1, details1, 10);
        Reservatie reservatie2 = new Reservatie(date3, date4, gebruiker1, details2, 2);
        Reservatie reservatie3 = new Reservatie(date1, date2, gebruiker2, details3, 3);
        Reservatie reservatie4 = new Reservatie(date3, date4, gebruiker2, details4, 5);

        
        reservatieDAO.startTransaction();
        
        reservatieDAO.insert(reservatie1);
        reservatieDAO.insert(reservatie2);
        reservatieDAO.insert(reservatie3);
        reservatieDAO.insert(reservatie4);
        
        reservatieDAO.commitTransaction();
        
//        beheerderDAO.startTransaction();
//        
//        Beheerder robin = new Beheerder(null, "Van den Broeck", null, null, "Robin", "robin.vandenbroeck.v7375@student.hogent.be", true);
//        Beheerder kas = new Beheerder(null, "De Durpel", null, null, "Kas", "kas.dedurpel.s3007@student.hogent.be", true);
//        Beheerder seba = new Beheerder(null, "Kesteloot", null, null, "Seba", "sebastiaanwillem.kesteloot.v5118@student.hogent.be", true);
//        beheerderDAO.insert(kas);
//        beheerderDAO.insert(robin);
//        beheerderDAO.insert(seba);
//        
//        beheerderDAO.commitTransaction();
    }

    public ObservableList<Materiaal> geefAlleMaterialen() {
        return (ObservableList<Materiaal>) materiaalDAO.getAlleMaterialen();
    }

    public ObservableList<Reservatie> geefAlleReservaties() {
        return (ObservableList<Reservatie>) reservatieDAO.getAlleReservaties();
    }

    public void voegMateriaalToe(Materiaal nieuwMateriaal) {
        materiaalDAO.startTransaction();
        
        materiaalDAO.insert(nieuwMateriaal);
        
        materiaalDAO.commitTransaction();
    }

    public void wijzigMateriaal(Materiaal materiaal, String naam, String omschrijving, double prijs, int aantal, int aantalOnbeschikbaar,
            boolean uitleenbaarheid, String plaats, String firma, String email, List<Doelgroep> doelgroepen,
            List<Leergebied> leergebieden, String foto) {
        
        //Materiaal mat = materiaalDAO.get(materiaal.getId());
//        materiaalDAO.wijzigMateriaal(materiaal, naam, omschrijving, prijs,
//                aantal, aantalOnbeschikbaar, uitleenbaarheid, plaats, firma, email, doelgroepen, leergebieden, foto);
        Materiaal mat = materiaalDAO.get(materiaal.getId());
            materiaalDAO.startTransaction();
            mat.setNaam(naam);
            mat.setOmschrijving(omschrijving);
            mat.setAantal(aantal);
            mat.setAantalOnbeschikbaar(aantalOnbeschikbaar);
            mat.setEmail(email);
            mat.setFirma(firma);
            mat.setFoto(foto);
            mat.setPlaats(plaats);
            mat.setPrijs(prijs);
            mat.setUitleenbaarheid(uitleenbaarheid);
            mat.setDoelgroepen(doelgroepen);
            mat.setLeergebieden(leergebieden);
//            mat.setItems(naam, omschrijving, prijs,
//                aantal, aantalOnbeschikbaar, uitleenbaarheid, plaats, firma, email, doelgroepen, leergebieden, foto);
//            em.merge(mat);
            materiaalDAO.commitTransaction();
    }

    public void verwijderMateriaal(Materiaal materiaal) {
        List<ReservatieDetail> resDetails = reservatieDetailDAO.getReservatieDetailsByMateriaal(materiaal.getId());
        
        List<Reservatie> reservatiesByMateriaal = new ArrayList<>();
        
        for (ReservatieDetail rs : resDetails){
            
             reservatiesByMateriaal.add(reservatieDAO.getReservatieByResDetail(rs.getId()));
        }
        
        reservatieDAO.startTransaction();

        for(Reservatie r : reservatiesByMateriaal){
            reservatieDAO.delete(r);
        }
        reservatieDAO.commitTransaction();
        
        materiaalDAO.startTransaction();
        
        materiaalDAO.delete(materiaal);
        
        materiaalDAO.commitTransaction();
    }

    public List<Doelgroep> geefAlleDoelgroepen() {
        return doelgroepDAO.getAlleDoelgroepen();
    }

    public List<Leergebied> geefAlleLeergebieden() {
        return leergebiedDAO.getAlleLeergebieden();
    }

    public ObservableList<Reservatie> geefReservatiesVanDatum(Date datum) {

        ObservableList<Reservatie> reservaties = FXCollections.observableArrayList();
        reservaties = geefAlleReservaties();
        ObservableList<Reservatie> doorgeefReservaties = FXCollections.observableArrayList();

        for (Reservatie r : reservaties) {
            if (asLocalDate(r.getBegindatum()).equals(asLocalDate(datum))) {
                doorgeefReservaties.add(r);
            }
        }
        return doorgeefReservaties;
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public List<Beheerder> geefAlleBeheerders() {
        return beheerderDAO.getAlleBeheerders();
    }

    public void voegBeheerderToe(String email,String type) {
        beheerderDAO.voegBeheerderToe(email, type);
    }

    void verwijderBeheerder(String email) {
        beheerderDAO.verwijderBeheerder(email);
    }

    void verwijderReservatie(Reservatie reservatie) {
        reservatieDAO.startTransaction();
        
        reservatieDAO.delete(reservatie);
        
        reservatieDAO.commitTransaction();
    }

}
