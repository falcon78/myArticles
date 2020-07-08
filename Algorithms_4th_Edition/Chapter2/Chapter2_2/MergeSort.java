package Chapter2.Chapter2_2;

import Chapter2.Chapter2_1.BaseSort;

/**
 * Implements merge sort algorithm.
 */
public class MergeSort extends BaseSort {
    public static long arrayAccessCount;

    public static void main(String[] args) {
        sort(unsortedArray(10));
    }

    /***
     * Merge two sorted sub arrays.
     */
    public static void merge(Comparable[] c, Comparable[] aux, int lo, int mid, int hi) {
        // skip merge if the sub-array is already sorted.
        if (less(c[mid], c[mid + 1]) || equal(c[mid], c[mid + 1]))
            return;

        int i = lo, j = mid + 1;

        /**
         *  Copy elements to auxiliary array.
         *  TODO: Optimize this code to remove array copy. (Exercise 2.2.11)
         */
        for (int k = lo; k <= hi; k++) {
            aux[k] = c[k];
            arrayAccessCount += 2;
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                c[k] = aux[j++];
            else if (j > hi)
                c[k] = aux[i++];

            else if (less(aux[i], aux[j])) {
                c[k] = aux[i++];
                arrayAccessCount += 2;
            } else
                c[k] = aux[j++];

            arrayAccessCount += 2;
        }
    }

    public static void sort(Comparable[] c, Comparable[] aux, int lo, int hi) {
        if (lo >= hi)
            return;

        // use insertion sort for smaller sub-arrays.
        if (hi - lo < 12) {
            insertionSort(c, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(c, aux, lo, mid);
        sort(c, aux, mid + 1, hi);
        merge(c, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] c) {
        arrayAccessCount = 0;
        Comparable[] aux = new Comparable[c.length];

        sort(c, aux, 0, c.length - 1);
        assert isSorted(c);
    }

    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            Comparable key = a[i];
            int j = i;
            
            arrayAccessCount += 2;
            while (j > lo && less(key, a[j - 1])) {
                a[j] = a[j - 1];
                arrayAccessCount += 2;
                j--;
            }
            arrayAccessCount += 1;
            a[j] = key;
        }
    }
}