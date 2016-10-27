/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DomeinController {

    private Catalogus catalogus;

    private Materiaal huidigMateriaal;
    private Reservatie huidigeReservatie;
    private Beheerder huidigeBeheerder;

    public DomeinController() throws ParseException {
        this.catalogus = new Catalogus();
        this.huidigMateriaal = null;
        this.huidigeReservatie = null;
        this.huidigeBeheerder = null;
    }

    public Beheerder getHuidigeBeheerder() {
        return huidigeBeheerder;
    }

    public void setHuidigeBeheerder(Beheerder huidigeBeheerder) {
        this.huidigeBeheerder = huidigeBeheerder;
    }

    //Voor testen
    public void setCatalogus(Catalogus mock) {
        this.catalogus = mock;
    }

    public void voegMateriaalToe(String naam, String omschrijving, double prijs, int aantal, int aantalOnbeschikbaar,
            boolean uitleenbaarheid, String plaats, String firma, String email, List<String> doelgroepStrings,
            List<String> leergebiedStrings, String foto) {
        
        if(doelgroepStrings == null || leergebiedStrings == null){
            throw new IllegalArgumentException("geen lijst van doelgroepen of leergebieden");
        }

        List<Doelgroep> doelgroepen = new ArrayList<>();
        for(String d: doelgroepStrings){
            Doelgroep f = new Doelgroep();
            f.setNaam(d);
            doelgroepen.add(f);
        }
        List<Leergebied> leergebieden = new ArrayList<>();
        for(String d: leergebiedStrings){
            Leergebied f = new Leergebied();
            f.setNaam(d);
            leergebieden.add(f);
        }
        Materiaal nieuwMateriaal = new Materiaal(naam, omschrijving, prijs,
                aantal, aantalOnbeschikbaar, uitleenbaarheid, plaats, firma, email, doelgroepen, leergebieden, foto);
        catalogus.voegMateriaalToe(nieuwMateriaal);

    }

    public void wijzigMateriaal(Materiaal materiaal, String naam, String omschrijving, double prijs, int aantal, int aantalOnbeschikbaar,
            boolean uitleenbaarheid, String plaats, String firma, String email, List<String> doelgroepStrings,
            List<String> leergebiedStrings, String foto) {
        
        if(doelgroepStrings == null || leergebiedStrings == null){
            throw new IllegalArgumentException("geen lijst van doelgroepen of leergebieden");
        }
        
        List<Doelgroep> doelgroepen = new ArrayList<>();
        
        doelgroepStrings.stream().map((d) -> {
            Doelgroep f = new Doelgroep();
            f.setNaam(d);
            return f;
        }).forEach((f) -> {
            doelgroepen.add(f);
        });
        
        List<Leergebied> leergebieden = new ArrayList<>();
        
        leergebiedStrings.stream().map((d) -> {
            Leergebied f = new Leergebied();
            f.setNaam(d);
            return f;
        }).forEach((f) -> {
            leergebieden.add(f);
        });
        
 //       Materiaal nieuwMateriaal = new Materiaal(naam, omschrijving, prijs,
 //               aantal, aantalOnbeschikbaar, uitleenbaarheid, plaats, firma, email, doelgroepen, leergebieden, foto);
//            materiaal.setItems(naam, omschrijving, prijs,
//                aantal, aantalOnbeschikbaar, uitleenbaarheid, plaats, firma, email, doelgroepen, leergebieden, foto);
        catalogus.wijzigMateriaal(materiaal, naam, omschrijving, prijs,
                aantal, aantalOnbeschikbaar, uitleenbaarheid, plaats, firma, email, doelgroepen, leergebieden, foto);
    }

    public void verwijderMateriaal(Materiaal materiaal) {

        catalogus.verwijderMateriaal(materiaal);

    }

    public ObservableList<Reservatie> geefAlleReservaties() {
        return (ObservableList<Reservatie>) catalogus.geefAlleReservaties();
    }

    public ObservableList<Materiaal> geefAlleMaterialen() {
        return (ObservableList<Materiaal>) catalogus.geefAlleMaterialen();
    }

    public List<Beheerder> geefAlleBeheerders() {
        return catalogus.geefAlleBeheerders();
    }

    public ObservableList<String> geefObservableListAlleDoelgroepen() {
        List<Doelgroep> doelgroepen = catalogus.geefAlleDoelgroepen();
        List<String> doelgroepenStrings = new ArrayList<>();
        doelgroepen.stream().forEach((d) -> {
            doelgroepenStrings.add(d.getNaam());
        });
        ObservableList<String> temp = FXCollections.observableArrayList(doelgroepenStrings);
        return temp;
    }

    public List<String> geefObservableListAlleLeergebieden() {
        List<Leergebied> leergebieden = catalogus.geefAlleLeergebieden();
        ObservableList<String> leergebiedenStrings = FXCollections.observableArrayList();
        leergebieden.stream().forEach((d) -> {
            leergebiedenStrings.add(d.getNaam());
        });
        ObservableList<String> temp = FXCollections.observableArrayList(leergebiedenStrings);
        return temp;
    }

    public void voegBeheerToe(String email, String type) {
        catalogus.voegBeheerderToe(email, type);
    }

    public void verwijderBeheerder(String email) {
        catalogus.verwijderBeheerder(email);
    }

    public ObservableList<Reservatie> geefReservatiesVanDatum(Date datum) {
        return catalogus.geefReservatiesVanDatum(datum);
    }

    public void wijzigReservatieDetail(ReservatieDetail resDetail, int aantalUitNumber, int aantalInNumber) {

        resDetail.setAantalUitgeleend(aantalUitNumber);
        resDetail.setAantalTerug(aantalInNumber);
    }

    public void verwijderReservatieDetail(Reservatie reservatie, ReservatieDetail resDetail) {
        reservatie.getReservatieDetails().remove(resDetail);
    }

    public void wijzigDatum(Reservatie res, Date date) {
        res.setBegindatum(date);
        res.setEinddatum(DateUtil.addDays(date, 4));
    }

    public void verwijderReservatie(Reservatie reservatie) {
        catalogus.verwijderReservatie(reservatie);
    }

}
