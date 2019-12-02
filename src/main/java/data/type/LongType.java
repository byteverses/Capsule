package data.type;

import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;

public interface LongType extends PrimitiveType<Long, LongType> {

    IntType mapToInt(LongToIntFunction mapper);

    DoubleType mapToDouble(LongToDoubleFunction mapper);
}
