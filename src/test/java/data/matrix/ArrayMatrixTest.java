package data.matrix;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayMatrixTest {
    
    private ArrayMatrix<Integer, String, Double> arrayMatrix;
    private List<Integer> rows;
    private List<String> cols;
    
    private ArrayMatrix<String, Integer, Double> transposMatrix;
    
    @Before public void init() {
        rows = Lists.newArrayList(1, 2, 3, 4, 5);
        cols = Lists.newArrayList("A", "B", "C", "D", "E");
        this.arrayMatrix = new ArrayMatrix<>(rows, cols);
        
        this.transposMatrix = new ArrayMatrix<>(cols, rows);
    }
    
    @Test public void sliceByIdx() {
    }
    
    @Test public void sliceByRowCol() {
    }
    
    @Test public void transpose() {
    
    
    }
    
    @Test public void getValue() {
    }
    
    @Test public void isEmpty() {
        Assert.assertTrue(new ArrayMatrix<>(new ArrayList<>(), new ArrayList<>()).isEmpty());
    }
    
    @Test public void size() {
        Assert.assertTrue(arrayMatrix.totalSize() == rows.size() * cols.size());
    }
}