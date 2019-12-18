package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MergeSortTest {

    @Test
    public void testIntMergeSort() {

        int[] nums = {1, 3, 5, 2, 4, 6, 7};
        int[] copy = Arrays.copyOf(nums, nums.length);

        MergeSort.mergeSort(nums, 0, nums.length - 1);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        Arrays.sort(copy);

        Assert.assertTrue(Arrays.equals(nums, copy));

    }


    @Test
    public void testDoubleMergeSort() {

        double[] nums = {1, 3, 5, 2, 4, 6, 7};
        double[] copy = Arrays.copyOf(nums, nums.length);

        MergeSort.mergeSort(nums, 0, nums.length - 1);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        Arrays.sort(copy);

        Assert.assertTrue(Arrays.equals(nums, copy));
    }

}