/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Doelgroep;
import domein.Leergebied;
import domein.Materiaal;
import java.util.List;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Robin Van den Broeck
 */

public interface MateriaalDao extends GenericDao<Materiaal> {
    public List<Materiaal> getAlleMaterialen() throws EntityNotFoundException;
    public void wijzigMateriaal (Materiaal materiaal, String naam, String omschrijving, double prijs, int aantal, int aantalOnbeschikbaar,
            boolean uitleenbaarheid, String plaats, String firma, String email, List<Doelgroep> doelgroepen,
            List<Leergebied> leergebieden, String foto) throws EntityNotFoundException;
//    public boolean update (Materiaal materiaal) throws EntityNotFoundException;
}