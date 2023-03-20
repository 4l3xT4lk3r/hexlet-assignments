package exercise;

import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
public class App {
    public static LinkedHashMap<String, Object> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.putAll(map1);
        result.putAll(map2);
        for (Map.Entry<String, Object> entry : result.entrySet()) {
            if (!map1.containsKey(entry.getKey()) && map2.containsKey(entry.getKey())) {
                result.put(entry.getKey(), "added");
            } else if (map1.containsKey(entry.getKey()) && !map2.containsKey(entry.getKey())) {
                result.put(entry.getKey(), "deleted");
            } else if (map1.containsKey(entry.getKey()) && map2.containsKey(entry.getKey())) {
                if (!map1.get(entry.getKey()).equals(map2.get(entry.getKey()))) {
                    result.put(entry.getKey(), "changed");
                } else if (map1.get(entry.getKey()).equals(map2.get(entry.getKey()))) {
                    result.put(entry.getKey(), "unchanged");
                }
            }
        }
        return result;
    }
}
//END
