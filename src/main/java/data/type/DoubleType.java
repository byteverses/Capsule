package data.type;

import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;

public interface DoubleType extends PrimitiveType<Double> {

    IntType mapToInt(DoubleToIntFunction mapper);

    LongType mapToLong(DoubleToLongFunction mapper);
}
