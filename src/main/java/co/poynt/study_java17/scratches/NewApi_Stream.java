package co.poynt.study_java17.scratches;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewApi_Stream {

    void java12_TeeingCollector() {

        Collector<Integer, ?, Double> avgCollector = Collectors.teeing(
                Collectors.summingDouble(i -> 1.0*i),
                Collectors.counting(),
                (sum, count) -> (sum / count)
        );

        double avg = Stream.of(1, 2, 3, 4, 5)
                .collect(avgCollector);

    }

    void java16_toList() {

        var list0 = List.of("a", "b", "c");

        var listA = list0.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList()); // could be mutable
        // for immutable list
        Collectors.toUnmodifiableList();

        // now
        var listB = list0.stream()
                .map(String::toUpperCase)
                .toList(); // immutable


    }

}
