package info.projekt.jonas.util;

import info.projekt.jonas.dweller.Dweller;

import java.util.concurrent.ThreadLocalRandom;

public class Random {

	public static boolean ranBool() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	public static Dweller.GENDER ranGender() {
		return ranBool() ? Dweller.GENDER.MALE : Dweller.GENDER.FEMALE;
	}

}
