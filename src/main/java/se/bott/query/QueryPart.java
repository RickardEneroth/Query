package se.bott.query;

import java.util.List;

public class QueryPart {
    private String inclusion;
    private String type;
    private List<String> tags;

    public String getInclusion() {
        return inclusion;
    }

    public void setInclusion(String inclusion) {
        this.inclusion = inclusion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
