package data.matrix.primitive;

import data.type.DoubleType;

import java.util.Collection;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;


public class DoubleMatrix extends PrimitiveTypeMatrix<Double> implements DoubleType {
    
    private final double[][] data;
    
    public DoubleMatrix(int rowSize, int colSize) {
        super(rowSize, colSize);
        this.data = new double[rowSize][colSize];
    }
    
    public double getDoubleValue(int row, int col) {
        return this.data[row][col];
    }
    
    public void putDoubleValue(int row, int col, double val) {
        this.data[row][col] = val;
    }
    
    @Override
    public Double getValue(Integer row, Integer col) {
        return this.getDoubleValue(row, col);
    }
    
    @Override
    public Double putValue(Integer row, Integer col, Double newValue) {
        double oldVal = this.data[row][col];
        this.data[row][col] = newValue.doubleValue();
        return oldVal;
    }
    
    @Override
    public DoubleMatrix slice(Collection<Integer> rows, Collection<Integer> cols) {
        DoubleMatrix sliceMatrix = new DoubleMatrix(rows.size(), cols.size());
        for(Integer rowIdx : rows) {
            for(Integer colIdx : cols) {
                sliceMatrix.data[rowIdx][colIdx] = this.data[rowIdx][colIdx];
            }
        }
        return sliceMatrix;
    }
    
    @Override
    public DoubleMatrix transpose() {
        DoubleMatrix transposeMatrix = new DoubleMatrix(this.colSize, this.rowSize);
        for(int i = 0; i < this.rowSize; i++) {
            for(int j = 0; j < this.colSize; j++) {
                transposeMatrix.data[j][i] = this.data[i][j];
            }
        }
        return transposeMatrix;
    }

    @Override
    public IntMatrix mapToInt(DoubleToIntFunction mapper) {
        IntMatrix intMatrix = new IntMatrix(this.rowSize, this.colSize);
        for(int i = 0; i < this.rowSize; i++) {
            for(int j = 0; j < this.colSize; j++) {
                intMatrix.putIntValue(i, j, mapper.applyAsInt(this.data[i][j]));
            }
        }
        return intMatrix;
    }

    @Override
    public LongMatrix mapToLong(DoubleToLongFunction mapper) {
        LongMatrix longMatrix = new LongMatrix(this.rowSize, this.colSize);
        for(int i = 0; i < this.rowSize; i++) {
            for(int j = 0; j < this.colSize; j++) {
                longMatrix.putLongValue(i, j, mapper.applyAsLong(this.data[i][j]));
            }
        }
        return longMatrix;
    }
}
