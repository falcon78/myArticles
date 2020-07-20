package Chapter1.Section1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchVsBruteForce {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\turing\\Downloads\\largeW.txt");
        List<String> strings = new ArrayList<>();

        try {
            strings = Files.readAllLines(path);
        } catch (Exception e) {
            System.out.println("oops");
            System.out.println(e);
        }

        int[] ints = new int[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            ints[i] = Integer.parseInt(strings.get(i).replaceAll("\\s", ""));
        }
        Arrays.sort(ints);

        int key = 999957;

        System.out.println("Brute Force Search");
        long start = System.currentTimeMillis();
        bruteForceSearch(ints, key);
        long finish = System.currentTimeMillis() - start;
        System.out.println("Finished in (ms): " + finish);

        System.out.println("Binary Search");
        start = System.currentTimeMillis();
        BinarySearch.rank(ints, key, 0, ints.length);
        finish = System.currentTimeMillis() - start;
        System.out.println("Finished in (ms): " + finish);
    }

    public static int bruteForceSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
