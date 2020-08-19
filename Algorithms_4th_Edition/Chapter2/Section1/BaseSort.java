package Chapter2.Section1;

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
     * @param a First element.
     * @param b Second element.
     * @return True if a is smaller than b.
     */
    protected static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * Returns true if a and b are equal.
     *
     * @param a First element.
     * @param b Second element.
     * @return True if a and b are equal.
     */
    protected static boolean equal(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }

    /**
     * Returns true if a is bigger than b.
     *
     * @param a First element.
     * @param b Second element.
     * @return True if a is bigger than b.
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
            if (a[i - 1].compareTo(a[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    protected static void print(Comparable[] c) {
        System.out.println(Arrays.toString(c));
    }

    /**
     * Returns unsorted array with N elements.
     *
     * @param N Size of array.
     * @return Unsorted array with N elements.
     */
    protected static Comparable[] unsortedArray(int N) {
        return unsortedArray(N, 1000000);
    }

    /**
     * Returns unsorted array with N elements.
     *
     * @param N        Size of array.
     * @param maxValue maximum value of each element
     * @return Unsorted array with N elements.
     */
    protected static Comparable[] unsortedArray(int N, int maxValue) {
        Comparable[] arr = new Comparable[N];
        for (int i = 0; i < N; i++) {
            if (StdRandom.bernoulli()) {
                arr[i] = -StdRandom.uniform(maxValue);
            } else {
                arr[i] = StdRandom.uniform(maxValue);
            }
        }
        return arr;
    }

    /**
     * Shuffles array in place.
     *
     * @param arr Array to shuffle.
     */
    public static void Shuffle(Comparable[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int randomIndex = StdRandom.uniform(0, i);
            Comparable temp = arr[randomIndex];
            arr[randomIndex] = arr[i];
            arr[i] = temp;
        }
    }
}