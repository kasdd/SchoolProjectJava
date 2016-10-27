/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Doelgroep;
import domein.Leergebied;
import domein.Materiaal;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Robin Van den Broeck
 */

public class MateriaalDaoJpa extends GenericDaoJpa<Materiaal> implements MateriaalDao{
    
    public MateriaalDaoJpa(){
        super(Materiaal.class);
    }
    
    @Override
    public List<Materiaal> getAlleMaterialen() throws EntityNotFoundException{
        try {
            ObservableList lijst = FXCollections.observableArrayList(em.createNamedQuery("Materiaal.getAlleMaterialen", Materiaal.class).getResultList());
            return lijst;
            //return em.createNamedQuery("Materiaal.getAlleMaterialen", Materiaal.class).getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
    
//    @Override
//    public void update (Materiaal materiaal) throws EntityNotFoundException{
//        try {
//            ObservableList lijst = FXCollections.observableArrayList(em.createNamedQuery("Materiaal.getAlleMaterialen", Materiaal.class).getResultList());
//            return m;
//            manager.createNamedQuery("updateEmployeeName", EmployeeEntity.class)
//            .setParameter(1, firstName)
//            .setParameter(2, lastName)
//            .setParameter(3, id)
//            .executeUpdate();
//        } catch (NoResultException ex) {
//            throw new EntityNotFoundException();
//        } 
//    }
    
    @Override
    public void wijzigMateriaal (Materiaal materiaal, String naam, String omschrijving, double prijs, int aantal, int aantalOnbeschikbaar,
            boolean uitleenbaarheid, String plaats, String firma, String email, List<Doelgroep> doelgroepen,
            List<Leergebied> leergebieden, String foto) throws EntityNotFoundException{
        try {
            
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}