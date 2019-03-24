package data.matrix;

import util.MapUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
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
        this.xs = StreamSupport.stream(xIterable.spliterator(), false)
                               .collect(Collectors.toCollection(LinkedList::new));
        this.ys = StreamSupport.stream(yIterable.spliterator(), false)
                               .collect(Collectors.toCollection(LinkedList::new));
        this.zs = StreamSupport.stream(zIterable.spliterator(), false)
                               .collect(Collectors.toCollection(LinkedList::new));
        this.xIdxes = MapUtil.mapIndex(xIterable, LinkedHashMap::new);
        this.yIdxes = MapUtil.mapIndex(yIterable, LinkedHashMap::new);
        this.zIdxes = MapUtil.mapIndex(zIterable, LinkedHashMap::new);
        
        @SuppressWarnings("unchecked")
        V[][][] tmp = (V[][][]) new Object[this.xs.size()][this.ys.size()][this.zs.size()];
        this.data = tmp;
    }
    
    @Override
    public V putValue(X x, Y y, Z z, V value) {
        
        //TODO: add index range check.
        Integer xIdx = this.xIdxes.get(x);
        Integer yIdx = this.yIdxes.get(y);
        Integer zIdx = this.zIdxes.get(z);
        V oldValue = this.data[xIdx][yIdx][zIdx];
        this.data[xIdx][yIdx][zIdx] = value;
        
        return oldValue;
    }
    
    @Override
    public V getValue(X x, Y y, Z z) {
        Integer xIdx;
        Integer yIdx;
        Integer zIdx;
        return ((xIdx = xIdxes.get(x)) == null || (yIdx = yIdxes.get(y)) == null || (zIdx = zIdxes.get(z)) == null)
               ? null
               : this.data[xIdx][yIdx][zIdx];
    }
    
    @Override
    public boolean isEmpty() {
        return this.xs.isEmpty() && this.ys.isEmpty() && this.zs.isEmpty();
    }
    
    @Override
    public int size() {
        return xs.size() * ys.size() * zs.size();
    }
}
