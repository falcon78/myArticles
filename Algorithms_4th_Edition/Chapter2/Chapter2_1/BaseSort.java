package Chapter2.Chapter2_1;

import java.util.Arrays;

import Library.StdRandom;

public class BaseSort {
    /**
     * Swap positions of elements at index a and b in an array c[].
     * 
     * @param c Array of {@link Comparable} elements.
     * @param a Index of an array.
     * @param b Index of an array.
     */
    protected static void swap(Comparable[] c, int a, int b) {
        Comparable temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }

    /**
     * Returns true if a is smaller than b.
     * 
     * @param a
     * @param b
     * @return
     */
    protected static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1;
    }

    /**
     * Returns true if a and b are equal.
     * 
     * @param a
     * @param b
     * @return
     */
    protected static boolean equal(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }

    /**
     * Returns true if a is bigger than b.
     * 
     * @param a
     * @param b
     * @return
     */
    protected static boolean bigger(Comparable a, Comparable b) {
        return a.compareTo(b) == 1;
    }

    /**
     * Returns true if given array is sorted, false otherwise.
     * 
     * @param a Array with {@link Comparable} elements.
     */
    protected static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].compareTo(a[i]) == 1) {
                return false;
            }
        }
        return true;
    }

    protected static void print(Comparable[] c) {
        System.out.println(Arrays.toString(c));
    }

    /**
     * Return unsorted array with N elements.
     * 
     * @param N Size of array.
     * @return Unsorted array with N elements.
     */
    protected static Comparable[] unsortedArray(int N) {
        Comparable[] arr = new Comparable[N];
        for (int i = 0; i < N; i++) {
            arr[i] = StdRandom.uniform(0, 9999);
        }
        return arr;
    }
}