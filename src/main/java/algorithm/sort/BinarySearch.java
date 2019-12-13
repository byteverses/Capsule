package algorithm.sort;

public class BinarySearch {

    private static int binarySearch(int[] nums, int num) {

        int left = 0;
        int right = nums.length-1;

        while(left <= right) {
//            int mid = (left+right)/2;
            int mid = left + ((right-left)>>1);

            if(nums[mid] > num) {
                right = mid - 1;
            }
            else if(nums[mid]< num) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }

        return -1;
    }

}
