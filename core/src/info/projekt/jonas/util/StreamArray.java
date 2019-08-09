package info.projekt.jonas.util;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamArray<T> {

	public final T[] t;

	@Contract(pure = true)
	public StreamArray(T[] t) {
		this.t = t;
	}

	public Stream<T> stream() {
		return Arrays.stream(t);
	}

}
