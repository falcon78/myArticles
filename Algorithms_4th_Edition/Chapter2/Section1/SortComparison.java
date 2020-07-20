package Chapter2.Section1;

public class SortComparison {
    public static final String insertionSort = "insertionSort";
    public static final String shellSort = "shellSort";

    public static void main(String[] args) {
        int N = 20;
        long insertionSortTime = 0;
        long shellSortTime = 0;
        for (int i = 0; i < N; i++) {
            Comparable[] arr = BaseSort.unsortedArray(30000);
            insertionSortTime += measure(insertionSort, arr);
            shellSortTime += measure(shellSort, arr);
        }

        System.out.println(insertionSort + ": " + (insertionSortTime / N) + "ms");
        System.out.println(shellSort + ": " + (shellSortTime / N) + "ms");
    }

    public static long measure(String sorter, Comparable[] arr) {
        long time = System.currentTimeMillis();

        switch (sorter) {
            case insertionSort:
                InsertionSort.sort(arr);
            case shellSort:
                ShellSort.sort(arr);
        }

        return System.currentTimeMillis() - time;
    }
}