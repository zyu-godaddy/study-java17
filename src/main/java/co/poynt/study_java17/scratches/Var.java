package co.poynt.study_java17.scratches;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Var {

    void test() throws Exception {

        String s1 = "abc";
        var s2 = "abc";

        List<String> list1 = List.of("a", "b", "c");
        var list2 = List.of("a", "b", "c");


        List<String> list3 = new LinkedList<>();

        var list4 = new LinkedList<String>(); // notice the <String>
        list4.add("a");
        var list4_first = list4.getFirst(); // `var list4` is inferred as LinkedList<String>

        {
            // compiles... but the exact type is unclear. don't do it.
            var list5 = new LinkedList<>();
        }

        // 'var' inside other constructs

        Map<String,Integer> map = Map.of("a", 1, "b", 2);
        // everybody hates this
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }
        // better...?
        for (var entry : map.entrySet()) {
            System.out.println(entry);
        }

        try(var reader = new FileReader("/tmp/test.txt")) {
            while( reader.read() != -1 ){}
        }


        // Is 'var' good, bad, or evil?
        //
        // VERY controversial. (in the end laziness always wins)
        //
        // Java is wordy by design. Explicit typing is generally preferred.
        // Until it pollutes code with types everywhere and hides real actions.
        // (Imagine Java without *any* type inference... )
        //
        // Style Guidelines for 'var'
        // https://openjdk.org/projects/amber/guides/lvti-style-guide

        // Replace 'var' with explicit types before code review if it's a concern
        //   IntelliJ: Option+Return on 'var'

        // =======================================================================
        // 'var' can do magics otherwise impossible, see VarForNonDenotableTypes //
        // =======================================================================

    }

}
