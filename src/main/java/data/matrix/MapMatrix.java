package data.matrix;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapMatrix<X, Y, V> implements Matrix<X, Y, V> {
    
    private final Map<X, Map<Y, V>> data = new HashMap<>();
    
    @Override
    public Matrix<X, Y, V> slice(Collection<X> rows, Collection<Y> cols) {
        MapMatrix<X, Y, V> sliceMatrix = new MapMatrix<>();
        
        //TODO: add index range check.
        
        for(X row : rows) {
            Map<Y, V> yvMap = this.data.get(row);
            if(yvMap != null) {
                for(Y col : cols) {
                    V v = yvMap.get(col);
                    if(v != null) {
                        sliceMatrix.putValue(row, col, v);
                    }
                }
            }
        }
        
        return sliceMatrix;
    }
    
    @Override
    public Matrix<Y, X, V> transpose() {
        MapMatrix<Y, X, V> transposeMatrix = new MapMatrix<>();
        for(Map.Entry<X, Map<Y, V>> mapEntry : data.entrySet()) {
            for(Map.Entry<Y, V> entry : mapEntry.getValue().entrySet()) {
                transposeMatrix.putValue(entry.getKey(), mapEntry.getKey(), entry.getValue());
            }
        }
        return transposeMatrix;
    }
    
    @Override
    public V putValue(X row, Y col, V value) {
        return data.computeIfAbsent(row, x -> new HashMap<>()).put(col, value);
    }
    
    @Override
    public V getValue(X x, Y y) {
        return data.getOrDefault(x, new HashMap<>()).get(y);
    }
    
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
    
    @Override
    public int totalSize() {
        return data.values().stream().mapToInt(Map::size).sum();
    }
    
    @Override
    public void clear() {
        data.clear();
    }
}
