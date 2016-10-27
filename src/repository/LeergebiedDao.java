/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Leergebied;
import java.util.List;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Robin Van den Broeck
 */
public interface LeergebiedDao extends GenericDao<Leergebied> {
    public List<Leergebied> getAlleLeergebieden() throws EntityNotFoundException;
}
