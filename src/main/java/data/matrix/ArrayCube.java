package data.matrix;

import util.MapUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ArrayCube<X, Y, Z, V> implements Cube<X, Y, Z, V> {
    
    private final LinkedList<X> xs;
    private final LinkedList<Y> ys;
    private final LinkedList<Z> zs;
    
    private final LinkedHashMap<X, Integer> xIdxes;
    private final LinkedHashMap<Y, Integer> yIdxes;
    private final LinkedHashMap<Z, Integer> zIdxes;
    
    private final V[][][] data;
    
    public ArrayCube(Iterable<X> xIterable, Iterable<Y> yIterable, Iterable<Z> zIterable) {
        Objects.requireNonNull(xIterable);
        Objects.requireNonNull(yIterable);
        Objects.requireNonNull(zIterable);
        xs = StreamSupport.stream(xIterable.spliterator(), false).collect(Collectors.toCollection(LinkedList::new));
        ys = StreamSupport.stream(yIterable.spliterator(), false).collect(Collectors.toCollection(LinkedList::new));
        zs = StreamSupport.stream(zIterable.spliterator(), false).collect(Collectors.toCollection(LinkedList::new));
        xIdxes = MapUtil.mapIndex(xIterable, LinkedHashMap::new);
        yIdxes = MapUtil.mapIndex(yIterable, LinkedHashMap::new);
        zIdxes = MapUtil.mapIndex(zIterable, LinkedHashMap::new);
        
        @SuppressWarnings("unchecked")
        V[][][] tmp = (V[][][]) new Object[xs.size()][ys.size()][zs.size()];
        data = tmp;
    }
    
    @Override
    public V putValue(X x, Y y, Z z, V value) {
        
        //TODO: add index range check.
        Integer xIdx = xIdxes.get(x);
        Integer yIdx = yIdxes.get(y);
        Integer zIdx = zIdxes.get(z);
        V oldValue = data[xIdx][yIdx][zIdx];
        data[xIdx][yIdx][zIdx] = value;
        
        return oldValue;
    }
    
    @Override
    public V getValue(X x, Y y, Z z) {
        Integer xIdx;
        Integer yIdx;
        Integer zIdx;
        return ((xIdx = xIdxes.get(x)) == null || (yIdx = yIdxes.get(y)) == null || (zIdx = zIdxes.get(z)) == null)
               ? null : data[xIdx][yIdx][zIdx];
    }
    
    @Override
    public boolean isEmpty() {
        return xs.isEmpty() && ys.isEmpty() && zs.isEmpty();
    }
    
    @Override
    public int size() {
        return xs.size() * ys.size() * zs.size();
    }
}
