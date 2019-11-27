package math;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FibonacciNumberTest {
    
    final long[] FIBONACCI_SEQ = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597};
    final FibonacciNumber fibonacciNumber = new FibonacciNumber();
    
    @Test
    public void recursivelyResolve() {
        for(int i = 0; i < FIBONACCI_SEQ.length; i++) {
            Assert.assertEquals(FIBONACCI_SEQ[i], fibonacciNumber.recursivelyResolve(i));
        }
    }
    
    @Test
    public void recursivelyResolveCached() {
        for(int i = 0; i < FIBONACCI_SEQ.length; i++) {
            Assert.assertEquals(FIBONACCI_SEQ[i], fibonacciNumber.recursivelyCacheResolve(i));
        }
    }
    
    @Test
    public void iterativelyResolve() {
        for(int i = 0; i < FIBONACCI_SEQ.length; i++) {
            Assert.assertEquals(FIBONACCI_SEQ[i], fibonacciNumber.iterativelyResolve(i));
        }
    }
    
    @Test
    public void formulaResolve() {
        for(int i = 0; i < FIBONACCI_SEQ.length; i++) {
            Assert.assertEquals(FIBONACCI_SEQ[i], fibonacciNumber.formulaResolve(i));
        }
    }
    
    @Test
    public void matrixFormulaResolve() {
        for(int i = 0; i < FIBONACCI_SEQ.length; i++) {
            Assert.assertEquals(FIBONACCI_SEQ[i], fibonacciNumber.matrixFormulaResolve(i));
        }
    }
    
    @Test
    public void test() {
        for(int num = 0; num < 50; num++) {
            long recursivelyResolve = fibonacciNumber.recursivelyCacheResolve(num);
            
            long iterativelyResolve = fibonacciNumber.iterativelyResolve(num);
            
            long formulaResolve = fibonacciNumber.formulaResolve(num);
            
            long matrixFormulaResolve = fibonacciNumber.matrixFormulaResolve(num);
            
            long[] results = {recursivelyResolve, iterativelyResolve, formulaResolve, matrixFormulaResolve};
            
            Assert.assertArrayEquals(results, Arrays.stream(results).sorted().toArray());
            Assert.assertEquals(Arrays.stream(results).min(), Arrays.stream(results).max());
        }
    }
}