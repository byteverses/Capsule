package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuickSortTest {

    @Test
    public void testIntQuickSort() {
        int[] nums = {1, 3, 5, 2, 4, 6, 7};
        int[] copy = Arrays.copyOf(nums, nums.length);

        QuickSort.quickSort(nums, 0, nums.length-1);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        Arrays.sort(copy);

        Assert.assertTrue(Arrays.equals(nums, copy));
    }

    @Test
    public void testDoubleQuickSort() {

        double[] nums = {1d, 3d, 5d, 2d, 4d, 6d, 7d};
        double[] copy = Arrays.copyOf(nums, nums.length);

        QuickSort.quickSort(nums, 0, nums.length-1);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        Arrays.sort(copy);

        Assert.assertTrue(Arrays.equals(nums, copy));
    }
}