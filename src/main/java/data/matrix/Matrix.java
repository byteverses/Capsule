package data.matrix;

public interface Matrix<X, Y, V> {
    
    
    
    V getValue(X x, Y y);
    
    boolean isEmpty();
    
    int size();
    
    interface Element<X, Y, V> {
        X getX();
    
        Y getY();
    
        V getValue();
    
        @Override boolean equals(Object obj);
    
        @Override int hashCode();
    }
}
