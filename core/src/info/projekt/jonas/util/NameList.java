package info.projekt.jonas.util;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.dwellers.Dweller.GENDER;
import info.projekt.jonas.dwellers.WeightedRandom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NameList {

    private ArrayList<Dweller> dwellersMale = new ArrayList<>();
    private ArrayList<Dweller> dwellersFemale = new ArrayList<>();

    public NameList() {
        try {
            URL url = new URL("https://uinames.com/api?region=United%20States&minlen=4&maxlen=10&gender=male&amount=100");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            JsonArray object = JsonArray.readFrom(content.toString());
            for (JsonValue value : object.asArray()) {
                JsonObject obj = value.asObject();
                dwellersMale.add(new Dweller(obj.get("name").asString(), obj.get("surname").asString(), GENDER.MALE, WeightedRandom.newInt(), WeightedRandom.newInt(), WeightedRandom.newInt(), WeightedRandom.newInt()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            URL url = new URL("https://uinames.com/api?region=United%20States&minlen=4&maxlen=10&gender=female&amount=100");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            JsonArray object = JsonArray.readFrom(content.toString());
            for (JsonValue value : object.asArray()) {
                JsonObject obj = value.asObject();
                dwellersFemale.add(new Dweller(obj.get("name").asString(), obj.get("surname").asString(), GENDER.FEMALE, WeightedRandom.newInt(), WeightedRandom.newInt(), WeightedRandom.newInt(), WeightedRandom.newInt()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dweller nextDweller(GENDER gender) {
        if (gender == GENDER.MALE) {
            Dweller dweller = dwellersMale.get(0);
            dwellersMale.remove(dweller);
            return dweller;
        } else {
            Dweller dweller = dwellersFemale.get(0);
            dwellersFemale.remove(dweller);
            return dweller;
        }
    }
}
