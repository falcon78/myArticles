package Chapter2.Section5;


import Chapter2.Section1.BaseSort;
import Library.StdRandom;

/**
 * Exercise 2.5.6
 * Implement a recursive version of fast select.
 */
public class RecursiveQuickSelect {
    /**
     * Partition an array so that the left side has elements that are
     * all smaller than arr[p] and right bigger than arr[p].
     */
    public static int partition(int[] arr, int lo, int hi) {
        if (hi - lo < 1) {
            return lo;
        }

        int key = arr[lo];
        int left = lo;
        int right = hi + 1;

        while (true) {
            while (left < hi && arr[++left] <= key) {
            }
            while (right > lo && arr[--right] > key) {
            }
            if (left >= right) {
                break;
            }
            swap(arr, left, right);
        }

        swap(arr, lo, right);
        return right;
    }

    /**
     * returns kth smallest item in unordered list.
     */
    public static int quickSelect(int[] arr, int k, int lo, int hi) {
        if (hi - lo < 1) {
            return arr[hi];
        }

        int p = partition(arr, lo, hi);
        if (p < k) {
            return quickSelect(arr, k, p + 1, hi);
        } else if (p > k) {
            return quickSelect(arr, k, lo, p - 1);
        } else {
            return arr[p];
        }
    }

    /**
     * returns kth smallest item in unordered list.
     */
    public static int quickSelect(int[] arr, int k) {
        if (k > arr.length) {
            return -1;
        }
        k--;
        return quickSelect(arr, k, 0, arr.length - 1);
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int N = 30;
        int M = 10000;

        // test if the partition method is correct by using it to perform quicksort.
        for (int i = 0; i < N; i++) {
            int[] arr = new int[M];
            for (int j = 0; j < M; j++) {
                arr[j] = StdRandom.uniform(-100, 100);
            }
            sort(arr, 0, arr.length - 1);
            assert BaseSort.isSorted(arr);
        }

        // test quickSelect method
        for (int i = 0; i < N; i++) {
            int[] arr = new int[M];
            for (int j = 0; j < M; j++) {
                arr[j] = StdRandom.uniform(-100, 100);
            }
            int k = StdRandom.uniform(0, M);
            int KthItem = quickSelect(arr, k);
            // Kth item must be equal to arr[k-1]
            assert arr[k-1] == KthItem;
        }
    }


    public static void sort(int[] a, int lo, int hi) {
        if (lo >= hi)
            return;

        int partitionIndex;
        partitionIndex = partition(a, lo, hi);

        sort(a, lo, partitionIndex - 1);
        sort(a, partitionIndex + 1, hi);
    }
}