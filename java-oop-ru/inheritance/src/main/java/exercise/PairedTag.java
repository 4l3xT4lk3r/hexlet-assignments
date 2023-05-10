package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag{

    private final String paragraph;
    private final List<Tag> innerTags;

    public PairedTag(String tagName, Map<String, String> attributes, String paragraph, List<Tag> innerTags) {
        super(tagName, attributes);
        this.paragraph = paragraph;
        this.innerTags = innerTags;
    }

    @Override
    public String toString() {
        StringBuilder tagText = new StringBuilder("<" + getTagName());
        getAttributes().forEach((key, value) -> tagText.append(" ")
                .append(key)
                .append("=\"")
                .append(value)
                .append("\""));
        tagText.append(">");
        innerTags.stream().forEach(x->tagText.append(x.toString()));
        tagText.append(paragraph);
        tagText.append("</").append(getTagName()).append(">");
        return tagText.toString();
    }
}
// END
