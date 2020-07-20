package Chapter1.Section5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// read int pairs from text file and return as an int array
public class GetPairs {

    public static ArrayList<int[]> pairs(String size) throws IOException {
        String path = "C:\\Users\\turing\\Projects\\myArticles";
        path += "\\Algorithms_4th_Edition\\Chapter1\\Chapter1_5\\";

        if (size.equals("medium")) {
            path += "mediumUF.txt";
        } else {
            path += "largeUF.txt";
        }

        ArrayList<int[]> integers;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            integers = new ArrayList<>(Integer.parseInt(br.readLine()));

            while ((line = br.readLine()) != null) {
                String[] strPairs = line.split(" ");
                if (strPairs.length < 2) continue;
                integers.add(new int[]{
                        Integer.parseInt(strPairs[0]),
                        Integer.parseInt(strPairs[1])
                });
            }
        }

        return integers;
    }
}
