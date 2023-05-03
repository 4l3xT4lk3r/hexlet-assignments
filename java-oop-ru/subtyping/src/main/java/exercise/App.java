package exercise;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

// BEGIN

public class App {

    public static void main(String[] args) {
        KeyValueStorage keyValueStorage = new FileKV("src/test/resources/file2",Map.of("key", "value"));
        System.out.println(keyValueStorage.get("key", "default"));
        System.out.println(keyValueStorage.toMap());
        keyValueStorage.set("key","v");
        keyValueStorage.unset("key");
    }

    public static void swapKeyValue(KeyValueStorage storage){
        Map<String, String> map = storage.toMap();
        for(Map.Entry<String, String> entry : map.entrySet()){
            storage.unset(entry.getKey());
            storage.set(entry.getValue(),entry.getKey());
        }
    }
}

// END
