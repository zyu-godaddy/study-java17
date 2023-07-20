package co.poynt.study_java17.intro;

import java.util.function.Function;

public class C6_SwitchExpression {

    void test(String s, int x) throws Exception {

        // previously, there's only switch *statements*
        {
            switch(x) {
                case 0:
                    System.out.println("zero");
                    break;
                case 1:
                    System.out.println("one");
                    break;
//                default:
//                    System.out.println("many");
            }
        }

        // now, there's switch *expressions*
        {
            String word = switch(x) {
                case 0 -> "zero";
                case 1 -> "one";
                default -> "many"; // default is required
            };
        }
        // it is an expression that evaluates to a value

        // multiple labels; exceptions
        {
            String word = switch(x) {
                case 1, 2 -> "few";
                case 3, 4 -> "more";
                default -> throw new Error();
            };
        }

        // switch on String
        {
            int i = switch(s) {
                case "zero" -> 0;
                case "one"  -> 1;
                default -> -1;
            };
        }

        // exhaustive listing of enums
        {
            enum Dir{ L, R }

            Function<Dir, String> dirToName = (dir) -> switch(dir) {
                case L -> "Left";
                case R -> "Right";
            };
            // no default clause.
            // NullPointerException if dir==null
        }

        // yield statement
        {
            String word = switch (x) {
                case 0 -> "zero";
                case 1 -> { // complex code block to yield a value
                    if (Math.tan(1)>1)
                        yield "one";
                    throw new AssertionError();
                }
                default -> "many";
            };
        }

        // "enhanced" switch *statement* with '->'
        {
            switch(x) {
                case 0 -> System.out.println("zero");
                case 1 -> System.out.println("one");
            }
            // default not required

            switch(x) {
                case 0 -> {
                    System.out.println("zero");
                    // no break necessary here
                }
                case 1 -> {
                    if (Math.tan(1)>1)
                        break; // can still break early
                    // any statements
                    System.getenv("JAVA_HOME");
                }
            }
        }

    }

}
