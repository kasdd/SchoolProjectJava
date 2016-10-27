/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Beheerder;
import domein.Doelgroep;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import static repository.GenericDaoJpa.em;

/**
 *
 * @author Robin Van den Broeck
 */
public class BeheerderDaoJpa extends GenericDaoJpa<Beheerder> implements BeheerderDao{
    public BeheerderDaoJpa(){
        super(Beheerder.class);
    }
    
    @Override
    public List<Beheerder> getAlleBeheerders() throws EntityNotFoundException{
        try {
            return em.createNamedQuery("Beheerder.getAlleBeheerders", Beheerder.class).getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }

    @Override
    public void voegBeheerderToe(String email, String type) throws EntityNotFoundException {
        String regularExpression
                = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regularExpression);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Geef een correct email adres van het formaat docent@hogent.be");
        }
        
        List<Beheerder> beheerders = getAlleBeheerders();
        for (Beheerder b : beheerders) {
            if (b.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalArgumentException("Dit email adres is reeds een beheerder");
            }
        }
        if (type.equalsIgnoreCase("hoofdbeheerder")) {
            this.startTransaction();
            this.insert(new Beheerder("", "", "", type, "", email, true));
            this.commitTransaction();
        //    beheerders.add(new Beheerder("", "", "", type, "", email, true));
        } else if (type.equalsIgnoreCase("beheerder")) {
            this.startTransaction();
            this.insert(new Beheerder("", "", "", type, "", email, false));
            this.commitTransaction();
        //    beheerders.add(new Beheerder("", "", "", type, "", email, false));
        } else {
            throw new IllegalArgumentException("Type is niet correct");
        }
    }
    
    public void verwijderBeheerder(String email)throws EntityNotFoundException  {
        if (!email.isEmpty()) {
            List<Beheerder> beheerders = getAlleBeheerders();
            for (Beheerder b : beheerders) {
                if(b.getEmail().equalsIgnoreCase(email)){
                    this.startTransaction();
                    this.delete(b);
                    this.commitTransaction();
                    //beheerders.remove(b);
                    break;
                }                 
            }
        } else{
            throw new IllegalArgumentException("Email adres is niet geldig");
        }

    }
}
