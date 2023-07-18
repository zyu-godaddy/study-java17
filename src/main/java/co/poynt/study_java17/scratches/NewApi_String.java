package co.poynt.study_java17.scratches;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class NewApi_String {

    void java11() {

        boolean b = " ".isBlank();

        var s = """
                line1
                line2""";
        Stream<String> lines = s.lines();

        " abc ".strip(); // unicode aware
        " abc ".stripLeading();
        " abc ".stripTrailing();

        " ".repeat(3);
    }

    void java12() throws Exception{

        // indent() for constructing pretty print
        {
            var head = "abc\n";
            var body = "1\n2\n";
            var lines = head + body.indent(2);
            // abc
            //   1
            //   2
        }

        // transform()
        {
            int x1 = "123".transform(Integer::parseInt);

            // what's the point? why not
            int x2 = Integer.parseInt("123");

            // two directions of function composition
            //     f2 f1 arg
            //     arg f1 f2
            // mixed:
            //     f2( arg.f1() )

            var bd1 = "$ 32,542,340,777,305"
                    .replaceAll("\\D", "") // keep digits only
                    .transform(BigDecimal::new);
            // vs.
            var bd2 = new BigDecimal(
                    "$ 32,542,340,777,305"
                            .replaceAll("\\D", "")
            );


            var c1 = """
                    The quick brown fox
                      jumps over
                    the lazy dog"""  // arg is heavy
                    .toLowerCase()
                    .transform(this::phrasing)
                    .strip()
                    .transform(this::wordCount);
            // vs.
            var c2 = wordCount(
                    phrasing(
                            """
                            The quick brown fox
                              jumps over
                            the lazy dog"""
                            .toLowerCase()
                    ).strip()
            );

        }


    }


    void java13() throws Exception {

        // String.formatted()
        {
            var s1 = String.format("%s=%s", "a", 1);
            // new method
            var s2 = "%s=%s".formatted("a", 1);

            // good for text blocks
            var s3 = """
                    {
                      "%s" : %s
                    }"""
                    .formatted("a", 1);

            // also, compare these two
            if(false) throw new Exception("something wrong; the %s is %s".formatted("a", 1));
            if(false) throw new Exception(String.format("something wrong; the %s is %s", "a", 1));

        }


    }





    String phrasing(String s) {
        return s.replaceAll("lazy", "calorie conserving");
    }
    int wordCount(String s) {
        throw new Error("todo");
    }

}
