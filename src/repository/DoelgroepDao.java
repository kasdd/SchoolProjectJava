/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Doelgroep;
import java.util.List;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Robin Van den Broeck
 */
public interface DoelgroepDao extends GenericDao<Doelgroep> {
    public List<Doelgroep> getAlleDoelgroepen() throws EntityNotFoundException;
}

