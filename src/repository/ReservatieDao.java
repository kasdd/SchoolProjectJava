/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Materiaal;
import domein.Reservatie;
import java.util.List;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Robin Van den Broeck
 */


public interface ReservatieDao extends GenericDao<Reservatie> {
    public List<Reservatie> getAlleReservaties() throws EntityNotFoundException;
    public Reservatie getReservatieByResDetail(long id) throws EntityNotFoundException;
//    public Reservatie getReservatie(String naam) throws EntityNotFoundException;
}
