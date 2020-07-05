package Chapter2.Chapter2_1;

public class InsertionSort extends BaseSort {
    public static void main(String[] args) {
        sort(unsortedArray(100000));
    }

    public static Comparable[] sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Comparable key = arr[i];
            int j = i;
            while (j > 0 && less(key, arr[j - 1])) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = key;
        }

        assert isSorted(arr);
        return arr;
    }
}