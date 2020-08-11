package Chapter2.Section3;

import Chapter2.Section1.BaseSort;

public class QuickSort extends BaseSort {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
            sort(unsortedArray(10000));
            
        sort(new Comparable[]{
                10, 1, 2, 3, -1, -1, -1, 36
        });
        sort(new Comparable[]{
                10
        });
        sort(new Comparable[]{
                10, -1, -2
        });
    }

    public static int hoarePartition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable pivotValue = a[lo];

        while (true) {
            while (less(a[++i], pivotValue)) {
                if (i >= hi)
                    break;
            }
            while (less(pivotValue, a[--j])) {
                if (j <= lo || j < i)
                    break;
            }

            if (i >= j) break;
            swap(a, i, j);
        }

        swap(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        int partitionIndex = hoarePartition(a, lo, hi);
        sort(a, lo, partitionIndex - 1);
        sort(a, partitionIndex + 1, hi);
    }

    public static void sort(Comparable[] a) {
        Shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

}
