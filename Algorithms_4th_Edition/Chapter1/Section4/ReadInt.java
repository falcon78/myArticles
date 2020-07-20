package Chapter1.Section4;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadInt {
    /**
     * Get Int arrays from 2Kints.txt files.
     *
     * @return arrays of int values in 2Kints.txt file.
     */
    public static int[] get() {
        String path = "C:\\Users\\turing\\Projects\\myArticles";
        path += "\\Algorithms_4th_Edition\\Chapter1\\Chapter1_4\\2Kints.txt";

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (Exception e) {
            return new int[1];
        }
        int[] nums = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            nums[i] = Integer.parseInt(lines.get(i).trim());
        }
        return nums;
    }
}
