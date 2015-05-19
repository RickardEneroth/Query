import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import se.bott.query.Query;
import se.bott.query.QueryBuilder;
import se.bott.query.QueryPart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestQuery {

    @Test
    public void testQueryBuilder() {

        QueryPart queryPart1 = new QueryPart();
        queryPart1.setInclusion("ANY");
        queryPart1.setType("person");
        List<String> tagList1 = new ArrayList<String>();
        tagList1.add("per-unkel");
        tagList1.add("nils-holgersson");
        queryPart1.setTags(tagList1);

        QueryPart queryPart5 = new QueryPart();
        queryPart5.setInclusion("ANY");
        queryPart5.setType("person");
        List<String> tagList5 = new ArrayList<String>();
        tagList5.add("gurra");
        tagList5.add("lasse");
        queryPart5.setTags(tagList5);

        QueryPart queryPart2 = new QueryPart();
        queryPart2.setInclusion("ALL");
        queryPart2.setType("other");
        List<String> tagList2 = new ArrayList<String>();
        tagList2.add("drama");
        tagList2.add("horror");
        queryPart2.setTags(tagList2);

        QueryPart queryPart4 = new QueryPart();
        queryPart4.setInclusion("ALL");
        queryPart4.setType("person");
        List<String> tagList4 = new ArrayList<String>();
        tagList4.add("olle");
        tagList4.add("nisse");
        queryPart4.setTags(tagList4);

        QueryPart queryPart3 = new QueryPart();
        queryPart3.setInclusion("NONE");
        queryPart3.setType("other");
        List<String> tagList3 = new ArrayList<String>();
        tagList3.add("documentary");
        tagList3.add("thriller");
        queryPart3.setTags(tagList3);

        List<QueryPart> queryPartList = new ArrayList<QueryPart>();
        queryPartList.add(queryPart1);
        queryPartList.add(queryPart2);
        queryPartList.add(queryPart3);
        queryPartList.add(queryPart4);
        queryPartList.add(queryPart5);
        Query query = new Query();
        query.setQuerys(queryPartList);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("query.json"), query);
        } catch (IOException e) {
            e.printStackTrace();
        }

        QueryBuilder queryBuilder = new QueryBuilder(query);

        assertEquals("other:drama AND other:horror AND person:olle AND person:nisse OR person:per-unkel OR person:nils-holgersson OR person:gurra OR person:lasse", queryBuilder.getTags());

        assertEquals("other:documentary,other:thriller", queryBuilder.getExcludedTags());
    }
}
