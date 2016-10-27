/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Doelgroep;
import domein.Gebruiker;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import static repository.GenericDaoJpa.em;

/**
 *
 * @author Robin Van den Broeck
 */
public class GebruikerDaoJpa extends GenericDaoJpa<Gebruiker> implements GebruikerDao{
    
    public GebruikerDaoJpa(){
        super(Gebruiker.class);
    }
    
//    @Override
//    public Gebruiker getGebruiker() throws EntityNotFoundException{
//        try {
//            return em.createNamedQuery("Gebruiker.getGebruiker", Gebruiker.class).getSingleResult();
//        } catch (NoResultException ex) {
//            throw new EntityNotFoundException();
//        } 
//    }
}
