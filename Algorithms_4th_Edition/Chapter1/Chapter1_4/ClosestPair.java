package Chapter1.Chapter1_4;

import java.util.Arrays;

/**
 * Find the closest pair in an given array in linearithmeric time.
 */
public class ClosestPair {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(closest(new int[]{
                1, 5, 8, 44, 45, 6, 33, 90
        })));
        System.out.println(Arrays.toString(closest(ReadInt.get())));
    }

    public static int[] closest(int[] arr) {
        Arrays.sort(arr);
        int num1 = arr[0];
        int num2 = arr[0];
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            int diff = (Math.abs(arr[i + 1]) - Math.abs(arr[i]));
            if (diff < minDiff) {
                minDiff = diff;
                num1 = arr[i];
                num2 = arr[i + 1];
            }
        }
        return new int[]{num1, num2};
    }
}
