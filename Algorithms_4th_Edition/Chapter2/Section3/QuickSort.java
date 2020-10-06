package Chapter2.Section3;

import Chapter2.Section1.BaseSort;
import Chapter2.ISorter;

public class QuickSort extends BaseSort implements ISorter {

    public static void main(String[] args) {
        QuickSort quick = new QuickSort();
        for (int i = 0; i < 100; i++)
            quick.sort(unsortedArray(10000));

        quick.sort(new Comparable[] { 10, 1, 2, 3, -1, -1, -1, 36 });
        quick.sort(new Comparable[] { 10 });
        quick.sort(new Comparable[] { 10, -1, -2 });
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
            }

            if (i >= j)
                break;
            swap(a, i, j);
        }

        swap(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;

        int partitionIndex;
        partitionIndex = hoarePartition(a, lo, hi);

        sort(a, lo, partitionIndex - 1);
        sort(a, partitionIndex + 1, hi);
    }

    public void sort(Comparable[] a) {
        Shuffle(a);

        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }
}
