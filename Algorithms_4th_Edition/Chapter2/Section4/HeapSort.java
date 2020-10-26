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

    private static void printWhiteSpaces(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
    }
}
