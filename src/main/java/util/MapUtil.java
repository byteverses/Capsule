package util;

import java.util.Map;
import java.util.function.Supplier;

public class MapUtil {
    
    public static <E, R extends Map<E, Integer>> R IndexMap(Iterable<E> iterable, Supplier<R> mapSupplier) {
        R indexMap = mapSupplier.get();
        int idx = 0;
        for(E element : iterable) {
            indexMap.put(element, idx++);
        }
        return indexMap;
    }
}
