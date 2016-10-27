package repository;

import domein.Beheerder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;

/**
 *
 * @author Robin Van den Broeck
 */
public class BeheerderRepository {

    private List<Beheerder> beheerders;

    public BeheerderRepository() {
        beheerders = FXCollections.observableArrayList();
        Beheerder robin = new Beheerder(null, "Van den Broeck", null, null, "Robin", "robin.vandenbroeck.v7375@student.hogent.be", true);
        Beheerder kas = new Beheerder(null, "De Durpel", null, null, "Kas", "kas.dedurpel.s3007@student.hogent.be", true);
        Beheerder seba = new Beheerder(null, "Kesteloot", null, null, "Seba", "sebastiaanwillem.kesteloot.v5118@student.hogent.be", true);
        beheerders.add(kas);
        beheerders.add(robin);
        beheerders.add(seba);
    }

    public List<Beheerder> geefAlleBeheerders() {
        return beheerders;
    }

    public void voegBeheerderToe(Beheerder nieuweBeheerder) {
        beheerders.add(nieuweBeheerder);
    }

    public void voegBeheerderToe(String email, String type) {
        String regularExpression
                = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regularExpression);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Geef een correct email adres van het formaat docent@hogent.be");
        }
        for (Beheerder b : beheerders) {
            if (b.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalArgumentException("Dit email adres is reeds een beheerder");
            }
        }
        if (type.equalsIgnoreCase("hoofdbeheerder")) {
            beheerders.add(new Beheerder("", "", "", type, "", email, true));
        } else if (type.equalsIgnoreCase("beheerder")) {
            beheerders.add(new Beheerder("", "", "", type, "", email, false));
        } else {
            throw new IllegalArgumentException("Type is niet correct");
        }
    }

    public void verwijderBeheerder(String email) {
        if (!email.isEmpty()) {
            for (Beheerder b : beheerders) {
                if(b.getEmail().equalsIgnoreCase(email)){
                    beheerders.remove(b);
                    break;
                }                 
            }
        } else{
            throw new IllegalArgumentException("Email adres is niet geldig");
        }

    }
}
