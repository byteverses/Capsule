package data.matrix;

/**
 *
 ***********************************************************************************************************************
 *
 *
 *    |
 *    |   /
 *  y |  / z
 *    | /
 *    |/_______
 *           x
 *
 ***********************************************************************************************************************
 */
public interface Cube<X, Y, Z, V> {
    
    V getValue(X x, Y y, Z z);
    
    boolean isEmpty();
    
    int size();
    
    @Override
    boolean equals(Object obj);
    
    @Override
    int hashCode();
    
    interface Element<X, Y, Z, V> {
        X getX();
        
        Y getY();
        
        Z getZ();
        
        V getValue();
        
        @Override
        boolean equals(Object obj);
        
        @Override
        int hashCode();
    }
}
