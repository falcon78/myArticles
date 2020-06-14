package Chapter1.Chapter1_4;

import java.io.IOException;
import java.math.BigInteger;

public class ThreeSum {
    public static void main(String[] args) throws IOException {
//        int count = count(ReadInt.get());
//        System.out.println(count);
        System.out.println(count(new int[]{-10, -10, -5, 0, 5, 10, 10, 15, 20}));
    }

    /**
     * Return the number of three pairs that sum to 0 in given int array.
     * Exercise 1.4.1
     *
     * @param nums array of numbers
     * @return number of pairs that sum to 0
     */
    public static int count(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    BigInteger sum = BigInteger.valueOf(nums[i]);
                    sum = sum.add(BigInteger.valueOf(nums[j]));
                    sum = sum.add(BigInteger.valueOf(nums[k]));
                    if (sum.intValue() == 0)
                        count++;
                }
            }
        }
        return count;
    }
}
