package domein;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name = "Materiaal.getAlleMaterialen",
            query = "select m from Materiaal m"),
        })
@Access(AccessType.PROPERTY)
public class Materiaal implements Serializable {

    private long id;
    private StringProperty naam;
    private StringProperty omschrijving;
    private DoubleProperty prijs;
    private IntegerProperty aantal;
    private IntegerProperty aantalOnbeschikbaar;
    private BooleanProperty uitleenbaarheid;
    private StringProperty plaats;
    private StringProperty firma;
    private StringProperty email;
    private ListProperty<Doelgroep> doelgroepen;
    private ListProperty<Leergebied> leergebieden;
    private String foto;

    protected Materiaal() {
        //JPA
    }

    public Materiaal(String naam, String omschrijving, double prijs, int aantal, int aantalOnbeschikbaar, boolean uitleenbaarheid, 
            String plaats, String firma, String email, List<Doelgroep> doelgroepen, List<Leergebied> leergebieden, String foto) {
        setNaam(naam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
        setAantal(aantal);
        setAantalOnbeschikbaar(aantalOnbeschikbaar);
        setDoelgroepen(doelgroepen);
        setLeergebieden(leergebieden);
        setEmail(email);
        setFirma(firma);
        setFoto(foto);
        setPlaats(plaats);
        setUitleenbaarheid(uitleenbaarheid);

    }

    //constructor voor testen
    public Materiaal(String naam) {
        setNaam(naam);
    }
    
    public void setItems(String naam, String omschrijving, double prijs, int aantal, int aantalOnbeschikbaar, boolean uitleenbaarheid, 
            String plaats, String firma, String email, List<Doelgroep> doelgroepen, List<Leergebied> leergebieden, String foto){
        setNaam(naam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
        setAantal(aantal);
        setAantalOnbeschikbaar(aantalOnbeschikbaar);
        setDoelgroepen(doelgroepen);
        setLeergebieden(leergebieden);
        setEmail(email);
        setFirma(firma);
        setFoto(foto);
        setPlaats(plaats);
        setUitleenbaarheid(uitleenbaarheid);
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    @Access(AccessType.PROPERTY)
    public String getNaam() {
        return this.naam.get();
    }

    public StringProperty getNaamProperty() {
        return this.naam;
    }

    /**
     *
     * @param Naam
     */
    public void setNaam(String naam) {
        this.naam = new SimpleStringProperty(naam);
    }

    @Access(AccessType.PROPERTY)
    public String getOmschrijving() {
        return this.omschrijving.get();
    }

    public StringProperty getOmschrijvingProperty() {
        return this.omschrijving;
    }

    /**
     *
     * @param Omschrijving
     */
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = new SimpleStringProperty(omschrijving);
    }

    @Access(AccessType.PROPERTY)
    public double getPrijs() {
        return this.prijs.get();
    }

    public DoubleProperty getPrijsProperty() {
        return this.prijs;
    }

    /**
     *
     * @param Prijs
     */
    public void setPrijs(double prijs) {
        this.prijs = new SimpleDoubleProperty(prijs);
    }

    @Access(AccessType.PROPERTY)
    public int getAantal() {
        return this.aantal.get();
    }

    public IntegerProperty getAantalProperty() {
        return this.aantal;
    }

    /**
     *
     * @param Aantal
     */
    public void setAantal(int aantal) {
        this.aantal = new SimpleIntegerProperty(aantal);
    }

    @Access(AccessType.PROPERTY)
    public int getAantalOnbeschikbaar() {
        return this.aantalOnbeschikbaar.get();
    }

    public IntegerProperty getAantalOnbeschikbaarProperty() {
        return this.aantalOnbeschikbaar;
    }

    /**
     *
     * @param aantalOnbeschikbaar
     */
    public void setAantalOnbeschikbaar(int aantalOnbeschikbaar) {
        this.aantalOnbeschikbaar = new SimpleIntegerProperty(aantalOnbeschikbaar);
    }

    @Access(AccessType.PROPERTY)
    public boolean getUitleenbaarheid() {
        return this.uitleenbaarheid.get();
    }

    public BooleanProperty getUitleenbaarheidProperty() {
        return this.uitleenbaarheid;
    }

    /**
     *
     * @param uitleenbaarheid
     */
    public void setUitleenbaarheid(boolean uitleenbaarheid) {
        this.uitleenbaarheid = new SimpleBooleanProperty(uitleenbaarheid);
    }

    @Access(AccessType.PROPERTY)
    public String getPlaats() {
        return this.plaats.get();
    }

    public StringProperty getPlaatsProperty() {
        return this.plaats;
    }

    /**
     *
     * @param plaats
     */
    public void setPlaats(String plaats) {
        this.plaats = new SimpleStringProperty(plaats);
    }

    @Access(AccessType.PROPERTY)
    public String getFirma() {
        return this.firma.get();
    }

    public StringProperty getFirmaProperty() {
        return this.firma;
    }

    /**
     *
     * @param firma
     */
    public void setFirma(String firma) {
        this.firma = new SimpleStringProperty(firma);
    }

    @Access(AccessType.PROPERTY)
    public String getEmail() {
        return this.email.get();
    }

    public StringProperty getEmailProperty() {
        return this.email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    @Access(AccessType.PROPERTY)
    @JoinTable(name = "materiaal_Doelgroepen")
    @ManyToMany(targetEntity = Doelgroep.class)
    public List<Doelgroep> getDoelgroepen() {
        if (this.doelgroepen != null) {
            List<Doelgroep> lijstje = this.doelgroepen.get();
            return lijstje;
        }else{
            List<Doelgroep> lijstje = FXCollections.observableArrayList();
            return lijstje;
        }
    }

    public ListProperty<Doelgroep> getDoelgroepenProperty() {
        return this.doelgroepen;
    }

    /**
     *
     * @param doelgroepen
     */
    public void setDoelgroepen(List<Doelgroep> doelgroepen) {
        if (doelgroepen != null) {
            ObservableList<Doelgroep> doelgroepenObservable = FXCollections.observableArrayList(doelgroepen);
            this.doelgroepen = new SimpleListProperty((ObservableList) doelgroepenObservable);
        } else {
            ObservableList<Doelgroep> doelgroepenObservable = FXCollections.observableArrayList();
            this.doelgroepen = new SimpleListProperty((ObservableList) doelgroepenObservable);
        }
    }

    @Access(AccessType.PROPERTY)
    @JoinTable(name = "materiaal_Leergebieden")
    @ManyToMany(targetEntity = Leergebied.class)
    public List<Leergebied> getLeergebieden() {
        if (this.leergebieden != null) {
            List<Leergebied> lijstje = this.leergebieden.get();
            return lijstje;
        }else{
            List<Leergebied> lijstje = FXCollections.observableArrayList();
            return lijstje;
        }
    }

    public ListProperty<Leergebied> getLeergebiedenProperty() {
        return this.leergebieden;
    }

    /**
     *
     * @param leergebieden
     */
    public void setLeergebieden(List<Leergebied> leergebieden) {
        if (leergebieden != null) {
            ObservableList<Leergebied> leergebiedenObservable = FXCollections.observableArrayList(leergebieden);
            this.leergebieden = new SimpleListProperty((ObservableList) leergebiedenObservable);
        }else {
            ObservableList<Leergebied> leergebiedenObservable = FXCollections.observableArrayList();
            this.leergebieden = new SimpleListProperty((ObservableList) leergebiedenObservable);
        }
    }

    @Transient
    public String getFoto() {
        return this.foto;
    }

    /**
     *
     * @param foto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return getNaam();
    }

}
