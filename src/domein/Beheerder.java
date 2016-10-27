/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;

/**
 *
 * @author Robin Van den Broeck
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Beheerder.getAlleBeheerders",
                         query = "select b from Beheerder b")            
})
@Access(AccessType.PROPERTY)
public class Beheerder {

    
    private int beheerderId;
    private String faculteit;
    private String naam;
    private String foto;
    private StringProperty type;
    private String voornaam;
    private StringProperty email;
    private String wachtwoord;
    private boolean isHoofdbeheerder;

    public Beheerder(String f, String fm, String foto, String type, String vm, String email, Boolean beheerder) {
        this.faculteit = f;
        setType(type);
        this.naam = fm;
        this.foto = foto;
        this.voornaam = vm;
        setEmail(email);
        setWachtwoord("1234ab");
        this.isHoofdbeheerder = beheerder;
    }

    public Beheerder() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    public int getBeheerderId() {
        return beheerderId;
    }

    public void setBeheerderId(int beheerderId) {
        this.beheerderId = beheerderId;
    }
    
    
    
    @Access(AccessType.PROPERTY)
    public String getFaculteit() {
        return faculteit;
    }

    public void setFaculteit(String faculteit) {
        this.faculteit = faculteit;
    }

    @Access(AccessType.PROPERTY)
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Access(AccessType.PROPERTY)
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Access(AccessType.PROPERTY)
    public String getType() {
        return type.get();
    }

    public StringProperty getTypeProperty() {
        if (isHoofdbeheerder) {
            return new SimpleStringProperty("Hoofdbeheerder");
        } else {
            return new SimpleStringProperty("Beheerder");
        }
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    @Access(AccessType.PROPERTY)
    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    @Access(AccessType.PROPERTY)
    public String getEmail() {
        return email.get();
    }

    public StringProperty getEmailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    @Access(AccessType.PROPERTY)
    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
    
    

    @Access(AccessType.PROPERTY)
    public boolean isIsHoofdbeheerder() {
        return isHoofdbeheerder;
    }

    public void setIsHoofdbeheerder(boolean isHoofdbeheerder) {
        this.isHoofdbeheerder = isHoofdbeheerder;
    }

}
