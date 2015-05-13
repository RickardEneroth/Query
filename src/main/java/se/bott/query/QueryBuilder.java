package se.bott.query;

public class QueryBuilder {
    private Query query;

    public QueryBuilder(Query query) {
        this.query = query;
    }

    public String getTags() {
        //ALL
        String result = "";
        int loopNo = 0;
        boolean ALL_Present = false;
        for (QueryPart queryPart : query.getQuerys()) {
            if (queryPart.getInclusion().equalsIgnoreCase("ALL")) {
                ALL_Present = true;
                if (loopNo > 0) {
                    result += " AND ";
                }
                boolean first = true;
                loopNo++;
                for (String tag : queryPart.getTags()) {
                    if (!first) {
                        result += " AND ";
                    }
                    result += queryPart.getType() + ":" + tag;
                    first = false;
                }
            }
        }

        //ANY
        int loopNo2 = 0;
        for (QueryPart queryPart : query.getQuerys()) {
            if (queryPart.getInclusion().equalsIgnoreCase("ANY")) {
                if (loopNo2 > 0 || ALL_Present) {
                    result += " OR ";
                }
                boolean first = true;
                loopNo2++;
                for (String tag : queryPart.getTags()) {
                    if (!first) {
                        result += " OR ";
                    }
                    result += queryPart.getType() + ":" + tag;
                    first = false;
                }
            }
        }

        return result;
    }

    public String getExcludedTags() {
        //NONE
        String result = "";
        int loopNo = 0;
        for (QueryPart queryPart : query.getQuerys()) {
            if (queryPart.getInclusion().equalsIgnoreCase("NONE")) {
                if (loopNo > 0) {
                    result += ",";
                }
                boolean first = true;
                loopNo++;
                for (String tag : queryPart.getTags()) {
                    if (!first) {
                        result += ",";
                    }
                    result += queryPart.getType() + ":" + tag;
                    first = false;
                }
            }
        }
        return result;
    }
}
