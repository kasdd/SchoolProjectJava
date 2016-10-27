/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Kas De Durpel
 */
@Entity
public class Gebruiker {

    private String voornaam;
    private String familienaam;
    @Id
    private String email;

    Gebruiker() {
        this.voornaam = "";
        this.familienaam = "";
        this.email = "";
    }
    
    public Gebruiker(String vn, String fn, String email){
        this.voornaam = vn;
        this.familienaam = fn;
        this.email = email;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    @Override
    public String toString(){
        return familienaam + " ; " + voornaam;
    }
}
