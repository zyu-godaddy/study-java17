package co.poynt.study_java17.intro;

// Articles:
//   https://docs.oracle.com/en/java/javase/17/text-blocks/index.html

public class C2_TextBlock {

    public static void main(String[] args) {

        // multi-line text blocks, with """ as opening/closing delimiter
        {
            String s = """
                    line 1
                      line 2
                    line 3
                    """;
            // nothing allowed after opening """, not even // comments
            // "incidental" leading indentations are discarded. IntelliJ shows beginning of lines.
            // 's' ends with '\n'
        }

        // usages:
        // json literals in unit tests
        {
            String json = """
                    {
                      "name": "abc",
                      "value": 123
                    }
                    """;
        }

        // in general, no need to escape, especially quotes and new lines
        // must escape:
        //   """
        //   \
        {
            String s = """
                    text blocks use \""" as delimiters.
                    escapes: \\ \t \n \s
                    json escape + java escape: { "lines": "111\\n222" }
                    """;
        }

        // indentation
        {
            String s = """
                    line 1
                    line 2
                """; // add indentation by moving closing """ to the left

            // or
            String s2 = """
                    line 1
                    line 2
                    """.indent(4);
        }

        // last line
        {
            String s1 = """
                    line 1
                    line 2""";
            // 's' does not end with '\n'

            // or do this to remove the trailing '\n'
            String s2 = """
                    line 1
                    line 2
                    """.stripTrailing();

            // or this: remove last '\n' with '\'
            String s3 = """
                    line 1
                    line 2 \
                    """;

        }

        // line continuation: a trailing '\' removes the following '\n'
        {
            String s = """
                    Line 1: The quick brown fox \
                    jumps over the lazy dog.
                    Line 2: The quick brown fox jumps over the lazy dog.
                    """;
        }


    }

}
