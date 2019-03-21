package data.matrix;

public interface Matrix<X, Y, V> {
    
    V putValue(X row, Y col, V value);
    
    V getValue(X x, Y y);
    
    boolean isEmpty();
    
    int size();
    
    void clear();
    
    @Override boolean equals(Object obj);
    
    @Override int hashCode();
    
    interface Element<X, Y, V> {
        X getX();
    
        Y getY();
    
        V getValue();
    
        @Override boolean equals(Object obj);
    
        @Override int hashCode();
    }
}
