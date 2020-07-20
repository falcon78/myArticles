package Chapter1.Section4;

import java.util.Arrays;

public class BinarySearchArray {
    int[] items;

    public BinarySearchArray(int[] items) {
        this.items = new int[items.length];
        System.arraycopy(items, 0, this.items, 0, items.length);
        Arrays.sort(this.items);
    }

    /**
     * Return the index of an given key in array.
     *
     * @param key The value of item to search.
     * @return index of a given item in array.
     */
    public int rank(int key) {
        int lo = 0;
        int hi = items.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (items[mid] > key)
                hi = mid - 1;
            else if (items[mid] < key)
                lo = mid + 1;
            else
                return mid;
        }

        return -1;
    }

    /**
     * Returns the number of occurrence for a given key in array.
     * Exercise 1.4.11
     *
     * @param key Number to count
     * @return Number of occurrence for a given key, -1 if it doesn't exist.
     */
    public int count(int key) {
        int last = lastIndexOf(key);
        int first = firstIndexOf(key);
        if (first < 0 || last < 0) return -1;
        return (last - first) + 1;
    }

    /**
     * Find the first index of an element in an sorted array.
     * Also answer to Exercise 1.4.10
     *
     * @param key Number to search for.
     * @return first index of the search number, -1 if it does't exists.
     */
    public int firstIndexOf(int key) {
        int lo = 0;
        int hi = items.length - 1;
        int result = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (items[mid] > key)
                hi = mid - 1;
            else if (items[mid] < key)
                lo = mid + 1;
            else {
                result = mid;
                hi = mid - 1;
            }
        }
        return result;
    }

    /**
     * Find the last index of an element in an sorted array.
     *
     * @param key Number to search for.
     * @return last index of the search number, -1 if it does't exists.
     */
    public int lastIndexOf(int key) {
        int lo = 0;
        int hi = items.length - 1;
        int result = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (items[mid] > key)
                hi = mid - 1;
            else if (items[mid] < key)
                lo = mid + 1;
            else {
                result = mid;
                lo = mid + 1;
            }
        }
        return result;
    }
}
