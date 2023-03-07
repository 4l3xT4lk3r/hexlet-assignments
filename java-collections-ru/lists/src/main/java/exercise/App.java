package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble(String letters, String word) {
        List<String> list1 = new ArrayList<>(Arrays.asList(letters.toLowerCase().split("")));
        List<String> list2 = new ArrayList<>(Arrays.asList(word.toLowerCase().split("")));
        for (String s : list2) {
            if (list1.contains(s)) {
                list1.remove(s);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
