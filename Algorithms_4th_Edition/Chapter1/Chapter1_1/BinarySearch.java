package Chapter1.Chapter1_1;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 2434, 54, 34, 65, 3, 4, 65, 34, 6, 54, 6, 3, 7, 34, 6, 3333, 646, 34, 3};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        int foundIndex = rank(array, 3, 0, array.length - 1);
        System.out.println("Found at Index: " + foundIndex);

        System.out.println("smaller than count: " + smallerThanKey(array, 3, 0, array.length - 1));
        System.out.println("bigger than count: " + biggerThanKey(array, 3, 0, array.length - 1));
        System.out.println("element count: " + count(array, 3));
    }

    /*
    Returns the index of given array where the elements value is same as key
     */
    public static int rank(int[] array, int key, int lo, int hi) {
        if (lo >= hi) return -1;

        int mid = lo + (hi - lo) / 2;
        if (array[mid] > key)
            return rank(array, key, lo, mid - 1);
        if (array[mid] < key)
            return rank(array, key, mid + 1, hi);
        return mid;
    }

    /*
    Returns of number of elements in an array that are smaller than key
     */
    static int smallerThanKey(int[] arr, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] > key)
                hi = mid - 1;
            else if (arr[mid] < key)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return lo;
    }

    /*
    Returns of number of elements in an array that are bigger than key
     */
    static int biggerThanKey(int[] arr, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] > key)
                hi = mid - 1;
            else if (arr[mid] < key)
                lo = mid + 1;
            else
                lo = mid + 1;
        }
        return arr.length - hi - 1;
    }

    /*
    Count the number of times an given number appears in an array
     */
    static int count(int[] arr, int key) {
        int smallerThan = smallerThanKey(arr, key, 0, arr.length - 1);
        int biggerThan = biggerThanKey(arr, key, 0, arr.length - 1);
        return arr.length - smallerThan - biggerThan;
    }

}
