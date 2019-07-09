package info.projekt.jonas.util;

import java.util.HashMap;

public class LimitedHashMap<V, K> extends HashMap {

	private int size;

	public LimitedHashMap(int size) {
		this.size = size;
	}

	@Override
	public Object put(Object key, Object value) {
		if(size() > size) throw new ArrayIndexOutOfBoundsException((size() + 1) + " is out of size " + size());
		return super.put(key, value);
	}

	public boolean isSpace() {
		return size() + 1 <= size;
	}
}
