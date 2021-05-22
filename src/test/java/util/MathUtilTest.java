package util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathUtilTest {
    
    @Test
    public void testDevsq() {
    
        Assert.assertEquals(MathUtil.devsq(new double[]{1,2,3,4,5,6,7,8,9}), 60.0, 0.0);
    
        double[] array = {5,5,5,5,5};
        Assert.assertEquals(MathUtil.devsq(array), 0.0, 0.0);

        List<Character> characters = Arrays.asList('a', 'b');

    }
}