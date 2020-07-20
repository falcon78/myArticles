package Chapter2.Section2;

import Chapter2.Section1.BaseSort;

/**
 * Test performance of NaturalMergeSort
 */
public class DoublingTest extends BaseSort {
    public static void main(String[] args) {
        long previousTime = 0;

        for (int i = 1; i < 100000000; i = i * 2) {
            long currentTime = System.currentTimeMillis();
            Comparable[] a = unsortedArray(i);
            NaturalMergeSort.sort(a);
            long executionTime = System.currentTimeMillis() - currentTime;

            if (previousTime > 0) {
                long ratio = executionTime / previousTime;
                System.out.println("Input Size: " + i + " Exec Time: " + executionTime + " Growth Ratio " + ratio);
            }

            previousTime = executionTime;
        }
    }
}
