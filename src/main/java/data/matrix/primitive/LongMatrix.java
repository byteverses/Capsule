package data.matrix.primitive;

import data.type.LongType;

import java.util.Collection;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;

public class LongMatrix extends PrimitiveTypeMatrix<Long> implements LongType {
    
    private final long[][] data;
    
    public LongMatrix(int rowSize, int colSize) {
        super(rowSize, colSize);
        this.data = new long[rowSize][colSize];
    }

    public long getLongValue(int row, int col) {
        return this.data[row][col];
    }
    
    public void putLongValue(int row, int col, long val) {
        this.data[row][col] = val;
    }

    @Override
    public Long putValue(Integer row, Integer col, Long newValue) {
        return null;
    }

    @Override
    public Long getValue(Integer row, Integer col) {
        return null;
    }

    @Override
    public LongMatrix slice(Collection<Integer> rows, Collection<Integer> cols) {
        LongMatrix sliceMatrix = new LongMatrix(rows.size(), cols.size());
        for(Integer rowIdx : rows) {
            for(Integer colIdx : cols) {
                sliceMatrix.data[rowIdx][colIdx] = this.data[rowIdx][colIdx];
            }
        }
        return sliceMatrix;
    }
    
    @Override
    public LongMatrix transpose() {
        LongMatrix transposeMatrix = new LongMatrix(this.colSize, this.rowSize);
        for(int i = 0; i < this.rowSize; i++) {
            for(int j = 0; j < this.colSize; j++) {
                transposeMatrix.data[j][i] = this.data[i][j];
            }
        }
        return transposeMatrix;
    }
    
    @Override
    public IntMatrix mapToInt(LongToIntFunction mapper) {
        IntMatrix intMatrix = new IntMatrix(this.rowSize, this.colSize);
        for(int i = 0; i < this.rowSize; i++) {
            for(int j = 0; j < this.colSize; j++) {
                intMatrix.putIntValue(i, j, mapper.applyAsInt(this.data[i][j]));
            }
        }
        return intMatrix;
    }

    @Override
    public DoubleMatrix mapToDouble(LongToDoubleFunction mapper) {
        DoubleMatrix doubleMatrix = new DoubleMatrix(this.rowSize, this.colSize);
        for(int i = 0; i < this.rowSize; i++) {
            for(int j = 0; j < this.colSize; j++) {
                doubleMatrix.putDoubleValue(i, j, mapper.applyAsDouble(this.data[i][j]));
            }
        }
        return doubleMatrix;
    }
}
