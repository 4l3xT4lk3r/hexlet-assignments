package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String tagName, Map<String, String> attributes) {
        super(tagName, attributes);
    }

    @Override
    public String toString() {
        StringBuilder tagText = new StringBuilder("<" + getTagName());
        getAttributes().forEach((key, value) -> tagText.append(" ")
                .append(key)
                .append("=\"")
                .append(value)
                .append("\""));

        return tagText+">";
    }
}
// END
