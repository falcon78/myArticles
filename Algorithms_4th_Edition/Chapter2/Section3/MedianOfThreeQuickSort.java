package Chapter2.Section3;

import Chapter2.ISorter;
import Chapter2.Section1.BaseSort;

/**
 * Exercise 2.3.18 Implementation quicksort with Media-of-3 partitioning
 */

public class MedianOfThreeQuickSort extends BaseSort implements ISorter {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Comparable[] arr = unsortedArray(10000);
            sort(arr, 0, arr.length - 1);
            assert isSorted(arr);
        }
    }

    public void sort(Comparable[] arr) {
        Shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int p = partition(arr, lo, hi);
        sort(arr, lo, p - 1);
        sort(arr, p + 1, hi);
    }

    public static int partition(Comparable[] arr, int lo, int hi) {
        int partitionIndex = medianOfThree(arr, lo, hi);
        if ((hi - lo) <= 2)
            return lo;

        swap(arr, lo + 1, partitionIndex);
        Comparable pivot = arr[lo + 1];
        int i = lo + 1;
        int j = hi;

        while (true) {
            while (less(arr[++i], pivot)) {
            }
            while (less(pivot, arr[--j])) {
            }

            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, lo + 1, j);

        return j;
    }

    public static int medianOfThree(Comparable[] arr, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;

        if (bigger(arr[lo], arr[mid])) {
            swap(arr, lo, mid);
        }
        if (bigger(arr[lo], arr[hi])) {
            swap(arr, lo, hi);
        }
        if (bigger(arr[mid], arr[hi])) {
            swap(arr, mid, hi);
        }

        return mid;
    }
}