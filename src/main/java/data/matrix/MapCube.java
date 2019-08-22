package data.matrix;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapCube<X, Y, Z, V> implements Cube<X, Y, Z, V> {
    
    private final Map<X, Map<Y, Map<Z, V>>> data = new HashMap<>();
    
    @Override
    public V putValue(X x, Y y, Z z, V v) {
        return data.computeIfAbsent(x, key -> new HashMap<>()).computeIfAbsent(y, key -> new HashMap<>()).put(z, v);
    }
    
    @Override
    public V getValue(X x, Y y, Z z) {
        return data.getOrDefault(x, new HashMap<>()).getOrDefault(y, new HashMap<>()).get(z);
    }
    
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
    
    @Override
    public int totalSize() {
        return data.values().stream().map(Map::values).flatMap(Collection::stream).mapToInt(Map::size).sum();
    }
}
