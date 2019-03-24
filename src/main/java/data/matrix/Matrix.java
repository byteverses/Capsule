package data.matrix;

import java.util.Collection;

public interface Matrix<X, Y, V> {
    
    Matrix<X, Y, V> slice(Collection<X> rows, Collection<Y> cols);
    
    Matrix<Y, X, V> transpose();
    
    /**
     *
     * @return oldValue
     */
    V putValue(X row, Y col, V newValue);
    
    V getValue(X x, Y y);
    
    boolean isEmpty();
    
    int size();
    
    void clear();
    
    @Override
    boolean equals(Object obj);
    
    @Override
    int hashCode();
    
    interface Element<X, Y, V> {
        X getX();
        
        Y getY();
        
        V getValue();
        
        @Override
        boolean equals(Object obj);
        
        @Override
        int hashCode();
    }
}
