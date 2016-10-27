/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.ReservatieDetail;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import static repository.GenericDaoJpa.em;

/**
 *
 * @author Robin Van den Broeck
 */

public class ReservatieDetailDaoJpa extends GenericDaoJpa<ReservatieDetail> implements ReservatieDetailDao{
    
    public ReservatieDetailDaoJpa(){
        super(ReservatieDetail.class);
    }
    
//    @Override
//    public List<ReservatieDetail> getAlleReservatieDetails() throws EntityNotFoundException{
//        try {
//            return em.createNamedQuery("ReservatieDetail.getAlleReservatieDetails", ReservatieDetail.class).getResultList();
//        } catch (NoResultException ex) {
//            throw new EntityNotFoundException();
//        } 
//    }

    @Override
    public List<ReservatieDetail> getReservatieDetailsByMateriaal(long id) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("ReservatieDetail.getAlleReservatieDetailsByMat", ReservatieDetail.class).setParameter(1, id).getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}
