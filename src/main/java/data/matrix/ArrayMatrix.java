package data.matrix;

import data.Tuple;
import util.MapUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ArrayMatrix<R, C, V> implements Matrix<R, C, V> {
    
    private final LinkedList<R>             rows;
    private final LinkedList<C>             cols;
    private final LinkedHashMap<R, Integer> rowIdxes;
    private final LinkedHashMap<C, Integer> colIdxes;
    private final V[][]                     data;
    
    public ArrayMatrix(Iterable<R> iterableRows, Iterable<C> iterableCols) {
        this.rows = StreamSupport.stream(iterableRows.spliterator(), false)
                                 .collect(Collectors.toCollection(LinkedList::new));
        this.cols = StreamSupport.stream(iterableCols.spliterator(), false)
                                 .collect(Collectors.toCollection(LinkedList::new));
        
        this.data = (V[][]) new Object[this.rows.size()][this.cols.size()];
        
        this.rowIdxes = MapUtil.IndexMap(iterableRows, LinkedHashMap::new);
        this.colIdxes = MapUtil.IndexMap(iterableCols, LinkedHashMap::new);
    }
    
    public ArrayMatrix<R, C, V> slice(Tuple<Integer, Integer> rowIdxRange, Tuple<Integer, Integer> colIdxRange) {
        
        Integer rowStartIdx = rowIdxRange.getX();
        Integer rowEndIdx = rowIdxRange.getY();
        Integer colStartIdx = colIdxRange.getX();
        Integer colEndIdx = colIdxRange.getY();
        
        LinkedList<R> newRowList = new LinkedList<>(rows.subList(rowStartIdx, rowEndIdx));
        LinkedList<C> newColList = new LinkedList<>(cols.subList(rowStartIdx, rowEndIdx));
        
        ArrayMatrix<R, C, V> sliceMatrix = new ArrayMatrix<>(newRowList, newColList);
        
        for(int idx = rowStartIdx; idx < rowEndIdx; idx++) {
            sliceMatrix.data[idx] = (V[]) Arrays.copyOfRange(this.data, colStartIdx, colEndIdx);
        }
        
        return sliceMatrix;
    }
    
    public ArrayMatrix<R, C, V> slice(Collection<R> rows, Collection<C> cols) {
        
        LinkedList<R> newRowList = new LinkedList<>(rows);
        LinkedList<C> newColList = new LinkedList<>(cols);
        
        ArrayMatrix<R, C, V> sliceMatrix = new ArrayMatrix<>(newRowList, newColList);
        
        for(R row : newRowList) {
            Integer rowIdx = this.rowIdxes.get(row);
            for(C col : newColList) {
                Integer colIdx = this.colIdxes.get(col);
                sliceMatrix.data[rowIdx][colIdx] = data[rowIdx][colIdx];
            }
        }
        
        return sliceMatrix;
    }
    
    public ArrayMatrix<C, R, V> transpose() {
        ArrayMatrix<C, R, V> transposeMatrix = new ArrayMatrix<>(new LinkedList<>(this.cols),
                                                                 new LinkedList<>(this.rows));
        for(int i = 0; i < this.rows.size(); i++) {
            for(int j = 0; j < this.cols.size(); j++) {
                transposeMatrix.data[j][i] = this.data[i][j];
            }
        }
        
        return transposeMatrix;
    }
    
    @Override public V putValue(R row, C col, V value) {
        //TODO: add index range check.
        Integer rowIdx = this.rowIdxes.get(row);
        Integer colIdx = this.colIdxes.get(col);
        V oldValue = this.data[rowIdx][colIdx];
        this.data[rowIdx][colIdx] = value;
        
        return oldValue;
    }
    
    @Override public V getValue(R r, C c) {
        Integer rowIdx;
        Integer colIdx;
        return ((rowIdx = this.rowIdxes.get(r)) == null || (colIdx = this.colIdxes.get(c)) == null)
                ? null
                : this.data[rowIdx][colIdx];
    }
    
    @Override public boolean isEmpty() {
        return this.rows.isEmpty() || this.cols.isEmpty();
    }
    
    @Override public int size() {
        return this.rows.size() * this.cols.size();
    }
    
    @Override public void clear() {
        throw new UnsupportedOperationException("ArrayMatrix can't be clear! New an ArrayMatrix instead");
    }
}
