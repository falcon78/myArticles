package Chapter1.Chapter1_4;

import java.util.Arrays;

public class FasterThreeSum {
    public static void main(String[] args) {
        System.out.println(count(new int[]{-10, -10, -5, 0, 5, 10, 10, 15, 20}));
        System.out.println(count(new int[]{-3, -2, 2, 3, 5, 99}));
        System.out.println(count(new int[]{-10, -10, -10, 10}));
        System.out.println(count(new int[]{0, 0, 0, 0, 0, 0, 0}));
        System.out.println(count(new int[]{-2, -1, 0, 0, 0, 0, 0, 0, 3}));
        int[] bigAssArray = ReadInt.get();
        Arrays.sort(bigAssArray);
        System.out.println(count(bigAssArray));
    }

    public static int count(int[] arr) {
        if (arr[arr.length - 1] > 0 && arr[0] > 0)
            return 0;
        if (arr[arr.length - 1] - 1 < 0 && arr[0] < 0)
            return 0;

        int zeroCount = zeroCount(arr);
        // Number of possible ways you can combine zeros
        // Zeros always sum to zero so we have to handle multiple zeros differently.
        int count = ((zeroCount - 2) * (zeroCount - 1) * zeroCount) / 6;

        for (int i = 0; i < arr.length; i++) {

            int start = i + 1;
            int end = arr.length - 1;

            while (start < end) {
                int threeSum = arr[i] + arr[start] + arr[end];
                if (threeSum < 0) {
                    start++;
                } else if (threeSum > 0) {
                    end--;
                } else {

                    // if start and end are zero and threesum if also zero then it means
                    // arr[i] is also zero.
                    // Since we already handled zeros, we ignore them
                    if (arr[start] == 0 && arr[end] == 0) {
                        start++;
                        end--;
                        continue;
                    }

                    int startValue = arr[start];
                    int startDuplicateCount = 1;
                    while (start + 1 < end && arr[start + 1] == startValue) {
                        startDuplicateCount++;
                        start++;
                    }

                    int endValue = arr[end];
                    int endDuplicateCount = 1;
                    while (end - 1 > start && arr[end - 1] == endValue) {
                        endDuplicateCount++;
                        end--;
                    }

                    count += endDuplicateCount * startDuplicateCount;
                    end--;
                    start++;
                }
            }
        }
        return count;
    }

    public static int zeroCount(int[] arr) {
        int count = 0;
        for (int num : arr)
            if (num == 0)
                count++;
        return count;
    }
}
