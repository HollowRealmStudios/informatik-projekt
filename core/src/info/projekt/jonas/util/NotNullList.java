package info.projekt.jonas.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class NotNullList<T> extends ArrayList {

	@Override
	public boolean add(Object o) {
		if(o != null)
		return super.add(o);
		else throw new IllegalArgumentException("Null is not allowed in a NotNullList");
	}

	@Override
	public boolean addAll(Collection c) {
		if(c.stream().anyMatch(Objects::isNull)) throw new IllegalArgumentException("Null is not allowed in a NotNullList");
		return super.addAll(c);
	}
}
