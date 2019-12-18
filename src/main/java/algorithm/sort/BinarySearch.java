package algorithm.sort;

public class BinarySearch {

    public static int binarySearch(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
//            int mid = (left+right)/2;
            int mid = left + ((right - left) >> 1);

            if (nums[mid] > target) {
                right = mid - 1;
            }
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }

        return -1;
    }

    public static int binarySearch(double[] nums, double target) {
        int left = 0;
        int right = nums.length;
        while (left <= right) {
//            int mid = left + (right - left) / 2;
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            }
            else if (nums[mid] > target) {
                right = mid - 1;
            }
            else {
                return mid;
            }
        }

        return -1;
    }
}
