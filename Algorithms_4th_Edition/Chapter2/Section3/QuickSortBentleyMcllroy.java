package Chapter2.Section3;

import Chapter2.Section1.BaseSort;

/**
 * Quicksort implementation with three way partition. Three way partition method
 * is efficient if there are lots of duplicate items in array.
 */
public class QuickSortBentleyMcllroy extends BaseSort implements ISorter {
    public static void main(String[] args) {
        QuickSortBentleyMcllroy sort = new QuickSortBentleyMcllroy();
        for (int i = 0; i < 100000; i++)
            sort.sort(unsortedArray((int) (Math.random() * 100), 100));
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

        int leftEqual = lo, rightEqual = hi + 1;
        int i = lo, j = hi + 1;
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
}