package Chapter2.Chapter2_2;

import Chapter2.Chapter2_1.BaseSort;

/**
 * Most of the array have some sub arrays that are already sorted.
 * Here we use this property to further optimize traditional merge sort.
 * This method is called Natural Merge Sort.
 * Exercise: 2.2.16
 */
public class NaturalMergeSort extends BaseSort {
    public static void main(String[] args) {
        sort(new Comparable[]{1, 0});
        sort(new Comparable[]{0});
        sort(new Comparable[]{1, 1, 1, 2, 0});
        sort(new Comparable[]{-1, -2, 0});
        sort(new Comparable[]{7848, 5813, 1633, 5851, 6260, 6362, 4033, 5618, 6519, 6448});
        for (int i = 0; i < 100; i++)
            sort(unsortedArray(1000 + i));
    }

    public static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        System.arraycopy(a, low, aux, low, high - low + 1);

        int left = low;
        int right = mid + 1;

        for (int i = low; i <= high; i++) {
            if (left > mid) {
                a[i] = aux[right++];
            } else if (right > high) {
                a[i] = aux[left++];
            } else if (less(aux[left], aux[right])) {
                a[i] = aux[left++];
            } else {
                a[i] = aux[right++];
            }
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];

        sort(a, aux);

        assert isSorted(a);
    }

    public static void sort(Comparable[] a, Comparable[] aux) {
        while (true) {
            int low = 0;
            int mid = 0;
            int high = a.length - 1;

            // whether or not first sorted sub array is found.
            boolean foundFirstArray = false;
            // whether or not second sorted sub array is found.
            boolean foundSecondArray = false;

            for (int j = 0; j < a.length; j++) {
                boolean isArrayAccessSafe = j + 1 <= a.length - 1;

                // find the first sorted sub array
                if (isArrayAccessSafe && !foundFirstArray && (less(a[j], a[j + 1]) || equal(a[j], a[j + 1]))) {
                    mid = j + 1;
                    continue;
                } else if (!foundFirstArray) {
                    mid = j;
                    foundFirstArray = true;
                    continue;
                }

                // find the second sorted sub array
                if (isArrayAccessSafe && (less(a[j], a[j + 1]) || equal(a[j], a[j + 1]))) {
                    high = j + 1;
                } else {
                    high = j;
                    foundSecondArray = true;
                }

                // If two sorted sub arrays are found merge them.
                if (foundSecondArray) {
                    merge(a, aux, low, mid, high);

                    low = high + 1;
                    mid = high + 1;
                    foundFirstArray = false;
                    foundSecondArray = false;
                }
            }

            // Return if array is sorted.
            // Here we check if the first sorted subarray length is equals to
            // the length of the input array. If this is true we know that the
            // array is already sorted.
            if (low == 0 && mid == a.length - 1) {
                return;
            }
        }
    }
}
