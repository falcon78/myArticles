package Chapter2.Section3;

import Chapter2.Section1.BaseSort;

public class ThreeWayQuickSort extends BaseSort {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
            sort(unsortedArray(10000));
        sort(new Comparable[]{0});
        sort(new Comparable[]{});
        sort(new Comparable[]{1,1,1,1,1,1,1,1,2});
    }

    public static void sort(Comparable[] a) {
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