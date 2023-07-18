package co.poynt.study_java17.scratches;

// Java 15
// https://openjdk.org/jeps/358
public class Helpful_NullPointerExceptions {

    public static void main(String[] args) {

        boolean b = Math.tan(1) > 1;
        String s1 =  b ? "" : null;
        String s2 = !b ? "" : null;

        // null pointer - but which is null?

        try {
            s1.concat(s2.toLowerCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Cannot invoke "String.toLowerCase()" because "s2" is null
        }

        try {
            s2.concat(s1.toLowerCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Cannot invoke "String.concat(String)" because "s2" is null
        }

    }

}
