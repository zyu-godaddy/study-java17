package co.poynt.study_java17.scratches;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NewApi_Collection {

    void test() throws Exception {

        // Java 11

        // collection.toArray( i -> E[] )
        {
            var list = List.of("a", "b", "c");
            // old way
            list.toArray(new String[0]);
            list.toArray(new String[list.size()]);

            // new way
            var array = list.toArray( size->new String[size] );
            // or
            var array2 = list.toArray(String[]::new);

            // what's the point?
            {
                // 1. shorter syntax

                // 2. the size of a concurrent collection is volatile
                var concurrentList = new ConcurrentLinkedQueue<String>();
                concurrentList.toArray(String[]::new);
            }

        }

    }

}
