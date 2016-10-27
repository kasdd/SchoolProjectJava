/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Doelgroep;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

/**
 *
 * @author Robin Van den Broeck
 */
public class DoelgroepDaoJpa extends GenericDaoJpa<Doelgroep> implements DoelgroepDao{
    
    public DoelgroepDaoJpa(){
        super(Doelgroep.class);
    }
    
    @Override
    public List<Doelgroep> getAlleDoelgroepen() throws EntityNotFoundException{
        try {
            return em.createNamedQuery("Doelgroep.getAlleDoelgroepen", Doelgroep.class).getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}

//public class BierDaoJpa extends GenericDaoJpa<Bier> implements BierDao  {
//    public BierDaoJpa() {
//        super(Bier.class);
//    }
//
//    @Override
//    public Bier getBierByName(String name) throws EntityNotFoundException {
//        try {
//            return em.createNamedQuery("Bier.findByName", Bier.class)
//                 .setParameter("bierNaam", name)
//                .getSingleResult();
//        } catch (NoResultException ex) {
//            throw new EntityNotFoundException();
//        } 
////       /* try {
////        return em.createQuery("select b from Bier b where b.naam = :bierNaam", Bier.class)
////                .setParameter("bierNaam", name)
////                .getSingleResult();
////        } catch (NoResultException ex) {
////            throw new EntityNotFoundException();
////        } */
//    }
//}
//
