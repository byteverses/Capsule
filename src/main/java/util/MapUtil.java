package util;

import java.util.Map;
import java.util.function.Supplier;

public class MapUtil {
    
    private MapUtil(){}
    
    public static <E, R extends Map<E, Integer>> R mapIndex(Iterable<E> iterable, Supplier<R> mapSupplier) {
        R indexMap = mapSupplier.get();
        int idx = 0;
        for(E element : iterable) {
            indexMap.put(element, idx++);
        }
        return indexMap;
    }
    
    public static <E, R extends Map<Integer, E>> R indexMap(Iterable<E> iterable, Supplier<R> mapSupplier) {
        R mapIndex = mapSupplier.get();
        int idx = 0;
        for(E element : iterable) {
            mapIndex.put(idx++, element);
        }
        return mapIndex;
    }
}
