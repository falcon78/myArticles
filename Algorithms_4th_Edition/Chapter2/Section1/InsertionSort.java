package Chapter2.Section1;

public class InsertionSort extends BaseSort {
    public static void main(String[] args) {
        sort(unsortedArray(1000));
    }

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
        assert isSorted(arr);
    }

    public static void sort(Comparable[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            Comparable key = arr[i];
            int j = i;
            while (j > lo && less(key, arr[j - 1])) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = key;
        }

    }
}