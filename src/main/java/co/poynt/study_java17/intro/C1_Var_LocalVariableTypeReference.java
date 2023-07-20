package co.poynt.study_java17.intro;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Articles:
//   https://www.baeldung.com/java-10-local-variable-type-inference
//   https://developer.oracle.com/learn/technical-articles/jdk-10-local-variable-type-inference

public class C1_Var_LocalVariableTypeReference {

    public static void main(String[] args) throws Exception {

        // Previously in Java, local variables must be declared with explicit static types
        {
            int i = 123;
            String s = "abc";
            List<String> list = new ArrayList<>();
        }

        // Now, local variables can be declared with `var` in place of the type
        {
            var i = 123;
            var s = "abc";
            var list = new ArrayList<String>();
        }
        // The local variables still have static types that are inferred by javac through context.
        //
        // Notice that for `list`, <String> is no longer given on the left-hand-side,
        // therefore <String> must be provided on the right-hand-side.
        // Also, the inferred type is the most specific one, ArrayList<String>

        // 'var' is for local variables only. Not for instance variables, method parameters, etc.

        // 'var' is not final by default
        {
            final var INF = Long.MAX_VALUE;
        }

        // Controversial - does 'var' improve readability or hinder it?

        // A guideline: https://openjdk.org/projects/amber/guides/lvti-style-guide

        // IntelliJ: F1 to show variable type


        // Sensible usages of var

        {
            class SomeVeryLongClassName{}

            var obj = new SomeVeryLongClassName();
        }

        {
            // map entry iteration
            Map<String, Integer> map = new HashMap<>();

            // everybody hates this
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.printf("%s: %d", entry.getKey(), entry.getValue());
            }

            // with 'var'
            for (var entry : map.entrySet()) {
                var key = entry.getKey();
                var value = entry.getValue();
            }
            // it is no more implicit than another widely accepted style
            map.forEach((key, value)->{
                ;
            });

            // inside other constructs
            try(var reader = new FileReader("/tmp/test.txt")) {
                while( reader.read() != -1 ){}
            }

        }

        // encourages introducing intermediary variables
        {
            var s = "123";
            var i = Integer.parseInt(s);
            var d = new BigDecimal(i);
            var f = d.floatValue();

            // compare with complex nested & chained calls
            f = new BigDecimal(Integer.parseInt("123")).floatValue();
        }


        // Team Convention?
        // - Up to code authors to exercise their own discretion
        // - Code reviewers can raise concerns for unclear 'var's.

        // It's ok to use a lot of 'var's in private development;
        // if it's a concern, replace 'var' with explicit type before code review.
        // IntelliJ: Option-Return

    }

}
