package info.projekt.jonas.util;

public class Tuple<V, K> {

    private  V v;
    private  K k;

    public Tuple(V v, K k) {
        this.v = v;
        this.k = k;
    }

    public V getOne() { return v; }

    public K getTwo() { return k; }

    public Object[] asArray() {
        return new Object[]{v, k};
    }
}


