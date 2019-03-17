package data;

import java.util.Objects;

public final class Tuple<K, V> {
    private final K k;
    private final V v;
    
    public Tuple(K key, V value) {
        this.k = key;
        this.v = value;
    }
    
    public K getK() {
        return k;
    }
    
    public V getV() {
        return v;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(k, tuple.k) && Objects.equals(v, tuple.v);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(k, v);
    }
    
    @Override
    public String toString() {
        return "[" + k + ":" + v + ']';
    }
}
