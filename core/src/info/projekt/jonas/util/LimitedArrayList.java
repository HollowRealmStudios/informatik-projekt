package info.projekt.jonas.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author Jonas
 */
public class LimitedArrayList<T> extends ArrayList<T> implements Serializable {

	private int size;

	public LimitedArrayList(int size) {
		this.size = size;
	}

	public void setSize(int size) {
		if (size() > size)
			throw new IllegalArgumentException("You can't shrink the list below the amount of used capacity");
		this.size = size;
	}

	@Override
	public boolean add(T o) {
		if (size() < size) super.add(o);
		else throw new ArrayIndexOutOfBoundsException((size() + 1) + " is out of size " + size());
		return true;
	}

	public boolean isSpace() {
		return size() + 1 <= size;
	}
}

