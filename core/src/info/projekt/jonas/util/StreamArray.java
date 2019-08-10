package info.projekt.jonas.util;

import com.badlogic.gdx.utils.Array;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StreamArray<T> implements Serializable {

	private T[] t;
	private int carriage;

	@Contract(pure = true)
	public StreamArray(T[] t) {
		this.t = t;
	}

	public Stream<T> stream() {
		return Arrays.stream(t).filter(Objects::nonNull);
	}

	public T get(int index) {
		return t[index];
	}

	public void set(int index, @Nullable T t) {
		this.t[index] = t;
	}

	public void add(@Nullable T t) {
		this.t[carriage] = t;
		carriage++;
	}

	public int size() {
		return t.length;
	}

	public int getCarriage() {
		return carriage;
	}
}
