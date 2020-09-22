package Chapter2.Section3;

public class BentleyMcllroy {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] arr = new int[10000];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (int) (Math.random() * 19999);
            }
            sort(arr);
            isSorted(arr);
        }
    }

    static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    static void sort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;

        int leftEqual = lo + 1;
        int rightEqual = hi;
        int i = lo;
        int j = hi + 1;
        int pivot = arr[lo];

        while (true) {
            while (i < hi && pivot > arr[++i]) {
            }
            while (j > lo && pivot < arr[--j]) {
            }

            if (i == j && arr[i] == pivot) {
                swap(arr, i, leftEqual++);
            }
            if (i >= j) {
                break;
            }

            swap(arr, i, j);
            if (arr[i] == pivot) {
                swap(arr, i, leftEqual++);
            }
            if (arr[j] == pivot) {
                swap(arr, j, rightEqual--);
            }
        }

        i = j + 1;

        for (int k = lo; k < leftEqual; k++) {
            swap(arr, k, --i);
        }
        for (int k = hi; k > rightEqual; k--) {
            swap(arr, k, ++j);
        }

        sort(arr, lo, i - 1);
        sort(arr, j + 1, hi);
    }

    static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void isSorted(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            assert arr[i] >= arr[i - 1];
        }
    }
}
