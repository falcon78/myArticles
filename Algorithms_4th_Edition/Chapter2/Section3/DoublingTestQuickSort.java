package Chapter2.Section3;

import Chapter2.Section1.BaseSort;

/**
 * Test performance of Different sort algorithms
 */
public class DoublingTestQuickSort extends BaseSort {
    public static int N = 6000000;

    public static void main(String[] args) {
        QuickSort quicksort = new QuickSort();
        ThreeWayQuickSort threeWayPartition = new ThreeWayQuickSort();
        MedianOfThreeQuickSort medianOfThree = new MedianOfThreeQuickSort();
        IterativeQuickSort iterativeQuickSort = new IterativeQuickSort();
        QuickSortBentleyMcllroy bentleyMcllroy = new QuickSortBentleyMcllroy();

        benchmark("quicksort \n", quicksort);
        benchmark("Three way partition quicksort \n", threeWayPartition);
        benchmark("Median of three quicksort \n", medianOfThree);
        benchmark("iterative quicksort \n", iterativeQuickSort);
        benchmark("Bentley Mcllory 3 way partitioning \n", bentleyMcllroy);
    }

    public static void benchmark(String name, ISorter Sort) {
        long growthRatio = 0;
        long averageExecutionTime = 0;
        long previousTime = 0;

        for (int i = 1; i < N; i = i * 2) {
            long currentTime = System.currentTimeMillis();
            Comparable[] a = unsortedArray(i);
            Sort.sort(a);
            long executionTime = System.currentTimeMillis() - currentTime;
            averageExecutionTime += executionTime;

            if (previousTime > 0) {
                growthRatio = executionTime / previousTime;
            }

            previousTime = executionTime;
        }

        System.out.println(name + "Exec Time: " + averageExecutionTime + "ms Growth Ratio: " + growthRatio + "\n");
    }
}
