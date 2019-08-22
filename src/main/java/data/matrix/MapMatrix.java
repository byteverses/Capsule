package data.matrix;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MapMatrix<X, Y, V> implements Matrix<X, Y, V> {
    
    private final Map<X, Map<Y, V>> data = new HashMap<>();
    
    @Override
    public Matrix<X, Y, V> slice(Collection<X> rows, Collection<Y> cols) {
        MapMatrix<X, Y, V> sliceMatrix = new MapMatrix<>();
        
        //TODO: add index range check.
        HashSet<X> rowsSet = new HashSet<>(rows);
        HashSet<Y> colsSet = new HashSet<>(cols);
        
        for(Map.Entry<X, Map<Y, V>> entry : data.entrySet()) {
            if(rowsSet.contains(entry.getKey())) {
                for(Map.Entry<Y, V> colEntry : entry.getValue().entrySet()) {
                    if(colsSet.contains(colEntry.getKey())) {
                        sliceMatrix.putValue(entry.getKey(), colEntry.getKey(), colEntry.getValue());
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
