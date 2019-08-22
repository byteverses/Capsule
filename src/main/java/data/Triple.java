package data;

import java.util.Objects;

public final class Triple<X, Y, Z> {
    
    private final X x;
    private final Y y;
    private final Z z;
    
    private int hash;
    
    public Triple(X x, Y y, Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public X getX() {
        return x;
    }
    
    public Y getY() {
        return y;
    }
    
    public Z getZ() {
        return z;
    }
    
    @Override public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Triple<?, ?, ?> triTuple = (Triple<?, ?, ?>) o;
        return Objects.equals(x, triTuple.x) && Objects.equals(y, triTuple.y) && Objects.equals(z, triTuple.z);
    }
    
    @Override public int hashCode() {
        int h = hash;
        if(h == 0) {
            h = Objects.hash(x, y, z);
            hash = h;
        }
        return h;
    }
    
    @Override public String toString() {
        return "[" + x + ", " + y + ", " + z + ']';
    }
}
