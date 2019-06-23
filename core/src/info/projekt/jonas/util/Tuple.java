package info.projekt.jonas.util;

import java.io.Serializable;

public class Tuple<V, K> implements Serializable {

	private V v;
	private K k;

	public Tuple(V v, K k) {
		this.v = v;
		this.k = k;
	}

	public Tuple() {
	}

	public void setOne(V one) {
		v = one;
	}

	public void setTwo(K two) {
		k = two;
	}

	public V getOne() {
		return v;
	}

	public K getTwo() {
		return k;
	}

	public Object[] asArray() {
		return new Object[]{v, k};
	}
}


