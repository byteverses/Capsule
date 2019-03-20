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
        this.xIdxes = MapUtil.IndexMap(xIterable, LinkedHashMap::new);
        this.yIdxes = MapUtil.IndexMap(yIterable, LinkedHashMap::new);
        this.zIdxes = MapUtil.IndexMap(zIterable, LinkedHashMap::new);
        
        this.data = (V[][][]) new Object[this.xs.size()][this.ys.size()][this.zs.size()];
    }
    
    @Override public V getValue(X x, Y y, Z z) {
        Integer xIdx;
        Integer yIdx;
        Integer zIdx;
        return ((xIdx = xIdxes.get(x)) == null || (yIdx = yIdxes.get(y)) == null || (zIdx = zIdxes.get(z)) == null)
                ? null
                : this.data[xIdx][yIdx][zIdx];
    }
    
    @Override public boolean isEmpty() {
        return this.xs.isEmpty() && this.ys.isEmpty() && this.zs.isEmpty();
    }
    
    @Override public int size() {
        return xs.size() * ys.size() * zs.size();
    }
}
