package Chapter2.Chapter2_2;

import Chapter2.Chapter2_1.BaseSort;

/**
 * Implements merge sort algorithm.
 */
public class MergeSort extends BaseSort {
    private static long arrayAccessCount;

    public static void main(String[] args) {
        sort(unsortedArray(1000));
    }

    /***
     * Merge two sorted sub arrays.
     */
    public static void merge(Comparable[] c, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        // copy elements to auxiliary array.
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

        int mid = lo + (hi - lo) / 2;

        sort(c, aux, lo, mid);
        sort(c, aux, mid + 1, hi);
        merge(c, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] c) {
        Comparable[] aux = new Comparable[c.length];
        sort(c, aux, 0, c.length - 1);
        System.out.println(arrayAccessCount);
        assert isSorted(c);
    }
}