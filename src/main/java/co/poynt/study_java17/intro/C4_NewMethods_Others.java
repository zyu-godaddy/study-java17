package co.poynt.study_java17.intro;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class C4_NewMethods_Others {

    public static void main(String[] args) throws Exception {

        // Stream.toList()
        {
            var lines = """
                    line 1
                    line 2
                    """
                    .lines()   // Stream<String>
                    .toList(); // List<String>, immutable.
        }

        // copyOf() - immutable snapshot copy
        {
            Collection<String> source = new ArrayList<String>();

            var immutableCopyList = List.copyOf(source);

            var immutableCopySet = Set.copyOf(source);

            var immutableCopyMap = Map.copyOf(new HashMap<>());
        }

        // collection factory methods. immutable
        {
            var list = List.of("a", "b", "c");

            var set = Set.of(1, 2, 3);

            var map = Map.of(
                    "a", 1,
                    "b", 2
            ); // up to 10 entries

            var map2 = Map.ofEntries(
                    entry("a", 1),
                    entry("b", 2),
                    entry("y", 25),
                    entry("z", 26)
            );
            // do not mistakenly call Map.of() here! which will yield a map of entry->entry

            // empty ones
            List.of();
            Set.of();
            Map.of();
        }


        // Files
        {
            Path path1 = Paths.get("/tmp/test1.txt");
            Path path2 = Paths.get("/tmp/test2.txt");

            String content = Files.readString(path1);

            Files.writeString(path2, content);

            long diffAt = Files.mismatch(path1, path2);
        }

        // built-in simple http server and client
        {
            HttpServer.create(new InetSocketAddress(8080), 0);

            HttpClient.newHttpClient();
        }

    }

}
