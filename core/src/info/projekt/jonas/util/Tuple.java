package info.projekt.jonas.util;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;

/**
 * @author Jonas
 */
public class Tuple<V, K> implements Serializable {

	private V v;
	private K k;

	@Contract(pure = true)
	public Tuple(V v, K k) {
		this.v = v;
		this.k = k;
	}

	@Contract(pure = true)
	public Tuple() {
	}

	public V getOne() {
		return v;
	}

	public void setOne(V one) {
		v = one;
	}

	public K getTwo() {
		return k;
	}

	public void setTwo(K two) {
		k = two;
	}

}


