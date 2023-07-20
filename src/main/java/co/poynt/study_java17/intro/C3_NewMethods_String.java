package co.poynt.study_java17.intro;

import java.util.stream.Stream;

public class C3_NewMethods_String {

    public static void main(String[] args) throws Exception {

        {
            boolean isBlank = " \t ".isBlank();

            String four_spaces = " ".repeat(4);
        }

        // lines()
        {
            String s = """
                line 1
                line 2
                """;

            Stream<String> lineStream = s.lines(); // .toList()
        }

        // formatted()
        {
            String f = """
                { "name": %s, "value": %d }
                """;
            String s1 = f.formatted("abc", 123); // method chaining
            String s2 = String.format(f, "abc", 123);

            // compare two styles
            if (false)
                throw new Exception("Error: name:%s, value:%d".formatted("abc", 123));
            if (false)
                throw new Exception(String.format("Error: name:%s, value:%d", "abc", 123));
        }

        // transform
        {
            String s = """
                    i am calm
                    """
                    .transform(String::toUpperCase); // method chaining
        }



    }

}
