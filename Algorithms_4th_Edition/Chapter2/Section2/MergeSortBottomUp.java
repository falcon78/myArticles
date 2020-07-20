package Chapter2.Section2;

import Chapter2.Section1.BaseSort;

// This class implements bottom up merge sort.
public class MergeSortBottomUp extends BaseSort {
    public static int arrayAccessCount;

    public static void main(String[] args) {
        sort(unsortedArray(1000));
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // skip merge if the sub-array is already sorted or mid is equal to hi.
        if (mid + 1 >= a.length || (less(a[mid], a[mid + 1]) || equal(a[mid], a[mid + 1])))
            return;

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            arrayAccessCount += 2;
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
                arrayAccessCount += 2;
            } else {
                a[k] = aux[j++];
            }

            arrayAccessCount += 2;
        }
    }

    public static void sort(Comparable[] a) {
        arrayAccessCount = 0;
        Comparable[] aux = new Comparable[a.length];

        for (int size = 1; size < a.length; size += size) {
            for (int j = 0; j < a.length - size + size; j += size + size) {
                // use insertionSort for smaller sub-arrays.
                if (size < 12)
                    insertionSort(a, j, Math.min(j + size + size - 1, a.length - 1));
                else
                    merge(a, aux, j, j + size - 1, Math.min(j + size + size - 1, a.length - 1));
            }
        }

        assert isSorted(a);
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