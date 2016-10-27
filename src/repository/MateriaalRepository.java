/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Doelgroep;
import domein.Leergebied;
import domein.Materiaal;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kas De Durpel
 */
public class MateriaalRepository {
    
    private List<Materiaal> materialen;
    private List<Doelgroep> doelgroepen;
    private List<Leergebied> leergebieden;
    
    public MateriaalRepository(){
        doelgroepen = FXCollections.observableArrayList();
        leergebieden = FXCollections.observableArrayList();
        materialen = FXCollections.observableArrayList();
        
        Doelgroep kleuteronderwijs = new Doelgroep("Kleuteronderwijs");
        Doelgroep lagerOnderwijs = new Doelgroep("Lager onderwijs");
        Doelgroep middelbaarOnderwijs = new Doelgroep("Middelbaar onderwijs");
        
        doelgroepen.add(kleuteronderwijs);
        doelgroepen.add(lagerOnderwijs);
        doelgroepen.add(middelbaarOnderwijs);
        
        Leergebied mens = new Leergebied("Mens");
        Leergebied maatschappij = new Leergebied("Maatschappij");
        Leergebied geschiedenis = new Leergebied("Geschiedenis");
        Leergebied wetenschap = new Leergebied("Wetenschap");
        Leergebied biologie = new Leergebied("Biologie");
        Leergebied fysica = new Leergebied("Fysica");
        Leergebied techniek = new Leergebied("Techniek");
        Leergebied aardrijkskunde = new Leergebied("Aardrijkskunde");
        Leergebied wiskunde = new Leergebied("Wiskunde");
        
        leergebieden.add(mens);
        leergebieden.add(maatschappij);
        leergebieden.add(geschiedenis);
        leergebieden.add(wetenschap);
        leergebieden.add(biologie);
        leergebieden.add(fysica);
        leergebieden.add(techniek);
        leergebieden.add(aardrijkskunde);
        leergebieden.add(wiskunde);
        
        List<Doelgroep> doelgroep1 = new ArrayList<>();
        doelgroep1.add(kleuteronderwijs);
        doelgroep1.add(lagerOnderwijs);
        List<Doelgroep> doelgroep2 = new ArrayList<>();
        doelgroep2.add(middelbaarOnderwijs);
        doelgroep2.add(lagerOnderwijs);
        List<Leergebied> leergebied1 = new ArrayList<>();
        leergebied1.add(mens);
        leergebied1.add(wiskunde);
        List<Leergebied> leergebied2 = new ArrayList<>();
        leergebied2.add(aardrijkskunde);
        List<Leergebied> leergebied3 = new ArrayList<>();
        leergebied3.add(wetenschap);
        materialen.add(new Materiaal("Dobbelsteen", "Dobbelstenen uit de hema", 1.95, 20, 0, true, "B1.0325", "FirmaNaam", "firma@dobbelstenen.be", doelgroep1, leergebied1, "foto1.jpg"));
        materialen.add(new Materiaal("Wereldbol", "Wereldbol uit de hema", 22.95, 20, 0, true, "B1.0123", "FirmaNaam", "firma@dobbelstenen.be", doelgroep1, leergebied2, "foto1.jpg"));
        materialen.add(new Materiaal("Atlas", "Atlas uit dreamland", 10.95, 20, 0, true, "B2.0325", "FirmaNaam", "firma@dobbelstenen.be", doelgroep2, leergebied2, "foto1.jpg"));
        materialen.add(new Materiaal("Vuursteen", "Vuurstenen van Pompeiji", 5.95, 20, 0, true, "B1.0325", "FirmaNaam", "firma@dobbelstenen.be", doelgroep2, leergebied2, "foto1.jpg"));
        materialen.add(new Materiaal("Maatbeker", "Maatbeker voor chemie", 2.95, 20, 0, true, "B1.0325", "FirmaNaam", "firma@dobbelstenen.be", doelgroep2, leergebied3, "foto1.jpg"));
        
    }
    public List<Materiaal> geefAlleMaterialen() {
        return materialen;
    }   
    
    public List<Materiaal> geefGefilterdeMaterialen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void voegMateriaalToe(Materiaal nieuwMateriaal){
        materialen.add(nieuwMateriaal);
    }
    
    public void wijzigMateriaal(Materiaal oudMateriaal, Materiaal nieuwMateriaal){
        Boolean correct = false;
        if(oudMateriaal == null)
            throw new IllegalArgumentException("Gelieve een correcte aanpassing te maken!");
        if(nieuwMateriaal == null)
            throw new IllegalArgumentException("Gelieve een correcte aanpassing te maken!");
        for (Materiaal zoekMateriaal : materialen){
            if(zoekMateriaal.getNaam().equals(oudMateriaal.getNaam())){
                materialen.remove(zoekMateriaal);
                materialen.add(nieuwMateriaal);
                correct = true;
                break;
            }
        } if(!correct){
            throw new IllegalArgumentException("Gelieve een correcte aanpassing te maken!");
        }
    }
    
    public void verwijderMateriaal(Materiaal materiaal)
    {
        Boolean correct = true;
        for (Materiaal zoekMateriaal : materialen){
            if(zoekMateriaal.getNaam().equals(materiaal.getNaam())){
                materialen.remove(zoekMateriaal);
                correct = false;
                break;
            }
        }        
        if(correct)
            throw new IllegalArgumentException("Materiaal zit niet in de catalogus.");
    }

    public List<Leergebied> geefAlleLeergebieden() {
        return leergebieden;
    }
    
     public List<Doelgroep> geefAlleDoelgroepen() {
        return doelgroepen;
    }
}
