package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> vocabulary = new HashMap<>();
        if (sentence.length() == 0) {
            return vocabulary;
        }
        String[] words = sentence.split(" ");
        for (String w : words) {
            if (!vocabulary.containsKey(w)) {
                vocabulary.put(w, 1);
            } else {
                vocabulary.put(w, vocabulary.get(w) + 1);
            }
        }
        return vocabulary;
    }

    public static String toString(Map<String, Integer> vocabulary) {
        if (vocabulary.size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map.Entry<String, Integer> entry : vocabulary.entrySet()) {
            sb.append("  " + entry.getKey() + ": " + entry.getValue() + "\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
//END
