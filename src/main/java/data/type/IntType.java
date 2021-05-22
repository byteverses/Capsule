package data.type;

import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;

public interface IntType extends PrimitiveType<Integer> {
    
    
    LongType mapToLong(IntToLongFunction mapper);
    
    DoubleType mapToDouble(IntToDoubleFunction mapper);
    
}
