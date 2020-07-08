package Chapter2.Chapter2_2;

import java.util.Arrays;

import Chapter2.Chapter2_1.BaseSort;

// Exercise 2.2.6
// Merge sort bottom up vs top down approach. (Array access count)
public class ArrayAccessTest extends BaseSort {
    public static void main(String[] args) {
        benchmark(10);
    } 

    public static void benchmark(int N) {
        int topDown = 0;
        int bottomUp = 0;
        
        for (int i = 0; i < N; i++) {
            Comparable[] array = unsortedArray(512);
            
            MergeSort.sort(Arrays.copyOf(array, array.length));
            topDown  += MergeSort.arrayAccessCount;
            
            MergeSortBottomUp.sort(Arrays.copyOf(array, array.length));
            bottomUp += MergeSortBottomUp.arrayAccessCount;
        }

        System.out.println("Array access for topDown merge sort: " + topDown/N);
        System.out.println("Array access for bottomUp merge sort: " + bottomUp/N);
        // Results for 100000 element array
        // Array access for topDown merge sort: 8228507
        // Array access for bottomUp merge sort: 8429143

        // Results for 512 element array
        // Array access for topDown merge sort: 22389
        // Array access for bottomUp merge sort: 22389
    }
}