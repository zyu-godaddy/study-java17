package co.poynt.study_java17.intro;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class C7_Records {

    public static void main(String[] args) {

        record Item(String name, int size){}

        // constructor
        var item = new Item("abc", 123);

        // accessors
        item.name();
        item.size();
        // no setters. records are meant to be immutable.

        // Object methods
        item.hashCode();
        item.equals(item);
        item.toString();

        // record: a named tuple with named fields
        // That is all.
        // Meant for defining simple datatype in a simple way.
        // Classes are usually too heavy; we don't always need full OOP.

        // records are static final.
        // cannot be extended.
        // cannot extend another class.
        // can implement interfaces - typically sealed interfaces

        // keep records simple; don't add stuff to its class body.
        // except static factory methods may be ok
        record NameValue(String name, int value){
            public static NameValue anon(int value){
                return new NameValue("anon", value);
            }
        }

    }


    // It is lightweight.
    // Use it anywhere you find it brings convenience (and you will)

    // functions returning multiple values
    record NameValue(String name, int value){}
    NameValue parseLine(String line) {
        throw new Error("todo");
    }

    // compound map keys/values
    void exampleMap() {

        record Key(String s1, String s2){}
        record Val(int x, boolean flag){}

        var map = Map.of(
            new Key("a","b"), new Val(2, true)
        );
    }

    // define data matrix, test fixture
    void someUnittest() {

        record Param(String id, boolean flag, int val){}
        var params = List.of(
            new Param("id1", true, 123),
            new Param("id2", false, 987)
        );

        params.forEach(param->{
            // run test with the param
        });

    }

    // intermediary, temporary aggregate of data
    void exampleStream() {

        // sort strings, case-insensitive

        record OU(String original, String upper){}

        Stream.of("tick", "TOCK")
                .map(s->new OU(s, s.toUpperCase()))
                .sorted(Comparator.comparing(OU::upper))
                .map(OU::original)
                .toList();
    }

}
