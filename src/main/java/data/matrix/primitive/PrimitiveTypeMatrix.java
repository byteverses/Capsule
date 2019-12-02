package data.matrix.primitive;

import data.matrix.Matrix;

import java.util.Collection;

public abstract class PrimitiveTypeMatrix<V> implements Matrix<Integer, Integer, V> {
    
    final int rowSize;
    final int colSize;
    
    protected PrimitiveTypeMatrix(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
    }
    
    @Override
    public abstract PrimitiveTypeMatrix<V> slice(Collection<Integer> rows, Collection<Integer> cols);
    
    @Override
    public abstract PrimitiveTypeMatrix<V> transpose();
    
    @Override
    public abstract V putValue(Integer row, Integer col, V newValue);
    
    @Override
    public abstract V getValue(Integer row, Integer col);
    
    @Override
    public int totalSize() {
        return this.rowSize * this.colSize;
    }
}
