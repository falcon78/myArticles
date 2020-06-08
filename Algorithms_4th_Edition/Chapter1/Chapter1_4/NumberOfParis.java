package Chapter1.Chapter1_4;

import java.util.Arrays;

/**
 * Find out how many numbers of pairs of values are equal in an array
 * in linearithmic time
 */
public class NumberOfParis {
    public static void main(String[] args) {
        System.out.println(count(ReadInt.get(), 260607));
    }

    public static int count(int[] nums, int find) {
        Arrays.sort(nums);
        int last = lastIndexOf(nums, find);
        int first = firstIndexOf(nums, find);
        System.out.println("first" + first);
        System.out.println("last" + last);
        if (first < 0 || last < 0) return -1;
        return (last - first) + 1;
    }

    /**
     * Find the first index of an element in an sorted array.
     *
     * @param nums sorted int array.
     * @param find Number to search for.
     * @return first index of the search number, -1 if it does't exists.
     */
    public static int firstIndexOf(int[] nums, int find) {
        int lo = 0;
        int high = nums.length - 1;
        int result = -1;

        while (lo <= high) {
            int mid = lo + (high - lo) / 2;
            if (nums[mid] > find)
                high = mid - 1;
            else if (nums[mid] < find)
                lo = mid + 1;
            else {
                result = mid;
                high = mid - 1;
            }
        }
        return result;
    }

    /**
     * Find the last index of an element in an sorted array.
     *
     * @param nums sorted int array.
     * @param find Number to search for.
     * @return last index of the search number, -1 if it does't exists.
     */
    public static int lastIndexOf(int[] nums, int find) {
        int lo = 0;
        int high = nums.length - 1;
        int result = -1;

        while (lo <= high) {
            int mid = lo + (high - lo) / 2;
            if (nums[mid] > find)
                high = mid - 1;
            else if (nums[mid] < find)
                lo = mid + 1;
            else {
                result = mid;
                lo = mid + 1;
            }
        }
        return result;
    }
}
