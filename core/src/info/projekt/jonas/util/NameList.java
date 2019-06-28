package info.projekt.jonas.util;

import com.badlogic.gdx.Gdx;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.dwellers.WeightedRandom;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class NameList {

	private final ArrayList<Dweller> dwellersMale = new ArrayList<>();
	private final ArrayList<Dweller> dwellersFemale = new ArrayList<>();

	public NameList() {
		Scanner male = new Scanner(Gdx.files.internal("Male.txt").read());
		Scanner female = new Scanner(Gdx.files.internal("Female.txt").read());
		while (male.hasNextLine()) {
			String m = male.nextLine();
			String f = female.nextLine();
			dwellersMale.add(new Dweller(m.split(" ")[1], m.split(" ")[0], Dweller.GENDER.MALE, WeightedRandom.newInt(), WeightedRandom.newInt(), WeightedRandom.newInt(), WeightedRandom.newInt()));
			dwellersFemale.add(new Dweller(f.split(" ")[1], f.split(" ")[0], Dweller.GENDER.FEMALE, WeightedRandom.newInt(), WeightedRandom.newInt(), WeightedRandom.newInt(), WeightedRandom.newInt()));
		}
		male.close();
		female.close();
	}

	public Dweller nextDweller(Dweller.GENDER gender) {
		if(gender == Dweller.GENDER.MALE) {
			Dweller d = dwellersMale.get(0);
			dwellersMale.remove(d);
			return d;
		}
		else {
			Dweller d = dwellersFemale.get(0);
			dwellersFemale.remove(d);
			return d;
		}
	}
}
