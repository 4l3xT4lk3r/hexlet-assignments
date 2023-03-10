package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {

    public static List<Map<String, String>> findWhere(List<Map<String, String>> bookList, Map<String, String> vocabulary) {
        List<Map<String, String>> result = new ArrayList<>();
        if (vocabulary.size() == 0) {
            return result;
        }

        for (Map<String, String> book : bookList) {
            boolean isMatch = true;
            for (Map.Entry<String, String> vocabularyEntry : vocabulary.entrySet()) {
                if (!book.get(vocabularyEntry.getKey()).equals(vocabularyEntry.getValue())) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
