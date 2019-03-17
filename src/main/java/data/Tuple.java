package data;

import java.util.Objects;

public final class Tuple<K, V> {
    private final K key;
    private final V value;
    
    public Tuple(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tuple<?, ?> keyValue = (Tuple<?, ?>) o;
        return Objects.equals(key, keyValue.key) && Objects.equals(value, keyValue.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
    
    @Override
    public String toString() {
        return "[" + key + ":" + value + ']';
    }
}
