package info.projekt.jonas.util;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamArray<T> {

	public T[] t;

	public StreamArray(T[] t) {
		this.t = t;
	}

	public Stream<T> stream() {
		return Arrays.stream(t);
	}

}
