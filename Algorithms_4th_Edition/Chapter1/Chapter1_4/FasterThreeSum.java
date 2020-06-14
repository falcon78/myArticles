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

            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int threeSum = arr[i] + arr[left] + arr[right];
                if (threeSum < 0) {
                    left++;
                } else if (threeSum > 0) {
                    right--;
                } else {

                    // if left and right are zero and threesum if also zero then it means
                    // arr[i] is also zero.
                    // Since we already handled zeros, we ignore them
                    if (arr[left] == 0 && arr[right] == 0) {
                        left++;
                        right--;
                        continue;
                    }

                    int startValue = arr[left];
                    int startDuplicateCount = 1;
                    while (left + 1 < right && arr[left + 1] == startValue) {
                        startDuplicateCount++;
                        left++;
                    }

                    int endValue = arr[right];
                    int endDuplicateCount = 1;
                    while (right - 1 > left && arr[right - 1] == endValue) {
                        endDuplicateCount++;
                        right--;
                    }

                    count += endDuplicateCount * startDuplicateCount;
                    right--;
                    left++;
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
