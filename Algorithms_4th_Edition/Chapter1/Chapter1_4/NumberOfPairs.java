package Chapter1.Chapter1_4;

import java.util.Arrays;

/**
 * Find out how many numbers of pairs of values are equal in an array
 * in linearithmic time
 */
public class NumberOfPairs {
    public static void main(String[] args) {
        BinarySearchArray binarySearchArray = new BinarySearchArray(ReadInt.get());
        System.out.println(binarySearchArray.count(260607));
        System.out.println(binarySearchArray.rank(260607));
    }
}
