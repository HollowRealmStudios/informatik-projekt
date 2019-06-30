package info.projekt.jonas.util;

import java.util.ArrayList;

public class LimitedArrayList<T> extends ArrayList<T> {

	private final int size;

	public LimitedArrayList(int size) {
		this.size = size;
	}

	@Override
	public boolean add(T o) {
		if (size() < size) super.add(o);
		else throw new ArrayIndexOutOfBoundsException(size() + 1 + " is out of size " + size());
		return true;
	}
}

