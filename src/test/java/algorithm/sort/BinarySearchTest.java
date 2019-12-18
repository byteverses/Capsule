package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {


    @Test
    public void testIntBinarySearch() {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int bsIdx = BinarySearch.binarySearch(nums, 4);
        System.out.println("bsIdx = " + bsIdx);
        Assert.assertTrue(bsIdx == 4);
    }

    @Test
    public void testDoubleBinarySearch() {
        double[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int bsIdx = BinarySearch.binarySearch(nums, 4.0);
        System.out.println("bsIdx = " + bsIdx);
        Assert.assertTrue(bsIdx == 4);
    }
}