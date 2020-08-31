package Chapter2.Section3;

import Chapter2.Section1.BaseSort;
import Chapter2.Section1.InsertionSort;

/**
 * (This is a algorithm used in java system sort)
 * Quicksort implementation with three way Bentley Mcllroy partition.
 * Three way partition method is efficient if there are lots of duplicate items in array.
 * This algorithm also choose the partitioning element using Turkey ninther method.
 */
public class QuickSortBentleyMcllroy extends BaseSort implements ISorter {
    public static void main(String[] args) {
        QuickSortBentleyMcllroy sort = new QuickSortBentleyMcllroy();
        for (int i = 0; i < 10000; i++)
            sort.sort(unsortedArray((int) (Math.random() * i), 100));
        sort.sort(new Comparable[]{0});
        sort.sort(new Comparable[]{4, 2, 2, 4, 3, 3});
        sort.sort(new Comparable[]{1, 1, 1, 1, 1, 1, 1, 1, 2});
    }

    public void sort(Comparable[] arr) {
        Shuffle(arr);
        sort(arr, 0, arr.length - 1);
        assert isSorted(arr);
    }

    public static void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi)
            return;


        if ((hi - lo) < 10) {
            InsertionSort.sort(arr, lo, hi);
            return;
        }

        int leftEqual = lo, rightEqual = hi + 1;
        int i = lo, j = hi + 1;

        turkeyNintherMedian(arr, lo, hi);
        Comparable pivot = arr[lo];


        while (true) {
            while (i < hi && less(arr[++i], pivot)) {
            }
            while (j > lo && less(pivot, arr[--j])) {
            }

            if (i == j && equal(pivot, arr[i])) {
                swap(arr, ++leftEqual, i);
            }

            // pointers cross
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
            if (equal(arr[i], pivot)) {
                swap(arr, i, ++leftEqual);
            }
            if (equal(arr[j], pivot)) {
                swap(arr, j, --rightEqual);
            }
        }

        // in-case pointers do cross
        i = j + 1;

        for (int left = lo; left <= leftEqual; left++) {
            swap(arr, left, j--);
        }
        for (int right = hi; right >= rightEqual; right--) {
            swap(arr, right, i++);
        }

        sort(arr, lo, j);
        sort(arr, i, hi);

    }

    /**
     * Compute median of three subarrays with three elements and compute median of those three medians
     * and replace the value of arr[lo] with the computed median.
     *
     * @param arr - array to compute medians from
     * @param lo  - lower bounds of array
     * @param hi  - upper bound of array
     */
    public static void turkeyNintherMedian(Comparable[] arr, int lo, int hi) {
        if ((hi - lo) >= 8) {
            int median1 = medianOfThree(arr, lo, lo + 1, lo + 2);
            int median2 = medianOfThree(arr, hi, hi - 1, hi - 2);
            int mid = lo + (hi - lo) / 2;
            int median3 = medianOfThree(arr, mid - 1, mid, mid + 1);

            int median = medianOfThree(arr, median1, median2, median3);
            swap(arr, lo, median);
        }
    }

    /**
     * Compute median of three elements in index a, b, c and return the index of median element.
     *
     * @param arr - array to compute median from
     * @param a   - index a
     * @param b   - index b
     * @param c   - index c
     * @return index of median element.
     */
    public static int medianOfThree(Comparable[] arr, int a, int b, int c) {
        int[] arrIndex = new int[]{a, b, c};
        if (less(arr[arrIndex[1]], arr[arrIndex[0]])) {
            int temp = arrIndex[0];
            arrIndex[0] = arrIndex[1];
            arrIndex[1] = temp;
        }

        if (less(arr[arrIndex[0]], arr[arrIndex[2]]) && less(arr[arrIndex[1]], arr[arrIndex[2]])) {
            return arrIndex[1];
        } else if (less(arr[arrIndex[0]], arr[arrIndex[2]]) && less(arr[arrIndex[2]], arr[arrIndex[1]])) {
            return arrIndex[2];
        } else {
            return arrIndex[0];
        }
    }
}