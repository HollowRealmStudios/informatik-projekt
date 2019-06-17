package info.projekt.jonas.dwellers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.eclipsesource.json.JsonObject;
import com.google.common.base.CharMatcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jonas
 */
public class Dweller implements Serializable {

	enum GENDER {MALE, FEMALE}

	private transient Texture texture;
	private GENDER gender;
	private String name;
	private String surname;
	private final String completeName;
	private int strength;
	private int intelligence;
	private int charisma;
	private int creativity;

	public Dweller(String name, String surname, GENDER gender, int strength, int intelligence, int charisma, int creativity) {
		this.name = name;
		this.surname = surname;
		this.completeName = surname + "," + name;
		this.gender = gender;
		this.strength = MathUtils.clamp(strength, 0, 10);
		this.intelligence = MathUtils.clamp(intelligence, 0, 10);
		this.charisma = MathUtils.clamp(charisma, 0, 10);
		this.creativity = MathUtils.clamp(creativity, 0, 10);
	}

	public Dweller() {
		gender = ThreadLocalRandom.current().nextBoolean() ? gender = GENDER.FEMALE : GENDER.MALE;
		String name = getName();
		while (!CharMatcher.ascii().matchesAllOf(Objects.requireNonNull(name))) {
			name = getName();
		}
		parseName(name);
		this.completeName = surname + "," + name;
		Random random = new Random();
		strength = WeightedRandom.newInt();
		intelligence = WeightedRandom.newInt();
		charisma = WeightedRandom.newInt();
		creativity = WeightedRandom.newInt();
	}

	public Texture getTexture() {
		if(texture == null) texture = new Texture(gender == GENDER.MALE ? "Male.png" : "Female.png");
		return texture;
	}

	private String getName() {
		try {
			URL url = new URL("https://uinames.com/api/?minlen=4&maxlen=10?gender=" + (gender == GENDER.FEMALE ? "female" : "male"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			return content.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void parseName(String name) {
		if (name != null) {
			JsonObject object = JsonObject.readFrom(name);
			this.surname = object.get("surname").asString();
			this.name = object.get("name").asString();
		} else {
			this.surname = "Marcel";
			this.name = "Davis";
		}
	}

	public int getStrength() {
		return strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getCharisma() {
		return charisma;
	}

	public int getCreativity() {
		return creativity;
	}

	@Override
	public String toString() {
		return name + ", " + surname + " | " + strength + " | " + intelligence + " | " + charisma + " | " + creativity;
	}
}
