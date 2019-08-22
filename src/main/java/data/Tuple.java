package data;

import java.util.Objects;

public final class Tuple<X, Y> {
    private final X x;
    private final Y y;
    
    private int hash;
    
    public Tuple(X key, Y value) {
        this.x = key;
        this.y = value;
    }
    
    public X getX() {
        return x;
    }
    
    public Y getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(x, tuple.x) && Objects.equals(y, tuple.y);
    }
    
    @Override
    public int hashCode() {
        int h = hash;
        if(h == 0) {
            h = Objects.hash(x, y);
            hash = h;
        }
        return h;
    }
    
    @Override
    public String toString() {
        return "[" + x + ", " + y + ']';
    }
}
