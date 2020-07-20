package Chapter1.Section1;

import java.util.Arrays;

/**
 * Exercise 1.1.14
 */
public class histogram {
    public static void main(String[] args) {
        int[] values = {1, 2, 2, 1, 8, 2, 2, 2, 3, 3, 2, 3, 2, 3};
        System.out.println(Arrays.toString(histogram(values, values.length)));
    }

    public static int[] histogram(int[] a, int M) {
        int[] hist = new int[M];
        for (int num : a) {
            if (num > M - 1) continue;
            hist[num]++;
        }
        return hist;
    }
}
