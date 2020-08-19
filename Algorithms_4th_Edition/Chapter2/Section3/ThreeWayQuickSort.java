package Chapter2.Section3;

import Chapter2.Section1.BaseSort;

/**
 * Quicksort implementation with three way partition.
 * Three way partition method is efficient if there are lots of duplicate items in array.
 */
public class ThreeWayQuickSort extends BaseSort implements ISorter {
    public static void main(String[] args) {
        ThreeWayQuickSort sort = new ThreeWayQuickSort();
        for (int i = 0; i < 100; i++)
            sort.sort(unsortedArray(10000));
        sort.sort(new Comparable[] { 0 });
        sort.sort(new Comparable[] {});
        sort.sort(new Comparable[] { 1, 1, 1, 1, 1, 1, 1, 1, 2 });
    }

    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;

        Comparable pivotValue = a[lo];
        int left = lo, i = lo + 1, right = hi;

        while (i <= right) {
            int cmp = a[i].compareTo(pivotValue);
            if (cmp < 0)
                swap(a, i++, left++);
            else if (cmp > 0)
                swap(a, i, right--);
            else
                i++;
        }

        sort(a, lo, left - 1);
        sort(a, right + 1, hi);
    }
}