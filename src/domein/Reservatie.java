/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Kas De Durpel
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Reservatie.getAlleReservaties",
            query = "select r from Reservatie r"),
    @NamedQuery(name = "Reservatie.getReservatieByResDetail",
            query = "select r from Reservatie r LEFT JOIN r.reservatieDetails AS rd WHERE rd.id = ?1")
})

@Access(AccessType.PROPERTY)
public class Reservatie implements Serializable{

    
    private long id;
    
    private Gebruiker gebruiker;
    
    private Date beginDatum;
    
    private Date eindDatum;
    //private IntegerProperty stuks;

    private ListProperty<ReservatieDetail> reservatieDetails;
    
    public Reservatie(Date begin, Date eind, Gebruiker gb, ObservableList<ReservatieDetail> reservatieDetails, int stuks){
        this.beginDatum = begin;
        this.eindDatum = eind;
        this.gebruiker = gb;
        setReservatieDetails(reservatieDetails);
    }
    
    public Reservatie(){
        this.beginDatum = new Date();
        this.eindDatum = new Date();
        this.gebruiker = new Gebruiker();
        setReservatieDetails(null);
    }

    @Id
    @GeneratedValue
    @Access(AccessType.PROPERTY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
     
    @ManyToOne
    @JoinTable(name = "Reservatie_gebruiker")
    @Access(AccessType.PROPERTY)
    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    @Access(AccessType.PROPERTY)
    @JoinTable(name = "Reservatie_reservatieDetail")
    @OneToMany(/*targetEntity = ReservatieDetail.class,*/ cascade = CascadeType.REMOVE)
    public List<ReservatieDetail> getReservatieDetails() {
        if (this.reservatieDetails != null) {
            return this.reservatieDetails.get();
        }else{
            ObservableList<ReservatieDetail> lijstje = FXCollections.observableArrayList();
            return lijstje;
        }
    }

    public void setReservatieDetails(List<ReservatieDetail> reservatieDetails) {
        if (reservatieDetails != null) {
            ObservableList<ReservatieDetail> reservatieDetailsObservable = FXCollections.observableArrayList(reservatieDetails);
            this.reservatieDetails = new SimpleListProperty((ObservableList) reservatieDetailsObservable);
        } else {
            ObservableList<ReservatieDetail> reservatieDetailsObservable = FXCollections.observableArrayList();
            this.reservatieDetails = new SimpleListProperty((ObservableList) reservatieDetailsObservable);
        }
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    @Temporal(TemporalType.DATE)
    @Access(AccessType.PROPERTY)
    public Date getBegindatum() {
        return beginDatum;
    }

    public void setBegindatum(Date begindatum) {
        this.beginDatum = begindatum;
    }

    @Temporal(TemporalType.DATE)
    @Access(AccessType.PROPERTY)
    public Date getEinddatum() {
        return eindDatum;
    }

    public void setEinddatum(Date einddatum) {
        this.eindDatum = einddatum;
    }
    
    @Transient
    public IntegerProperty getAantalMaterialen() {
		return new SimpleIntegerProperty(reservatieDetails.size());
	}

    
    
}
