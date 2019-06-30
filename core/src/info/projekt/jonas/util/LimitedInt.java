package info.projekt.jonas.util;

import java.io.Serializable;

@SuppressWarnings("unused")
public class LimitedInt extends Number implements Serializable {

	private final int lower;
	private final int upper;
	private final boolean overflow;
	private int i;

	public LimitedInt(int lower, int upper, boolean overflow) {
		this.lower = lower;
		this.upper = upper;
		this.overflow = overflow;
	}

	public void add(int amount) {
		i += amount;
		correct();
	}

	public void set(int amount) {
		i = amount;
		correct();
	}

	public void subtract(int amount) {
		i -= amount;
		correct();
	}

	private void correct() {
		if (overflow) {
			if (i < lower) i = (i - lower) * -1;
			if (i > upper) i = (i - upper) * -1;

		} else {
			if (i < lower) i = lower;
			if (i > upper) i = upper;
		}
	}

	public int get() {
		return i;
	}

	@Override
	public int intValue() {
		return 0;
	}

	@Override
	public long longValue() {
		return 0;
	}

	@Override
	public float floatValue() {
		return 0;
	}

	@Override
	public double doubleValue() {
		return 0;
	}
}
