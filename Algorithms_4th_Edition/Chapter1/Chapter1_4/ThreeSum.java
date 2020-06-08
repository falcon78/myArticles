package Chapter1.Chapter1_4;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) throws IOException {
        int count = count(ReadInt.get());
        System.out.println(count);
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
            for (int j = i; j < nums.length; j++) {
                for (int k = j; k < nums.length; k++) {
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
