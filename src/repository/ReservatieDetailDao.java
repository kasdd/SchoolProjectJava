/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.ReservatieDetail;
import java.util.List;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Robin Van den Broeck
 */
public interface ReservatieDetailDao extends GenericDao<ReservatieDetail> {
//    public List<ReservatieDetail> getAlleReservatieDetails() throws EntityNotFoundException;
    public List<ReservatieDetail> getReservatieDetailsByMateriaal(long id) throws EntityNotFoundException;
}


