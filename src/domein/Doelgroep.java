package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Seba
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Doelgroep.getAlleDoelgroepen",
                         query = "select d from Doelgroep d")
})


public class Doelgroep implements Serializable  {
    
    @Id
//    @GeneratedValue
//    private long id;
    private String naam;

    public Doelgroep()
    {
        //JPA
    }
    
    public Doelgroep(String d){
        this.naam = d;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    
    @Override
    public String toString(){
        return naam;
    }
}
