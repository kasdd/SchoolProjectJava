/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Beheerder;
import java.util.List;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Robin Van den Broeck
 */
public interface BeheerderDao extends GenericDao<Beheerder> {
    public List<Beheerder> getAlleBeheerders() throws EntityNotFoundException;
    public void voegBeheerderToe(String email, String type) throws EntityNotFoundException;
    public void verwijderBeheerder(String email)throws EntityNotFoundException;
}
