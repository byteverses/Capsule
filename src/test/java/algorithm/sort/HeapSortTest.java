package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class HeapSortTest {

    @Test
    public void heapSort() {

        int[] nums = {1, 3, 5, 2, 4, 6, 7};
        int[] copy = Arrays.copyOf(nums, nums.length);

        HeapSort.ascHeapSort(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        Arrays.sort(copy);

        Assert.assertTrue(Arrays.equals(nums, copy));

        HeapSort.descHeapSort(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        int[] descCopy = Arrays.stream(copy).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();
        Assert.assertTrue(Arrays.equals(nums, descCopy));

    }

}