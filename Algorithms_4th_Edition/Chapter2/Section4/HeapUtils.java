package Chapter2.Section4;

import Chapter2.Section1.BaseSort;

public class HeapUtils extends BaseSort {
    /**
     * Verify the correctness of a binary heap min-priority queue
     *
     * @param arr - the underlying array of the queue
     * @param N   - array sentinel
     * @return true if queue is correct false otherwise.
     */
    public static boolean verifyMinHeap(Comparable[] arr, int N) {
        for (int parent = 1; parent < N / 2; parent++) {
            int leftNode = parent * 2;
            int rightNode = leftNode + 1;

            if (less(arr, leftNode, parent) || (rightNode < N && less(arr, rightNode, parent)))
                return false;
        }
        return true;
    }

    /**
     * Verify the correctness of a binary heap max-priority queue
     *
     * @param arr - the underlying array of the queue
     * @param N   - array sentinel
     * @return true if queue is correct false otherwise.
     */
    public static boolean verifyMaxHeap(Comparable[] arr, int N) {
        for (int parent = 1; parent < N / 2; parent++) {
            int leftNode = parent * 2;
            int rightNode = leftNode + 1;

            if (less(arr, parent, leftNode) || (rightNode < N && less(arr, parent, rightNode)))
                return false;
        }
        return true;
    }

    public static boolean less(Comparable[] arr, int a, int b) {
        return arr[a].compareTo(arr[b]) < 0;
    }


    protected static void toString(Comparable[] arr) {
        int maxDepth = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
        int currentPadding = 2 * arr.length;

        for (int i = 0; i <= maxDepth; i++) {
            int start = 0, end;
            for (int j = i - 1; j >= 0; j--) {
                start += Math.pow(2, j);
            }
            end = (int) (start + Math.pow(2, i));

            for (int j = start; j < end && j < arr.length; j++) {
                printWhiteSpaces(currentPadding);
                System.out.print(arr[j]);
                printWhiteSpaces(currentPadding);
            }

            System.out.println();
            System.out.println();
            currentPadding = currentPadding / 2;
        }
    }

    private static void printWhiteSpaces(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
    }
    

    // tests
    public static void main(String[] args) {
        Comparable[] testArr = new Comparable[]{0, 0, 1, 2, -1, -5, 99, 6, 7, 8};
        assert !verifyMinHeap(testArr, testArr.length);

        testArr = new Comparable[]{0, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        assert verifyMinHeap(testArr, testArr.length);

        testArr = unsortedArray(1000);
        assert !verifyMinHeap(testArr, testArr.length);

        HeapSort.heapSort(testArr);
        assert isSorted(testArr);
        assert verifyMinHeap(testArr, testArr.length);
    }
}
