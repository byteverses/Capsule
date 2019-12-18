package algorithm.sort;

import static util.ArrayUtil.swap;

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
                swap(nums, left, right);
            }
        }
        nums[start] = nums[left];
        nums[left] = mark;

        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }

    public static void quickSort(double[] nums, int start, int end) {
        if(start > end) {
            return;
        }

        int left = start;
        int right = end;
        double mark = nums[start];

        while(left < right) {
            while(left < right && nums[right] >= mark) {
                right --;
            }
            while(left < right && nums[left] <= mark) {
                left ++;
            }
            if(left < right) {
                swap(nums, left, right);
            }
        }
        nums[start] = nums[left];
        nums[left] = mark;

        quickSort(nums, start, left -1);
        quickSort(nums, left + 1, end);
    }
}
