/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Leergebied;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import static repository.GenericDaoJpa.em;

/**
 *
 * @author Robin Van den Broeck
 */
public class LeergebiedDaoJpa extends GenericDaoJpa<Leergebied> implements LeergebiedDao{
    
    public LeergebiedDaoJpa(){
        super(Leergebied.class);
    }
    
    @Override
    public List<Leergebied> getAlleLeergebieden() throws EntityNotFoundException{
        try {
            return em.createNamedQuery("Leergebied.getAlleLeergebieden", Leergebied.class).getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}
