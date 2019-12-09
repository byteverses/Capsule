package algorithm.sort;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }

        int left = start;
        int right = end;
        int mark = nums[start];

        while (left < right) {

            while (left < right && nums[right] >= mark) {
                right--;
            }
            while (left < right && nums[left] <= mark) {
                left++;
            }
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }

        nums[start] = nums[left];
        nums[left] = mark;

        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        quickSort(nums, 0, nums.length - 1);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }
}
