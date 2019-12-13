package algorithm.sort;

import util.ArrayUtil;

public class HeapSort {


    public static void ascHeapSort(int[] nums) {
        // adjust max heap from the first non-leaf node
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            maxHeap(nums, nums.length, i);
        }
        // swap the first(max) heap node and the last leaf node.
        // adjust max heap from the first(root) node
        for (int i = nums.length - 1; i > 0; i--) {
            ArrayUtil.swap(nums, 0, i);
            maxHeap(nums, i, 0);
        }
    }

    public static void descHeapSort(int[] nums) {
        // adjust min heap from the first non-leaf node
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            minHeap(nums, nums.length, i);
        }
        // swap the first(max) heap node and the last leaf node.
        // adjust max heap from the first(root) node
        for (int i = nums.length - 1; i > 0; i--) {
            ArrayUtil.swap(nums, 0, i);
            minHeap(nums, i, 0);
        }
    }

    private static void maxHeap(int[] heapNums, int end, int currIdx) {
        int temp = heapNums[currIdx];
        for (int k = 2 * currIdx + 1; k < end; k = 2 * k + 1) {
            if (k + 1 < end && heapNums[k] < heapNums[k + 1]) {
                k++;
            }
            if (heapNums[k] > temp) {
                heapNums[currIdx] = heapNums[k];
                currIdx = k;
            }
            else {
                break;
            }
        }
        heapNums[currIdx] = temp;
    }

    private static void minHeap(int[] heapNums, int end, int currIdx) {
        int temp = heapNums[currIdx];
        for (int k = 2 * currIdx + 1; k < end; k = 2 * k + 1) {
            if (k + 1 < end && heapNums[k] > heapNums[k + 1]) {
                k++;
            }
            if(heapNums[k] < temp) {
                heapNums[currIdx] = heapNums[k];
                currIdx=k;
            }
            else {
                break;
            }
        }
        heapNums[currIdx] = temp;
    }

}
