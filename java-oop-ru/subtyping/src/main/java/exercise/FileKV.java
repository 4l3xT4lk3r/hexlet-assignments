package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private final String filePath;

    public FileKV(String filePath, Map<String, String> storage) {
        this.filePath = filePath;
        Utils.writeFile(this.filePath, Utils.serialize(storage));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> jsonMap = Utils.unserialize(Utils.readFile(filePath));
        jsonMap.put(key, value);
        try {
            Files.writeString(Paths.get(filePath),Utils.serialize(jsonMap), StandardOpenOption.TRUNCATE_EXISTING);
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    @Override
    public void unset(String key) {
        Map<String, String> jsonMap = Utils.unserialize(Utils.readFile(filePath));
        jsonMap.remove(key);
        try {
            Files.writeString(Paths.get(filePath),Utils.serialize(jsonMap), StandardOpenOption.TRUNCATE_EXISTING);
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> json = Utils.unserialize(Utils.readFile(filePath));
        if (json.containsKey(key)) {
            return json.get(key);
        }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(filePath));
    }
}

// END
