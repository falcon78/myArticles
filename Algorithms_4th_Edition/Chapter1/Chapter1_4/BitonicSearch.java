package Chapter1.Chapter1_4;

/**
 * Exercise 1.4.20
 * Bitonic Search - O(3logN)
 * Search for an element in a bitonic array.
 * TODO: Write an improved O(2logN) solution.
 */
public class BitonicSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 2, 4, 6, 8, 7, 5, 3, 1};
        assert search(arr, 0) == 0;
        assert search(arr, 1) == 8;
        assert search(arr, 6) == 3;
        assert search(arr, 8) == 4;

        assert search(new int[]{22}, 1) == -1;
        assert search(new int[]{22}, 1) == -1;

        int[] arr2 = new int[]{0, 2, 4, 6, 8, 9, 5, 3, 2, 1};
        assert search(arr2, 0) == 0;
        assert search(arr2, 1) == 9;
        assert search(arr2, 6) == 3;
        assert search(arr2, 8) == 4;
        assert search(arr2, 8) == 4;
        assert search(arr2, 1) == 9;
    }

    public static int search(int[] arr, int key) {
        if (arr.length == 0) return -1;

        int hi = arr.length - 1;
        int lo = 0;
        while (lo < hi) {
            if (arr[lo] < Math.min(arr.length - 1, arr[lo + 1])) {
                lo++;
            }
            if (arr[hi] < Math.max(0, arr[hi - 1])) {
                hi--;
            }
        }

        int lo1 = 0;
        int hi1 = lo;
        while (lo1 <= hi1) {
            int mid = lo1 + (hi1 - lo1) / 2;
            if (arr[mid] < key) {
                lo1 = mid + 1;
            } else if (arr[mid] > key) {
                hi1 = mid - 1;
            } else {
                return mid;
            }
        }

        int lo2 = lo;
        int hi2 = arr.length - 1;
        while (lo2 <= hi2) {
            int mid = lo2 + (hi2 - lo2) / 2;
            if (arr[mid] < key) {
                hi2 = mid - 1;
            } else if (arr[mid] > key) {
                lo2 = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
