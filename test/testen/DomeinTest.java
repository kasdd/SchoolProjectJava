package testen;

import domein.Beheerder;
import domein.Doelgroep;
import domein.DomeinController;
import domein.InlogController;
import domein.Materiaal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.BeheerderRepository;
import repository.MateriaalRepository;
import repository.ReservatieRepository;

/**
 *
 * @author Kas De Durpel
 */
public class DomeinTest {

    private DomeinController domeinController;
    private InlogController ic;
    private BeheerderRepository beheerderRepoDummy;
    private MateriaalRepository materiaalRepoDummy;
    private ReservatieRepository reservatieRepoDummy;

    @Before
    public void before() throws ParseException {
        ic = new InlogController();
        domeinController = new DomeinController();
    }

    //Testen login
    @Test
    public void testHash() throws ParseException {
        String hast1 = "dbfcfd0d87220f629339bd3adcf452d083fde3246625fb3a93e314f833e20d37";
        String hast2 = "fc911b444d8b02db1fbe22fbfa87a4029db3138d7cf21cc37f5411e07700775b";
        String paswoord1 = "abc1";
        String paswoord2 = "gh45ze";

        Assert.assertEquals(ic.sha256(paswoord1), hast1);
        Assert.assertEquals(ic.sha256(paswoord2), hast2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMeldAanFoutieveGebruiker() throws JSONException {
        ic.meldAan("Hallo123", "Hallo123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMeldAanFoutiefWachtwoord() throws JSONException {
        ic.meldAan("kas.dedurpel.s3007@student.hogent.be", "hallo123");
    }

    @Test
    public void testAanmeldenCorrect() throws JSONException {
        Beheerder beheerderCorrect = new Beheerder();
        beheerderCorrect.setEmail("kas.dedurpel.s3007@student.hogent.be");
        beheerderCorrect.setVoornaam("Kas");
        beheerderCorrect.setNaam("De Durpel");
        Beheerder beheerderTest = ic.meldAan("kas.dedurpel.s3007@student.hogent.be", "74dmgdy");
        Assert.assertEquals(beheerderCorrect.getEmail(), beheerderTest.getEmail());
        Assert.assertEquals(beheerderCorrect.getNaam(), beheerderTest.getNaam());
        Assert.assertEquals(beheerderCorrect.getVoornaam(), beheerderTest.getVoornaam());
    }

    // Materiaal testen
    @Test
    public void geefMaterialenTerug() {
        Assert.assertEquals(domeinController.geefAlleMaterialen().size(), 5);
    }

    @Test
    public void verwijderMateriaal() {
        int i = domeinController.geefAlleMaterialen().size();
        Materiaal m = new Materiaal("Dobbelsteen");
        domeinController.verwijderMateriaal(m);
        Assert.assertEquals(domeinController.geefAlleMaterialen().size(), i - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verwijderMateriaalFoutief() {
        int i = domeinController.geefAlleMaterialen().size();
        Materiaal m = new Materiaal("FoutMateriaal");
        domeinController.verwijderMateriaal(m);
    }

    @Test
    public void wijzigMateriaal() {
        Materiaal oudMateriaal = new Materiaal("oudMateriaal");
        Materiaal nieuwMateriaal = new Materiaal("nieuwMateriaal");
        Boolean bezitOud = false;
        Boolean bezitNieuw = false;
        domeinController.voegMateriaalToe(oudMateriaal.getNaam(), "", 0, 0, 0, true, "", "", "", new ArrayList<>(), new ArrayList<>(), "");
        for (Materiaal m : domeinController.geefAlleMaterialen()) {
            if (nieuwMateriaal.getNaam().equalsIgnoreCase(m.getNaam())) {
                bezitOud = true;
            }
        }
        domeinController.wijzigMateriaal(oudMateriaal, nieuwMateriaal.getNaam(), "", 0, 0, 0, true, "", "", "", new ArrayList<>(), new ArrayList<>(), "");
        for (Materiaal m : domeinController.geefAlleMaterialen()) {
            if (oudMateriaal.getNaam().equalsIgnoreCase(m.getNaam())) {
                bezitNieuw = true;
            }
        }
        Assert.assertEquals(bezitOud, bezitNieuw);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wijzigMateriaalFoutief() {
        Materiaal oudMateriaal = new Materiaal("oudMateriaal");
        Materiaal nieuwMateriaal = new Materiaal("nieuwMateriaal");
        domeinController.wijzigMateriaal(oudMateriaal, nieuwMateriaal.getNaam(), "", 0, 0, 0, true, "", "", "", null, null, "");
    }

    //Reservaties testen
    @Test
    public void geefReservatiesTerug() {
        Assert.assertEquals(domeinController.geefAlleReservaties().size(), 4);
    }

    //Beheerder testen
    @Test
    public void geefBeheedersTerug() {
        Assert.assertEquals(domeinController.geefAlleBeheerders().size(), 3);
    }

    @Test
    public void voegBeheerderToeCorrect() {
        int i = domeinController.geefAlleBeheerders().size();
        domeinController.voegBeheerToe("docent@hogent.be", "beheerder");
        Assert.assertEquals(domeinController.geefAlleBeheerders().size(), i + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void voegBeheerderToeEmailBestaatReeds() {
        domeinController.voegBeheerToe("kas.dedurpel.s3007@student.hogent.be", "beheerder");
    }

    @Test(expected = IllegalArgumentException.class)
    public void voegBeheerderToeTestReguliereExpressie() {
        domeinController.voegBeheerToe("dfqsdfsdq", "beheerder");
    }

    @Test(expected = IllegalArgumentException.class)
    public void voegFoutiefTypeToe() {
        domeinController.voegBeheerToe("docent@hogent.be", "fout123");
    }
}