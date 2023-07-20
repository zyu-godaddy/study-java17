package co.poynt.study_java17.scratches;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

public class CollectionLiterals {

    void test() {

        // Java 9

        List.of("a", "b");

        Set.of("a", "b");

        Map.of(
                "a", 1,
                "b", 2
        );
        // ... up to 10 entries

        Map.ofEntries(   // Map.of() here would be wrong....
                entry("a",1),
                entry("b", 2)
        );


    }
}