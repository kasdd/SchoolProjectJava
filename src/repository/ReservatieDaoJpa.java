/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Doelgroep;
import domein.Reservatie;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import static repository.GenericDaoJpa.em;

/**
 *
 * @author Robin Van den Broeck
 */

public class ReservatieDaoJpa extends GenericDaoJpa<Reservatie> implements ReservatieDao{
    
    public ReservatieDaoJpa(){
        super(Reservatie.class);
    }
    
    @Override
    public List<Reservatie> getAlleReservaties() throws EntityNotFoundException{
        try {
            ObservableList lijst = FXCollections.observableArrayList(em.createNamedQuery("Reservatie.getAlleReservaties", Reservatie.class).getResultList());
            return lijst;
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
    
    @Override
    public Reservatie getReservatieByResDetail(long id) throws EntityNotFoundException{
        try {
            return em.createNamedQuery("Reservatie.getReservatieByResDetail", Reservatie.class).setParameter(1, id).getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
    
    //    @Override
//    public Reservatie (Materiaal materiaal) throws EntityNotFoundException{
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
    
}