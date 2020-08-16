package Chapter2.Section3;

public class NutsAndBolts {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] nuts = unsortedArray(300, 100);
            int[] bolts = new int[nuts.length];
            System.arraycopy(nuts, 0, bolts, 0, nuts.length);

            arrayShuffle(nuts);
            arrayShuffle(bolts);

            sort(nuts, bolts);

            assert isSorted(nuts);
            assert isSorted(bolts);
        }
    }

    static void sort(int[] nuts, int[] bolts) {
        sort(nuts, bolts, 0, nuts.length - 1);
    }

    static void sort(int[] nuts, int[] bolts, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int partition = partition(nuts, lo, hi, bolts[lo]);
        partition(bolts, lo, hi, nuts[partition]);

        sort(nuts, bolts, lo, partition - 1);
        sort(nuts, bolts, partition + 1, hi);
    }


    static int partition(int[] arr, int lo, int hi, int pivot) {
        int left = lo, right = hi, i = lo;

        while (i <= right) {
            if (arr[i] > pivot) {
                swap(arr, i, right--);
            } else if (arr[i] < pivot) {
                swap(arr, i, left);
                left++;
                i++;
            } else {
                i++;
            }
        }

        return left;
    }

    static int[] unsortedArray(int N, int maxValue) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue + 1);
        }
        return arr;
    }

    static void arrayShuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * i);
            int temp = arr[randomIndex];
            arr[randomIndex] = arr[i];
            arr[i] = temp;
        }
    }

    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                System.out.println("NOT SORTED!!!");
                return false;
            }
        }
        return true;
    }
    
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}