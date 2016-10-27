package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Seba
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Leergebied.getAlleLeergebieden",
                         query = "select l from Leergebied l")            
})
public class Leergebied implements Serializable {
    
    @Id
    private String naam;

    public Leergebied()
    {
        //JPA
    }
    
    public Leergebied(String n){
        this.naam = n;
    }

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
