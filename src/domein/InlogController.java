/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import repository.BeheerderDaoJpa;

/**
 *
 * @author Robin Van den Broeck
 */
public class InlogController {

    private BeheerderDaoJpa repo;

    public InlogController() throws ParseException {

        this.repo = new BeheerderDaoJpa();
        repo.startTransaction();
        
        Beheerder robin = new Beheerder(null, "Van den Broeck", null, null, "Robin", "robin.vandenbroeck.v7375@student.hogent.be", true);
        Beheerder kas = new Beheerder(null, "De Durpel", null, null, "Kas", "kas.dedurpel.s3007@student.hogent.be", true);
        Beheerder seba = new Beheerder(null, "Kesteloot", null, null, "Seba", "sebastiaanwillem.kesteloot.v5118@student.hogent.be", true);
        repo.insert(kas);
        repo.insert(robin);
        repo.insert(seba);
        
        repo.commitTransaction();
    }

    public Beheerder meldAan(String email, String wachtwoord) throws JSONException {
        if (email == null || wachtwoord == null) {
            throw new IllegalArgumentException("Gebruikersnaam en wachtwoord moeten worden ingevuld.");
        }
        Beheerder beheerder = new Beheerder();
        StringBuilder sb = null;
        try {

            List<Beheerder> beheerders = repo.getAlleBeheerders();

            if (controlleerBeheerder(beheerders, email)) {
                throw new IllegalArgumentException("Gebruikersnaam is verkeerd ingevuld.");
            }

            String hash = sha256(wachtwoord);
            HttpURLConnection connection = (HttpURLConnection) new URL("https://studservice.hogent.be/auth/" + email + "/" + hash).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.setConnectTimeout(7000);
            connection.setReadTimeout(7000);
            connection.connect();
            int status = connection.getResponseCode();

            switch (status) {
                case 200:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    connection.disconnect();
            }
            String json = sb.toString();
            JSONObject object = new JSONObject(json);
            String emai = object.getString("EMAIL");
            String fm = object.getString("NAAM");
            String vm = object.getString("VOORNAAM");
            beheerder.setEmail(emai);
            beheerder.setNaam(fm);
            beheerder.setVoornaam(vm);
            for (Beheerder b : repo.getAlleBeheerders()) {
                if (b.getEmail().equalsIgnoreCase(emai)) {
                    if (b.isIsHoofdbeheerder()) {
                        beheerder.setIsHoofdbeheerder(true);
                    } else {
                        beheerder.setIsHoofdbeheerder(false);
                    }
                    break;
                }
            }

            return beheerder;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new IllegalArgumentException("Gelieve een correct wachtwoord in te geven");
        }

        return beheerder;

    }

    public String sha256(String wachtwoord) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(wachtwoord.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean controlleerBeheerder(List<Beheerder> beheerders, String email) {
        for (Beheerder b : beheerders) {
            if (b.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }
}