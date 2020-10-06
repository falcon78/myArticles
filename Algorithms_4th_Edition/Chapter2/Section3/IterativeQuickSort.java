package Chapter2.Section3;

import Chapter2.ISorter;
import Chapter2.Section1.BaseSort;
import Chapter1.Section3.Stack;

/**
 * Iterative implementation of quicksort.
 */
public class IterativeQuickSort extends BaseSort implements ISorter {
    public static void main(String[] args) {
        IterativeQuickSort sort = new IterativeQuickSort();
        for (int i = 0; i < 100; i++) {
            Comparable[] arr = unsortedArray(10000);
            sort.sort(arr);
            assert isSorted(arr);
        }
    }

    public void sort(Comparable[] arr) {
        try {
            quicksort(arr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void quicksort(Comparable[] arr) throws IllegalAccessException {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(arr.length - 1);

        while (!stack.isEmpty()) {
            int hi = stack.pop();
            int lo = stack.pop();

            if ((hi - lo) < 1)
                continue;

            int partition = MedianOfThreeQuickSort.partition(arr, lo, hi);
            
            stack.push(lo);
            stack.push(partition - 1);

            stack.push(partition + 1);
            stack.push(hi);
        }

        return;
    }
}