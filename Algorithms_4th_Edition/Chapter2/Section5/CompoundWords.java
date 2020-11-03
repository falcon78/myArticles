package Chapter2.Section5;

import java.util.*;

public class CompoundWords {
    // O(N^2)
    private static List<String> getCompoundWords(List<String> words) {
        Collections.sort(words);
        Set<String> wordsSet = Set.copyOf(words);

        List<String> compoundWords = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                String prefix = words.get(i);
                String compoundWord = words.get(j);
                if (compoundWord.startsWith(prefix)) {
                    String suffix = compoundWord.substring(prefix.length());
                    if (wordsSet.contains(suffix)) {
                        compoundWords.add(compoundWord);
                    }
                } else {
                    break;
                }
            }
        }
        return compoundWords;
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>(
                List.of("after", "hey", "thought", "text", "afterthought", "cali", "city", "fornia", "california")
        );

        List<String> compoundWords = getCompoundWords(words);
        assert compoundWords.get(0).equals("afterthought");
        assert compoundWords.get(1).equals("california");
    }
}
