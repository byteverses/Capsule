package data.matrix.primitive;

import data.type.IntType;

import java.util.Collection;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;

public class IntMatrix extends PrimitiveTypeMatrix<Integer> implements IntType {
    
    private final int[][] data;
    
    public IntMatrix(int rowSize, int colSize) {
        super(rowSize, colSize);
        this.data = new int[rowSize][colSize];
    }
    
    public int getIntValue(int row, int col) {
        return this.data[row][col];
    }
    
    public void putIntValue(int row, int col, int val) {
        this.data[row][col] = val;
    }
    
    @Override
    public Integer getValue(Integer row, Integer col) {
        return this.getIntValue(row, col);
    }
    
    @Override
    public Integer putValue(Integer row, Integer col, Integer newValue) {
        int oldVal = this.data[row][col];
        this.data[row][col] = newValue;
        return oldVal;
    }
    
    @Override
    public IntMatrix slice(Collection<Integer> rows, Collection<Integer> cols) {
        IntMatrix sliceMatrix = new IntMatrix(rows.size(), cols.size());
        for(Integer rowIdx : rows) {
            for(Integer colIdx : cols) {
                sliceMatrix.data[rowIdx][colIdx] = this.data[rowIdx][colIdx];
            }
        }
        return sliceMatrix;
    }
    
    @Override
    public IntMatrix transpose() {
        IntMatrix transposeMatrix = new IntMatrix(this.colSize, this.rowSize);
        for(int i = 0; i < this.rowSize; i++) {
            for(int j = 0; j < this.colSize; j++) {
                transposeMatrix.data[j][i] = this.data[i][j];
            }
        }
        return transposeMatrix;
    }
    
    @Override
    public LongMatrix mapToLong(IntToLongFunction mapper) {
        LongMatrix longMatrix = new LongMatrix(this.rowSize, this.colSize);
        for(int i = 0; i < this.rowSize; i++) {
            for(int j = 0; j < this.colSize; j++) {
                longMatrix.putLongValue(i, j, mapper.applyAsLong(this.data[i][j]));
            }
        }
        return longMatrix;
    }
    
    @Override
    public DoubleMatrix mapToDouble(IntToDoubleFunction mapper) {
        DoubleMatrix doubleMatrix = new DoubleMatrix(this.rowSize, this.colSize);
        for(int i = 0; i < this.rowSize; i++) {
            for(int j = 0; j < this.colSize; j++) {
                doubleMatrix.putDoubleValue(i, j, mapper.applyAsDouble(this.data[i][j]));
            }
        }
        return doubleMatrix;
    }
}
