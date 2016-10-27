/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 *
 * @author Kas De Durpel
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ReservatieDetail.getAlleReservatieDetailsByMat",
            query = "select r from ReservatieDetail r where r.materiaal.id = ?1")
})
@Access(AccessType.PROPERTY)
public class ReservatieDetail implements Serializable {

    
    private long id;
    
    private Materiaal materiaal;
    private IntegerProperty aantalUitgeleend;
    private IntegerProperty aantalTerug;

    public ReservatieDetail() {
        //JPA
    }
    
    public ReservatieDetail(Materiaal m, int aantalUitgeleend, int aantalTerug){
        this.materiaal = m;
        setAantalUitgeleend(aantalUitgeleend);
        setAantalTerug(aantalTerug);
    }
    
    @Id
    @GeneratedValue
    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @ManyToOne
    @JoinTable(name = "ReservatieDetail_materiaal")
    public Materiaal getMateriaal() {
        return materiaal;
    }

    public void setMateriaal(Materiaal materiaal) {
        this.materiaal = materiaal;
    }

     public IntegerProperty getAantalUitgeleendProperty() {
        return aantalUitgeleend;
    }
     

    @Access(AccessType.PROPERTY)
    public int getAantalUitgeleend() {
        return aantalUitgeleend.get();
    }
    
    public void setAantalUitgeleend(int aantalUitgeleend) {
        this.aantalUitgeleend = new SimpleIntegerProperty(aantalUitgeleend);
    }

    public IntegerProperty getAantalTerugProperty() {
        return aantalTerug;
    }
    

    @Access(AccessType.PROPERTY)
    public int getAantalTerug() {
        return aantalTerug.get();
    }
    
    public void setAantalTerug(int aantalTerug) {
        this.aantalTerug = new SimpleIntegerProperty(aantalTerug);
    }
    
    @Override
    public String toString(){
        return materiaal.getNaam();
    }

}
