package Chapter1.Section3;

import java.io.File;

/**
 * Exercise 1.3.43
 * Recursively get all folders, files and print them.
 */
public class ListingFiles {
    public static void main(String[] args) {
        list("./Algorithms_4th_Edition");
    }

    public static void list(String dir, int depth) {
        File[] files = new File(dir).listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                for (int i = 0; i < depth; i++) {
                    System.out.print("\t");
                }
                System.out.println(dir + file.getName());
                list(dir + "/" + file.getName(), depth + 1);
            } else {
                for (int i = 0; i < depth; i++) {
                    System.out.print("\t");
                }
                System.out.println(file.getName());
            }
        }
    }

    public static void list(String dir) {
        list(dir, 0);
    }
}
