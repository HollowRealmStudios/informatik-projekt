package info.projekt.jonas;

import com.badlogic.gdx.math.MathUtils;
import com.google.common.base.CharMatcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Dweller {

    enum GENDER {MALE, FEMALE}

    private GENDER gender;
    private String name;
    private int strength;
    private int intelligence;
    private int charisma;
    private int creativity;

    public Dweller(String name, GENDER gender, int strength, int intelligence, int charisma, int creativity) {
        this.name = name;
        this.gender = gender;
        this.strength = MathUtils.clamp(strength, 0, 10);
        this.intelligence = MathUtils.clamp(intelligence, 0, 10);
        this.charisma = MathUtils.clamp(charisma, 0, 10);
        this.creativity = MathUtils.clamp(creativity, 0, 10);
    }

    public Dweller() {
        gender = ThreadLocalRandom.current().nextBoolean() ? gender = GENDER.FEMALE : GENDER.MALE;
        try {
            URL url = new URL("https://uinames.com/api/?gender=" + (gender == GENDER.FEMALE ? "female" : "male"));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            name = content.toString();
        }
        catch (Exception e) { e.printStackTrace(); }
        System.out.println(CharMatcher.ascii().matchesAllOf(name));
        Random random = new Random();
        strength = random.nextInt(11);
        intelligence = random.nextInt(11);
        charisma = random.nextInt(11);
        creativity = random.nextInt(11);
    }

    @Override
    public String toString() {
        return name + " | " + strength + " | " + intelligence + " | " + charisma + " | " + creativity;
    }
}
