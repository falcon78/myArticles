package Chapter2.Section4;

import Chapter2.Section1.BaseSort;
import Chapter2.ISorter;

/**
 * Heap sort implemented with max priority queue.
 */
public class HeapSort extends BaseSort implements ISorter {
    public static void main(String[] args) {
        Comparable[] arr = unsortedArray(1000000);
        heapSort(arr);
        assert isSorted(arr);
    }

    public void sort(Comparable[] arr)  {
        heapSort(arr);
    }

    public static void heapSort(Comparable[] arr) {
        for (int i = (arr.length - 1); i >= 0; i--) {
            sink(arr, i, arr.length - 1);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            sink(arr, 0, i - 1);
        }
    }

    private static void sink(Comparable[] arr, int parentNode, int maxIndex) {
        while ((parentNode * 2) + 1 <= maxIndex) {
            int leftNode = (2 * parentNode) + 1;
            int rightNode = leftNode + 1;

            int biggerChild = leftNode;
            if (rightNode <= maxIndex && less(arr[biggerChild], arr[rightNode])) {
                biggerChild = rightNode;
            }

            if (less(arr[parentNode], arr[biggerChild])) {
                swap(arr, parentNode, biggerChild);
                parentNode = biggerChild;
                continue;
            }

            break;
        }
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
}
