package co.poynt.study_java17.scratches;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JacksonTypeReference {

    void test() throws Exception {

        var json = """
                ["a", "b", "c"]
                """;

        ObjectMapper om = new ObjectMapper();

        // Java-7
        List<String> a = om.readValue(json, new TypeReference<List<String>>(){});

        // Java shortcomings:
        // no type literal
        //    List<String> a = om.readValue(json, List<String>);
        // generics type erasure. There is no way to achieve
        //    List<String> a = om.readValue(json)
        // TypeReference is a hack we have to live with in Java.

        // Java-9: <> works on anonymous classes, making it simpler:
        List<String> b = om.readValue(json, new TypeReference<>(){});

        // or, define and reuse a type reference. a little faster.
        List<String> c = om.readValue(json, LIST_STR);


    }

    static final TypeReference<List<String>> LIST_STR = new TypeReference<>(){};
}
