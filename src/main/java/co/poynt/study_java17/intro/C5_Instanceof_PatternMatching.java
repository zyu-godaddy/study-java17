package co.poynt.study_java17.intro;

public class C5_Instanceof_PatternMatching {

    void test(Object obj) throws Exception {

        // previously
        {
            if (obj instanceof String) {
                String s = (String)obj;
                // kind of silly. javac already knows obj is a String
                s.toUpperCase();
            }
        }

        // now: Pattern matching in instanceof
        {
            if (obj instanceof String s) { // variable `String s` is introduced
                s.toUpperCase();  // s is a String, non-null
            }

            else if (obj instanceof Integer i) {
                i.intValue();
            }
        }

        // `instanceof` is frowned upon in OOP. but it's not bad with sealed types.

        // scope of an introduced variable
        //   it is visible where it makes sense. follow your instinct.

        {
            if (obj instanceof String s && s.length()>10) {

            }

            if (!(obj instanceof String s) || s.length()<10) {

            }

            // ternary operator ?:
            var v1 = (obj instanceof String s) ? s.toLowerCase() : "NA";
            var v2 = !(obj instanceof String s) ? "NA" : s.toLowerCase();

        }
        // the scope of the variable could be extended
        {
            if (!(obj instanceof String s)) {
                throw new IllegalArgumentException();
            }

            // `s` is introduced from here on

            s.toUpperCase();
        }

    }

}
