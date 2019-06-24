package info.projekt.jonas.util;

import java.util.ArrayList;

public class LimitedArrayList<T> extends ArrayList<T> {

    private int size;

    public LimitedArrayList(int size) {
        this.size = size;
    }

    @Override
    public boolean add(Object o) {
        if (size() < size) super.add((T) o);
        else throw new ArrayIndexOutOfBoundsException(size() + 1 + " is out of size " + size());
        return true;
    }
}

