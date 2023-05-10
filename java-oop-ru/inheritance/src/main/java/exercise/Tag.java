package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    private final String tagName;
    private final Map<String,String> attributes;

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    public String getTagName() {
        return tagName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

}
// END
