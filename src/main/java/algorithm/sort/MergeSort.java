package algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] nums, int start, int end) {
        if(start >= end) {
            return;
        }
        int mid = (start + end) / 2;

        mergeSort(nums, start, mid);
        mergeSort(nums, mid+1, end);

        merge(nums, start, mid, end);
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end-start+1];

        int i = start;
        int j = mid+1;
        int idx = 0;
        while(i <= mid && j <= end) {
            if(nums[i] < nums[j]) {
                temp[idx++] = nums[i++];
            }
            else {
                temp[idx++] = nums[j++];
            }
        }
        while(i <= mid) {
            temp[idx++] = nums[i++];
        }
        while(j <= end) {
            temp[idx++] = nums[j++];
        }

        System.arraycopy(temp, 0, nums, start, temp.length);
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6,2,8,7};
        mergeSort(nums, 0, nums.length-1);

        System.out.println("nums = " + Arrays.toString(nums));
    }
}
