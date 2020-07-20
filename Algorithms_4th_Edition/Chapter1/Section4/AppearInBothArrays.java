package Chapter1.Section4;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Find all equal elements in two sorted array in O(n) time.
 */
public class AppearInBothArrays {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 2, 2, 3, 4, 5, 5, 6, 7, 8, 12, 90, 99, 99, 202, 202, 300};
        int[] arr2 = new int[]{2, 2, 3, 5, 6, 90, 99, 300};

        System.out.println(Arrays.toString(countInBothArrays(
                arr1,
                arr2
        )));
    }

    /**
     * Return a sub array with elements that appear in both sorted array arr1 and arr2.
     *
     * @param arr1 sorted array 1.
     * @param arr2 sorted array 2.
     * @return Sub array with elements that appear in both arr1 and arr2.
     */
    public static int[] countInBothArrays(int[] arr1, int[] arr2) {
        int indexArr1 = 0;
        int indexArr2 = 0;
        Integer recentMatch = null;
        ArrayList<Integer> matchSubArray = new ArrayList<>();

        while (indexArr1 < arr1.length && indexArr2 < arr2.length) {
            if (arr1[indexArr1] < arr2[indexArr2])
                indexArr1++;
            else if (arr1[indexArr1] > arr2[indexArr2])
                indexArr2++;
            else if (recentMatch == null || recentMatch != arr1[indexArr1]) {
                recentMatch = arr1[indexArr1];
                matchSubArray.add(arr1[indexArr1]);
                indexArr1++;
                indexArr2++;
            } else {
                indexArr1++;
                indexArr2++;
            }

        }

        int[] array = new int[matchSubArray.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = matchSubArray.get(i);
        }
        return array;
    }
}
