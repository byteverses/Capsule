package math;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewtonRaphsonMethodTest {
    
    public static final double DELTA = 0.000001;
    
    @Test
    public void calcRoot() {
        double squareRoot = NewtonRaphsonMethod.calcRoot(9, 2);
        Assert.assertEquals(3, squareRoot, DELTA);
    
        double cubeRoot = NewtonRaphsonMethod.calcRoot(27, 3);
        Assert.assertEquals(3, cubeRoot, DELTA);
    }
    
    @Test
    public void squareRoot() {
        double squareRoot = NewtonRaphsonMethod.squareRoot(9);
        Assert.assertEquals(3, squareRoot, DELTA);
    }
    
    @Test
    public void cubeRoot() {
        double cubeRoot = NewtonRaphsonMethod.cubeRoot(27);
        Assert.assertEquals(3, cubeRoot, DELTA);
    }
}